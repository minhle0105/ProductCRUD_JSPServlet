package controller;

import model.Product;
import service.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/productController")
public class ProductServlet extends HttpServlet {
    ProductServiceImpl psi = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("ac");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showAddForm(request, response);
                break;
            case "update":
                showUpdateForm(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
            default:
                showProducts(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("ac");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create" -> addProduct(request, response);
            case "update" -> updateProduct(request, response);
            case "search" -> searchProduct(request, response);
        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addform.jsp");
        dispatcher.forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUpdate = Integer.parseInt(request.getParameter("idUpdate"));
        Product productToUpdate = psi.getProductByID(idUpdate);
        request.setAttribute("product", productToUpdate);
        RequestDispatcher dispatcher = request.getRequestDispatcher("updateform.jsp");
        dispatcher.forward(request, response);
    }

    private void showProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = psi.showAllProducts();
        request.setAttribute("products", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idDel = Integer.parseInt(request.getParameter("idDelete"));
        psi.deleteProduct(idDel);
        List<Product> productList = psi.showAllProducts();
        request.setAttribute("products", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        String newName = request.getParameter("new_name");
        double newPrice = Double.parseDouble(request.getParameter("new_price"));
        int newQuantity = Integer.parseInt(request.getParameter("new_quantity"));
        String newColor = request.getParameter("new_color");
        int newCategoryId = Integer.parseInt(request.getParameter("new_category_id"));
        Product p = new Product(productId, newName, newPrice, newQuantity, newColor, newCategoryId);
        psi.updateProduct(p);
        response.sendRedirect("/productController");
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String productName = request.getParameter("name");
        double productPrice = Double.parseDouble(request.getParameter("price"));
        int productQuantity = Integer.parseInt(request.getParameter("quantity"));
        String productColor = request.getParameter("color");
        int categoryId = Integer.parseInt(request.getParameter("categoryid"));
        Product p = new Product(productName, productPrice, productQuantity,productColor,categoryId);
        psi.addNewProduct(p);
        response.sendRedirect("/productController");
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productNameToSearch = request.getParameter("searchValue");
        List<Product> searchResult = psi.searchProduct(productNameToSearch);
        request.setAttribute("products", searchResult);
        RequestDispatcher dispatcher = request.getRequestDispatcher("searchResult.jsp");
        dispatcher.forward(request, response);
    }
}
