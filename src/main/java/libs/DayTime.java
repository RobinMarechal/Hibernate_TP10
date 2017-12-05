package libs;

import java.io.Serializable;

public enum DayTime implements Serializable
{
    NIGHT(1),
    DAY(0);

    private int value;

    DayTime (int value)
    {
        this.value = value;
    }

    @Override
    public String toString ()
    {
        return this.value + "";
    }
}
