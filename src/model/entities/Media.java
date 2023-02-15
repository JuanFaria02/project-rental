package model.entities;


import java.util.Random;


public class Media {
    private Integer id;
    private String codeBar;
    private Movie movie;

    public Media(){
    }

    public Media(Movie movie) {
        this.codeBar = String.valueOf(generateCodeBar());
        this.movie = movie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeBar() {
        return codeBar;
    }

    public void setCodeBar(String codeBar) {
        this.codeBar = codeBar;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    private Integer generateCodeBar() {
        Random code = new Random();
        return code.hashCode();
    }
    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", codeBarr='" + codeBar + '\'' +
                ", movie=" + movie +
                '}';
    }
}
