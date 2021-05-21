/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.Dao;

import fr.utbm.school.core.entity.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
public interface EntityLocationDao {

    Location getLocationById(Long locationId);

    ArrayList<Location> getListLocation();

    Location save(Location location) ;

    Location update(Location location) ;
}
