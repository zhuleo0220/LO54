/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.Dao.impl;

import fr.utbm.school.core.Dao.EntityLocationDao;
import fr.utbm.school.core.entity.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 *
 * @author neil
 */
@Repository
@Transactional
public class EntityLocationDaoImpl implements EntityLocationDao {
    @PersistenceContext
    private EntityManager entityManager ;

    public Location getLocationById(Long locationId){
        return entityManager.find(Location.class, locationId);
    }

    public ArrayList<Location> getListLocation(){
        ArrayList<Location> listLocation = new ArrayList<Location>();
        Query q = entityManager.createQuery("from Location");
        listLocation = (ArrayList<Location>) q.getResultList();
        return listLocation;
    }

    public void save(Location location) {
        entityManager.persist(location);
    }

    public void update(Location location) {
        entityManager.merge(location);
    }
}
