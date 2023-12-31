package web.app.api.objects;

import java.util.ArrayList;

public class Playlist {
    String name;
    String description;
    ArrayList<Track> tracks;
    Boolean isPublic;
    int userId;
    int id;

    public Playlist(String name, String description, Boolean isPublic) {
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
        // tracks = new ArrayList<Track>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTracks(ArrayList tracks) {
        this.tracks = tracks;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
