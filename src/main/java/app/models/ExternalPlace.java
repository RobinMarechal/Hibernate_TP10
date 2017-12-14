package app.models;

import libs.PlaceType;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class ExternalPlace extends Place
{
    public ExternalPlace ()
    {
        super();
    }

    public ExternalPlace (String name)
    {
        super(name);
    }

    public ExternalPlace (String name, String address)
    {
        super(name, address);
    }

    public ExternalPlace (String name, String address, String description)
    {
        super(name, address, description);
    }

    @Transient
    @Override
    public PlaceType getType ()
    {
        return PlaceType.EXTERNAL_PLACE;
    }


    @Override
    public boolean equals (Object o)
    {
        return super.equals(o) && o instanceof ExternalPlace;
    }


}
