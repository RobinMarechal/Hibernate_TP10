package app.controllers;

import app.models.Producer;
import app.views.producers.AllProducersView;
import app.views.producers.ShowProducerView;
import libs.mvc.Controller;
import libs.mvc.Home;
import libs.mvc.View;

import java.io.Serializable;
import java.util.List;

public class ProducerController extends Controller implements Home
{

    public ProducerController ()
    {
        super();
    }

    @Override
    public void home ()
    {
        showAll();
    }

    @Override
    public void show (Serializable id)
    {
        Producer producer = em.find(Producer.class, id);
        View  view  = new ShowProducerView(this, producer);
        this.setTemplateView(view);
    }

    @Override
    public void showAll ()
    {
        List<Producer>   producers = (List<Producer>) em.createQuery("FROM Producer").getResultList();
        AllProducersView view   = new AllProducersView(this, producers);
        this.setTemplateView(view);
    }
}
