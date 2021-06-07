package com.LiQijun.controller;

import com.LiQijun.dao.ProductDao;
import com.LiQijun.model.Item;
import com.LiQijun.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
    Connection con = null;

    @Override
    public void init() throws ServletException {
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);//get existing session ,not create new session
        if (session != null && session.getAttribute("user") != null) {
            //user has logged in 
            if (request.getParameter("action") == null) {
                displayCart(request, response);
            } else if (request.getParameter("action").equals("add")) {
                //System.out.println("buy");
                buy(request, response);
            } else if (request.getParameter("action").equals("remove")) {
                remove(request, response);
            }

        } else {
            response.sendRedirect("login");
        }
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //remove item from cart
        List<Item> cart = (List<Item>) request.getSession().getAttribute("cart");
        int id = 0;
        if (request.getParameter("productId") != null) {
            id = Integer.parseInt(request.getParameter("productId"));
        }
        int index = isExisting(id, cart);
        cart.remove(index);
        request.getSession().setAttribute("cart", cart);
        String path = request.getContextPath() + "/cart";
        response.sendRedirect(path);
    }

    private void buy(HttpServletRequest request, HttpServletResponse response)throws IOException {
        //add item into cart
        HttpSession session = request.getSession();
        int id = request.getParameter("productId") != null ? Integer.parseInt(request.getParameter("productId")) : 0;
        int quantity = request.getParameter("quantity") != null ? Integer.parseInt(request.getParameter("quantity")) : 1;
        if (id == 0 || quantity == 0) {
            //error
            return;
        }
        ProductDao productDao = new ProductDao();
        if (session.getAttribute("cart") == null) {
            //create a new cart
            List<Item> cart = new ArrayList<Item>();
            Product p = productDao.findById(id, con);
            cart.add(new Item(p, quantity));
            session.setAttribute("cart", cart);
        } else {
            //have cart -need to add new item
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            //check this product is in cart - add quantity++ or not  -add new item into cart
            int index = isExisting(id, cart);
            if (index == -1) {
                //new item
                Product p = productDao.findById(id, con);
                cart.add(new Item(p, 1));
            } else {
                //only quantity++
                int newQuantity = cart.get(index).getQuantity() + 1;//add 1
                cart.get(index).setQuantity(newQuantity);
            }
            session.setAttribute("cart", cart);
        }
        String path = request.getContextPath() + "/cart";
        response.sendRedirect(path);
    }

    private int isExisting(int id, List<Item> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getProductId() == id) {
                return i;
            }
        }
        return -1;
    }

    private void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message", "Your Cart");
        request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
