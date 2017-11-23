package models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "owners")
public class Owner
{
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty name = new SimpleStringProperty(this, "name");
    private StringProperty address = new SimpleStringProperty(this, "address");

    private ListProperty<Phone> phones = new SimpleListProperty<>(this, "phones");
    private ListProperty<Group> groups = new SimpleListProperty<>(this, "groups");

    public Owner ()
    {
        this.phones.setValue(FXCollections.observableArrayList());
        this.groups.setValue(FXCollections.observableArrayList());
    }

    public Owner (String name, String address)
    {
        this();
        setName(name);
        setAddress(address);
    }


    // ------------------------------
    // Getters
    // ------------------------------

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public int getId ()
    {
        return id.getValue();
    }

    @Column
    public String getName ()
    {
        return name.getValueSafe();
    }

    @Column
    public String getAddress ()
    {
        return address.getValueSafe();
    }

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owner")
    public List<Phone> getPhones ()
    {
        return phones.getValue();
    }

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owner")
    public List<Group> getGroups ()
    {
        return groups.getValue();
    }


    // ------------------------------
    // Setters
    // ------------------------------

    public Owner setName (String name)
    {
        this.name.setValue(name);
        return this;
    }

    public Owner setAddress (String address)
    {
        this.address.setValue(address);
        return this;
    }

    public Owner addPhones (Phone... phones)
    {
        ObservableList<Phone> list = this.phones.getValue();
        for (Phone phone : phones) {
            list.add(phone);
            phone.setOwner(this);
        }
        return this;
    }

    public Owner addGroups (Group... groups)
    {
        ObservableList<Group> list = this.groups.getValue();
        for (Group group : groups) {
            list.add(group);
            group.setOwner(this);
        }
        return this;
    }

    // ------------------------------
    // Propeties
    // ------------------------------

    public IntegerProperty idProperty ()
    {
        return id;
    }

    public StringProperty nameProperty ()
    {
        return name;
    }

    public StringProperty addressProperty ()
    {
        return address;
    }

    public ListProperty<Phone> phonesProperty ()
    {
        return phones;
    }

    public ListProperty<Group> groupsProperty ()
    {
        return groups;
    }

    // ------------------------------
    // Should not be called
    // ------------------------------

    /**
     * Does nothing
     *
     * @param phones whatever you want
     * @return this
     */
    @Deprecated
    public Owner setPhones (List<Phone> phones)
    {
        if (this.phones.getValue() == null || this.phones.getValue().isEmpty()) {
            this.phones.setValue(FXCollections.observableList(phones));
            phones.forEach(phone -> phone.setOwner(this));
        }
        return this;
    }

    /**
     * Does nothing
     *
     * @param groups whatever you want
     * @return this
     */
    @Deprecated
    public Owner setGroups (List<Group> groups)
    {
        if (this.groups.getValue() == null || this.groups.getValue().isEmpty()) {
            this.groups.setValue(FXCollections.observableList(groups));
            groups.forEach(group -> group.setOwner(this));
        }
        return this;
    }

    /**
     * Does nothing, you shouldn't modify the id
     *
     * @param id whatever you want, I don't care
     * @return this
     */
    @Deprecated
    public Owner setId (int id)
    {
        if (this.id.getValue() == 0) {
            this.id.setValue(id);
        }
        return this;
    }

    // ---------------------
    // Others
    // ---------------------

    public Phone addPhoneNumber(String phoneNumber)
    {
        Phone p = new Phone(phoneNumber);
        this.addPhones(p);
        return p;
    }


    @Override
    public String toString ()
    {
        return "Owner{" + "id=" + getId() + ", name=" + getName() + ", address=" + getAddress() + ", phones=" + getPhones() + ", groups=" + getGroups().size
                () + '}';
    }
}
