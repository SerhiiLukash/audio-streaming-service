package web.app.api.objects;

public class Track {
    int id;
    String title;
    String artist;
    int duration;
    int year;
    String album;

    public Track(String title, String artist, int duration, int year, String album) {

        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.year = year;
        this.album = album;
    }

}
