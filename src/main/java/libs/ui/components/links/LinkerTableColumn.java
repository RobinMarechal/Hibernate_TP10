package libs.ui.components.links;

import app.models.pk.ClapPrimaryKey;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.Tooltip;
import libs.mvc.controllers.Controller;
import libs.mvc.models.Model;

import java.io.Serializable;

public class LinkerTableColumn<ModelType extends Model, FieldType, PKType extends Serializable> extends TableColumn<ModelType, FieldType> implements ControllerLinker<PKType>
{
    private RedirectType redirectType = RedirectType.SHOW_ONE;
    private Controller targetController;

    public LinkerTableColumn (Controller targetController)
    {
        super();
        this.targetController = targetController;

        this.getStyleClass().add("link");
    }

    public LinkerTableColumn (Controller targetController, RedirectType redirectType)
    {
        this(targetController);
        this.redirectType = redirectType;
    }

    @Override
    public void prepareEventHandler ()
    {
        this.setCellFactory(tableCell -> {
            TableCell<ModelType, FieldType> cell = new TableCell<ModelType, FieldType>()
            {

                @Override
                protected void updateItem (FieldType item, boolean empty)
                {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText("");
                        setTooltip(null);
                    }
                    else {
                        setText(item.toString());

                        TableRow tableRow = this.getTableRow();
                        setTooltip(new Tooltip("Click here to see more details about this data"));

                        if (tableRow != null) {
                            ModelType model = ((ModelType) tableRow.getItem());
                            this.setOnMouseClicked(event -> redirect((PKType) model.getId()));
                        }
                        else {
                            this.setOnMouseClicked(event -> redirect(null));
                        }
                    }
                }
            };

            return cell;
        });
    }

    public RedirectType getRedirectType ()
    {
        return redirectType;
    }

    public void setRedirectType (RedirectType redirectType)
    {
        this.redirectType = redirectType;
    }

    public Controller getTargetController ()
    {
        return targetController;
    }

    public void setTargetController (Controller targetController)
    {
        this.targetController = targetController;
    }

    @Override
    public void redirect (PKType id)
    {
        if (id == null) {
            return;
        }

        if (id instanceof Integer && (Integer) id == -1) {
            return;
        }

        if (id instanceof ClapPrimaryKey && ((ClapPrimaryKey) id).getSetup() == null) {
            return;
        }

        if (redirectType == RedirectType.SHOW_ALL) {
            targetController.showAll();
        }
        else {
            targetController.show(id);
        }
    }
}
