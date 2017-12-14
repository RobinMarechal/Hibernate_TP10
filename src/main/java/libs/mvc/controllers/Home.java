package libs.mvc.controllers;

import libs.ui.template.Template;
import libs.ui.template.nav.NavbarItem;

public interface Home
{
    void home();

    NavbarItem getAssociatedNavbarItem();

    default void selectNabarItem()
    {
        NavbarItem item = getAssociatedNavbarItem();
        Template.instance.setSelectedNavbarItem(item);
    }
}
