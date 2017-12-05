package models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import libs.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Film extends Model
{
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty director = new SimpleStringProperty();

    private ObjectProperty<Producer> producer = new SimpleObjectProperty<>();

    private ListProperty<Scene> scenes = new SimpleListProperty<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public String getDirector ()
    {
        return director.get();
    }

    @ManyToOne
    public Producer getProducer ()
    {
        return producer.get();
    }

    @OneToMany (cascade = CascadeType.DETACH, fetch = FetchType.LAZY, targetEntity = Scene.class, mappedBy = "film")
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

    public StringProperty directorProperty ()
    {
        return director;
    }

    public ObjectProperty<Producer> producersProperty ()
    {
        return producer;
    }

    public ListProperty<Scene> scenesProperty ()
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

    public void setDirector (String director)
    {
        this.director.set(director);
    }

    public void setProducer (Producer producers)
    {
        this.producer.set(producers);
    }

    public void setScenes (List<Scene> scenes)
    {
        this.scenes.set(FXCollections.observableList(scenes));
    }
}
