package libs.ui.template;

import app.controllers.MovieController;
import app.controllers.PlaceController;
import app.controllers.ProducerController;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import libs.ui.template.nav.NavbarItem;
import libs.ui.template.nav.NavbarItemList;

import java.net.URL;

/**
 * @author Utilisateur
 * @date 05/07/2017
 */
public class Template extends Scene
{
    private static Template instance = new Template();

    public static Template getInstance ()
    {
        return instance;
    }

    private BorderPane layout;

    private VBox navbar;
    private ScrollPane contentPane;
    private MenuBar menubar;

    private NavbarItemList navbarItems;
    private NavbarItem selectedNavbarItem;

    public final static int CONTENT_WIDTH = 600;
    public final static int NAVBAR_WIDTH = 200;
    public final static int HEIGHT = 350;

    private Template ()
    {
        super(new Pane());

        layout = new BorderPane();
        layout.setPrefWidth(CONTENT_WIDTH + NAVBAR_WIDTH);
        layout.setPrefHeight(HEIGHT + 18.33);
        layout.getStyleClass().add("bg-almost-white");
        this.setRoot(layout);

        displayNavbar();
        displayContentPane();

        URL jbootx = Template.class.getResource("/css/jbootx.css");
        if (jbootx != null) {
            getStylesheets().add(jbootx.toString());
        }

        URL style = Template.class.getResource("/css/style.css");
        if (style != null) {
            getStylesheets().add(style.toString());
        }
    }

    private void displayNavbar ()
    {
        navbar = new VBox();
        navbar.setPrefWidth(NAVBAR_WIDTH);
        navbar.getStyleClass().add("navbar");

        navbarItems = new NavbarItemList();

        //        navbarItems.add(new NavbarItem("Home", new MainController()));
        navbarItems.add(new NavbarItem("Movies", new MovieController()));
        navbarItems.add(new NavbarItem("Producers", new ProducerController()));
        navbarItems.add(new NavbarItem("Places", new PlaceController()));

        navbarItems.sort();

        navbar.getChildren().addAll(navbarItems);

        navbar.getStyleClass().add("bg-almost-white");

        layout.setLeft(navbar);
    }

    private void displayContentPane ()
    {
        contentPane = new ScrollPane();
        layout.setCenter(contentPane);

        contentPane.setFitToWidth(true);
        contentPane.setFitToHeight(true);

        Pane p = new Pane();
        p.getStyleClass().add("bg-almost-white");
        setView(p);
    }

    public void setView (Pane view)
    {
        contentPane.setContent(view);
    }

    public void setSelectedNavbarItem (NavbarItem selectedNavbarItem)
    {
        if (this.selectedNavbarItem != null) {
            this.selectedNavbarItem.getStyleClass().remove("navbar-item-selected");
        }

        this.selectedNavbarItem = selectedNavbarItem;
        this.selectedNavbarItem.getStyleClass().add("navbar-item-selected");
    }

    public NavbarItem getSelectedNavbarItem ()
    {
        return selectedNavbarItem;
    }

    public void selectFirstNavbarItem ()
    {
        setSelectedNavbarItem(navbarItems.get(0));
    }
}