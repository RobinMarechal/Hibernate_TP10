package app.views.producers;

import app.controllers.ProducerController;
import app.models.Producer;
import fr.polytech.marechal.FieldValueType;
import fr.polytech.marechal.FormMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import libs.ui.components.dialogs.Dialog;

public class CreateProducerDialog extends Dialog
{
    private final ProducerController controller;
    private final Producer producer;
    private FormMap form;

    private Label dialogTitle;
    private VBox formBox;

    private Label nameLabel;
    private TextField nameField;
    private VBox nameFormGroup;

    private Button submit;
    private VBox submitFormGroup;

    /**
     * Default constructor
     */
    public CreateProducerDialog (ProducerController controller, Producer producer)
    {
        super();
        this.producer = producer;
        this.controller = controller;

        this.sizeToScene();

        formBox = new VBox(20);
        formBox.getStyleClass().add("form");
        formBox.setPadding(new Insets(20, 40, 20, 40));
        addStylesheetTo(formBox);

        dialogTitle = new Label((producer == null ? "Creation of a producer" : "Update producer"));
        dialogTitle.getStyleClass().add("h2");
        dialogTitle.setMaxWidth(Double.MAX_VALUE);
        dialogTitle.setAlignment(Pos.CENTER);

        nameLabel = new Label("Name: ");
        nameField = new TextField();
        nameFormGroup = new VBox();
        nameFormGroup.getChildren().addAll(nameLabel, nameField);

        submit = new Button("Submit");
        submitFormGroup = new VBox();
        submitFormGroup.setAlignment(Pos.CENTER);
        submitFormGroup.getChildren().add(submit);
        submitFormGroup.getStyleClass().add("submit-form-group");

        formBox.getChildren().addAll(dialogTitle, nameFormGroup, submitFormGroup);

        setup();

        setContent(formBox);
    }

    public void setup ()
    {
        form = new FormMap();

        if(producer != null)
        {
            nameField.setText(producer.getName());
        }

        form.add("name", FieldValueType.NAME, nameField, true);
        form.setSubmitButton(submit);

        form.setOnSubmit(event -> controller.update(producer, form));
    }
}
