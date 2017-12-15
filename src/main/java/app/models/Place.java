package app.models;

import libs.PlaceType;
import libs.mvc.models.Model;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Place is the class representing the place where the scene is set
 * The db create one single table where all the inherited classes of Place are 
 * @author Robin Marechal
 * @author Pierre Vende
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Place extends Model<Integer>
{	
	/**
	 * Id of the Place
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Name of the Place
     */
    @Basic
    private String name;

    /**
     * Address of the Place
     */
    @Basic
    private String address;

    /**
     * Description of the Place
     */
    @Basic
    private String description;

    /**
     * Scenes set in this place
     */
    @OneToMany (cascade = CascadeType.DETACH, mappedBy = "place")
    private List<Scene> scenes;

    /**
     * Default constructor
     */
    public Place ()
    {
        scenes = new ArrayList<>();
    }

    /**
     * Constructor setting the name's place
     * @param name Name of the place to be created
     */
    public Place (String name)
    {
        this();
        this.name = name;
    }

    /**
     * Constructor setting the name's place and its address
     * @param name Name of the place to be created
     * @param address Address of the place to be created
     */
    public Place (String name, String address)
    {
        this(name);
        this.address = address;
    }

    /**
     * Constructor setting the name's place, its address and its description
     * @param name Name of the place to be created
     * @param address Address of the place to be created
     * @param description Description of the place to be created
     */
    public Place (String name, String address, String description)
    {
        this(name, address);
        this.description = description;
    }

    /**
     * Getter for Id attribute
     * @return Id's Place
     */
    @Override
    public Integer getId ()
    {
        return id;
    }


    /**
     * Getter for name attribute
     * @return name's Place
     */
    public String getName ()
    {
        return name;
    }

    /**
     * Getter for address attribute
     * @return address' Place
     */
    public String getAddress ()
    {
        return address;
    }

    /**
     * Getter for description attribute
     * @return description's Place
     */
    public String getDescription ()
    {
        return description;
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
     * Setter for name attribute
     * @param name name's Place
     */
    public void setName (String name)
    {
        this.name = name;
    }

    /**
     * Setter for address attribute
     * @param address address' Place
     */
    public void setAddress (String address)
    {
        this.address = address;
    }

    /**
     * Setter for description attribute
     * @param description description's Place
     */
    public void setDescription (String description)
    {
        this.description = description;
    }

    /**
     * Add a list of scenes to the scenes' list attribute
     * @param scenes List to be added
     */
    void addScenes (Scene... scenes)
    {
        Collections.addAll(this.scenes, scenes);
    }

    /**
     * Get the type of the Place (Theater, ExternalPlace)
     * Need to be defined in the inherited classes
     * @return Type of the place
     */
    @Transient
    public abstract PlaceType getType ();

    /**
     * Convert the place to string
     * @return String version of the place
     */
    @Override
    public String toString ()
    {
        return name;
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
        o = Hibernate.unproxy(o);
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Place place = (Place) o;

        return id == place.id && id != null && id != 0;
    }

    /**
     * Get the hashcode of the Id's place
     * @return hashcode of the Id's place if there is an Id
     */
    @Override
    public int hashCode ()
    {
        return id != null ? id.hashCode() : 0;
    }

//    /**
//     * Convert the place into a Theatre object
//     * @throws ModelException Can't transform a ExternalPlace's instance to a Theatre's instance
//     * @return converted object
//     */
//    public Theatre toTheatre()
//    {
//        if(this instanceof ExternalPlace)
//            throw new ModelException("Can't transform a ExternalPlace's instance to a Theatre's instance");
//
//        Place t = new Theatre();
//        t.id = this.id;
//        t.address = this.address;
//        t.name = this.name;
//        t.description = this.description;
//        t.scenes = this.scenes;
//
//        return (Theatre) t;
//    }
//
//    /**
//     * Convert the place into a ExternalPlace object
//     * @throws ModelException Can't transform a Theatre's instance to a ExternalPlace's instance
//     * @return converted object
//     */
//    public ExternalPlace toExternalPlace()
//    {
//        if(this instanceof Theatre)
//            throw new ModelException("Can't transform a Theatre's instance to a ExternalPlace's instance");
//
//        Place t = new ExternalPlace();
//        t.id = this.id;
//        t.address = this.address;
//        t.name = this.name;
//        t.description = this.description;
//        t.scenes = this.scenes;
//
//        return (ExternalPlace) t;
//    }
}
