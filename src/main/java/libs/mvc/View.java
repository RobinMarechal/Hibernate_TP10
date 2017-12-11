package libs.mvc;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

public abstract class View extends AnchorPane
{
    protected final Controller controller;

    public View (Controller controller)
    {
        this.controller = controller;
        this.getStyleClass().addAll("bg-almost-white");
    }

    protected abstract void setup ();

    protected abstract void display ();

    protected void fitComponentToParent (Region component)
    {
        setAnchorOfComponent(component, 0, 0, 0, 0);
    }

    protected void setAnchorOfComponent(Region component, double top, double right, double bottom, double left)
    {
        AnchorPane.setTopAnchor(component, top);
        AnchorPane.setBottomAnchor(component, bottom);
        AnchorPane.setLeftAnchor(component, left);
        AnchorPane.setRightAnchor(component, right);
    }

    protected void setSizeOfColumnInTable (TableColumn column, TableView owner, double percentageWidth)
    {
        column.setPrefWidth(owner.getWidth() * percentageWidth / 100);
    }

    public void addComponents (Region... regions)
    {
        this.getChildren().addAll(regions);
    }
}
