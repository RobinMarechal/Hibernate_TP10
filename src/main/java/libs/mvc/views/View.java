package libs.mvc.views;

import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import libs.mvc.controllers.Controller;
import libs.ui.template.Template;


/**
 * Abstract view class
 * @param <CType> the type of the view's controller
 */
public abstract class View<CType extends Controller> extends AnchorPane
{
    /** The view's controller */
    protected final CType controller;

    /**
     * Constructor
     * @param controller the views controller
     */
    public View (CType controller)
    {
        this.controller = controller;
        this.getStyleClass().addAll("bg-almost-white");
    }


    protected abstract void setup ();

    protected abstract void display ();

    /**
     * Fit the component the its parent using AnchorPane
     * @param component the component to fit
     */
    protected void fitComponentToParent (Region component)
    {
        setAnchorOfComponent(component, 0, 0, 0, 0);
    }

    /**
     * Fit a component to the AnchorPane with a margin
     * @param component the component to fit
     * @param top top margin
     * @param right right margin
     * @param bottom bottom margin
     * @param left left margin
     */
    protected void setAnchorOfComponent (Region component, double top, double right, double bottom, double left)
    {
        AnchorPane.setTopAnchor(component, top);
        AnchorPane.setBottomAnchor(component, bottom);
        AnchorPane.setLeftAnchor(component, left);
        AnchorPane.setRightAnchor(component, right);
    }

    /**
     * Set the percentage size of a table column
     * @param column the column
     * @param percentageWidth the width in percentage
     */
    protected void setSizeOfColumnInTable (TableColumn column, double percentageWidth)
    {
        setSizeOfColumnInTable(column, percentageWidth, 0);
    }

    /**
     * Set the percentage size of table column
     * @param column the column
     * @param percentageWidth the width in percentage
     * @param toRemove pixels to remove
     */
    protected void setSizeOfColumnInTable (TableColumn column, double percentageWidth, double toRemove)
    {
        column.setPrefWidth((Template.CONTENT_WIDTH - toRemove) * percentageWidth / 100);
    }

    /**
     * Add components to the view
     * @param regions the components to add to the view
     */
    public void addComponents (Region... regions)
    {
        this.getChildren().addAll(regions);
    }
}
