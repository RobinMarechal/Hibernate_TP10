package app.models;

import libs.PlaceType;

import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Theater is the class representing one type of place, the theatre
 * @author Robin Marechal
 * @author Pierre Vende
 *
 */
@Entity
public class Theatre extends Place
{
	/**
	 * Default constructor
	 */
    public Theatre ()
    {
        super();
    }

    /**
     * Constructor setting the name's theatre
     * @param name Name of the theatre to be created
     */
    public Theatre (String name)
    {
        super(name);
    }

    /**
     * Constructor setting the name's theatre and its address
     * @param name Name of the theatre to be created
     * @param address Address of the theatre to be created
     */
    public Theatre (String name, String address)
    {
        super(name, address);
    }

    /**
     * Constructor setting the name's theatre, its address and its description
     * @param name Name of the theatre to be created
     * @param address Address of the theatre to be created
     * @param description Description of the theatre to be created
     */
    public Theatre (String name, String address, String description)
    {
        super(name, address, description);
    }

    /**
     * Getter for the type of place
     * @return PlaceType.THEATRE
     */
    @Transient
    @Override
    public PlaceType getType ()
    {
        return PlaceType.THEATRE;
    }

    /**
     * Check if the two objects are equals
     * @param o Object to be compared
     * @return true if they are equals, false instead
     */
    @Override
    public boolean equals (Object o)
    {
        return super.equals(o) && o instanceof ExternalPlace;
    }
}
