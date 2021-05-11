/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.Dao;

import fr.utbm.school.core.entity.Client;
import java.util.ArrayList;

/**
 *
 * @author neil
 */
public interface EntityClientDao {


    public ArrayList<Client> searchClientByEmail(String email);

    public Client getClientById(Long clientId);

    public ArrayList<Client> getListClient();

    public ArrayList<Client> getListClientRegisterCourseSession(Long courseSessionId);

     public void save(Client client);

    public void update(Client client);
}
