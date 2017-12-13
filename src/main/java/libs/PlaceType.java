package libs;

public enum PlaceType
{
    THEATRE("Theatre"),
    EXTERNAL_PLACE("External Place");

    private String value;

    PlaceType (String value)
    {
        this.value = value;
    }

    @Override
    public String toString ()
    {
        return this.value;
    }
}
