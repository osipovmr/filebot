package TelegramBot;


import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Post {
    public static String http(String query) throws IOException, InterruptedException, ExecutionException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:4567/postAirport"))
                .POST(HttpRequest.BodyPublishers.ofString(query))
                .build();
        Main.logger.info(query);
        CompletableFuture<HttpResponse<String>> futureResponse = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> response = futureResponse.get();
        return response.body();

    }
}


