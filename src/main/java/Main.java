/**
 * @author Pierre Vendé
 * @author Robin Marechal
 * Sujet 3
 */

import app.controllers.MovieController;
import app.models.Movie;
import app.models.Producer;
import javafx.application.Application;
import javafx.stage.Stage;
import libs.EntityManagerHolder;
import libs.ui.template.Template;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main extends Application
{
    MovieController tankController;


    public static void main (String[] args)
    {
        launch(args);
    }

    @Override
    public void start (Stage window) throws Exception
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp_note_vende_marechal");
        EntityManager        em  = emf.createEntityManager();

        EntityManagerHolder.setEntityManager(em);

        testData();

        window.setResizable(false);
        window.setTitle("TP NOTE - Pierre Vendé & Robin Maréchal");
        window.setOnCloseRequest(event -> System.exit(0));
        window.setScene(Template.getInstance());
        window.show();

        window.centerOnScreen();

        new MovieController().home();

        Template.getInstance().selectFirstNavbarItem();
    }

    public void testData ()
    {
        EntityManager em = EntityManagerHolder.getEntityManager();
        em.getTransaction().begin();


        Movie    movie    = new Movie("Bonsoir", "Nous");
        Movie    movie1   = new Movie("Salut", "Lui");
        Movie    movie2   = new Movie("Bonjour", "Moi");
        Producer producer = new Producer("John Doe");

        movie.setProducer(producer);

        em.persist(producer);
        em.persist(movie);
        em.persist(movie1);
        em.persist(movie2);

        em.getTransaction().commit();
    }

    private void run ()
    {

        //        tankController = new TankController(em);
        //
        //        Owner o1 = new Owner("Robin", "Ici");
        //        Tank  t  = new Tank();
        //        Tank  t2 = new Tank();
        //        Tank  t3 = new Tank();
        //        Tank  t4 = new Tank();
        //
        //        t.setCapacity(500);
        //        t.setMaterial("Métal");
        //
        //        t2.setCapacity(300);
        //        t2.setMaterial("Chrome");
        //
        //        t3.setCapacity(800);
        //        t3.setMaterial("Verre");
        //
        //        t4.setCapacity(800);
        //        t4.setMaterial("PVC");
        //
        //        em.getTransaction().begin();
        //
        //        em.persist(o1);
        //
        //        em.persist(t);
        //        em.persist(t2);
        //        em.persist(t3);
        //        em.persist(t4);
        //
        //        o1.addPhoneNumber("0662119806");
        //
        //        em.getTransaction().commit();
        //
        //
        //        tankController.showAll();
        //
        //        tankController.show(2);
        //        tankController.show(3);
        //
        //        System.out.println(o1);
        //
        //        System.out.println("Tests Query<T>: ");
        //
        //        System.out.println("All: ");
        //
        //        Query.forModel(Tank.class, em).get().forEach(System.out::println);
        //
        //        System.out.println("Using Query<T>: ");
        //
        //        List<Tank> tanks = Query.forModel(Tank.class, em)
        //                                .whereGreaterOrEqual("capacity", 500)
        //                                .whereDifferent("material", "PVC")
        //                                .whereDifferent("material", "Verre")
        //                                .get();
        //
        //        tanks.forEach(System.out::println);
        //
        //        System.out.println("2");
        //
        //        List<Tank> tanks2 = Query.forModel(Tank.class, em)
        //                                 .where("capacity", Operator.GREATER_EQUAL, 500)
        //                                 .where("material", Operator.DIFFERENT, "PVC")
        //                                 .where("material", Operator.DIFFERENT, "VERRE")
        //                                 .get();
        //
        //        tanks2.forEach(System.out::println);
    }
}
