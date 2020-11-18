package cn.edu.ujn.lab3.model;

import cn.edu.ujn.lab3.utils.BCrypt;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Collection;


/*@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)*/
@Data
@RequiredArgsConstructor
public class User {


    private static final long serialVersionUID = 1L;//序列化时为了保持bai版本的兼容性,在版本升级时反序列化仍保持对象的唯一性

    private Integer userId;

    private String usercode;

    private String username;

    private String password;

    private Integer userState;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password=password;
        //this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
}