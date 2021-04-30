/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.service.impl;

import fr.utbm.school.core.entity.Location;
import fr.utbm.school.core.Dao.EntityLocationDao;
import fr.utbm.school.core.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 *
 * @author Neil FARMER
 */
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private EntityLocationDao entityLocationDao;

    public Location searchLocationById(Long idLocation){
        return entityLocationDao.getLocationById(idLocation);
    }

    public ArrayList<Location> getListLocation(){
        return entityLocationDao.getListLocation();
    }

    public void saveLocation(Location location){
        entityLocationDao.save(location);
    }

    public void updateLocation(Location location){
        entityLocationDao.update(location);
    }
}
