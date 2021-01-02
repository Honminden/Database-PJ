/**
 * 实现账户管理功能
 */
package service.account;

import model.user.User;
import service.sql.SQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AccountUtil
{
    public static boolean usernameExists(String username)
    {
        return getUser(username) != null;
    }
    
    public static User getUser(String username, String password)
    {
        User user = getUser(username);
        // 检查密码是否正确
        if (user != null && user.getPassword().equals(password))
        {
            return user;
        }
        else
        {
            return null;
        }
    }
    
    public static User getUser(String username)
    {
        try
        {
            // 查找用户名
            Connection con = SQLUtil.getConnection();
            PreparedStatement findUserByUsername = con.prepareStatement("select * from users where username=?");
            findUserByUsername.setString(1, username);
            try (ResultSet usersFound = findUserByUsername.executeQuery())
            {
                if (usersFound.next())
                {
                    String password = usersFound.getString("password");
                    String type = usersFound.getString("type");
                    con.close();
                    return User.getInstance(username, password, type);
                }
                else
                {
                    con.close();
                    return null;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("##【SQL错误】请重试。");
        }
        return null;
    }
    
    public static ArrayList<User> getAllUsers()
    {
        ArrayList<User> users = new ArrayList<>();
        try
        {
            // 获取全部用户
            Connection con = SQLUtil.getConnection();
            PreparedStatement findAllUsers = con.prepareStatement("select * from users");
            try (ResultSet usersFound = findAllUsers.executeQuery())
            {
                while (usersFound.next())
                {
                    String username = usersFound.getString("username");
                    String password = usersFound.getString("password");
                    String type = usersFound.getString("type");
                    System.out.println(type);
                    User user = User.getInstance(username, password, type);
                    users.add(user);
                }
            }
    
            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("##【SQL错误】请重试。");
        }
        return users;
    }
    
    public static void addUser(User user)
    {
        try
        {
            // 添加新用户
            Connection con = SQLUtil.getConnection();
            PreparedStatement addNewUser = con.prepareStatement("insert into users values (?,?,?)");
            addNewUser.setString(1, user.getUsername());
            addNewUser.setString(2, user.getPassword());
            addNewUser.setString(3, user.getType());
            addNewUser.executeUpdate();
            con.close();
        }
        catch (Exception e)
        {
            System.out.println("##【SQL错误】请重试。");
        }
    }
    
    public static void updateUser(User user)
    {
        try
        {
            // 修改用户信息
            Connection con = SQLUtil.getConnection();
            PreparedStatement reviseUser = con.prepareStatement("update users set password=?, type=? where username=?");
            reviseUser.setString(1, user.getPassword());
            reviseUser.setString(2, user.getType());
            reviseUser.setString(3, user.getUsername());
            reviseUser.executeUpdate();
            con.close();
        }
        catch (Exception e)
        {
            System.out.println("##【SQL错误】请重试。");
        }
    }
    
    public static void removeUser(User user)
    {
        try
        {
            // 删除用户
            Connection con = SQLUtil.getConnection();
            PreparedStatement deleteUser = con.prepareStatement("delete from users where username=?");
            deleteUser.setString(1, user.getUsername());
            deleteUser.executeUpdate();
            con.close();
        }
        catch (Exception e)
        {
            System.out.println("##【SQL错误】请重试。");
        }
    }
}
