package cn.edu.ujn.lab3.service.impl;

import cn.edu.ujn.lab3.dao.UserMapper;
import cn.edu.ujn.lab3.model.User;
import cn.edu.ujn.lab3.service.IUserService;
import cn.edu.ujn.lab3.utils.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author:Joshua
 * @Date:2020/11/5
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean registerUser(User u) {
        User byUsercode = userMapper.findByUsercode(u.getUsercode());
        if (byUsercode == null) {
            u.setPassword(BCrypt.hashpw(u.getPassword(),BCrypt.gensalt()));
            int insert = userMapper.insert(u);
            return insert == -1 ? false : true;
        } else return false;

    }

    @Override
    public boolean findByUserCode(String usercode) {
        User byUsercode = userMapper.findByUsercode(usercode);
        if (byUsercode == null)
            return true;
        return false;
    }

    @Override
    public boolean loginUser(User user) {
        User byUsercode = userMapper.findByUsercode(user.getUsercode());
        //System.out.println("byUsercode = " + byUsercode);
        //判断是否存在用户
        if (byUsercode == null) {                                            //不存在用户,则直接返回登录失败
            return false;
        }

        if (BCrypt.checkpw(user.getPassword(), byUsercode.getPassword())/*byUsercode.getPassword().equals(user.getPassword())*/) {           //密码正确
            return true;                                                     //登陆成功
        } else return false;
    }
}
