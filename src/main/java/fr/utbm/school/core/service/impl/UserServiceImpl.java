package fr.utbm.school.core.service.impl;

import fr.utbm.school.core.Dao.UserDao;
import fr.utbm.school.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserDao userDao;
}
