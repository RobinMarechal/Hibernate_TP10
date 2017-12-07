package app.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Place
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String name;

    @Basic
    private String address;

    @Basic
    private String description;

    @OneToMany (cascade = CascadeType.DETACH, mappedBy = "place")
    private List<Scene> scenes;

    public Place ()
    {
    }

    public Place (String name)
    {
        this();
        this.name = name;
    }

    public Place (String name, String address)
    {
        this(name);
        this.address = address;
    }

    public Place (String name, String address, String description)
    {
        this(name, address);
        this.description = description;
    }

    public int getId ()
    {
        return id;
    }


    public String getName ()
    {
        return name;
    }

    public String getAddress ()
    {
        return address;
    }

    public String getDescription ()
    {
        return description;
    }

    public List<Scene> getScenes ()
    {
        return scenes;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public void addScenes (Scene... scenes)
    {
        // TODO
    }
}
