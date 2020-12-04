/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author hp
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
    private static final String ERROR = "invalid.jsp";
    private static final String REGISTER = "RegisterController";
    private static final String DISPLAY = "SearchController";
    private static final String DETAILS = "DetailsDisplayController";
    private static final String LOVE = "LoveController";
    private static final String DELETE = "PostDeleteController";
    private static final String COMMENT = "CommentController";
    private static final String POST = "PostArtController";
    private static final String CMTDEL = "DeleteCmtController";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            System.out.println("ismultipart" + isMultiPart);
            String action = request.getParameter("action");
            if (!isMultiPart) {
                if (action.equals("Log In")) {
                    url = LOGIN;
                } else if (action.equals("Log Out")) {
                    url = LOGOUT;
                } else if (action.equals("Sign Up")) {
                    url = REGISTER;
                } else if (action.equals("SearchPage")) {
                    url = DISPLAY;
                } else if (action.equals("Search")) {
                    url = DISPLAY;
                    request.setAttribute("PAGEID", 1);
                } else if (action.equals("Details")) {
                    url = DETAILS;
                } else if (action.equals("LOVE")) {
                    url = LOVE;
                } else if (action.equals("Delete")) {
                    url = DELETE;
                } else if (action.equals("Comment")) {
                    url = COMMENT;
                } else if (action.equals("CmtDel")) {
                    url = CMTDEL;
                } else if (action.equals("Back")) {
                    request.setAttribute("PAGEID", 1);
                    url = DISPLAY;
                } else {
                    request.setAttribute("ERROR", "Invalid action");
                }
            } else {
                url = POST;
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.getMessage());
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
