/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.service.impl;

import fr.utbm.school.core.entity.Location;
import fr.utbm.school.core.dao.EntityLocationDao;
import fr.utbm.school.core.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private EntityLocationDao entityLocationDao;

    @Cacheable(cacheNames = "locationCache", key = "#idLocation")
    public Location searchLocationById(Long idLocation){
        return entityLocationDao.getLocationById(idLocation);
    }

    @Cacheable(cacheNames = "locationCache")
    public ArrayList<Location> getListLocation(){
        return entityLocationDao.getListLocation();
    }

    @CachePut(value = "locationCache", key = "#location.id")
    public Location saveLocation(@Valid Location location){
        return entityLocationDao.save(location);
    }

    @CacheEvict(value = "locationCache", allEntries = true)
    public Location updateLocation(@Valid Location location){
        return entityLocationDao.update(location);
    }
}
