package app.models;

import app.models.exceptions.ModelException;
import libs.DayTime;
import libs.PlaceType;
import libs.mvc.models.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Scene is the class representing a scene of a movie
 * @author Robin Marechal
 * @author Pierre Vende
 *
 */
@Entity
public class Scene extends Model<Integer>
{
	/**
	 * Id of the Scene
	 */
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Description of the Scene
     */
    @Basic
    private String description;

    /**
     * DayTime of the Scene
     */
    @Basic
    @Enumerated (EnumType.STRING)
    private DayTime dayTime = DayTime.NULL;

    /**
     * Movie where the Scene was shot
     */
    @ManyToOne (optional = false)
    private Movie movie;

    /**
     * List of all setups used in the Scene
     */
    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "scene")
    private List<Setup> setups;

    /**
     * Place of the Scene
     */
    @ManyToOne (cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Place place;

    /**
     * Default constructor
     */
    public Scene ()
    {
        setups = new ArrayList<>();
    }

    /**
     * Constructor setting the movie's scene
     * @param movie Movie where the Scene was shot
     */
    public Scene (Movie movie)
    {
        this();
        setMovie(movie);
    }

    /**
     * Constructor setting the movie's scene and the place's scene
     * @param movie Movie where the Scene was shot
     * @param place Place where the Scene was shot
     */
    public Scene (Movie movie, Place place)
    {
        this(movie, place, null);
    }

    /**
     * Constructor setting the movie's scene and the place's scene
     * @param movie Movie where the Scene was shot
     * @param place Place where the Scene was shot
     * @param dayTime DayTime when the Scene was shot
     */
    public Scene (Movie movie, Place place, DayTime dayTime)
    {
        this(movie);
        if(place.getType() == PlaceType.THEATRE)
            setPlace((Theatre) place);
        else
            setPlace((ExternalPlace) place, dayTime);
    }

    /**
     * Constructor setting the movie's scene and the place's scene
     * @param movie Movie where the Scene was shot
     * @param place Place where the Scene was shot
     * @param dayTime DayTime when the Scene was shot
     * @param description Description of the scene to be created
     */
    public Scene (Movie movie, Place place, DayTime dayTime, String description)
    {
        this(movie, place, dayTime);
        this.description = description;
    }

    /**
     * Getter for Id attribute
     * @return Id's Scene
     */
    @Override
    public Integer getId ()
    {
        return id;
    }

    /**
     * Getter for description attribute
     * @return description's Scene
     */
    public String getDescription ()
    {
        return description;
    }

    /**
     * Getter for dayTime attribute
     * @return dayTime's Scene
     */
    public DayTime getDayTime ()
    {
        return dayTime;
    }

    /**
     * Getter for dayTime attribute
     * @return dayTime's Scene
     */
    public Movie getMovie ()
    {
        return movie;
    }

    /**
     * Getter for setups attribute
     * @return setups' Scene
     */
    public List<Setup> getSetups ()
    {
        return setups;
    }

    /**
     * Getter for place attribute
     * @return place's Scene
     */
    public Place getPlace ()
    {
        return place;
    }

    /**
     * Setter for description attribute
     * @param description Description's Scene
     */
    public void setDescription (String description)
    {
        this.description = description;
    }

    /**
     * Setter for dayTime attribute
     * @param dayTime DayTime's Scene
     */
    void setDayTime (DayTime dayTime)
    {
        this.dayTime = dayTime;
    }

    /**
     * Setter for movie attribute
     * @param movie Movie's Scene
     */
    void setMovie (Movie movie)
    {
        this.movie = movie;
    }

    /**
     * Setter for place attribute for a theatre
     * @param theatre Theatre's Scene
     */
    public void setPlace (Theatre theatre)
    {
        if (theatre != null && !this.place.equals(theatre)) {
            this.place = theatre;
            theatre.addScenes(this);
        }
    }

    /**
     * Setter for place attribute for an external place with default dayTime (DayTime.DAY)
     * @param externalPlace ExternalPlace's Scene
     */
    public void setPlace (ExternalPlace externalPlace)
    {
        setPlace(externalPlace, DayTime.DAY);
    }

    /**
     * Setter for place attribute for an external place
     * @param externalPlace ExternalPlace's Scene
     * @param dayTime DayTime's Scene
     * @throws ModelException The daytime must not be null for external places
     */
    public void setPlace (ExternalPlace externalPlace, DayTime dayTime)
    {
        if (externalPlace != null && !this.place.equals(externalPlace)) {
            if(dayTime == null)
                throw new ModelException("The daytime must not be null for external places");

            this.dayTime = dayTime;
            this.place = externalPlace;
            externalPlace.addScenes(this);

        }
    }

    /**
     * Add a list of setups to the setups' list attribute
     * @param setups List to be added
     */
    public void addSetups (Setup... setups)
    {
        for (Setup setup : setups) {
            this.setups.add(setup);
            setup.setScene(this);
        }
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

        Scene scene = (Scene) o;

        return id == scene.id && id != 0;
    }

    /**
     * Getter for Id attribute as an hashcode
     * @return Id's Scene
     */
    @Override
    public int hashCode ()
    {
        return id;
    }
}
