package com.senjay.vc_javaee;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@WebServlet("/wr1")
public class WriterDemo1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        out.write("Hello world");
        //字符数据
        // 不能同时使用
        ServletOutputStream sos = response.getOutputStream();
        sos.write("<h1 style=\"color:red;\">Hello World</h1>".getBytes());
        // 二进制数据


    }
}
