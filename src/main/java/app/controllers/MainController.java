package app.controllers;

import libs.mvc.Controller;
import libs.mvc.Home;

import java.io.Serializable;

public class MainController extends Controller implements Home
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
    public void show (Serializable id)
    {

    }

    @Override
    public void showAll ()
    {

    }
}
