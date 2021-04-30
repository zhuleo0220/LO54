/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.Dao;

import fr.utbm.school.core.entity.Location;
import java.util.ArrayList;

/**
 *
 * @author neil
 */
public interface EntityLocationDao {

    public Location getLocationById(Long locationId);

    public ArrayList<Location> getListLocation();

    public void save(Location location) ;

    public void update(Location location) ;
}
