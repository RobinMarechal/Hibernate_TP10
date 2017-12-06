package app.models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import libs.mvc.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Setup extends Model
{
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty description = new SimpleStringProperty();

    private ObjectProperty<Scene> scene = new SimpleObjectProperty<>();

    private ListProperty<Clap> claps = new SimpleListProperty<>();

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public int getId ()
    {
        return id.get();
    }

    @Column
    public String getDescription ()
    {
        return description.get();
    }

    @ManyToOne
    public Scene getScene ()
    {
        return scene.get();
    }

    @OneToMany (cascade = CascadeType.DETACH, fetch = FetchType.LAZY, targetEntity = Clap.class, mappedBy = "setup")
    public List<Clap> getClaps ()
    {
        return claps.get();
    }

    public IntegerProperty idProperty ()
    {
        return id;
    }

    public StringProperty descriptionProperty ()
    {
        return description;
    }

    public ObjectProperty<Scene> sceneProperty ()
    {
        return scene;
    }

    public ListProperty<Clap> clapsProperty ()
    {
        return claps;
    }

    public void setId (int id)
    {
        this.id.set(id);
    }

    public void setDescription (String description)
    {
        this.description.set(description);
    }

    public void setScene (Scene scene)
    {
        this.scene.set(scene);
    }

    public void setClaps (List<Clap> claps)
    {
        this.claps.set(FXCollections.observableList(claps));
    }
}
