package libs.mvc;

import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;

public abstract class View extends AnchorPane
{
    protected final Controller controller;

    public View (Controller controller)
    {
        this.controller = controller;
    }

    protected abstract void setup ();

    public abstract void display ();

    public void fitComponentToParent (Control component)
    {
        AnchorPane.setTopAnchor(component, 0d);
        AnchorPane.setBottomAnchor(component, 0d);
        AnchorPane.setLeftAnchor(component, 0d);
        AnchorPane.setRightAnchor(component, 0d);
    }
}
