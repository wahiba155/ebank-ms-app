package org.moussaoui.ebankbot.discord;

import com.zgamelogic.discord.annotations.DiscordController;
import com.zgamelogic.discord.annotations.DiscordMapping;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.moussaoui.ebankbot.Agents.EbankAiAgent;
import org.springframework.ai.chat.prompt.Prompt;

@DiscordController
public class DiscordBot {
    private EbankAiAgent ebankAgent;

    public DiscordBot(EbankAiAgent ebankAgent) {
        this.ebankAgent = ebankAgent;
    }
    @DiscordMapping
    private void perform(MessageReceivedEvent event) {
        if  (event.getAuthor().isBot()) return;
        String query = event.getMessage().getContentRaw();
        String response = ebankAgent.chat(query);
        event.getChannel().sendMessage(response).queue();
    }
}
