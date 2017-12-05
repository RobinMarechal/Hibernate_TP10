import controllers.FilmController;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main extends Application
{
    FilmController tankController;

    public static void main (String[] args)
    {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp_note_vende_marechal");
        EntityManager        em  = emf.createEntityManager();
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
