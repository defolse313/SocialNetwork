/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.controllers;

import hieudn.blos.RgistrationBLO;
import hieudn.enitties.Article;
import hieudn.enitties.Emotion;
import hieudn.enitties.Member1;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */
public class InsertEmoController extends HttpServlet {

    private static final String SUCCESS = "DetailsDisplayController";
    private static final String ERROR = "error.jsp";

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
            String emoji = request.getParameter("action");
            HttpSession session = request.getSession();
            Member1 mem = new Member1((String) session.getAttribute("ID"));
            Article article = new Article(Integer.parseInt(request.getParameter("txtArticle")));
            Emotion e = new Emotion(emoji, article, mem);
            RgistrationBLO blo = new RgistrationBLO();
            String check = blo.reactionSeperate(article.getArticleID(), mem.getMemberId());
            if (check == null) {
                if (blo.insertReact(emoji, article.getArticleID(), mem.getMemberId())) {
                    url = SUCCESS;
                } else {
                    url = ERROR;
                }
            } else {
                if (emoji.equalsIgnoreCase("LOVE")) {
                    if (check.equalsIgnoreCase("LOVE")) {
                        if(blo.updateReact("UNDEFINED", article.getArticleID(), mem.getMemberId())) {
                            url = SUCCESS;
                        } else {
                            url = ERROR;
                        }
                    } else {
                        if(blo.updateReact("LOVE", article.getArticleID(), mem.getMemberId())) {
                            url = SUCCESS;
                    } else {
                            url = ERROR;
                        }
                }
                }else {
                    if (check.equalsIgnoreCase("DISLIKE")) {
                        if(blo.updateReact("UNDEFINED", article.getArticleID(), mem.getMemberId())) {
                            url = SUCCESS;
                        } else {
                            url = ERROR;
                        }
                    } else {
                        if(blo.updateReact("DISLIKE", article.getArticleID(), mem.getMemberId())) {
                            url = SUCCESS;
                        } else {
                            url = ERROR;
                        }
                    }
                }
            }
         
        }catch (Exception e) {
            log("error at EmoController: " + e.getMessage());
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
