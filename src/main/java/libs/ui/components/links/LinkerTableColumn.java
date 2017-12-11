package libs.ui.components.links;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import libs.mvc.Controller;
import libs.mvc.Model;

import java.io.Serializable;

public class LinkerTableColumn<ModelType extends Model, FieldType, IDType extends Serializable> extends TableColumn<ModelType, FieldType> implements
        Linker<IDType>
{
    private RedirectType redirectType = RedirectType.SHOW_ONE;
    private Controller targetController;

    public LinkerTableColumn (Controller targetController)
    {
        super();
        this.targetController = targetController;

        this.getStyleClass().add("link");
    }

    @Override
    public void prepareCellFactory ()
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
                    }
                    else {
                        setText(item.toString());

                        TableRow tableRow = this.getTableRow();

                        if (tableRow != null) {
                            ModelType model = ((ModelType) tableRow.getItem());
                            this.setOnMouseClicked(event -> redirect(model.getId()));
                        }
                        else {
                            this.setOnMouseClicked(event -> redirect(-1));
                        }
                    }
                }
            };

            return cell;
        });
    }

    public LinkerTableColumn (Controller targetController, RedirectType redirectType)
    {
        this(targetController);
        this.redirectType = redirectType;
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
    public void redirect (Serializable id)
    {
        if (id instanceof Integer && (Integer) id == -1) {
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
