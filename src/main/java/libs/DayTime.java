package libs;

import java.io.Serializable;

public enum DayTime implements Serializable
{
    NIGHT("night"),
    DAY("day");

    private String value;

    DayTime (String value)
    {
        this.value = value;
    }

    @Override
    public String toString ()
    {
        return this.value;
    }
}
