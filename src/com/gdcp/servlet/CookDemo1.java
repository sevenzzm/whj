package com.gdcp.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "CookDemo1", urlPatterns = "/CookDemo1")
public class CookDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                response.setContentType("text/html;charset=utf8");
        PrintWriter out=response.getWriter();
        String lastTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        lastTime= URLEncoder.encode(lastTime,"utf-8");
        Cookie cookie = new Cookie("time", lastTime);
        cookie.setMaxAge(60*60*24*30);
        response.addCookie(cookie);
        boolean isFlag=false;

        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie ck : cookies) {
                if (ck.getName().equals("time")) {
                    String t2=ck.getValue();
                    isFlag=true;
                   out.write("上一次访问时间为： " + "<span style='color:red'>" + URLDecoder.decode(t2,"utf-8") + "</span>");
                        break;
                }
            }
        }
        if(!isFlag){
           out.write("欢迎访问");
        }

    }
}
