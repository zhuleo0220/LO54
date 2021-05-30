/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.dao.impl;

import fr.utbm.school.core.dao.EntityLocationDao;
import fr.utbm.school.core.entity.Location;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 * Implementation of the DAO (Data Access Object) of
 * {@link Location}
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
@Log4j
@Repository
@Transactional
public class EntityLocationDaoImpl implements EntityLocationDao {

    @PersistenceContext
    private EntityManager entityManager ;

    /**
     * Method to get a location by his id
     *
     * @param locationId to search for
     * @return location with the corresponding id
     */
    public Location getLocationById(Long locationId){

        log.info("Location with id=" + locationId + " is requested");

        return entityManager.find(Location.class, locationId);
    }

    /**
     *  Method to get list of all location
     *
     * @return
     */
    public ArrayList<Location> getListLocation(){
        log.info("All location have been requested");

        ArrayList<Location> listLocation = new ArrayList<Location>();

        // Create the query
        Query q = entityManager.createQuery("from Location LCT order by LCT.city");

        // Get the result of the query
        listLocation = (ArrayList<Location>) q.getResultList();

        log.info(listLocation.size() + " Location(s) returned");

        return listLocation;
    }

    /**
     * Methdo to save a location
     *
     * @param location to save
     * @return saved location
     */
    public Location save(@NonNull Location location) {
        log.info("Location : " + location.toString() + " requested to be saved");

        entityManager.persist(location);

        log.info("Location : " + location.toString() + " have been saved");

        return location;
    }

    /**
     * Method to update the location
     *
     * @param location to update
     * @return updated location
     */
    public Location update(@NonNull Location location) {
        log.info("Location : " + location.toString() + " requested to be deleted");

        entityManager.merge(location);

        log.info("Location : " + location.toString() + " have been deleted");

        return location;
    }
}
