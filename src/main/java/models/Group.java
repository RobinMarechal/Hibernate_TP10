package models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "groups")
public class Group
{
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private IntegerProperty nbMeals = new SimpleIntegerProperty(this, "nbMeals");

    private ListProperty<Typology> typologies = new SimpleListProperty<>(this, "typologies");

    private ObjectProperty<Owner> owner = new SimpleObjectProperty<>(this, "owner");
    private ObjectProperty<Tank> tank = new SimpleObjectProperty<>(this, "tank");

    public Group()
    {
        this.typologies.setValue(FXCollections.observableArrayList());
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
    public int getNbMeals ()
    {
        return nbMeals.getValue();
    }

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Typology.class, mappedBy = "group")
    public List<Typology> getTypologies ()
    {
        return typologies;
    }

    @ManyToOne
    public Owner getOwner ()
    {
        return owner.getValue();
    }

    @ManyToOne
    public Tank getTank ()
    {
        return tank.getValue();
    }


    // ------------------------------
    // Propeties
    // ------------------------------

    public ListProperty<Typology> typologiesProperty ()
    {
        return typologies;
    }

    public ObjectProperty<Owner> ownerProperty ()
    {
        return owner;
    }

    public ObjectProperty<Tank> tankProperty ()
    {
        return tank;
    }

    public IntegerProperty idProperty ()
    {
        return id;
    }

    public IntegerProperty nbMealsProperty ()
    {
        return nbMeals;
    }


    // ------------------------------
    // Setters
    // ------------------------------


    Group setTank (Tank tank)
    {
        this.tank.setValue(tank);
        return this;
    }

    public Group setNbMeals (int nbMeals)
    {
        this.nbMeals.setValue(nbMeals);
        return this;
    }

    Group setOwner (Owner owner)
    {
        this.owner.setValue(owner);
        return this;
    }

    public Group addTypologies (Typology... typologies)
    {
        ObservableList<Typology> list = this.typologies.getValue();
        for (Typology typology : typologies) {
            list.add(typology);
            typology.setGroup(this);
        }
        return this;
    }

    // ------------------------------
    // Should not be called
    // ------------------------------

    /**
     * Does nothing
     *
     * @param typologies whatever you want
     * @return this
     */
    public Group setTypologies (List<Typology> typologies)
    {
        if (this.typologies.getValue() == null || this.typologies.getValue().isEmpty()) {
            this.typologies.setValue(FXCollections.observableList(typologies));
            typologies.forEach(typology -> typology.setGroup(this));
        }
        return this;
    }

    /**
     * Does nothing, you shouldn't modify the id
     *
     * @param id whatever you want, I don't care
     * @return this
     */
    public Group setId (int id)
    {
        if (this.id.getValue() == 0) {
            this.id.setValue(id);
        }
        return this;
    }
}
