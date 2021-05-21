/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.Dao;

import fr.utbm.school.core.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.core.CrudMethods;

import java.util.ArrayList;

/**
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
public interface EntityClientDao {


    ArrayList<Client> searchClientByEmail(String email);

    Client getClientById(Long clientId);

    ArrayList<Client> getListClient();

    ArrayList<Client> getListClientRegisterCourseSession(Long courseSessionId);

    Client save(Client client);

    Client update(Client client);

    Client delete(Client client);
}
