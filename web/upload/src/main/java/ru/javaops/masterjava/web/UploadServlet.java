package ru.javaops.masterjava.web;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;

@WebServlet(urlPatterns = "/")
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        WebContext webContext = new WebContext(request, response, getServletContext());
        TemplateEngine engine = ThymeleafUtil.getTemplateEngine(getServletContext());

        Collection<Part> parts = request.getParts();
        for (Part part: parts) {
            if ("file".equals(part.getName())) {
                request.setAttribute("message", "Dima");
            }
        }

        final String templateStr = engine.process("users", webContext);
        response.getWriter().write(templateStr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        WebContext webContext = new WebContext(request, response, getServletContext());
        TemplateEngine engine = ThymeleafUtil.getTemplateEngine(getServletContext());

        final String templateStr = engine.process("upload", webContext);
        response.getWriter().write(templateStr);
    }
}
