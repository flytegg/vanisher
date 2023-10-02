package gg.stephen;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.concrete.ForumChannel;
import net.dv8tion.jda.api.entities.channel.concrete.NewsChannel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.ThreadChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Timer;

public class Bot {

    private static JDA jda;

    public Bot() throws InterruptedException {
        jda = JDABuilder.createDefault("TOKEN")
                            .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                            .addEventListeners(new Command())
                            .build()
                            .awaitReady();

        jda.getGuildById("397526357191557121").updateCommands().addCommands(
                Commands.slash("vanish", "Vanish.")
                        .setGuildOnly(true)
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR))).complete();

        new Timer().schedule(new Task(), 0, 86400000L);
    }

    public static JDA getJDA() { return jda; }

}