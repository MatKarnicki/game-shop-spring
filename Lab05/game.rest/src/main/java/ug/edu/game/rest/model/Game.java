package ug.edu.game.rest.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.UUID;
import java.util.regex.Pattern;

public class Game {
    private final String id;
    @NonNull
    private String title;
    @NonNull
    private String genre;
    @NonNull
    private int sales;
    private boolean released;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    public Game(String title, String genre, LocalDate releaseDate, int sales) {
        this.id = UUID.randomUUID().toString();
        setTitle(title);
        setGenre(genre);
        setReleaseDate(releaseDate);
        setSales(sales);
        this.released = releaseDate.isBefore(LocalDate.now());
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public boolean isReleased() {
        return released;
    }

    public void setTitle(String title) {
        if (title == null || !Pattern.matches("^[A-Z0-9].*", title)) {
            System.out.println("title: " + title);
            throw new IllegalArgumentException("Title must begin with a big letter.");
        }
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        if (releaseDate.isBefore(LocalDate.of(1958, 11, 1))) {
            throw new IllegalArgumentException("Can't set the date before the first game ever made.");
        }
        this.releaseDate = releaseDate;
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

    public String toString() {
        return "Game [id=" + id + ", title=" + title + ", genre=" + genre + ", releaseDate=" + releaseDate + ", sales=" + sales + ", released=" + released + "]";
    }
}
