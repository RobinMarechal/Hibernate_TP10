package libs.mvc.views;

import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import libs.mvc.controllers.Controller;
import libs.ui.template.Template;

public abstract class View<CType extends Controller> extends AnchorPane
{
    protected final CType controller;

    public View (CType controller)
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

    protected void setAnchorOfComponent (Region component, double top, double right, double bottom, double left)
    {
        AnchorPane.setTopAnchor(component, top);
        AnchorPane.setBottomAnchor(component, bottom);
        AnchorPane.setLeftAnchor(component, left);
        AnchorPane.setRightAnchor(component, right);
    }

    protected void setSizeOfColumnInTable (TableColumn column, Region container, double percentageWidth)
    {
        setSizeOfColumnInTable(column, container, percentageWidth, 0);
    }

    protected void setSizeOfColumnInTable (TableColumn column, Region container, double percentageWidth, double toRemove)
    {
        //        column.setPrefWidth(container.getWidth() * percentageWidth / 100);
        column.setPrefWidth((Template.CONTENT_WIDTH - toRemove) * percentageWidth / 100);
    }

    public void addComponents (Region... regions)
    {
        this.getChildren().addAll(regions);
    }
}
