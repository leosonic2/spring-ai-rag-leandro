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
    │   ├── SpringAiRagApplication.java       # Application entry point
    │   └── config/
    │       ├── VectorStoreConfig.java         # SimpleVectorStore bean configuration
    │       └── VectorStoreProperties.java     # External config properties (sfg.aiapp)
    └── resources/
        ├── application.properties             # App settings
        └── data/
            └── movies500.csv                  # Movie dataset used to populate the vector store
```

---

## Configuration

Set the following environment variable before running the application:

```bash
OPENAI_API_KEY=your-openai-api-key-here
```

You can also customize the vector store file path in `application.properties`:

```properties
sfg.aiapp.vectorStorePath=/tmp/vectorstore.json
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

