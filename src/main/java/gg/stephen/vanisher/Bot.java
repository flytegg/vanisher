package gg.stephen.vanisher;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.*;

public class Bot {

    private static final boolean RUN_ON_STARTUP = true;
    private static final long DELAY = 31557600000L; // Millis delay. 86400000 = 1 day

    private static JDA jda;

    public Bot() throws InterruptedException {
        jda = JDABuilder.createDefault(System.getenv("BOT_TOKEN"))
                            .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                            .addEventListeners(new Command())
                            .build()
                            .awaitReady();

        jda.getGuildById("397526357191557121").updateCommands().addCommands(
                Commands.slash("vanish", "Vanish.")
                        .setGuildOnly(true)
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR))).complete();

        new Timer().schedule(new Task(), RUN_ON_STARTUP ? 0 : DELAY , DELAY);
    }

    public static JDA getJDA() { return jda; }

}