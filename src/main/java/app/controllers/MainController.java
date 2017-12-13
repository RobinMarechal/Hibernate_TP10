package app.controllers;

import libs.mvc.controllers.Controller;
import libs.mvc.controllers.Home;

public class MainController extends Controller<Integer> implements Home
{
    public MainController ()
    {
        super();
    }

    @Override
    public void home ()
    {
        
    }

    @Override
    public void show (Integer id)
    {

    }

    @Override
    public void showAll ()
    {

    }
}
