/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.service;

import fr.utbm.school.core.entity.Client;
import fr.utbm.school.core.exceptions.ClientException;
import java.util.ArrayList;

/**
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
public interface ClientService {
    Client searchClientById(Long idClient);

    ArrayList<Client> searchClientByEmail(String email);

    ArrayList<Client> getListClient();

    ArrayList<Client> getListClientRegisterCourseSession(Long courseSessionId);

    Client saveClient(Client client) throws ClientException;

    Client updateClient(Client client);
}
