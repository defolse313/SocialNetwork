/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.controllers;

import hieudn.blos.RgistrationBLO;
import hieudn.enitties.Article;
import hieudn.enitties.Member1;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author hp
 */
public class PostArtController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SearchController";

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
        System.out.println("LuonVUiTuoi");
        try {
            String realPath_DB = "";
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (!isMultiPart) {
            } else {
                FileItemFactory factory = new org.apache.commons.fileupload.disk.DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload((FileItemFactory) factory);
                List items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException e) {
//                    log("ERROR FileUploadException at PostController: " + e.getMessage());
//                    e.printStackTrace();
                }
                Iterator iter = items.iterator();
                Hashtable params = new Hashtable();
                String fileName = null;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                    } else {
                        try {
                            String itemName = item.getName();
                            fileName = itemName.substring(itemName.lastIndexOf("\\") + 1);
                            System.out.println("path " + fileName);
                            String realPath = getServletContext().getRealPath("/")
                                    + "images\\" + fileName;
                            System.out.println("Real Path " + realPath);
                            realPath_DB = fileName;
                            File savedFile = new File(realPath);
                            item.write(savedFile);
                        } catch (Exception e) {
                            log("ERROR ImageUploadException at PostController: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
                String articleTitle = (String) params.get("txtArticleTitle");
                System.out.println(articleTitle + "bbbbbb");
                String articleContent = (String) params.get("txtArticleContent");
                java.util.Date d = new java.util.Date();
                Date d1 = new Date(d.getTime());
                HttpSession session = request.getSession();
                Member1 mem = new Member1((String) session.getAttribute("ID"));
                String articleImage = realPath_DB;
                System.out.println("aI: " + articleImage);
                if (articleImage.length() == 0) {
                    articleImage = null;
                }
                RgistrationBLO blo = new RgistrationBLO();
                if (articleTitle.length() != 0 || articleContent.length() != 0) {
                    blo.createArticle(mem.getMemberId(), articleTitle, articleContent, articleImage, d1, "ACTIVE");
                    request.setAttribute("NOTI", "Post Successful - Please Reload Page");
                    System.out.println("success");
                    url = SUCCESS;
                    request.setAttribute("PAGEID", 1);
                } else {
                    request.setAttribute("NOTI", "Post UnSuccessful - Try Again");
                    System.out.println("fail");
                    url = ERROR;
                }
                url = SUCCESS;
                request.setAttribute("PAGEID", 1);
            }
        } catch (Exception e) {
            log("Error at PostController: " + e.getMessage());
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
//            response.sendRedirect("SearchController?pageIDPaging=1&txtSearch=");
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
