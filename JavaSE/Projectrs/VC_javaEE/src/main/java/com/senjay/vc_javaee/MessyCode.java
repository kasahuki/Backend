package com.senjay.vc_javaee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/test1")
public class MessyCode extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println(request.getParameter("name"));
        // get 不会messycode
        // 试试post
        request.setCharacterEncoding("UTF-8");
        // 如果是在html而不是jsp文件中提交中文表单数据的话可能不会起作用

        request.getRequestDispatcher("sr06").forward(request,response);
        System.out.println(request.getParameter("age")); // 表单提交本质和get里请求行一样 只是和数据的敏感性以及数据的数量有关
        System.out.println(request.getParameter("grade"));


    }
}
