package libs.ui.template.nav;

import libs.Helpers;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Utilisateur
 * @date 05/07/2017
 */
public class NavbarItemList extends ArrayList<NavbarItem>
{
    public void sort ()
    {
        Comparator<? super NavbarItem> comparator = new Comparator<NavbarItem>() {
            @Override
            public int compare (NavbarItem b1, NavbarItem b2)
            {
                String s1 = Helpers.stripAccents(b1.getText());
                String s2 = Helpers.stripAccents(b2.getText());

                return s1.compareTo(s2);
            }
        };

        super.sort(comparator);
    }
}