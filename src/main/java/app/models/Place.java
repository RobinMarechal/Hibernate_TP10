package app.models;

import libs.PlaceType;
import libs.mvc.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Place extends Model<Integer>
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
        scenes = new ArrayList<>();
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

    @Override
    public Integer getId ()
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

    void addScenes (Scene... scenes)
    {
        Collections.addAll(this.scenes, scenes);
    }

    @Transient
    public abstract PlaceType getType ();

    @Override
    public String toString ()
    {
        return name;
    }
}
