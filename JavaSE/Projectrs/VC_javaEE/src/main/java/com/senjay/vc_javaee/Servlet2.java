package com.senjay.vc_javaee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/sr2")
public class Servlet2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getRequestURI()); // /myArtifact1/sr2
        System.out.println(request.getRequestURL()); // http://localhost:8080/myArtifact1/sr2
        System.out.println(request.getQueryString()); // ?name=Senjay&age=25
        System.out.println(request.getParameter("name")); // Senjay
        System.out.println(request.getParameter("age"));// 25
        System.out.println(request.getMethod()); // GET
        System.out.println(request.getProtocol()); // HTTP/1.1
        System.out.println(request.getContextPath()); //获取webapp名字 // /myArtifact1
    }
}
