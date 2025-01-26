package com.senjay.vc_javaee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

        @WebServlet("/sr1") // 一定不要忘记写/
        public class Servlet01 extends HttpServlet {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                System.out.println("hello Lunafreya !");
                Writer writer = response.getWriter();
                writer.write("hello Lunafreya !");
            }
        }
