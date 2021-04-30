/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.service;

import fr.utbm.school.core.entity.Location;
import java.util.ArrayList;

/**
 *
 * @author Neil FARMER
 */
public interface LocationService {
    public Location searchLocationById(Long idLocation);

    public ArrayList<Location> getListLocation();

    public void saveLocation(Location location);

    public void updateLocation(Location location);
}
