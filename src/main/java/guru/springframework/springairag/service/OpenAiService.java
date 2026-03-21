package guru.springframework.springairag.service;


import guru.springframework.springairag.model.Answer;
import guru.springframework.springairag.model.Question;

public interface OpenAiService {
    public Answer getAnswer(Question question);

}
