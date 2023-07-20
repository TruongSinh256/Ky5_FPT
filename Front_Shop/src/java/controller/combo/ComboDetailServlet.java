/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.combo;

import dal.ComboDAO;
import dal.ProductDAO;
import dal.ProductImageDAO;
import dal.StockDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import models.Color;
import models.ComboDetails;
import models.ComboList;
import models.Product;
import models.ProductImage;
import models.StockDetail;

/**
 *
 * @author Asus
 */
public class ComboDetailServlet extends HttpServlet {
   
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
            out.println("<title>Servlet ComboDetailServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComboDetailServlet at " + request.getContextPath () + "</h1>");
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
        ComboDAO dao = new ComboDAO();
        StockDAO sdb = new StockDAO();
        
        
        ProductImageDAO daoPI = new ProductImageDAO();
        ProductDAO pdb = new ProductDAO();
        String proColorID_raw = request.getParameter("colorID");
        request.setAttribute("coID", proColorID_raw);
        String Sizeid_raw = request.getParameter("sizeID");
        request.setAttribute("siID", Sizeid_raw);
        int colorID = 0;
        int sizeID = 0;
       
        Product pro = new Product();
        int comboId = 0;
        int proid = 0 ;
        try {
            comboId =  Integer.parseInt(request.getParameter("comboID"));
            proid =  Integer.parseInt(request.getParameter("proID"));
            request.setAttribute("comboId", comboId);
            colorID = Integer.parseInt(proColorID_raw);
            sizeID = Integer.parseInt(Sizeid_raw);
            
            
            
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        List<ComboDetails> listComboDetail = new ArrayList<>();
        StockDetail stock_quantity = sdb.getStockQuantity(proid, colorID, sizeID);
            //Lay san pham voi proID va colorId truyen vao
            List<StockDetail> list_stock = sdb.getStockProduct(proid, colorID);
            //lay stock cua tat ca san pham voi proID truyen vao
            List<StockDetail> list_stock_all = sdb.getStockProductAll(proid);
        listComboDetail = dao.getComboDetailByComboID(comboId);
        
        List<ProductImage> listImg = new ArrayList<>();
        for (int i = 0; i < listComboDetail.size(); i++) {
            
            listImg.add(daoPI.getImageById(listComboDetail.get(i).getProID()));
        }
        //xu li mau 
           List<Color> list_color = new ArrayList<>();
            for (int i = 0; i < list_stock_all.size(); i++) {
                Color c = new Color();
                c.setProColorID(list_stock_all.get(i).getProColorID());
                c.setProColorName(list_stock_all.get(i).getProColorName());
                list_color.add(c);
            }

            for (int i = 0; i < list_color.size(); i++) {
                for (int j = 1; j < list_color.size(); j++) {
                    if (list_color.get(i).getProColorName().equals(list_color.get(j).getProColorName())) {
                        list_color.remove(j);
                    }
                }
            }
        
        pro = pdb.getProductByID(proid);
        ProductImage pi = listImg.get(0);
        
        request.setAttribute("list_color", list_color);
        request.setAttribute("stock_quantity", stock_quantity);
        request.setAttribute("list_stock", list_stock);
        request.setAttribute("list_stock_all", list_stock_all);
        request.setAttribute("pro", pro);
        request.setAttribute("fisrtImg", pi);
        request.setAttribute("listimg", listImg);
        request.setAttribute("combodetail", listComboDetail);
        request.getRequestDispatcher("../view/ComboDetails.jsp").forward(request, response);
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
