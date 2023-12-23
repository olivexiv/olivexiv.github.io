package com.example.demo;

import org.chatterbot.api.ChatBot;
import org.chatterbot.api.ChatterBotFactory;
import org.chatterbot.api.ChatterBotType;
import org.chatterbot.api.ChatSessionType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chatbot")
public class ChatbotController {

    private final ChatBot chatbot;

    public ChatbotController() {
        // Initialisez le chatbot lors de la création du contrôleur
        chatbot = new ChatterBotFactory().create(ChatterBotType.PANDORABOTS, "your-pandorabot-id");
    }

    @PostMapping("/ask")
    public String askQuestion(@RequestBody String question) {
        // Traitez la question et obtenez la réponse du chatbot
        String response = processQuestion(question);

        return response;
    }

    private String processQuestion(String question) {
        try {
            // Créez une session de chat avec le chatbot
            String sessionId = "unique-session-id"; // ID de session réel
            chatbot.createSession(sessionId, ChatSessionType.MULTI);
            
            // Obtenez une réponse du chatbot en fonction de la question
            String response = chatbot.sendMessage(sessionId, question);

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors du traitement de la question.";
        }
    }
}
