/**
 * root账户可以添加新用户,修改和删除已有用户
 */

package model.user;

import service.account.AccountUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class Root extends User
{
    public static final String USERNAME = "root";
    public static final String PASSWORD = "123123";
    
    public Root()
    {
        super(USERNAME, PASSWORD);
    }
    
    @Override
    public String getType()
    {
        return "Root";
    }
    
    @Override
    public void routine()
    {
        // 进入账户操作界面
        while (true)
        {
            System.out.println("##【root账户权限操作】==========");
            System.out.println("##【root账户权限操作】root账户可以进行以下操作：");
            System.out.println("##【root账户权限操作】指令“list”：列出所有用户（不包括root）");
            System.out.println("##【root账户权限操作】指令“add”：添加新用户");
            System.out.println("##【root账户权限操作】指令“revise”：修改已有用户信息");
            System.out.println("##【root账户权限操作】指令“delete”：删除已有用户");
            System.out.println("##【root账户权限操作】指令“logout”：注销");
            System.out.println("##【root账户权限操作】指令“exit”：退出系统");
            System.out.println("##【root账户权限操作】==========");
            System.out.print(">");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (command.equals("list"))
            {
                list();
            }
            else if (command.equals("add"))
            {
                add();
            }
            else if (command.equals("revise"))
            {
                revise();
            }
            else if (command.equals("delete"))
            {
                delete();
            }
            else if (command.equals("logout"))
            {
                break;
            }
            else if (command.equals("exit"))
            {
                System.exit(0);
            }
            else
            {
                System.out.println("##【root账户权限操作】无此指令，请重新输入。");
            }
        }
    }
    
    private void list()
    {
        ArrayList<User> users = AccountUtil.getAllUsers();
        System.out.println("##【root账户权限操作】全部用户信息如下");
        System.out.println("##【root账户权限操作】----------");
        for (User user: users)
        {
            System.out.println(String.format("##【root账户权限操作】用户名：%s, 类型：%s", user.getUsername(), user.getType()));
        }
        System.out.println("##【root账户权限操作】----------");
    }
    
    private void add()
    {
        while(true)
        {
            System.out.println("##【root账户权限操作】请输入需要添加的新用户名,或输入“cancel”取消：");
            System.out.print(">");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();
            if (username.equals("cancel"))
            {
                break;
            }
            else if (username.equals("root"))
            {
                System.out.println("##【root账户权限操作】不能注册root账户，请重试。");
            }
            else if (AccountUtil.usernameExists(username))
            {
                System.out.println("##【root账户权限操作】该用户名已被使用，请重试。");
            }
            else
            {
                User user;
                
                // 输入密码
                System.out.println("##【root账户权限操作】请输入新用户的密码,或输入“cancel”取消：");
                System.out.print(">");
                String password = scanner.nextLine();
                if (password.equals("cancel"))
                {
                    return;
                }
                
                // 输入类型
                while (true)
                {
                    System.out.println("##【root账户权限操作】请选择新用户的医护人员类型,或输入“cancel”取消：");
                    System.out.println("##【root账户权限操作】“Doctor”：主治医生");
                    System.out.println("##【root账户权限操作】“ChiefNurse”：护士长");
                    System.out.println("##【root账户权限操作】“EmergencyNurse”：急诊护士");
                    System.out.println("##【root账户权限操作】“WardNurse”：病房护士");
                    System.out.print(">");
                    String type = scanner.nextLine();
                    if (type.equals("cancel"))
                    {
                        return;
                    }
                    else if (type.equals("Doctor"))
                    {
                        user = new Doctor(username, password);
                        break;
                    }
                    else if (type.equals("ChiefNurse"))
                    {
                        user = new ChiefNurse(username, password);
                        break;
                    }
                    else if (type.equals("EmergencyNurse"))
                    {
                        user = new EmergencyNurse(username, password);
                        break;
                    }
                    else if (type.equals("WardNurse"))
                    {
                        user = new WardNurse(username, password);
                        break;
                    }
                    else
                    {
                        System.out.println("##【root账户权限操作】无此医护人员类型，请重试。");
                    }
                }
    
                // 向数据库中加入新用户
                AccountUtil.addUser(user);
                System.out.println(String.format("##【root账户权限操作】添加新用户%s成功。", user.getUsername()));
            }
        }
    }
    
    private void revise()
    {
        while(true)
        {
            System.out.println("##【root账户权限操作】请输入需要修改的用户名称,或输入“cancel”取消：");
            System.out.print(">");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();
            if (username.equals("cancel"))
            {
                break;
            }
            else if (username.equals("root"))
            {
                System.out.println("##【root账户权限操作】不能修改root账户，请重试。");
            }
            else if (AccountUtil.usernameExists(username))
            {
                User user;
    
                // 输入密码
                System.out.println("##【root账户权限操作】请输入新的密码,或输入“cancel”取消：");
                System.out.print(">");
                String password = scanner.nextLine();
                if (password.equals("cancel"))
                {
                    return;
                }
    
                // 输入类型
                while (true)
                {
                    System.out.println("##【root账户权限操作】请选择新的医护人员类型,或输入“cancel”取消：");
                    System.out.println("##【root账户权限操作】“Doctor”：主治医生");
                    System.out.println("##【root账户权限操作】“ChiefNurse”：护士长");
                    System.out.println("##【root账户权限操作】“EmergencyNurse”：急诊护士");
                    System.out.println("##【root账户权限操作】“WardNurse”：病房护士");
                    System.out.print(">");
                    String type = scanner.nextLine();
                    if (type.equals("cancel"))
                    {
                        return;
                    }
                    else if (type.equals("Doctor"))
                    {
                        user = new Doctor(username, password);
                        break;
                    }
                    else if (type.equals("ChiefNurse"))
                    {
                        user = new ChiefNurse(username, password);
                        break;
                    }
                    else if (type.equals("EmergencyNurse"))
                    {
                        user = new EmergencyNurse(username, password);
                        break;
                    }
                    else if (type.equals("WardNurse"))
                    {
                        user = new WardNurse(username, password);
                        break;
                    }
                    else
                    {
                        System.out.println("##【root账户权限操作】无此医护人员类型，请重试。");
                    }
                }
    
                //更新数据库中用户信息
                AccountUtil.updateUser(user);
                System.out.println(String.format("##【root账户权限操作】修改用户%s成功。", user.getUsername()));
            }
            else
            {
                System.out.println("##【root账户权限操作】用户不存在，请重试。");
            }
        }
    }
    
    private void delete()
    {
        while(true)
        {
            System.out.println("##【root账户权限操作】请输入需要删除的用户名称,或输入“cancel”取消：");
            System.out.print(">");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();
            if (username.equals("cancel"))
            {
                break;
            }
            else if (username.equals("root"))
            {
                System.out.println("##【root账户权限操作】不能删除root账户，请重试。");
            }
            else if (AccountUtil.usernameExists(username))
            {
                User user = AccountUtil.getUser(username);
                if (user != null)
                {
                    // 删除数据库中用户信息
                    AccountUtil.removeUser(user);
                    System.out.println(String.format("##【root账户权限操作】删除用户%s成功。", user.getUsername()));
                }
            }
            else
            {
                System.out.println("##【root账户权限操作】用户不存在，请重试。");
            }
        }
    }
}
