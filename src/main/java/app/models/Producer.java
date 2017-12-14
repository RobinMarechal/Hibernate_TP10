package app.models;

import libs.mvc.models.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Producer is the class representing a producer of a movie
 * @author Robin Marechal
 * @author Pierre Vende
 *
 */
@Entity
public class Producer extends Model<Integer>
{
	/**
	 * Id of the Producer
	 */
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Name of the Producer
     */
    @Basic
    private String name;

    /**
     * Movies produced in this Producer
     */
    @OneToMany (mappedBy = "producer")
    private List<Movie> movies;

    /**
     * Default constructor
     */
    public Producer ()
    {
        this.movies = new ArrayList<>();
    }

    /**
     * Constructor setting the name's producer
     * @param name Name of the producer to be created
     */
    public Producer (String name)
    {
        this();
        this.name = name;
    }

    /**
     * Getter for Id attribute
     * @return Id's Producer
     */
    @Override
    public Integer getId ()
    {
        return id;
    }

    /**
     * Getter for name attribute
     * @return name's Producer
     */
    public String getName ()
    {
        return name;
    }

    /**
     * Setter for name attribute
     * @param name name's Producer
     */
    public void setName (String name)
    {
        this.name = name;
    }

    /**
     * Getter for movies attribute
     * @return Movies' Producer
     */
    public List<Movie> getMovies ()
    {
        return movies;
    }

    /**
     * Add a list of movies to the movies' list attribute
     * @param movies List to be added
     */
    void addMovies (Movie... movies)
    {
        this.movies.addAll(Arrays.asList(movies));
    }

    /**
     * Convert the producer to string
     * @return String version of the producer
     */
    @Override
    public String toString ()
    {
        return this.name;
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

        Producer producer = (Producer) o;

        return id == producer.id && id != null && id != 0;
    }

    /**
     * Getter for Id attribute as an hashcode
     * @return Id's Producer
     */
    @Override
    public int hashCode ()
    {
        return id;
    }
}
