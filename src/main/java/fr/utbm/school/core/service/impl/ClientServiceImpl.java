/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.service.impl;

import fr.utbm.school.core.Dao.impl.EntityClientDaoImpl;
import fr.utbm.school.core.entity.Client;
import fr.utbm.school.core.exceptions.ClientException;
import fr.utbm.school.core.service.ClientService;
import fr.utbm.school.core.service.CourseSessionService;
import fr.utbm.school.core.tools.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


/**
 *
 * @author Neil FARMER
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private EntityClientDaoImpl entityClientDao;

    @Autowired
    private CourseSessionService courseSessionService;

    public Client searchClientById(Long idClient){
        return entityClientDao.getClientById(idClient);
    }


    public ArrayList<Client> searchClientByEmail(String email) {
        return entityClientDao.searchClientByEmail(email);
    }

    public ArrayList<Client> getListClient(){
        return entityClientDao.getListClient();
    }

    public ArrayList<Client> getListClientRegisterCourseSession(Long courseSessionId){
        return entityClientDao.getListClientRegisterCourseSession(courseSessionId);
    }

    public void saveClient(Client client) throws ClientException{
        if(courseSessionService.getPercentStudent(client.getCourseSession().getId()) >= 100){
            ClientException ex = new ClientException("Too many registered client on this session");
            throw ex;
        }

        entityClientDao.save(client);

        /*
        // Uncomment to send mail to the user who registered

        MailSender ms = new MailSender();
        String nomCours = client.getCourseSession().getCourse().getTitle();

        ms.sendMail(client.getEmail(), "Inscription a " + nomCours + " confirmé",
                "Bonjour M/Mme " + client.getFirstName() + " " +
                        client.getLastName() + ", \nNous vous confirmons votre inscription au cours " +
                        nomCours + ".\nCordialement,\nNeil Farmer & Ruiqing Zhu");

         */
    }

    public void updateClient(Client client){
        entityClientDao.update(client);
    }
}
