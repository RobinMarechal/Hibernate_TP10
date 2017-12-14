package app.models;

import app.models.exceptions.ModelException;
import libs.PlaceType;
import libs.mvc.models.Model;

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

    @Override
    public boolean equals (Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Place place = (Place) o;

        return id == place.id && id != null && id != 0;
    }

    @Override
    public int hashCode ()
    {
        return id != null ? id.hashCode() : 0;
    }

    public Theatre toTheatre()
    {
        if(this instanceof ExternalPlace)
            throw new ModelException("Can't transform a ExternalPlace's instance to a Theatre's instance");

        Place t = new Theatre();
        t.id = this.id;
        t.address = this.address;
        t.name = this.name;
        t.description = this.description;
        t.scenes = this.scenes;

        return (Theatre) t;
    }

    public ExternalPlace toExternalPlace()
    {
        if(this instanceof Theatre)
            throw new ModelException("Can't transform a Theatre's instance to a ExternalPlace's instance");

        Place t = new ExternalPlace();
        t.id = this.id;
        t.address = this.address;
        t.name = this.name;
        t.description = this.description;
        t.scenes = this.scenes;

        return (ExternalPlace) t;
    }
}
