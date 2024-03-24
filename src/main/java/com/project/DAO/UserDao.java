package com.project.DAO;
import com.project.DTO.*;
import com.project.BUS.*;
import com.project.GUI.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public List<User> getAllUser() throws Exception
    {
        List<User> users = new ArrayList<User>();
        Connection con = MyConnection.getMyConnection();
        String sql = "SELECT * FROM giaovien";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setAge(rs.getInt("age"));
            users.add(user);
        }
        return users;
    }
    public void addUser(User user) throws Exception
    {
        Connection con = MyConnection.getMyConnection();
        String sql = "INSERT INTO giaovien(id, name, age) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, user.getId());
        ps.setString(2, user.getName());
        ps.setInt(3, user.getAge());
        ps.executeUpdate();
    }
    public void deleteUser(int id) throws Exception
    {
        Connection con = MyConnection.getMyConnection();
        String sql = "DELETE FROM giaovien WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
    public void updateUser(User user) throws Exception
    {
        Connection con = MyConnection.getMyConnection();
        String sql = "UPDATE giaovien SET name = ?, age = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, user.getName());
        ps.setInt(2, user.getAge());
        ps.setInt(3, user.getId());
        ps.executeUpdate();
    }
    public User getIdUser(int id) throws Exception
    {
        Connection con = MyConnection.getMyConnection();
        String sql = "SELECT * FROM giaovien WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setAge(rs.getInt("age"));
            return user;
        }
        return null;
    }
    public List<User> SearchAllUserByName(String name) throws Exception
    {
        Connection con = MyConnection.getMyConnection();
        String sql = "SELECT * FROM giaovien WHERE name = ? ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        List<User> users = new ArrayList<User>();
        while(rs.next())
        {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setAge(rs.getInt("age"));
            users.add(user);
            
        }
        return users;
    }
    public List<User> SearchAllUserById(int id ) throws Exception 
    {
        List<User> users = new ArrayList<User>();
        Connection con = MyConnection.getMyConnection();
        String sql = "SELECT * FROM giaovien WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setAge(rs.getInt("age"));
            users.add(user);
        }
        return users;
    }
}


