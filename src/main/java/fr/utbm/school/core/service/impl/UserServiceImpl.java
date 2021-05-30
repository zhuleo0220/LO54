package fr.utbm.school.core.service.impl;

import fr.utbm.school.core.dao.UserDao;
import fr.utbm.school.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserDao userDao;
}
