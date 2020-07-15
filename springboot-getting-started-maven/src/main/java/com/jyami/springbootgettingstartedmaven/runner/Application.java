package com.jyami.springbootgettingstartedmaven.runner;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Application {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        Context context = tomcat.addContext("/", "/");

        HttpServlet httpServlet = new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                PrintWriter printWriter = resp.getWriter();
                printWriter.println("<html><head><title>");
                printWriter.println("Hey, Tomcat");
                printWriter.println("<<title/><head/>");
                printWriter.println("<body><h1>Hello Tomcat</h1></body>");
                printWriter.println("</html>");

            }
        };

        String servletName = "helloServlet";
        tomcat.addServlet("/", servletName, httpServlet);
        context.addServletMappingDecoded("/hello", servletName);

        tomcat.start();
        tomcat.getServer().await();
    }
}