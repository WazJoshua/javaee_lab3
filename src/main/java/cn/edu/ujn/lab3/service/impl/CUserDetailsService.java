package cn.edu.ujn.lab3.service.impl;

import cn.edu.ujn.lab3.dao.UserMapper;
import cn.edu.ujn.lab3.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Joshua
 * @Date:2020/11/8
 */
@Service
public class CUserDetailsService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsercode = userMapper.findByUsercode(username);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        System.out.println("authorities = " + authorities);
        System.out.println("byUsercode = " + byUsercode);
        return new org.springframework.security.core.userdetails.User(byUsercode.getUsercode(), byUsercode.getPassword(), authorities);
    }
}
