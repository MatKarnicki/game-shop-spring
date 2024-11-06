package ug.edu.game.rest.model;
import java.util.UUID;

public class Game {
    private final String id;
    private String title;
    private String genre;
    private int releaseYear;

    public Game(String title, String genre, int releaseYear) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    public Game(String id, String title, String genre, int releaseYear){
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String toString(){
        return "Game [id=" + id + ", title=" + title + ", genre=" + genre + ", releaseYear=" + releaseYear + "]";
    }
}