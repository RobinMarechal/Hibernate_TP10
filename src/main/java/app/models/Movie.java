package app.models;

import libs.mvc.models.Model;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Movie extends Model<Integer>
{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    private String title;

    @Basic
    private String director;

    @ManyToOne
    private Producer producer;

    @OneToMany (mappedBy = "movie")
    private List<Scene> scenes;

    public Movie ()
    {
        scenes = new ArrayList<>();
    }

    public Movie (String title, String director, Producer producer)
    {
        this(title, director);
        this.producer = producer;
    }

    public Movie (String title, String director)
    {
        this(title);
        this.director = director;
    }

    public Movie (String title)
    {
        this();
        this.title = title;
    }

    @Override
    public Integer getId ()
    {
        return id;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getDirector ()
    {
        return director;
    }

    public void setDirector (String director)
    {
        this.director = director;
    }

    public Producer getProducer ()
    {
        return producer;
    }

    public void setProducer (Producer producer)
    {
        if(producer != null && this.producer != producer)
        {
            this.producer = producer;
            producer.addMovies(this);
        }
    }

    public List<Scene> getScenes ()
    {
        return scenes;
    }

    public void addScenes (Scene... scenes)
    {
        for (Scene scene : scenes) {
            this.scenes.add(scene);
            scene.setMovie(this);
        }
    }

    @Override
    public String toString ()
    {
        return this.title;
    }

    @Override
    public boolean equals (Object o)
    {
        if (this == o) {
            return true;
        }
        o = Hibernate.unproxy(o);
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Movie movie = (Movie) o;

        return id == movie.id && id != null && id != 0;
    }

    @Override
    public int hashCode ()
    {
        return id != null ? id.hashCode() : 0;
    }
}
