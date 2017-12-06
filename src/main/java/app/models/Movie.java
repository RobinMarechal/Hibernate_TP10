package app.models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import libs.mvc.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Movie extends Model
{
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty director = new SimpleStringProperty();

    private ObjectProperty<Producer> producer = new SimpleObjectProperty<>();

    private ListProperty<Scene> scenes = new SimpleListProperty<>();

    public Movie ()
    {
        this.scenes.setValue(FXCollections.observableArrayList());
    }

    public Movie (String title, String director, Producer producer)
    {
        this();
        setTitle(title);
        setDirector(director);
        setProducer(producer);
    }

    public Movie (String title, String director)
    {
        this(title, director, null);
    }

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
    public String getDirector ()
    {
        return director.get();
    }

    @ManyToOne
    public Producer getProducer ()
    {
        return producer.get();
    }

    @OneToMany (cascade = CascadeType.DETACH, fetch = FetchType.LAZY, targetEntity = Scene.class, mappedBy = "movies")
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

    @Override
    public String toString ()
    {
        return "Movie{" + "id=" + getId()
                + ", title=" + getTitle()
                + ", director=" + getDirector()
                + ", producer=" + getProducer()
                + ", scenes=" + getScenes().size()
                + '}';
    }
}
