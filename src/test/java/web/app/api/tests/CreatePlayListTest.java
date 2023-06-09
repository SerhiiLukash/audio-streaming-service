package web.app.api.tests;

import org.junit.jupiter.api.Test;
import web.app.api.objects.Playlist;
import web.app.api.objects.User;
import web.app.api.utils.UserService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreatePlayListTest {


    @Test
    public void testCreatePlaylist() {
        String endpoint = "http://localhost:8080/api/playlists";
        Playlist body = new Playlist("PlaylistName", "PlaylistDescription", true);
        body.setUserId(31);
        given().
                header("Content-Type", "application/json").
                body(body).
                when().
                post(endpoint).
                then().
                assertThat().
                statusCode(200).
                body("PlaylistName", equalTo("PlaylistName"));
    }

}
