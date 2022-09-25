package integration.br.com.notes.infrastructure.http.spark.controllers;

import integration.BaseIntegrationTest;

import static org.junit.jupiter.api.Assertions.*;
import static java.net.HttpURLConnection.HTTP_OK;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class NoteControllerTest extends BaseIntegrationTest {

    @Test
    public void testCreate() throws URISyntaxException, IOException {
        String body = readFileFromResources("create_note.json");
        TestResponse response = request("POST", "/api/notes", body);

        Map<String, String> responseBody = response.json();

        assertEquals(HTTP_OK, response.status);
        assertEquals("Nota teste", responseBody.get("title"));
        assertEquals("Uma nota de teste", responseBody.get("content"));
        assertNotNull(responseBody.get("id"));
    }

}
