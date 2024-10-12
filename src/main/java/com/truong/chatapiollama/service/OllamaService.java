package com.truong.chatapiollama.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

@Service
public class OllamaService {
    public String runOllamaModel(String modelName, String input) {
        try {
            // Command to run Ollama with the specific model
            ProcessBuilder processBuilder = new ProcessBuilder("ollama", "run", modelName);
            Process process = processBuilder.start();

            // Write input to Ollama
            process.getOutputStream().write((input + "\n").getBytes());
            process.getOutputStream().flush();
            process.getOutputStream().close();

            // Read output from Ollama
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            reader.close();

            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error running Ollama model.";
        }
    }

    public void streamResponse(String modelName, String input, Consumer<String> onResponse) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("ollama", "run", modelName);
            Process process = processBuilder.start();

            // Send input to Ollama
            process.getOutputStream().write((input + "\n").getBytes());
            process.getOutputStream().flush();
            process.getOutputStream().close();

            // Read output from Ollama and send response word by word
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    onResponse.accept(word + " ");
                    Thread.sleep(100);
                }
                onResponse.accept("\n"); // End of line
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            onResponse.accept("Error running Ollama model.");
        }
    }

}
