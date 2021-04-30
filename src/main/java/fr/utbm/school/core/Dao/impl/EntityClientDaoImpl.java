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

/**
 *
 * @author neil
 */
@Repository
@Transactional
public class EntityClientDaoImpl implements EntityClientDao {

    @PersistenceContext
    private EntityManager entityManager ;

    public Client getClientById(Long clientId){
        return entityManager.find(Client.class, clientId);
    }

    public ArrayList<Client> getListClient(){
        ArrayList<Client> listClient = new ArrayList<Client>();
        Query q = entityManager.createQuery("from Client");
        listClient = (ArrayList<Client>) q.getResultList();
        return listClient;
    }

    public ArrayList<Client> getListClientRegisterCourseSession(Long courseSessionId){
        ArrayList<Client> listClient = new ArrayList<Client>();
        Query q = entityManager.createQuery("from Client cli where cli.courseSession.id = :courseSessionId");
        q.setParameter("courseSessionId", courseSessionId);
        listClient = (ArrayList<Client>) q.getResultList();
        return listClient;
    }

     public void save(Client client) {
        entityManager.persist(client);
    }

    public void update(Client client) {
        entityManager.merge(client);
    }
}
