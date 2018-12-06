/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package de.astride.darkbedrock.apis.modules.common.util

import com.google.common.collect.ImmutableSet
import com.google.common.collect.Maps
import com.google.common.graph.Graph
import com.google.common.graph.GraphBuilder
import de.astride.darkbedrock.apis.modules.api.interfaces.ModuleDescription
import java.util.*

object ModuleDependencyUtils {

	/**
	 * Attempts to topographically sort all plugins for the proxy to load by dependencies using
	 * Kahn's algorithm.
	 *
	 * @param candidates the plugins to sort
	 *
	 * @return the sorted list of plugins
	 *
	 * @throws IllegalStateException if there is a circular loop in the dependency graph
	 */
	fun sortCandidates(candidates: List<ModuleDescription>): List<ModuleDescription> {
		// Create our graph, we're going to be using this for Kahn's algorithm.
		val graph = GraphBuilder.directed().allowsSelfLoops(false).build<ModuleDescription>()
        val candidateMap = Maps.uniqueIndex(candidates) { it?.id }
		// Add edges
		for (description in candidates) {
			graph.addNode(description)
			for (dependency in description.dependencies) {
				val `in` = candidateMap[dependency.id]
                if (`in` != null) graph.putEdge(description, `in`)
			}
		}
		// Find nodes that have no edges
		val noEdges = getNoDependencyCandidates(graph)
		// Actually run Kahn's algorithm
		val sorted = ArrayList<ModuleDescription>()
		while (!noEdges.isEmpty()) {
			val candidate = noEdges.remove()
			sorted.add(candidate)
			for (node in ImmutableSet.copyOf(graph.adjacentNodes(candidate))) {
				graph.removeEdge(node, candidate)
				if (graph.adjacentNodes(node).isEmpty()) {
					noEdges.add(node)
				}
			}
		}
		if (!graph.edges().isEmpty()) {
			throw IllegalStateException(
					"Plugin circular dependency found: " + createLoopInformation(graph))
		}
		return sorted
	}

	private fun getNoDependencyCandidates(graph: Graph<ModuleDescription>): Queue<ModuleDescription> {
		val found = ArrayDeque<ModuleDescription>()
        for (node in graph.nodes()) if (graph.outDegree(node) == 0) found.add(node)
		return found
	}

	private fun createLoopInformation(graph: Graph<ModuleDescription>): String {
		val repr = StringBuilder("{")
        for (edge in graph.edges()) repr
            .append(edge.target().id).append(": [")
            .append(dependencyLoopInfo(graph, edge.target(), HashSet()))
            .append("], ")
		repr.setLength(repr.length - 2)
		repr.append("}")
		return repr.toString()
	}

	private fun dependencyLoopInfo(graph: Graph<ModuleDescription>,
								   dependency: ModuleDescription, seen: MutableSet<ModuleDescription>): String {
		val repr = StringBuilder()
		for (pd in graph.adjacentNodes(dependency)) {
			if (seen.add(pd)) {
                repr.append(pd.id).append(": [").append(dependencyLoopInfo(graph, dependency, seen)).append("], ")
			} else {
				repr.append(pd.id).append(", ")
			}
		}
		return if (repr.isNotEmpty()) {
			repr.setLength(repr.length - 2)
			repr.toString()
		} else {
			"<no depends>"
		}
	}
}