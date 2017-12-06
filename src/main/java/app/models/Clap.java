package app.models;

import javafx.beans.property.*;
import libs.mvc.Model;

import javax.persistence.*;

@Entity
public class Clap extends Model
{
    private IntegerProperty id    = new SimpleIntegerProperty();

    private IntegerProperty numero = new SimpleIntegerProperty();

    private StringProperty  description = new SimpleStringProperty();

    private ObjectProperty<Setup> setup = new SimpleObjectProperty<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId ()
    {
        return id.get();
    }

    @ManyToOne
    public Setup getSetup ()
    {
        return setup.get();
    }

    @Column
    public String getDescription ()
    {
        return description.get();
    }

    public IntegerProperty idProperty ()
    {
        return id;
    }

    public StringProperty descriptionProperty ()
    {
        return description;
    }

    public ObjectProperty<Setup> setupProperty ()
    {
        return setup;
    }

    public void setId (int id)
    {
        this.id.set(id);
    }

    public void setDescription (String description)
    {
        this.description.set(description);
    }

    public void setSetup (Setup setup)
    {
        this.setup.set(setup);
    }
}
