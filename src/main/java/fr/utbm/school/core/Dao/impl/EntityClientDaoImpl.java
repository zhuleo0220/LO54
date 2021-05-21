/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.Dao.impl;

import fr.utbm.school.core.Dao.EntityClientDao;
import fr.utbm.school.core.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import org.apache.log4j.Logger;


/**
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
@Repository
@Transactional
public class EntityClientDaoImpl implements EntityClientDao {

    private static final Logger logger = Logger.getLogger(EntityClientDao.class.getName());

    @PersistenceContext
    private EntityManager entityManager ;

    /**
     * Method to get client by his id
     * @param clientId
     * @return
     */
    public Client getClientById(Long clientId){
        logger.info("Client requested for the id=" + String.valueOf(clientId));
        return entityManager.find(Client.class, clientId);
    }

    /**
     * Method to get client by his id
     * @param userEmail
     * @return
     */
    public ArrayList<Client> searchClientByEmail(String userEmail){
        logger.info("Client with the email=" + userEmail + " requested");

        Query query = entityManager.createQuery("FROM Client CLI WHERE CLI.email = :emailUser");
        query.setParameter("emailUser", userEmail);

        ArrayList<Client> clientList = new ArrayList<Client>();
        clientList = (ArrayList<Client>) query.getResultList();

        logger.info(String.valueOf(clientList.size()) + " clients found");

        return clientList;
    }

    /**
     * Method to get list of all client
     * @return
     */
    public ArrayList<Client> getListClient(){
        logger.info("List of all Client requested");
        ArrayList<Client> listClient = new ArrayList<Client>();
        Query q = entityManager.createQuery("from Client");
        listClient = (ArrayList<Client>) q.getResultList();
        return listClient;
    }

    /**
     * Method to get list of client registered to a course session
     * @param courseSessionId
     * @return
     */
    public ArrayList<Client> getListClientRegisterCourseSession(Long courseSessionId){

        logger.info("List of Client registered to the course session with the id=" +
                courseSessionId + " requested");

        ArrayList<Client> listClient = new ArrayList<Client>();
        Query q = entityManager.createQuery("from Client cli where cli.courseSession.id = :courseSessionId");
        q.setParameter("courseSessionId", courseSessionId);
        listClient = (ArrayList<Client>) q.getResultList();
        return listClient;
    }

    /**
     * Method to save a client
     * @param client
     */
     public Client save(Client client) {
        logger.info("The client " + client.toString() + " requested to be saved");

        assert client != null : "Null object can't be saved";
        entityManager.persist(client);

        return client;
    }

    /**
     * Method to update a client
     * @param client
     */
    public Client update(Client client) {
        logger.info("The client " + client.toString() + " requested to be updated");

        assert client != null : "Null object can't be merged";
        entityManager.merge(client);

        return client;
    }

    /**
     * Method to delete a client
     * @param client
     * @return
     */
    public Client delete(Client client){
        logger.info("The client " + client.toString() + " requested to be deleted");

        assert client != null : "Null object can't be deleted";
        entityManager.remove(client);

        return client;
    }
}
