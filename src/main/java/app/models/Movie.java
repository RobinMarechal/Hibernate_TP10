package app.models;

import libs.mvc.models.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Movie is a class representing a movie
 * @author Robin Marechal
 * @author Pierre Vende
 *
 */
@Entity
public class Movie extends Model<Integer>
{
	/**
	 * Id of the Movie
	 */
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Title of the Movie
     */
    @Basic
    private String title;

    /**
     * Director of the Movie
     */
    @Basic
    private String director;

    /**
     * Producer of the Movie
     */
    @ManyToOne
    private Producer producer;

    /**
     * Scenes shot during the movie
     */
    @OneToMany (mappedBy = "movie")
    private List<Scene> scenes;

    /**
     * Default constructor
     */
    public Movie ()
    {
        scenes = new ArrayList<>();
    }

    /**
     * Constructor setting the title's movie, its director and its producer
     * @param title Title of the movie
     * @param director Director of the movie
     * @param producer Producer of the movie
     */
    public Movie (String title, String director, Producer producer)
    {
        this(title, director);
        this.producer = producer;
    }

    /**
     * Constructor setting the title's movie and its director
     * @param title Title of the movie
     * @param director Director of the movie
     */
    public Movie (String title, String director)
    {
        this(title);
        this.director = director;
    }

    /**
     * Constructor setting the title's movie
     * @param title Title of the movie
     */
    public Movie (String title)
    {
        this();
        this.title = title;
    }

    /**
     * Getter for Id attribute
     * @return Id's Movie
     */
    @Override
    public Integer getId ()
    {
        return id;
    }

    /**
     * Getter for title attribute
     * @return Title's Movie
     */
    public String getTitle ()
    {
        return title;
    }

    /**
     * Setter for title attribute
     * @param title Title's Movie
     */
    public void setTitle (String title)
    {
        this.title = title;
    }

    /**
     * Getter for director attribute
     * @return Director's Movie
     */
    public String getDirector ()
    {
        return director;
    }

    /**
     * Setter for director attribute
     * @param director Director's Movie
     */
    public void setDirector (String director)
    {
        this.director = director;
    }

    /**
     * Getter for producer attribute
     * @return Producer's Movie
     */
    public Producer getProducer ()
    {
        return producer;
    }

    /**
     * Setter for producer attribute
     * @param producer Producer's Movie
     */
    public void setProducer (Producer producer)
    {
        if(producer != null && this.producer != producer)
        {
            this.producer = producer;
            producer.addMovies(this);
        }
    }

    /**
     * Getter for scenes attribute
     * @return scenes' Place
     */
    public List<Scene> getScenes ()
    {
        return scenes;
    }

    /**
     * Add a list of scenes to the scenes' list attribute
     * @param scenes List to be added
     */
    public void addScenes (Scene... scenes)
    {
        for (Scene scene : scenes) {
            this.scenes.add(scene);
            scene.setMovie(this);
        }
    }

    /**
     * Convert the movie to string
     * @return String version of the movie
     */
    @Override
    public String toString ()
    {
        return this.title;
    }

    /**
     * Check if the two objects are equals
     * @param o Object to be compared
     * @return true if they are equals, false instead
     */
    @Override
    public boolean equals (Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Movie movie = (Movie) o;

        return id == movie.id && id != null && id != 0;
    }

    /**
     * Get the hashcode of the Id's movie
     * @return hashcode of the Id's movie if there is an Id
     */
    @Override
    public int hashCode ()
    {
        return id != null ? id.hashCode() : 0;
    }
}
