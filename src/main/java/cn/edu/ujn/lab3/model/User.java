package cn.edu.ujn.lab3.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Data
/*@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)*/
@RequiredArgsConstructor
public class User implements UserDetails {
    private static final long serialVersionUID = 1L;//序列化时为了保持bai版本的兼容性,在版本升级时反序列化仍保持对象的唯一性

    private Integer userId;                         //唯一标识

    private String usercode;

    private String username;

    private String userpassword;

    private Integer userState;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.userpassword;
    }

    @Override
    public String getUsername() {
        return this.username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}