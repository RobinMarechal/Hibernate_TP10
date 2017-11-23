package models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "tanks")
public class Tank
{
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private DoubleProperty capacity = new SimpleDoubleProperty(this, "capacity");
    private StringProperty material = new SimpleStringProperty(this, "material");

    private ListProperty<Group> groups = new SimpleListProperty<>(this, "groups");

    public Tank ()
    {
        this.groups.setValue(FXCollections.observableArrayList());
    }


    // -----------------------
    // Getters
    // -----------------------------

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public int getId ()
    {
        return id.getValue();
    }

    @Column
    public double getCapacity ()
    {
        return capacity.getValue();
    }

    @Column
    public String getMaterial ()
    {
        return material.getValueSafe();
    }

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tank")
    public List<Group> getGroups ()
    {
        return groups.getValue();
    }

    @Transient
    public Group getGroup ()
    {
        return this.groups.getValue().size() == 0 ? null : groups.getValue().get(0);
    }

    // -----------------------
    // Setters
    // -----------------------

    /**
     * A tank can contain only one group <br>
     * If the tank already contains a group, it replaces it
     *
     * @param group the new group in the tank
     * @return this
     */
    public Tank setGroup (Group group)
    {
        ObservableList<Group> list = this.groups.getValue();
        while (!list.isEmpty()) {
            list.remove(0).setTank(null);
        }

        this.groups.add(group);
        group.setTank(this);

        return this;
    }

    public Tank setCapacity (double capacity)
    {
        this.capacity.setValue(capacity);
        return this;
    }

    public Tank setMaterial (String material)
    {
        this.material.setValue(material);
        return this;
    }

    // -----------------------
    // Properties
    // -----------------------

    public IntegerProperty idProperty ()
    {
        return id;
    }

    public DoubleProperty capacityProperty ()
    {
        return capacity;
    }

    public StringProperty materialProperty ()
    {
        return material;
    }

    public ListProperty<Group> groupsProperty ()
    {
        return groups;
    }

    // -----------------------
    // List
    // -----------------------

    public Tank removeGroup ()
    {
        if (groups.size() == 1) {
            groups.remove(0);
        }

        return this;
    }

    // -----------------------
    // Should not be called
    // -----------------------

    /**
     * Shouldn't be called, you should not modify the id
     *
     * @param id whatever you want, I don't care
     * @return this;
     */
    @Deprecated
    public Tank setId (int id)
    {
        if (this.id.getValue() == 0) {
            this.id.set(id);
        }
        return this;
    }

    /**
     * Does nothing
     *
     * @param groups whatever you want, I don't care
     * @return this;
     */
    @Deprecated
    public Tank setGroups (List<Group> groups)
    {
        // The groups list should only contains one element maximum
        if ((this.groups.getValue() == null || this.groups.getValue().isEmpty()) && !groups.isEmpty()) {
            this.groups.set(FXCollections.observableArrayList(groups.get(0)));
            groups.get(0).setTank(this);
        }
        return this;
    }

    // -----------------------
    // Others
    // -----------------------

    /**
     * Check if this tank has sufficient capacity to welcome a given {@link Group} instance
     *
     * @param group the group
     * @return true if this tank has sufficient capacity to welcome the {@link Group} instance
     */
    public boolean hasSufficientCapacityFor (Group group)
    {
        float waterRequired = 0;

        List<Typology> typologies = group.getTypologies();

        for (Typology typology : typologies) {
            waterRequired += typology.getWaterQuantity() * typology.getNbFish();
        }

        return this.getCapacity() >= waterRequired;
    }

    /**
     * Know if the tank is free
     * @return true if the tank is free, false if it contains a {@link Group} of fishes
     */
    @Transient
    public boolean isFree()
    {
        return this.getGroup() == null;
    }


    @Override
    public String toString ()
    {
        return "Tank{" + "id=" + getId() + ", capacity=" + getCapacity() + ", material=" + getMaterial() + ", groups=" + getGroup().getId() + '}';
    }
}
