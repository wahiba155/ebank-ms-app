
package org.moussaoui.ebankbot.controllers;

import org.moussaoui.ebankbot.Agents.EbankAiAgent;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EbankChatbotController {

    private final EbankAiAgent ebankAgent;

    public EbankChatbotController(EbankAiAgent ebankAgent) {
        this.ebankAgent = ebankAgent;
    }

    @GetMapping(value = "/chat", produces = MediaType.TEXT_PLAIN_VALUE)
    public String chat(
            @RequestParam(
                    name = "query",
                    defaultValue = "Bonjour"
            ) String query) {

        return ebankAgent.chat(new Prompt(query));
    }
}
