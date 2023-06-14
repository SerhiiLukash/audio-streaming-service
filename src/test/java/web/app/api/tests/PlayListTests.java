package web.app.api.tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import web.app.api.objects.Playlist;
import web.app.api.utils.PlaylistCreation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PlayListTests {
    int[] TrackIds = {1, 4, 8};

    static int playlistId = new PlaylistCreation().CreatePlaylist();

    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080/api/playlists";
    }

    @Test
    public void testCreatePlaylist() {


        given().basePath(String.valueOf(playlistId))
                .when()
                .get().then().assertThat().
                statusCode(200).
                body("name", equalTo("PlaylistName"));

    }

    @Test
    public void testUpdatePlaylist() {

        Playlist body = new Playlist("PlaylistNewName", "PlaylistDescription", true);
        body.setUserId(31);
        given().header("Content-Type", "application/json").
                basePath(String.valueOf(playlistId)).
                body(body).
                when().
                put().
                then().
                assertThat().
                statusCode(200).
                body("name", equalTo("PlaylistNewName"));

    }

    @Test
    public void addTrackToPlaylist() {

        int counter = 0;
        for (int trackId : TrackIds) {

            given().header("Content-Type", "application/json").
                    basePath(String.valueOf(playlistId) + "/tracks/add").
                    body("{\"trackId\":" + trackId + "}").
                    when().
                    post().
                    then().
                    assertThat().
                    statusCode(200).
                    body("tracks[" + counter + "].id", equalTo(trackId));
            counter++;
        }
    }

    @Test
    public void testTrackIsAdded() {

        int counter = 0;
        for (int trackId : TrackIds) {
            given().
                    basePath(String.valueOf(playlistId)).
                    when().
                    get().then().assertThat().
                    statusCode(200).
                    body("tracks[" + counter + "].id", equalTo(trackId));
            counter++;
        }
        System.out.println(playlistId);
    }

    @Test(dependsOnMethods = "addTrackToPlaylist")

    public void testDeletePlaylist() {


        given().header("Content-Type", "application/json").
                basePath(String.valueOf(playlistId)).
                when().
                delete().
                then().
                assertThat().
                statusCode(200);

    }

    @Test(dependsOnMethods = "testDeletePlaylist")
    public void testPlaylistIsDeleted() {


        given().
                basePath(String.valueOf(playlistId)).
                when().
                get().then().assertThat().
                statusCode(404);

    }

}
