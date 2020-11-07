package cn.edu.ujn.lab3.service;

import cn.edu.ujn.lab3.model.User;
import org.springframework.stereotype.Service;

/**
 * @Author:Joshua
 * @Date:2020/11/5
 */

public interface IUserService {
    public boolean registerUser(User u);

    public boolean findByUserCode(String usercode);

    public boolean loginUser(User user);
}
