package guru.springframework.springairag.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.List;

@Configuration
@Slf4j
public class VectorStoreConfig {

    @Bean
    public SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel, VectorStoreProperties vectorStoreProperties) {
        SimpleVectorStore simpleVectorStore = SimpleVectorStore.builder(embeddingModel).build();
        File vectorStoreFile = new File(vectorStoreProperties.getVectorStorePath());

        if (!vectorStoreFile.exists()) {
            simpleVectorStore.load(vectorStoreFile);
        } else {
            log.debug("Loading documents from " + vectorStoreFile.getAbsolutePath());
            vectorStoreProperties.getDocumentsToLoad().forEach(document -> {
                log.debug("Loading the document: " + document);
                TikaDocumentReader documentReader = new TikaDocumentReader(document);
                List<Document> docs = documentReader.get();
                TextSplitter splitter = new TokenTextSplitter();
                List<Document> splitDocs = splitter.split(docs);
                simpleVectorStore.add(splitDocs);

            });
            simpleVectorStore.save(vectorStoreFile);
        }
        return simpleVectorStore;
    }
}
