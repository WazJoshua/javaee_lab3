package cn.edu.ujn.lab3.security;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author:Joshua
 * @Date:2020/11/4
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DruidDataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select user_code,user_password,user_state from sys_user where user_code=?")
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /*.httpBasic()

                .and()
                .authorizeRequests()
                .antMatchers("/static/**", "/index.html", "/pages/loginpage.html", "/pages/registration.html")
                .permitAll()

                .and()*/
                .authorizeRequests()
                //.anyRequest()
                //.authenticated()
                //.access("hasRole('1')")
                .antMatchers("/**", "/")
                .permitAll()

                /*.and()
                .formLogin()
                .loginPage("/pages/loginpage.html")
                .loginProcessingUrl("/loginUser")
                .successForwardUrl("/login/success")
                .usernameParameter("usercode")
                .passwordParameter("password")

                .and()*/

        ;
        http.csrf().disable();
        /*http.cors();        //开启跨域*/
    }
}
