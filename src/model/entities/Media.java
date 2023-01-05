package model.entities;

import java.time.Instant;

public class Media {
    private Integer id;
    private String codeBarr;
    private Movie movie;

    public Media(){
    }

    public Media(Integer id, String codeBarr, Movie movie) {
        this.id = id;
        this.codeBarr = codeBarr;
        this.movie = movie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeBarr() {
        return codeBarr;
    }

    public void setCodeBarr(String codeBarr) {
        this.codeBarr = codeBarr;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", codeBarr='" + codeBarr + '\'' +
                ", movie=" + movie +
                '}';
    }
}
