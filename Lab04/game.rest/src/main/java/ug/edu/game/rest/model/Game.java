package ug.edu.game.rest.model;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

public class Game {
    private final String id;
    private String title;
    private String genre;
    private int sales;
    private boolean released;
    private Date releaseYear;

    public Game(String title, String genre, Date releaseYear, int sales) {
        this.id = UUID.randomUUID().toString();
        setTitle(title);
        setGenre(genre);
        setReleaseYear(releaseYear);
        setSales(sales);
        this.released = releaseYear.before(new Date());
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

    public int getSales() {
        return sales;
    }
    public Date getReleaseYear() {
        return releaseYear;
    }

    public boolean isReleased() {
        return released;
    }
    public void setTitle(String title) {
        if (title == null || !Pattern.matches("^[A-Z]", title)) {
            throw new IllegalArgumentException("Title must begin with a big letter.");
        }
        this.title = title;
    }

    public void setGenre(String genre) {
        if (genre == null || genre.isEmpty()) {
            throw new IllegalArgumentException("Genre cannot be null or empty.");
        }
        this.genre = genre;
    }

    public void setReleaseYear(Date releaseYear) {
        if (releaseYear == null) {
            throw new IllegalArgumentException("Release year cannot be null.");
        }
        if (releaseYear.before(new Date(1958, Calendar.NOVEMBER,1))) {
            throw new IllegalArgumentException("Can't set the date before the first game ever made.");
        }
        this.releaseYear = releaseYear;
    }

    public void setSales(int sales) {
        if (sales < 0) {
            throw new IllegalArgumentException("Sales must be a non-negative number.");
        }
        this.sales = sales;
    }

    public void setReleased(boolean isReleased) {
        this.released = isReleased;
    }


    public String toString(){
        return "Game [id=" + id + ", title=" + title + ", genre=" + genre + ", releaseYear=" + releaseYear + ", sales=" + sales + ", released=" + released + "]";
    }
}