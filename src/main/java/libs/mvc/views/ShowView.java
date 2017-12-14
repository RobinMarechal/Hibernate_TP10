package libs.mvc.views;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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

    public ShowView (CType controller, MType model)
    {
        super(controller);
        this.model = model;

        borderPane = new BorderPane();

        topLeftVBox = new VBox();

        topRightVbox = new VBox(10);
        deleteBtn = new Button("Delete");
        updateBtn = new Button("Update");
        showDetailsBtn = new Button("More details");


        topHbox = new HBox(topLeftVBox, topRightVbox);
        topHbox.setAlignment(Pos.CENTER);
        topHbox.setPadding(new Insets(0, 0, 5, 0));

        setAnchorOfComponent(borderPane, 5, 5, 5, 5);

        topHbox.setAlignment(Pos.CENTER);
        setAnchorOfComponent(topHbox, 0, 0, 0, 0);
        borderPane.setTop(topHbox);

        borderPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            topHbox.setMaxWidth(newValue.doubleValue());
            topLeftVBox.setPrefWidth(newValue.intValue() / 2);
            topRightVbox.setPrefWidth(newValue.intValue() / 2);
        });

        this.addComponents(borderPane);

        setupButtons();
        prepareEventHandlers();
    }

    private void prepareEventHandlers ()
    {
        deleteBtn.setOnAction(event -> controller.delete(model));
        updateBtn.setOnAction(event -> controller.showUpdateDialog(model));
        showDetailsBtn.setOnAction(event -> controller.showDetails(model));
    }

    public void setupButtons ()
    {
        ObservableList<Node> rightChildren = topRightVbox.getChildren();

        rightChildren.add(showDetailsBtn);
        rightChildren.add(updateBtn);
        rightChildren.add(deleteBtn);

        topRightVbox.setAlignment(Pos.CENTER);
        topRightVbox.setMaxHeight(Double.MAX_VALUE);

        showDetailsBtn.setPrefWidth(150);
        updateBtn.setPrefWidth(150);
        deleteBtn.setPrefWidth(150);
    }
}
