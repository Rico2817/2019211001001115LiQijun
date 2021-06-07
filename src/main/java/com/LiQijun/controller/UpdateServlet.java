package com.LiQijun.controller;

import com.LiQijun.dao.UserDao;
import com.LiQijun.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "UpdateServlet", value = "/UpdateUser")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //write code
        request.getRequestDispatcher("WEB-INF/views/updateUser.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //write code to update user info-
        //TODO 1:get all 6 request parameters
        int id= Integer.parseInt(request.getParameter("id"));
        String username=request.getParameter("username");//name of input type
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String gender=request.getParameter("gender");
        String birthDate=request.getParameter("birthDate");
        //transform String-->Date
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        try {
            date = ft.parse(birthDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //TODO 2:create an object of user Model
        User user=new User();
        //TODO 3:set all 6 request parameters values into User model -setXXX()
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setGender(gender);
        user.setBirthDate(date);
        //TODO 4:create an object of UserDao
        UserDao dao=new UserDao();
        //TODO 5:Call updateUser() in UserDao
        Connection con=(Connection) getServletContext().getAttribute("con");
        try {
            if(dao.updateUser(con,user)==1){
                System.out.println("更新成功");
            }else{
                System.out.println("更新失败");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //TODO 6:forward to WEB-INF/userInfo.jsp
        request.getRequestDispatcher("accountDetails").forward(request,response);

    }
}
