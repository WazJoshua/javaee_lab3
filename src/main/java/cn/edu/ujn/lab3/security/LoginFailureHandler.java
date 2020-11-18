package cn.edu.ujn.lab3.security;

import cn.edu.ujn.lab3.model.ResultMSG;
import com.google.gson.Gson;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author:Joshua
 * @Date:2020/11/17
 */
@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    Gson gson = new Gson();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ResultMSG error = ResultMSG.error(exception.getMessage());

        response.setContentType("application/json;charset=utf-8");
        //写出流
        PrintWriter out = response.getWriter();
        out.write(gson.toJson(error));
        out.flush();
        out.close();

    }
}
