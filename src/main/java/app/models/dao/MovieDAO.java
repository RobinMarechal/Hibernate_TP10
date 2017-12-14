package app.models.dao;

import app.models.Movie;
import app.models.Place;
import app.models.Scene;
import libs.DayTime;
import libs.PlaceType;
import libs.mvc.models.ModelDAO;

import java.util.ArrayList;
import java.util.List;

public class MovieDAO extends ModelDAO<Movie, Integer>
{
    @Override
    public Class<Movie> getModelClazz ()
    {
        return Movie.class;
    }


    public List<Scene> getScenesAtPlaceType (Integer id, PlaceType placeType)
    {
        String hql;
        if (placeType == PlaceType.THEATRE) {
            hql = "FROM Scene s INNER JOIN s.movie ON s.movie.id = 2 INNER JOIN s.place p WHERE p.class = Theatre";
        }
        else {
            hql = "FROM Scene s INNER JOIN s.movie ON s.movie.id = 2 INNER JOIN s.place p WHERE p.class = ExternalPlace";
        }

        List<Object[]> results = em.createQuery(hql).getResultList();
        List<Scene>    scenes  = new ArrayList<>();

        for (Object[] result : results) {
            Scene scene = (Scene) result[0]; // first in the query
            Place place = (Place) result[2]; // 3rd in the query
            // We don't care about the movie

            scene.setPlace(place);
            scenes.add(scene);
        }

        return scenes;
    }

    public List<Scene> getScenesAtDayTime (Integer id, DayTime dayTime)
    {
        return em.createQuery("FROM Scene s WHERE s.dayTime = :dayTime AND s.movie.id = :movieId")
                 .setParameter("dayTime", dayTime)
                 .setParameter("movieId", id)
                 .getResultList();
    }
}
