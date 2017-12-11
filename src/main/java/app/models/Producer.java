package app.models;

import libs.mvc.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Producer extends Model<Integer>
{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String name;

    @OneToMany (mappedBy = "producer")
    private List<Movie> movies;

    public Producer ()
    {
        this.movies = new ArrayList<>();
    }

    public Producer (String name)
    {
        this();
        this.name = name;
    }

    @Override
    public Integer getId ()
    {
        return id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public List<Movie> getMovies ()
    {
        return movies;
    }

    void addMovies (Movie... movies)
    {
        this.movies.addAll(Arrays.asList(movies));
    }

    @Override
    public String toString ()
    {
        return this.name;
    }
}
