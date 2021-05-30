/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.dao.impl;

import fr.utbm.school.core.dao.EntityClientDao;
import fr.utbm.school.core.entity.Client;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;


/**
 *  Implementation of the DAO (Data Access Object) of
 *  {@link Client}
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
@Log4j
@Repository
@Transactional
public class EntityClientDaoImpl implements EntityClientDao {

    @PersistenceContext
    private EntityManager entityManager ;

    /**
     * Method to get client by his id
     *
     * @param clientId of the client wanted
     * @return The client with the given id
     */
    public Client getClientById(@NonNull Long clientId){
        log.info("Client requested for the id=" + String.valueOf(clientId));
        return entityManager.find(Client.class, clientId);
    }

    /**
     * Method to get client by his id
     *
     * @param userEmail of the client wanted
     * @return The client with the given id
     */
    public ArrayList<Client> searchClientByEmail(@NonNull String userEmail){
        log.info("Client with the email=" + userEmail + " requested");

        // Create the query
        Query query = entityManager.createQuery("FROM Client CLI WHERE CLI.email = :emailUser");
        query.setParameter("emailUser", userEmail);

        // Get the result of the query
        ArrayList<Client> clientList = new ArrayList<Client>();
        clientList = (ArrayList<Client>) query.getResultList();

        log.info(String.valueOf(clientList.size()) + " clients found");

        return clientList;
    }

    /**
     * Method to get list of all client
     *
     * @return list of all client
     */
    public ArrayList<Client> getListClient(){
        log.info("List of all Client requested");
        ArrayList<Client> listClient = new ArrayList<Client>();

        // Create the query
        Query q = entityManager.createQuery("from Client");
        listClient = (ArrayList<Client>) q.getResultList();

        return listClient;
    }

    /**
     * Method to get list of client registered to a course session
     *
     * @param courseSessionId of session
     * @return list of client registered to the given session
     */
    public ArrayList<Client> getListClientRegisterCourseSession(@NonNull Long courseSessionId){

        log.info("List of Client registered to the course session with the id=" +
                courseSessionId + " requested");

        ArrayList<Client> listClient = new ArrayList<Client>();

        // Create the query
        Query q = entityManager.createQuery("from Client cli where cli.courseSession.id = :courseSessionId");
        q.setParameter("courseSessionId", courseSessionId);

        // Get the result of the query
        listClient = (ArrayList<Client>) q.getResultList();
        return listClient;
    }

    /**
     * Method to save a client
     *
     * @param client to save to JavaDB
     * @return the client saved
     */
     public Client save(@NonNull Client client) {
        log.info("The client " + client.toString() + " requested to be saved");

        entityManager.persist(client);

        return client;
    }

    /**
     * Method to update a client
     *
     * @param client to update to JavaDB
     * @return the client updated
     */
    public Client update(@NonNull Client client) {
        log.info("The client " + client.toString() + " requested to be updated");

        entityManager.merge(client);

        return client;
    }

    /**
     * Method to delete a client
     *
     * @param client to delete of JavaDB
     * @return the client deleted
     */
    public Client delete(@NonNull Client client){
        log.info("The client " + client.toString() + " requested to be deleted");

        entityManager.remove(client);

        return client;
    }
}
