package libs.ui.template;

import app.controllers.MovieController;
import app.controllers.PlaceController;
import app.controllers.ProducerController;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
    public final static Template instance = new Template();

    public final NavbarItem moviesNavbarItem = new NavbarItem("Movies", new MovieController());
    public final NavbarItem producersNavbarItem = new NavbarItem("Producers", new ProducerController());
    public final NavbarItem placesNavbarItem = new NavbarItem("Places", new PlaceController());

    public static Template getInstance ()
    {
        return instance;
    }

    private BorderPane layout;

    private VBox navbar;
    private ScrollPane contentPane;
    private MenuBar menuBar;

    private NavbarItemList navbarItems;
    private NavbarItem selectedNavbarItem;

    public final static int CONTENT_WIDTH = 750;
    public final static int NAVBAR_WIDTH = 200;
    public final static int HEIGHT = 400;

    private Template ()
    {
        super(new Pane());

        layout = new BorderPane();
        layout.setPrefWidth(CONTENT_WIDTH + NAVBAR_WIDTH - 5);
        layout.setPrefHeight(HEIGHT + 18.33);
        layout.getStyleClass().add("bg-almost-white");
        this.setRoot(layout);

        displayNavbar();
        displayContentPane();


        initMenuBar();

        // Addition of the menubar
        Pane menuBarPane = new Pane();
        menuBarPane.setMaxHeight(30);
        menuBarPane.getStyleClass().add("bg-transparent");
        menuBarPane.getChildren().add(menuBar);
        layout.setTop(menuBarPane);

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
        navbarItems.add(moviesNavbarItem);
        navbarItems.add(producersNavbarItem);
        navbarItems.add(placesNavbarItem);

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

    private void initMenuBar ()
    {
        // Creation of the menu items
        // Add...
        MenuItem itemAddMovie   = new MenuItem("Movie            ");
        MenuItem itemAddPlace = new MenuItem("Place            ");
        MenuItem itemAddProducer = new MenuItem("Producer            ");

        // Preparation of the events
        itemAddMovie.setOnAction(event -> new MovieController().showCreationDialog());
        itemAddPlace.setOnAction(event -> new PlaceController().showCreationDialog());
        itemAddProducer.setOnAction(event -> new ProducerController().showCreationDialog());

        // Creation of the menus
        Menu menuAdd = new Menu("Create..");
        menuAdd.getItems().addAll(itemAddMovie, itemAddPlace, itemAddProducer);

        // We finally add all menus to the menubar
        menuBar = new MenuBar(menuAdd);
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