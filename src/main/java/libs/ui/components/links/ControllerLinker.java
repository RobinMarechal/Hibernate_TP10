package libs.ui.components.links;

public interface ControllerLinker<IDType>
{
    void prepareEventHandler ();

    void redirect (IDType id);
}
