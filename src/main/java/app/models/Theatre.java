package app.models;

import libs.PlaceType;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Theatre extends Place
{
    public Theatre ()
    {
        super();
    }

    public Theatre (String name)
    {
        super(name);
    }

    public Theatre (String name, String address)
    {
        super(name, address);
    }

    public Theatre (String name, String address, String description)
    {
        super(name, address, description);
    }

    @Transient
    @Override
    public PlaceType getType ()
    {
        return PlaceType.THEATRE;
    }

    @Override
    public boolean equals (Object o)
    {
        return super.equals(o) && o instanceof ExternalPlace;
    }
}
