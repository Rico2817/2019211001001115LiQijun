package com.LiQijun.controller;

import com.LiQijun.dao.OrderDao;
import com.LiQijun.model.Item;
import com.LiQijun.model.Order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "OrderDetailsServlet", value = "/orderDetails")
public class OrderDetailsServlet extends HttpServlet {
    Connection con=null;

    @Override
    public void init() throws ServletException {
        con=(Connection) getServletContext().getAttribute("con");
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId=request.getParameter("orderId")!=null?
                Integer.parseInt(request.getParameter("orderId")):0;
        Item item=new Item();
        OrderDao dao=new OrderDao();
        List<Item> items=dao.findItemsByOrderId(con,orderId);
        request.setAttribute("itemList",items);
        request.getRequestDispatcher("orderDetails.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
