package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "owners")
public class Owner
{
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owner")
    private List<Phone> phones;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owner")
    private List<Group> groups;

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

    public List<Phone> getPhones ()
    {
        return phones;
    }

    public List<Group> getGroups ()
    {
        return groups;
    }

    public Owner setName (String name)
    {
        this.name = name;
        return this;
    }

    public Owner setAddress (String address)
    {
        this.address = address;
        return this;
    }

    public Owner addPhones(Phone... phone)
    {
        // TODO
        return this;
    }

    public Owner addGroups(Group... group)
    {
        // TODO
        return this;
    }
}
