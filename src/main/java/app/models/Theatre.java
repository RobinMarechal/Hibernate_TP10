package app.models;

import javax.persistence.Entity;

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
}
