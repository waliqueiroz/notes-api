package integration;

import br.com.notes.NotesApplication;
import br.com.notes.infrastructure.converter.gson.GsonConverter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import spark.Spark;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseIntegrationTest {
    @BeforeAll
    public static void beforeAll() {
        NotesApplication.main(null);
        Spark.awaitInitialization();
    }

    @AfterAll
    public static void afterAll() {
        Spark.stop();
    }

    protected TestResponse request(String method, String path) {
        return request(method, path, null);
    }

    protected TestResponse request(String method, String path, String body) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080" + path));

            if (body == null) {
                requestBuilder.method(method, HttpRequest.BodyPublishers.noBody());
            } else {
                requestBuilder.method(method, HttpRequest.BodyPublishers.ofString(body));
            }

            HttpRequest request = requestBuilder.build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return new TestResponse(response.statusCode(), response.body());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Sending request failed: " + e.getMessage());
            return null;
        }
    }

    public static String readFileFromResources(String filename) throws URISyntaxException, IOException {
        URL resource = BaseIntegrationTest.class.getClassLoader().getResource(filename);
        byte[] bytes = Files.readAllBytes(Paths.get(resource.toURI()));
        return new String(bytes);
    }

    protected static class TestResponse {
        public final String body;
        public final int status;

        public TestResponse(int status, String body) {
            this.status = status;
            this.body = body;
        }

        public Map<String, String> json() {
            return GsonConverter.fromJson(body, HashMap.class);
        }
    }
}
