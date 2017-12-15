package libs.ui.components.dialogs;


import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import libs.ui.template.Template;

import java.net.URL;

/**
 * Created by Robin on 21/05/2017. <br>
 * This class represents a dialog box
 */
public abstract class Dialog extends javafx.stage.Stage
{
    /**
     * Default constructor
     */
    public Dialog ()
    {
//        initStyle(StageStyle.UTILITY);
//        prepareDialogEventListeners();
        setResizable(false);
    }

    protected void addStylesheetsTo (Pane pane)
    {
        URL jbootx = Template.class.getResource("/css/jbootx.css");
        if (jbootx != null) {
            pane.getStylesheets().add(jbootx.toString());
        }

        URL style = Template.class.getResource("/css/style.css");
        if (style != null) {
            pane.getStylesheets().add(style.toString());
        }
    }

    /**
     * Prepare dialog events listener like close on escape pressed, or close on focus loss
     */
    public void prepareDialogEventListeners ()
    {
        addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                close();
            }
        });

        focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                close();
            }
        });
    }

    /**
     * set the content and show the dialog
     *
     * @param content the inside pane
     */
    protected void setContent (Pane content)
    {
        if (content != null) {
            addStylesheetsTo(content);
            setScene(new Scene(content));
        }
    }
}