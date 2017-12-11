package libs.mvc;

import java.io.Serializable;

public abstract class Model<PKtype extends Serializable>
{
    abstract public PKtype getId();
}
