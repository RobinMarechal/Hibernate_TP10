package app.models.dao;

import app.models.*;
import libs.DayTime;
import libs.PlaceType;
import libs.mvc.models.ModelDAO;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for the Movies
 */
public class MovieDAO extends ModelDAO<Movie, Integer>
{

    /**
     * Getter for the Model's class
     *
     * @return the model's class
     */
    @Override
    public Class<Movie> getModelClazz ()
    {
        return Movie.class;
    }


    /**
     * Retrieve the scenes of a movie that take place at a type of place (Theatre or External Place)
     * @param movieId the id of the movie
     * @param placeType the type of place
     * @return a list of records
     */
    public List<Scene> getScenesAtPlaceType (Integer movieId, PlaceType placeType)
    {
        String hql;
        if (placeType == PlaceType.THEATRE) {
            hql = "from Theatre p inner join p.scenes s inner join s.movie where s.movie.id = :movieId";
        }
        else {
            hql = "from ExternalPlace p inner join p.scenes s inner join s.movie where s.movie.id = :movieId";
        }

        List<Object[]> results = em.createQuery(hql).setParameter("movieId", movieId).getResultList();
        List<Scene>    scenes  = new ArrayList<>();

        for (Object[] result : results) {
            Scene scene = (Scene) result[1];

            Object unproxied = Hibernate.unproxy(result[0]);

            if (placeType == PlaceType.EXTERNAL_PLACE) {
                ExternalPlace place = (ExternalPlace) unproxied;
                scene.setPlace(place, scene.getDayTime());
            }
            else {
                Theatre place = (Theatre) unproxied;
                scene.setPlace(place);
            }

            scenes.add(scene);
        }

        return scenes;
    }

    /**
     * Retrieve the scenes of a movie that take place at a specific day time
     * @param movieId the id of the movie
     * @param dayTime the day time
     * @return a list of records
     */
    public List<Scene> getScenesAtDayTime (Integer movieId, DayTime dayTime)
    {
        return em.createQuery("from Scene s where s.dayTime = :dayTime AND s.movie.id = :movieId")
                 .setParameter("dayTime", dayTime)
                 .setParameter("movieId", movieId)
                 .getResultList();
    }
}
