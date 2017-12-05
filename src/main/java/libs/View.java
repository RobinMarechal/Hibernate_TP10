package libs;

public abstract class View
{
    protected final Controller controller;

    public View (Controller controller)
    {
        this.controller = controller;
    }

    public abstract void display();
}
