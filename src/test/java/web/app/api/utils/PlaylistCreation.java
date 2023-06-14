package web.app.api.utils;

import io.restassured.response.Response;
import web.app.api.objects.Playlist;

import static io.restassured.RestAssured.given;

public class PlaylistCreation {

    public int CreatePlaylist() {
        String endpoint = "http://localhost:8080/api/playlists";

        Playlist body = new Playlist("PlaylistName", "PlaylistDescription", true);
        body.setUserId(31);
        Response response = given().
                header("Content-Type", "application/json").
                body(body).
                when().
                post(endpoint);
        Playlist playlist = response.as(Playlist.class);
        return playlist.getId();
    }

}
