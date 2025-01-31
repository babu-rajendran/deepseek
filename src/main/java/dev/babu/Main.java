package dev.babu;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {

    private static final String DEEPSEEK_API_KEY = System.getenv("DEEPSEEK_API_KEY");
    private static final String DEEPSEEK_API_BASE_URL = "https://api.deepseek.com/v1";

    public static void main(String[] args) throws IOException, InterruptedException {
        var body = """
                {
                    "model": "deepseek-reasoner",
                    "messages": [
                        {
                            "role": "user",
                            "content": "How many r's are in the word Strawberry"
                        }
                    ]
                }
                """;

        var request = HttpRequest.newBuilder()
                .uri(URI.create(DEEPSEEK_API_BASE_URL + "/chat/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + DEEPSEEK_API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        var client = HttpClient.newHttpClient();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        var responseBody = response.body();

        System.out.println(responseBody);

    }
}