package com.LiQijun.dao;

import com.LiQijun.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDao implements IUserDao {
    @Override
    public boolean saveUser(Connection con, User user) throws SQLException {
        //insert ...into usertable --write code by myself
        String sql="insert into usertable " +
                "values(?,?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setInt(1,user.getId());
        ps.setString(2,user.getUsername());
        ps.setString(3,user.getPassword());
        ps.setString(4,user.getEmail());
        ps.setString(5,user.getGender());
        ps.setDate(6,(java.sql.Date) user.getBirthDate());
        ps.close();
        return false;
    }

    @Override
    public int deleteUser(Connection con, User user) throws SQLException {
        // delete ... where id=?
        int flag=0;
        String sql="delete from usertable where id=?";
        PreparedStatement ps=null;
        ps=con.prepareStatement(sql);
        ps.setInt(1,user.getId());
        flag=ps.executeUpdate();
        ps.close();
        return flag;
    }

    @Override
    public int updateUser(Connection con, User user) throws SQLException {
        //update ... where id=?;
        int flag=0;
        String sql="update usertable set username='"+user.getUsername()
                +"',password='"+user.getPassword()
                +"',email='"+user.getEmail()
                +"',gender='"+user.getGender()
                +"',birthDate='"+user.getBirthDate()
                +"'where id='"+user.getId()+"'";
        PreparedStatement ps= con.prepareStatement(sql);
        flag=ps.executeUpdate();
        ps.close();
        return flag;
    }

    @Override
    public User findById(Connection con, Integer id) throws SQLException {
        //select ..where id=? -- write jdbc code
        String sql="select * from usertable where id='"+id+"'";
        PreparedStatement ps= con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        User user=null;
        if(rs.next()){
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthDate(rs.getDate("birthDate"));
        }
        ps.close();
        return user;
    }

    @Override
    public User findByUsernamePassword(Connection con, String username, String password) throws SQLException {
        //select .. where username=? and password=? -- write jdbc code
        String sql="select id,username,password,email,gender,birthDate from usertable where username=? and password=?";
        PreparedStatement st=con.prepareStatement(sql);
        st.setString(1,username);
        st.setString(2,password);
        User user=null;
        ResultSet rs=st.executeQuery();
        if(rs.next()){
            //get from rs and set into user model
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthDate(rs.getDate("birthDate"));
        }
        st.close();
        return user;
    }

    @Override
    public List<User> findByUsername(Connection con, String username) throws SQLException {
        //select ---where username=? --jdbc code
        String sql="select * from usertable where username='"+username+"'";
        PreparedStatement ps= con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        List<User> userList=new ArrayList<User>();
        User user=new User();
        if(rs.next()){
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthDate(rs.getDate("birthDate"));
            userList.add(user);
        }
        ps.close();
        return userList;
    }

    @Override
    public List<User> findByPassword(Connection con, String password) throws SQLException {
        //select ---where password=? --jdbc code
        String sql="select * from usertable where password='"+password+"'";
        PreparedStatement ps= con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        List<User> userList=new ArrayList<User>();
        User user=new User();
        if(rs.next()){
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthDate(rs.getDate("birthDate"));
            userList.add(user);
        }
        ps.close();
        return userList;
    }

    @Override
    public List<User> findByEmail(Connection con, String email) throws SQLException {
        //select ---where email=? --jdbc code
        String sql="select * from usertable where email='"+email+"'";
        PreparedStatement ps= con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        List<User> userList=new ArrayList<User>();
        User user=new User();
        if(rs.next()){
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthDate(rs.getDate("birthDate"));
            userList.add(user);
        }
        ps.close();
        return userList;
    }

    @Override
    public List<User> findByGender(Connection con, String gender) throws SQLException {
        //select ---where gender=? --jdbc code
        String sql="select * from usertable where gender='"+gender+"'";
        PreparedStatement ps= con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        List<User> userList=new ArrayList<User>();
        User user=new User();
        if(rs.next()){
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthDate(rs.getDate("birthDate"));
            userList.add(user);
        }
        ps.close();
        return userList;
    }

    @Override
    public List<User> findByBirthdate(Connection con, Date birthDate) throws SQLException {
        //select ---where BirthDate=? --jdbc code
        String sql="select * from usertable where birthDate='"+birthDate+"'";
        PreparedStatement ps= con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        List<User> userList=new ArrayList<User>();
        User user=new User();
        if(rs.next()){
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthDate(rs.getDate("birthDate"));
            userList.add(user);
        }
        ps.close();
        return userList;
    }

    @Override
    public List<User> findAllUser(Connection con) throws SQLException {
        //select all;
        String sql="select * from usertable";
        PreparedStatement ps= con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        List<User> userList=new ArrayList<User>();
        User user=new User();
        if(rs.next()){
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthDate(rs.getDate("birthDate"));
            userList.add(user);
        }
        ps.close();
        return userList;
    }
}
