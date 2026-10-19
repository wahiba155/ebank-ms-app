
package org.moussaoui.ebankbot.Agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class EbankAiAgent {

    private final ChatClient chatClient;

    private static final String CONVERSATION_ID =
            "ebankbot-demo-qwen-v1";

    public EbankAiAgent(
            ChatClient.Builder chatClientBuilder,
            ChatMemory chatMemory, ToolCallbackProvider tools) {

        this.chatClient = chatClientBuilder
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build()
                )
                .defaultAdvisors(
                        MessageChatMemoryAdvisor
                                .builder(chatMemory)
                                .build()
                ).defaultTools(tools.getToolCallbacks())
                .build();
    }

    public String chat(String query) {

        return chatClient.prompt()
                .user(query)
                .advisors(a -> a.param(
                        ChatMemory.CONVERSATION_ID,
                        CONVERSATION_ID
                ))
                .call()
                .content();
    }
}
