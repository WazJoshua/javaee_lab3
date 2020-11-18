package cn.edu.ujn.lab3.security;

import cn.edu.ujn.lab3.model.ResultMSG;
import cn.edu.ujn.lab3.utils.JWTUtils;
import com.google.gson.Gson;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Author:Joshua
 * @Date:2020/11/17
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {


    Gson gson = new Gson();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //设置编码格式
        response.setCharacterEncoding("UTF-8");
        //这句话是解决乱码的
        //response.setContentType("text/html;charset=UTF-8");

        Cookie[] cookies = request.getCookies();
        String jwtToken = null;
        ResultMSG resultMSG = ResultMSG.success();
        if (cookies != null) {
            for (Cookie c :
                    cookies) {
                if (c.getName().equals("jwtToken")) {
                    jwtToken = c.getValue();
                    break;
                }
            }
        } else {
            resultMSG = ResultMSG.error("验证失败!");
            responseMessage(response, response.getWriter(), resultMSG);
            return false;
        }


        if (jwtToken != null) {
            if (JWTUtils.checkJWT(jwtToken)) {
                response.setContentType("text/html;charset=UTF-8");
                return true;
            } else {
                resultMSG = ResultMSG.error("验证失败!");
                responseMessage(response, response.getWriter(), resultMSG);
                return false;
            }
        } else {
            resultMSG = ResultMSG.error("验证失败!");
            responseMessage(response, response.getWriter(), resultMSG);
            return false;
        }
    }

    //请求不通过，返回错误信息给客户端
    private void responseMessage(HttpServletResponse response, PrintWriter out, ResultMSG responseData) {


        responseData = ResultMSG.error("请先登录!");
        response.setContentType("application/json;charset=UTF-8");
        String json = gson.toJson(responseData);
        out.print(json);
        out.flush();
        out.close();
    }
}
