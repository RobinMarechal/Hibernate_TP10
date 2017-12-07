package app.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Producer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String name;

    @OneToMany (mappedBy = "producer")
    private List<Movie> movies;

    public Producer ()
    {
    }

    public Producer (String name)
    {
        this();
        this.name = name;
    }

    public int getId ()
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

    public void addMovies(Movie... movies)
    {
        // TODO
    }
}
