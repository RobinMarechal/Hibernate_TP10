package models;

import javafx.beans.property.*;

import javax.persistence.*;

@Entity
@Table (name = "phones")
public class Phone
{
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty phoneNumber = new SimpleStringProperty(this, "phoneNumber");
    private ObjectProperty<Owner> owner = new SimpleObjectProperty<>(this, "owner");

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public int getId ()
    {
        return id.getValue();
    }

    @Column
    public String getPhoneNumber ()
    {
        return phoneNumber.getValueSafe();
    }

    @ManyToOne
    public Owner getOwner ()
    {
        return owner.getValue();
    }

    public Phone setPhoneNumber (String phoneNumber)
    {
        this.phoneNumber.setValue(phoneNumber);
        return this;
    }

    Phone setOwner (Owner owner)
    {
        this.owner.setValue(owner);
        return this;
    }

    /**
     * Does nothing, you shouldn't modify the id
     * @param id whatever you want, I don't care
     * @return this
     */
    public Phone setId(int id)
    {
        if(this.id.getValue() == 0)
            this.id.setValue(id);
        return this;
    }


    // PROPERTIES


    public IntegerProperty idProperty ()
    {
        return id;
    }

    public StringProperty phoneNumberProperty ()
    {
        return phoneNumber;
    }

    public ObjectProperty<Owner> ownerProperty ()
    {
        return owner;
    }
}
