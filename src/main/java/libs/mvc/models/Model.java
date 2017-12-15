package libs.mvc.models;

import java.io.Serializable;

/**
 * Abstract model's class
 * @param <PKtype> the type of the primary key
 */
public abstract class Model<PKtype extends Serializable>
{
    /**
     * Get the id
     * @return the id
     */
    abstract public PKtype getId ();
}
