🛠️ Overview
This project is a Chat API built using Spring Boot that integrates with the Ollama AI language model for natural language processing. It supports:

RESTful API for sending input and receiving responses from the Ollama model.
WebSocket Service for real-time streaming of responses from the model for an interactive chat experience.
🚀 Technologies Used:
Spring Boot: Backend framework for building the API and WebSocket.
Ollama: AI language model used for generating responses.
WebSocket: For enabling real-time communication between client and server.
🔧 Prerequisites
Before you run the application, you must have Ollama running in the background. Ollama is used to process the input and generate the output for the chatbot. Make sure you:

Install Ollama on your machine.
Start the Ollama service using the following command:
ollama serve
🏗️ Project Structure
OllamaService: Provides methods to interact with Ollama.

runOllamaModel(modelName, input): Runs the Ollama model and returns the full output.
streamResponse(modelName, input, Consumer<String> onResponse): Streams the response word-by-word, useful for real-time communication.
ChatWebSocketHandler: Handles WebSocket communication.

After receiving a message from the client, it uses the OllamaService to stream responses back to the client in real-time.

📡 API and WebSocket Endpoints

🔗 WebSocket Endpoint

URI: /chat
Description: Establishes a WebSocket connection for real-time communication with the chatbot.
Request: Send a text message (user input) via WebSocket.
Response: The server streams the model's output word by word in real-time.

🛠️ Sample Interaction:
Connect to /chat via WebSocket.
Send a message like "Hello, how are you?".

The server responds in real-time, streaming each word of the model’s response, such as:

css
I am doing well, thank you!

⚙️ How to Run
Clone the repository:

git clone https://github.com/Nguyen-Van-Truong/chatapiollama

cd chat-api-ollama

Install dependencies (Ensure you have Java and Maven installed):

mvn clean install

Run the application:

mvn spring-boot:run

Start Ollama in the background:

ollama serve

Start a WebSocket session: Use a WebSocket client (e.g., Postman or a browser extension) to connect to:

ws://localhost:8080/chat

Send a message: Interact with the chatbot by sending a text message, and receive real-time responses.
