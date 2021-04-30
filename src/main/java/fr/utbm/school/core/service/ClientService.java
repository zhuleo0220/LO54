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
 * @author Neil FARMER
 */
public interface ClientService {
    public Client searchClientById(Long idClient);

    public ArrayList<Client> getListClient();

    public ArrayList<Client> getListClientRegisterCourseSession(Long courseSessionId);

    public void saveClient(Client client) throws ClientException;

    public void updateClient(Client client);
}
