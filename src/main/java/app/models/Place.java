package app.models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import libs.mvc.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Place extends Model
{
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();


    private ListProperty<Scene> scenes = new SimpleListProperty<>();


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public int getId ()
    {
        return id.get();
    }

    @Column
    public String getTitle ()
    {
        return title.get();
    }


    @Column
    public String getAddress ()
    {
        return address.get();
    }


    @Column
    public String getDescription ()
    {
        return description.get();
    }

    @OneToMany (cascade = CascadeType.DETACH, fetch = FetchType.LAZY, targetEntity = Scene.class, mappedBy = "place")
    public List<Scene> getScenes ()
    {
        return scenes.get();
    }

    public IntegerProperty idProperty ()
    {
        return id;
    }

    public StringProperty titleProperty ()
    {
        return title;
    }

    public StringProperty addressProperty ()
    {
        return address;
    }

    public StringProperty descriptionProperty ()
    {
        return description;
    }

    public ListProperty<Scene> setupsProperty ()
    {
        return scenes;
    }

    public void setId (int id)
    {
        this.id.set(id);
    }

    public void setTitle (String title)
    {
        this.title.set(title);
    }

    public void setAddress (String address)
    {
        this.address.set(address);
    }

    public void setDescription (String description)
    {
        this.description.set(description);
    }

    public void setScenes (List<Scene> setups)
    {
        this.scenes.set(FXCollections.observableList(setups));
    }
}
