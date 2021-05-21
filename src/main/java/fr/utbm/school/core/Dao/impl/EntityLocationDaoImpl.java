/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.Dao.impl;

import fr.utbm.school.core.Dao.EntityLocationDao;
import fr.utbm.school.core.entity.Location;
import org.apache.log4j.Logger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
@Repository
@Transactional
public class EntityLocationDaoImpl implements EntityLocationDao {
    private static final Logger logger = Logger.getLogger(EntityLocationDao.class.getName());

    @PersistenceContext
    private EntityManager entityManager ;

    public Location getLocationById(Long locationId){

        logger.info("Location with id=" + locationId + " is requested");

        return entityManager.find(Location.class, locationId);
    }

    public ArrayList<Location> getListLocation(){
        logger.info("All location have been requested");

        ArrayList<Location> listLocation = new ArrayList<Location>();
        Query q = entityManager.createQuery("from Location");
        listLocation = (ArrayList<Location>) q.getResultList();

        logger.info(listLocation.size() + " Location(s) returned");

        return listLocation;
    }

    public Location save(Location location) {
        logger.info("Location : " + location.toString() + " requested to be saved");

        assert location != null : "Null object can't be saved";
        entityManager.persist(location);

        logger.info("Location : " + location.toString() + " have been saved");

        return location;
    }

    public Location update(Location location) {
        logger.info("Location : " + location.toString() + " requested to be deleted");

        assert location != null : "Null object can't be merged";
        entityManager.merge(location);

        logger.info("Location : " + location.toString() + " have been deleted");

        return location;
    }
}
