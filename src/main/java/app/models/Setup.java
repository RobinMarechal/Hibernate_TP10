package app.models;

import libs.mvc.models.Model;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Setup is the class representing the setup of a scene (acquisition parameters)
 * @author Robin Marechal
 * @author Pierre Vende
 *
 */
@Entity
public class Setup extends Model<Integer>
{
	/**
	 * Id of the Setup
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Description of the Setup
     */
    @Basic
    private String description;

    /**
     * Scene where the setup is used
     */
    @ManyToOne (cascade = CascadeType.DETACH, fetch = FetchType.EAGER, optional = false)
    private Scene scene;

    /**
     * List of all claps shot in the setup
     */
    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "primaryKey.setup")
    private List<Clap> claps;

    /**
     * Default constructor
     */
    public Setup ()
    {
        claps = new ArrayList<>();
    }

    /**
     * Constructor setting the scene's setup
     * @param scene Scene where the setup was used
     */
    public Setup (Scene scene)
    {
        this();
        this.scene = scene;
    }

    /**
     * Constructor setting the scene's setup and its description
     * @param scene Scene where the setup was used
     * @param description Description of the place to be created
     */
    public Setup (Scene scene, String description)
    {
        this(scene);
        this.description = description;
    }

    /**
     * Setter for description attribute
     * @param description Description's Setup
     */
    public void setDescription (String description)
    {
        this.description = description;
    }

    /**
     * Getter for Id attribute
     * @return Id's Setup
     */
    @Override
    public Integer getId ()
    {
        return id;
    }

    /**
     * Getter for description attribute
     * @return description's Setup
     */
    public String getDescription ()
    {
        return description;
    }

    /**
     * Getter for scene attribute
     * @return scene's Setup
     */
    public Scene getScene ()
    {
        return scene;
    }

    /**
     * Getter for claps attribute
     * @return claps' Setup
     */
    public List<Clap> getClaps ()
    {
        return claps;
    }

    /**
     * Setter for scene attribute
     * @param scene scene's Setup
     */
    void setScene (Scene scene)
    {
        this.scene = scene;
    }

    /**
     * Add a list of claps to the claps' list attribute
     * @param claps List to be added
     */
    public void addClaps (Clap... claps)
    {
        for (Clap clap : claps) {
            this.claps.add(clap);
            clap.setSetup(this);
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
        o = Hibernate.unproxy(o);
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Setup setup = (Setup) o;

        return id == setup.id && id != null && id != 0;
    }

    /**
     * Get the hashcode of the Id's setup
     * @return hashcode of the Id's setup if there is an Id
     */
    @Override
    public int hashCode ()
    {
    	return id != null ? id.hashCode() : 0;
    }
}
