package ru.itis.medportal.controller.common;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filePath = request.getParameter("file");
        if (filePath != null) {
            File file = new File(filePath);
            String safeFileName = "document_" + System.currentTimeMillis() + ".pdf";
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + safeFileName + "\"");
            response.setContentLengthLong(file.length());
            Files.copy(file.toPath(), response.getOutputStream());
        }
    }
}
