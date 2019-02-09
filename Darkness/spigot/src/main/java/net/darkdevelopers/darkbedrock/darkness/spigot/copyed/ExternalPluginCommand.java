package net.darkdevelopers.darkbedrock.darkness.spigot.copyed;

import org.apache.commons.lang.Validate;
import org.bukkit.command.*;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class ExternalPluginCommand extends Command implements PluginIdentifiableCommand
{
	private final Plugin owningPlugin;
	private CommandExecutor executor;
	private TabCompleter completer;
	
	public ExternalPluginCommand(String name, Plugin owner)
	{
		super(name);
		this.executor = owner;
		this.owningPlugin = owner;
		this.usageMessage = "";
	}
	
	/**
	 * Executes the command, returning its success
	 *
	 * @param sender       Source object which is executing this command
	 * @param commandLabel The alias of the command used
	 * @param args         All arguments passed to the command, split via ' '
	 *
	 * @return true if the command was successful, otherwise false
	 */
	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args)
	{
		boolean success;
		if (!this.owningPlugin.isEnabled())
		{
			return false;
		}
		if (!testPermission(sender))
		{
			return true;
		}
		try
		{
			success = this.executor.onCommand(sender, this, commandLabel, args);
		} catch (Throwable ex)
		{
			throw new CommandException("Unhandled exception executing command '" + commandLabel + "' in plugin " + this.owningPlugin.getDescription().getFullName(), ex);
		}
		if (!success && this.usageMessage.length() > 0)
		{
			for (String line : this.usageMessage.replace("<command>", commandLabel).split("\n"))
			{
				sender.sendMessage(line);
			}
		}
		return success;
	}
	
	/**
	 * Gets the {@link CommandExecutor} associated with this command
	 *
	 * @return CommandExecutor object linked to this command
	 */
	@SuppressWarnings("unused")
	public CommandExecutor getExecutor()
	{
		return this.executor;
	}
	
	/**
	 * Sets the {@link CommandExecutor} to run when parsing this command
	 *
	 * @param executor New executor to run
	 */
	public void setExecutor(CommandExecutor executor)
	{
		this.executor = executor == null ? this.owningPlugin : executor;
	}
	
	/**
	 * Gets the {@link TabCompleter} associated with this command.
	 *
	 * @return TabCompleter object linked to this command
	 */
	@SuppressWarnings("unused")
	public TabCompleter getTabCompleter()
	{
		return this.completer;
	}
	
	/**
	 * Sets the {@link TabCompleter} to run when tab-completing this command.
	 * <p>
	 * If no TabCompleter is specified, and the command's executor implements
	 * TabCompleter, then the executor will be used for tab completion.
	 *
	 * @param completer New tab completer
	 */
	public void setTabCompleter(TabCompleter completer)
	{
		this.completer = completer;
	}
	
	/**
	 * Gets the owner of this ExternalPluginCommand
	 *
	 * @return Plugin that owns this command
	 */
	public Plugin getPlugin()
	{
		return this.owningPlugin;
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to the tab completer if present.
	 * <p>
	 * If it is not present or returns null, will delegate to the current
	 * command executor if it implements {@link TabCompleter}. If a non-null
	 * list has not been found, will default to standard player gameName
	 * completion in {@link
	 * Command#tabComplete(CommandSender, String, String[])}.
	 * <p>
	 * This method does not consider permissions.
	 *
	 * @throws CommandException         if the completer or executor throw an
	 *                                  exception during the process of tab-completing.
	 * @throws IllegalArgumentException if sender, alias, or args is null
	 */
	@Override
	public java.util.List<String> tabComplete(CommandSender sender, String alias, String[] args) throws CommandException, IllegalArgumentException
	{
		Validate.notNull(sender, "Sender cannot be null");
		Validate.notNull(args, "Arguments cannot be null");
		Validate.notNull(alias, "Alias cannot be null");
		List<String> completions = null;
		try
		{
			if (this.completer != null)
			{
				completions = this.completer.onTabComplete(sender, this, alias, args);
			}
			if (completions == null && this.executor instanceof TabCompleter)
			{
				completions = ((TabCompleter) this.executor).onTabComplete(sender, this, alias, args);
			}
		} catch (Throwable ex)
		{
			StringBuilder message = new StringBuilder();
			message.append("Unhandled exception during tab completion for command '/").append(alias).append(' ');
			for (String arg : args)
			{
				message.append(arg).append(' ');
			}
			message.deleteCharAt(message.length() - 1).append("' in plugin ").append(this.owningPlugin.getDescription().getFullName());
			throw new CommandException(message.toString(), ex);
		}
		if (completions == null)
		{
			return super.tabComplete(sender, alias, args);
		}
		return completions;
	}
	
	@Override
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder(super.toString());
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		stringBuilder.append(", ").append(this.owningPlugin.getDescription().getFullName()).append(')');
		return stringBuilder.toString();
	}
}
