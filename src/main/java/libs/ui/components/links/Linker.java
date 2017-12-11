package libs.ui.components.links;

public interface Linker<IDType>
{
    void prepareCellFactory ();

    void redirect (IDType id);
}
