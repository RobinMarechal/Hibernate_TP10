package models;

import javax.persistence.*;

@Entity
@Table(name = "phones")
public class Phone
{
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    private String phoneNumber;

    @ManyToOne
    private Owner owner;

    public int getId ()
    {
        return id;
    }

    public String getPhoneNumber ()
    {
        return phoneNumber;
    }

    public Owner getOwner ()
    {
        return owner;
    }

    public Phone setPhoneNumber (String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Phone setOwner (Owner owner)
    {
        this.owner = owner;
        return this;
    }
}
