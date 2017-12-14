package app.models.dao;

import app.models.*;
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
            //            hql = "from Scene s inner join s.movie ON s.movie.id = :movieId inner join s.place p where type(p) = Theatre";
            hql = "from Theatre p inner join p.scenes s inner join s.movie where s.movie.id = :movieId";
        }
        else {
            //            hql = "from Scene s inner join s.movie ON s.movie.id = :movieId inner join s.place p where type(p) = ExternalPlace";
            hql = "from ExternalPlace p inner join p.scenes s inner join s.movie where s.movie.id = :movieId";
        }

        List<Object[]> results = em.createQuery(hql).setParameter("movieId", id).getResultList();
        List<Scene>    scenes  = new ArrayList<>();

        for (Object[] result : results) {
            Scene scene = (Scene) result[1];

            if (placeType == PlaceType.EXTERNAL_PLACE) {
                ExternalPlace place = (ExternalPlace) result[0];
                scene.setPlace(place, scene.getDayTime());
            }
            else {
                Theatre place = (Theatre) result[0];
                scene.setPlace(place);
            }

            scenes.add(scene);
        }

        return scenes;
    }

    public List<Scene> getScenesAtDayTime (Integer id, DayTime dayTime)
    {
        return em.createQuery("from Scene s where s.dayTime = :dayTime AND s.movie.id = :movieId")
                 .setParameter("dayTime", dayTime)
                 .setParameter("movieId", id)
                 .getResultList();
    }
}
