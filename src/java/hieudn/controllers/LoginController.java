/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.controllers;

import hieudn.blos.RgistrationBLO;
import hieudn.blos.SHA256;
import hieudn.enitties.Member1;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private static final String ADMIN = "SearchController";
    private static final String MEMBER = "SearchController";
    private static final String ERROR = "login.jsp";

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
            String memberId = request.getParameter("txtMemberId");
            String password = request.getParameter("txtPassword");
            String encrypted = null;
            boolean valid = true;
//            ErrorObj errorObj = new ErrorObj();

            if (!memberId.matches("[a-zA-Z0-9]{3,50}@[a-zA-Z0-9]{3,50}[.][a-zA-Z0-9]{2,7}([.][a-zA-Z]{1,2})?")) {

                valid = false;
            }
            if (valid) {
                RgistrationBLO blo = new RgistrationBLO();
                try {
                    encrypted = SHA256.toHexString(SHA256.getSHA(password));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                boolean result = blo.checkLogin(memberId, encrypted);
                if (!result) {
                    url = ERROR;

                } else {
                    HttpSession session = request.getSession();
                    Member1 m = new Member1();
                    m = blo.loginPage(memberId);
                    if (m.getRole().equals("admin")) {
                        url = ADMIN;
                        session.setAttribute("ROLE", m.getRole());
                        session.setAttribute("ID", m.getMemberId());
                    } else if (m.getRole().equals("member")) {
                        url = MEMBER;
                        session.setAttribute("ROLE", m.getRole());
                        session.setAttribute("ID", m.getMemberId());
                    }
                    session.setAttribute("ACCOUNT", m);
                    session.setAttribute("memberFullname", m.getMemberFullname());
                    request.setAttribute("PAGEID", 1);
                }
            } else {
                request.setAttribute("INVALID", "wrong format of email");
                request.setAttribute("INVALID1", "Invalid Email Or Password!");
            }
        } catch (Exception e) {
            log("error at LoginController: " + e.getMessage());
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
