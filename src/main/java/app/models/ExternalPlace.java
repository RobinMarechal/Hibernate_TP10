package app.models;

import javax.persistence.Entity;

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
}
