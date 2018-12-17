/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import com.google.inject.Inject;
import de.astride.darkbedrock.apis.annotatedcommands.api.*;

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 20.11.2018 08:26.
 * Current Version: 1.0 (20.11.2018 - 26.11.2018)
 */
@Command(aliases = {"JGM"})
@Permission("groupID.commands.<CommandName>")
public final class JavaGameMode {
    @Name
    private final String name;
    @Label
    private final String label;
    @Sender
    private final Object sender;

    @Inject
    public JavaGameMode(String name, String label, Object sender) {
        this.name = name;
        this.label = label;
        this.sender = sender;
    }

    @Implementation(args = {
            @Arg(values = {
                    @Value(value = "survival", aliases = {"0"}),
                    @Value(value = "creative", aliases = {"1"}),
                    @Value(value = "adventure", aliases = {"2"}),
                    @Value(value = "spectator", aliases = {"3"})
            })
    })
    @Permission("<GlobalPermission>.<Arg1>")
    @SendUsageIfThrownException
    private void execute(String arg) {
        //CODE bla bla bla...
    }

    @Implementation(args = {
            @Arg(values = {
                    @Value(value = "survival", aliases = {"0"}),
                    @Value(value = "creative", aliases = {"1"}),
                    @Value(value = "adventure", aliases = {"2"}),
                    @Value(value = "spectator", aliases = {"3"})
            }),
            @Arg(values = {@Value("<Player>")}, isInput = true)
    })
    @Permission("<GlobalPermission>.<Arg1>.<Arg2>")
    private void execute(String arg1, String arg2) {
        //CODE bla bla bla...
    }
}