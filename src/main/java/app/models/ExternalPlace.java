package app.models;

import libs.PlaceType;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * ExternalPlace is the class representing one type of place, the external place
 * @author Robin Marechal
 * @author Pierre Vende
 *
 */
@Entity
public class ExternalPlace extends Place
{
	/**
	 * Default constructor
	 */
    public ExternalPlace ()
    {
        super();
    }

    /**
     * Constructor setting the name's external place
     * @param name Name of the external place to be created
     */
    public ExternalPlace (String name)
    {
        super(name);
    }

    /**
     * Constructor setting the name's external place and its address
     * @param name Name of the external place to be created
     * @param address Address of the external place to be created
     */
    public ExternalPlace (String name, String address)
    {
        super(name, address);
    }

    /**
     * Constructor setting the name's external place, its address and its description
     * @param name Name of the external place to be created
     * @param address Address of the external place to be created
     * @param description Description of the external place to be created
     */
    public ExternalPlace (String name, String address, String description)
    {
        super(name, address, description);
    }

    /**
     * Getter for the type of place
     * @return PlaceType.EXTERNAL_PLACE
     */
    @Transient
    @Override
    public PlaceType getType ()
    {
        return PlaceType.EXTERNAL_PLACE;
    }

    /**
     * Check if the two objects are equals
     * @param o Object to be compared
     * @return true if they are equals, false instead
     */
    @Override
    public boolean equals (Object o)
    {
        o = Hibernate.unproxy(o);
        return super.equals(o) && o instanceof ExternalPlace;
    }


}
