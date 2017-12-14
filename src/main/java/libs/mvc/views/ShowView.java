package libs.mvc.views;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import libs.mvc.controllers.Controller;
import libs.mvc.models.Model;

public abstract class ShowView<MType extends Model, CType extends Controller> extends View<CType>
{
    protected MType model;
    protected BorderPane borderPane;

    protected HBox topHbox;

    protected VBox topRightVbox;
    protected Button deleteBtn;
    protected Button updateBtn;
    protected Button showDetailsBtn;

    protected VBox topLeftVBox;
    protected BorderPane topLeftBorderPane;

    public ShowView (CType controller, MType model)
    {
        super(controller);
        this.model = model;

        borderPane = new BorderPane();

        topLeftVBox = new VBox();
        topLeftBorderPane = new BorderPane();
        topLeftBorderPane.setTop(topLeftVBox);

        topRightVbox = new VBox(10);
        deleteBtn = new Button("Delete");
        updateBtn = new Button("Update");
        showDetailsBtn = new Button("More details");


        topHbox = new HBox(topLeftBorderPane, topRightVbox);
        topHbox.setAlignment(Pos.CENTER);
        topHbox.setPadding(new Insets(0, 0, 5, 0));

        setAnchorOfComponent(borderPane, 5, 5, 5, 5);

        topHbox.setAlignment(Pos.CENTER);
        setAnchorOfComponent(topHbox, 0, 0, 0, 0);
        borderPane.setTop(topHbox);

        borderPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            topHbox.setMaxWidth(newValue.doubleValue());
            topLeftVBox.setPrefWidth(newValue.intValue() / 2);
            topLeftBorderPane.setPrefWidth(newValue.intValue() / 2);
            topRightVbox.setPrefWidth(newValue.intValue() / 2);
        });

        this.addComponents(borderPane);

        setupButtons();
        prepareEventHandlers();
    }

    public void removeDetailsButton ()
    {
        topRightVbox.getChildren().remove(showDetailsBtn);
    }

    private void prepareEventHandlers ()
    {
        deleteBtn.setOnAction(event -> controller.showDeleteDialog(model));
        updateBtn.setOnAction(event -> controller.showUpdateDialog(model));
        showDetailsBtn.setOnAction(event -> controller.showDetails(model));
    }

    protected void setTopLeftComponents (Region bottom, Region... tops)
    {
        ObservableList<Node> children = topLeftVBox.getChildren();

        topLeftBorderPane.setBottom(null);
        children.clear();

        if (bottom != null) {
            topLeftBorderPane.setBottom(bottom);
        }

        if (tops != null && tops.length > 0) {
            children.addAll(tops);
        }
    }

    public void setupButtons ()
    {
        ObservableList<Node> rightChildren = topRightVbox.getChildren();

        rightChildren.add(showDetailsBtn);
        rightChildren.add(updateBtn);
        rightChildren.add(deleteBtn);

        updateBtn.setPrefWidth(150);
        deleteBtn.setPrefWidth(150);
        showDetailsBtn.setPrefWidth(150);

        topRightVbox.setAlignment(Pos.CENTER);
        topRightVbox.setMaxHeight(Double.MAX_VALUE);

    }
}
