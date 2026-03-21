# Spring AI RAG — Retrieval-Augmented Generation with Spring AI

> ⚠️ **This project is for learning purposes only.**

---

## About

This project demonstrates how to build a **Retrieval-Augmented Generation (RAG)** application using **Spring AI** and **OpenAI**. It was developed as a hands-on exercise while following the course:

🎓 [Spring AI: Beginner to Guru — Udemy](https://www.udemy.com/course/spring-ai-beginner-to-guru/)

All credits for the course content and architecture guidance go to the author:

👤 [John Thompson — LinkedIn](https://www.linkedin.com/in/springguru/)

---

## Tech Stack

| Technology        | Version     |
|-------------------|-------------|
| Java              | 21          |
| Spring Boot       | 3.3.6       |
| Spring AI         | 1.0.0-M5    |
| OpenAI API        | —           |
| Lombok            | —           |
| Maven             | Wrapper     |

---

## What is RAG?

**Retrieval-Augmented Generation (RAG)** is an AI pattern that enhances Large Language Model (LLM) responses by first retrieving relevant documents from a knowledge base (vector store) and injecting them as context into the prompt. This allows the model to answer questions based on your own data, not just its training knowledge.

---

## Project Structure

```
src/
└── main/
    ├── java/guru/springframework/springairag/
    │   ├── SpringAiRagApplication.java          # Application entry point
    │   ├── config/
    │   │   ├── VectorStoreConfig.java            # SimpleVectorStore bean — loads or builds the vector store
    │   │   └── VectorStoreProperties.java        # External config properties (sfg.aiapp)
    │   ├── model/
    │   │   ├── Question.java                     # Record — incoming question payload
    │   │   └── Answer.java                       # Record — outgoing answer payload
    │   ├── service/
    │   │   ├── OpenAiService.java                # Service interface
    │   │   └── OpenAiServiceImpl.java            # Calls OpenAI via ChatModel and returns the answer
    │   └── resource/
    │       └── QuestionController.java           # REST controller — exposes POST /ask
    └── resources/
        ├── application.properties                # App settings
        └── movies500.csv                         # Movie dataset used to populate the vector store
```

---

## API Usage

### `POST /ask`

Sends a question to OpenAI and returns the AI-generated answer.

**Request body:**
```json
{
  "question": "What is the best sci-fi movie of the 90s?"
}
```

**Response body:**
```json
{
  "answer": "Many consider The Matrix (1999) to be the best sci-fi film of the 90s..."
}
```

**Example with curl:**
```bash
curl -X POST http://localhost:8080/ask \
  -H "Content-Type: application/json" \
  -d '{"question": "What is the best sci-fi movie of the 90s?"}'
```

---

## Architecture Overview

```
Client
  │
  ▼
QuestionController (POST /ask)
  │
  ▼
OpenAiService → OpenAiServiceImpl
  │  Builds a PromptTemplate from the question
  │
  ▼
ChatModel (Spring AI / OpenAI)
  │  Returns ChatResponse
  ▼
Answer (returned as JSON)
```

The `SimpleVectorStore` is populated on startup by reading `movies500.csv`,
splitting its content via `TokenTextSplitter`, generating embeddings through the
`EmbeddingModel`, and persisting the result to a local JSON file. On subsequent
startups the vector store is loaded directly from that file, skipping the
embedding step.

---

## Configuration

Set the following environment variable before running the application:

```bash
OPENAI_API_KEY=your-openai-api-key-here
```

You can also customize the vector store file path and the documents to load in `application.properties`:

```properties
# Resolves to the OS temp directory (cross-platform)
sfg.aiapp.vectorStorePath=${java.io.tmpdir}vectorstore.json

# Path to the document(s) to be embedded into the vector store
sfg.aiapp.documentsToLoad[0]=classpath:/movies500.csv
```

---

## Running the Application

```bash
./mvnw spring-boot:run
```

Or on Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

---

## Data Source

The file `movies500.csv` used to populate the vector store was sourced from **[Kaggle](https://www.kaggle.com/)**, the world's largest platform for data science and machine learning datasets.

> All data credits go to the original dataset authors on Kaggle.

---

## Credits

This project is based on the course **[Spring AI: Beginner to Guru](https://www.udemy.com/course/spring-ai-beginner-to-guru/)** by **John Thompson**.  
Follow the author on LinkedIn: [linkedin.com/in/springguru](https://www.linkedin.com/in/springguru/)

---

## License

This repository is intended for **educational purposes only** and is not meant for production use.

