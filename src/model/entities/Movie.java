package model.entities;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Movie {
    private Integer id;
    private String tittle;
    private String director;

    Set<Type> types = new HashSet<>();

    public Movie(){
    }
    public Movie(Integer id, String tittle, String director) {
        this.id = id;
        this.tittle = tittle;
        this.director = director;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Set<Type> getTypes() {
        return types;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(tittle, movie.tittle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tittle);
    }


    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", tittle='" + tittle + '\'' +
                ", director='" + director + '\'' +
                '}';
    }
}
