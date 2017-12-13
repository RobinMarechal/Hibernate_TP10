package app.views.places;

import app.controllers.PlaceController;
import fr.polytech.marechal.FieldValueType;
import fr.polytech.marechal.FormMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import libs.PlaceType;
import libs.ui.components.dialogs.Dialog;

public class CreatePlaceDialog extends Dialog
{
    private final PlaceController controller;
    private FormMap form;

    private Label dialogTitle;
    private VBox formBox;

    private Label nameLabel;
    private TextField nameField;
    private VBox nameFormGroup;

    private Label addressLabel;
    private TextField addressField;
    private VBox addressFormGroup;

    private Label insideOutsideLabel;
    private ComboBox<PlaceType> insideOutsideField;
    private VBox insideOutsideFormGroup;

    private Label descriptionLabel;
    private TextArea descriptionField;
    private VBox descriptionFormGroup;

    private Button submit;
    private VBox submitFormGroup;

    /**
     * Default constructor
     */
    public CreatePlaceDialog (PlaceController controller)
    {
        super();
        this.controller = controller;

        this.sizeToScene();

        formBox = new VBox(20);
        formBox.getStyleClass().add("form");
        formBox.setPadding(new Insets(20, 40, 20, 40));
        addStylesheetTo(formBox);

        dialogTitle = new Label("Creation of a place");
        dialogTitle.getStyleClass().add("h2");
        dialogTitle.setMaxWidth(Double.MAX_VALUE);
        dialogTitle.setAlignment(Pos.CENTER);

        nameLabel = new Label("Name: ");
        nameField = new TextField();
        nameFormGroup = new VBox();
        nameFormGroup.getChildren().addAll(nameLabel, nameField);

        addressLabel = new Label("Address: ");
        addressField = new TextField();
        addressFormGroup = new VBox();
        addressFormGroup.getChildren().addAll(addressLabel, addressField);

        insideOutsideLabel = new Label("Type: ");
        insideOutsideField = new ComboBox<>();
        insideOutsideField.setPromptText("Select a type...");
        insideOutsideField.setMaxWidth(Double.MAX_VALUE);
        insideOutsideField.getItems().addAll(PlaceType.THEATRE, PlaceType.EXTERNAL_PLACE);
        insideOutsideFormGroup = new VBox(insideOutsideLabel, insideOutsideField);

        descriptionLabel = new Label("Description: ");
        descriptionField = new TextArea();
        descriptionField.setPrefRowCount(5);
        descriptionFormGroup = new VBox();
        descriptionField.setPromptText("Select a description...");
        descriptionFormGroup.getChildren().addAll(descriptionLabel, descriptionField);

        submit = new Button("Submit");
        submitFormGroup = new VBox();
        submitFormGroup.setAlignment(Pos.CENTER);
        submitFormGroup.getChildren().add(submit);
        submitFormGroup.getStyleClass().add("submit-form-group");

        formBox.getChildren().addAll(dialogTitle, nameFormGroup, addressFormGroup, insideOutsideFormGroup, descriptionFormGroup, submitFormGroup);

        setup();

        setContent(formBox);
    }

    public void setup ()
    {
        form = new FormMap();

        form.add("name", FieldValueType.VARCHAR, nameField, true);
        form.add("address", FieldValueType.VARCHAR, addressField, true);
        form.add("type", FieldValueType.UNDEFINED, insideOutsideField, true);
        form.add("description", FieldValueType.VARCHAR, descriptionField);
        form.setSubmitButton(submit);

        form.setOnSubmit(event -> controller.create(form));
    }
}
