package cn.edu.ujn.lab3.security;

import cn.edu.ujn.lab3.model.ResultMSG;
import com.google.gson.Gson;
import com.jn.sqlhelper.dialect.internal.GoldenDBDialect;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
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
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    Gson g=new Gson();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        ResultMSG success = ResultMSG.success("登陆成功!");
        response.setContentType("application/json;charset=utf-8");
        //写出流
        PrintWriter out = response.getWriter();
        out.write(g.toJson(success));
        out.flush();
        out.close();
    }
}
