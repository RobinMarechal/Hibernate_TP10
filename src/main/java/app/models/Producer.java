package app.models;

import libs.mvc.models.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Producer extends Model<Integer>
{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

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

    @Override
    public boolean equals (Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Producer producer = (Producer) o;

        return id == producer.id && id != null && id != 0;
    }

    @Override
    public int hashCode ()
    {
        return id;
    }
}
