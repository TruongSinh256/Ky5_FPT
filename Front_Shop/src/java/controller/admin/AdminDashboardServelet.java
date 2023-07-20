/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import dal.AdminDAO;
import dal.ProductImageDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import models.Color;

import models.Product;
import models.ProductImage;
import models.Size;

/**
 *
 * @author Asus
 */
public class AdminDashboardServelet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminDashboardServelet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminDashboardServelet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        AdminDAO dao = new AdminDAO();
        
        ProductImageDAO pidb = new ProductImageDAO();
        String choose = request.getParameter("table");
        request.setAttribute("table", choose);
         if (choose.equals("product")){
            List<Product> listProduct1 = dao.getAllProduct();
            List<Product> listProduct = dao.get10NewProduct();
           
            List<ProductImage> list_img = new ArrayList<>();
             for (int i = 0; i <= listProduct1.size() - 1; i++) {
                list_img.add(pidb.getImageById(listProduct1.get(i).getProID()));
            }
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("list_img", list_img);
            request.setAttribute("totalProduct", dao.getTotalOfProduct());
            request.setAttribute("totalOrder", dao.getTotalOfOrder());
            request.setAttribute("totalRevenue", dao.getTotalOfRevenue());
            request.setAttribute("listOrder", dao.get10NewOrderByStatusID());
            request.getRequestDispatcher("../view/Admin.jsp").forward(request, response);
         }else{
            List<Color> listColors = dao.getAllColor();
            List<Size> listSizes = dao.getAllSize();
            List<Product> listProduct = dao.getAllProduct();        
            List<ProductImage> list_img = new ArrayList<>();

            //lay anh
            for (int i = 0; i <= listProduct.size()-1; i++) {
                list_img.add(pidb.getImageById(listProduct.get(i).getProID()));
            }
            int size = dao.get10NewOrderByStatusID().size();
            request.setAttribute("listStock", dao.get10NewStock());
            request.setAttribute("listOrder", dao.get10NewOrderByStatusID());
            request.setAttribute("size", size==0?"":size);
            request.setAttribute("list_img", list_img);
            request.setAttribute("list_Pro", listProduct);
            request.setAttribute("list_Size", listSizes);
            request.setAttribute("list_Color", listColors);
            request.setAttribute("totalProduct", dao.getTotalOfProduct());
            request.setAttribute("totalOrder", dao.getTotalOfOrder());
            request.setAttribute("totalRevenue", dao.getTotalOfRevenue());
            request.getRequestDispatcher("../view/Admin.jsp").forward(request, response);
         }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
