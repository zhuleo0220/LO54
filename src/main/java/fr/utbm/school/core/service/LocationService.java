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
    Location searchLocationById(Long idLocation);

    ArrayList<Location> getListLocation();

    Location saveLocation(Location location);

    Location updateLocation(Location location);
}
