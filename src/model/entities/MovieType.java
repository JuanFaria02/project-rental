package model.entities;

public class MovieType {
    private Integer id;
    private Movie movie;
    private Type type;

    public MovieType(){
    }

    public MovieType(Movie movie, Type type) {
        this.movie = movie;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MovieType{" +
                "id=" + id +
                ", movie=" + movie +
                ", type=" + type +
                '}';
    }
}
