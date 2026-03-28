package guru.springframework.springairag.service;

import guru.springframework.springairag.model.Answer;
import guru.springframework.springairag.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class OpenAiServiceImpl implements OpenAiService{

    private final ChatModel chatModel;
    private final SimpleVectorStore vectorStore;

    @Value("classpath:templates/rag-prompt-template-meta.st")
    private Resource ragPromptTemplate;

    @Override
    public Answer getAnswer(Question question) {
        List<Document> documents = vectorStore.similaritySearch(SearchRequest
                .builder()
                .query(question.question()).topK(4)
                .build());

        assert documents != null;
        List<String> contentList = documents.stream().map(Document::getText).toList();

        System.out.println("OpenAi called");
        System.out.println("Question: " + question.question());
        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
        Prompt prompt = promptTemplate.create(Map.of("input",question.question(),
                "documents",String.join("\n",contentList)));

        contentList.forEach(System.out::println);
        ChatResponse response = chatModel.call(prompt);
        String aiResponse = response.getResult().getOutput().getText();
        System.out.println("OpenAi Response: " + aiResponse);
        System.out.println("OpenAi Finished");

        return new Answer(aiResponse);
    }
}
