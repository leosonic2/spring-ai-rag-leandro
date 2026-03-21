package guru.springframework.springairag.service;

import guru.springframework.springairag.model.Answer;
import guru.springframework.springairag.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OpenAiServiceImpl implements OpenAiService{

    private final ChatModel chatModel;

    @Override
    public Answer getAnswer(Question question) {
        System.out.println("OpenAi called");
        System.out.println("Question: " + question.question());
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();

        ChatResponse response = chatModel.call(prompt);
        String aiResponse = response.getResult().getOutput().getText();
        System.out.println("OpenAi Response: " + aiResponse);
        System.out.println("OpenAi Finished");

        return new Answer(aiResponse);
    }
}
