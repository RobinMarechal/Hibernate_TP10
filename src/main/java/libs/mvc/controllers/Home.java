package libs.mvc.controllers;

import libs.ui.template.Template;
import libs.ui.template.nav.NavbarItem;

/**
 * An application's home
 */
public interface Home
{
    /**
     * Show the data's home
     */
    void home();

    /**
     * Get the associated navbar item
     * @return the associated navbar item
     */
    NavbarItem getAssociatedNavbarItem();

    /**
     * Select the associated navbar item
     */
    default void selectNabarItem()
    {
        NavbarItem item = getAssociatedNavbarItem();
        Template.instance.setSelectedNavbarItem(item);
    }
}
