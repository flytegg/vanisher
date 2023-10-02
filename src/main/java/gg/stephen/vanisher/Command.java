package gg.stephen.vanisher;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class Command implements EventListener {

    @Override
    public void onEvent(GenericEvent genericEvent) {
        if (!(genericEvent instanceof SlashCommandInteractionEvent)) return;

        SlashCommandInteractionEvent slashEvent = (SlashCommandInteractionEvent) genericEvent;

        if (!slashEvent.getName().equals("vanish")) return;

        slashEvent.reply("Vanish has started...").setEphemeral(true).complete();

        new Task().run();
    }

}
