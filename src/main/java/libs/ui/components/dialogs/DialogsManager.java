package libs.ui.components.dialogs;

import java.util.ArrayList;
import java.util.List;

public class DialogsManager
{
    public final static DialogsManager instance = new DialogsManager();
    private List<Dialog> dialogs;

    private DialogsManager ()
    {
        dialogs = new ArrayList<>();
    }

    public void openDialog (Dialog dialog)
    {
        dialog.show();
        this.dialogs.add(dialog);
    }

    public List<Dialog> getOpenDialogsList ()
    {
        return dialogs;
    }

    public void closeLastOpened ()
    {
        if (!dialogs.isEmpty()) {
            int size = dialogs.size();
            dialogs.get(size - 1).close();
            dialogs.remove(size - 1);
        }
    }
}
