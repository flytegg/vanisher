package gg.stephen.vanisher;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;

public class Task extends TimerTask {

    private List<String> USERS = Arrays.asList("676926873669992459","181837365621424128","257818210697478145","867435070696456222","196505313099120640","174596615275806720");
    private List<String> CHANNELS = Arrays.asList("397526357757657089","397529992504016896","1054929646002638868","978339583148298240","695692238608531456","398279082996924416","888162787229048842");
    private int SEARCH_DELAY = 259_200_000; // Millis representing where to start searching from. Eg 259,200,000 = 3 days = start search from 3 days prior to task
    private long SEARCH_LENGTH = 604_800_000; // Millis representing how long to search for from the SEARCH_DELAY date. Eg 604,800,000 = 7 days = will stop searching once reached 7 days from SEARCH_DELAY

    @Override
    public void run() {
        log("Beginning search...");

        long searchStart = System.currentTimeMillis() - SEARCH_DELAY;
        long searchEnd = searchStart - SEARCH_LENGTH;

        List<Message> messageIds = new ArrayList<>();

        for (String channelId : CHANNELS) {
            TextChannel channel = Bot.getJDA().getTextChannelById(channelId);
            String latest = channel.getLatestMessageId();

            log("Search started in " + channel.getAsMention());

            boolean more = true;

            while (more) {
                try {
                    List<Message> history = channel.getHistoryBefore(latest, 100).complete().getRetrievedHistory();
                    if (history.size() != 100 || history.get(0).getTimeCreated().toInstant().toEpochMilli() < searchEnd) {
                        more = false;
                    } else {
                        latest = history.get(99).getId();
                    }

                    if (history.get(99).getTimeCreated().toInstant().toEpochMilli() > searchStart) {
                        log("Skipping history batch of " + channel.getAsMention() + " as prior to start date");
                        continue;
                    }

                    for (Message message : history) {
                        if (USERS.contains(message.getAuthor().getId())) {
                            messageIds.add(message);
                            log("Added message '" + message.getId() + "' to list from " + message.getAuthor().getName() + " in " + message.getChannel().getAsMention() + ". Total " + messageIds.size());
                        }
                    }

                    if (!more) {
                        log("Search in " + channel.getAsMention() + " ended as after end date");
                    }
                } catch (Exception x) {
                    log("Caught error while checking history - see console. Continuing");
                    x.printStackTrace();
                }
            }
        }

        log("Search ended. Identified " + messageIds.size() + " messages from " + USERS.size() + " users in " + CHANNELS.size() + " channels");
        log("Beginning deletion of " + messageIds.size() + " messages...");

        int amount = 0;
        for (Message message : messageIds) {
            try {
                message.delete().complete();
                amount++;
                log("Queued " + amount + " messages for deletion");
            } catch (Exception x) {
                // Rate limits. JDA will retry it and it will eventually go through
            }
        }

        log("Vanish complete");
    }

    private TextChannel logChannel = Bot.getJDA().getTextChannelById("1160733754193621153");

    private void log(String message) {
        logChannel.sendMessage(message).queue();
    }

}
