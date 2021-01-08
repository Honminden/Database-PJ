/**
 * 护士长
 */

package model.user;

import model.bed.Bed;
import restriction.user.UserRestriction;
import service.account.AccountUtil;
import service.id.IDGenerator;
import service.patient.BedUtil;
import service.patient.NurseUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class ChiefNurse extends User
{
    public static final String TYPE = "护士长";
    
    ChiefNurse(String id, String username, String password, String name, String area)
    {
        super(id, username, password, name, area);
    }
    
    @Override
    public String getType()
    {
        return TYPE;
    }
    
    @Override
    public void routine()
    {
        // 进入账户操作界面
        while (true)
        {
            System.out.println("##==========");
            System.out.println("##护士长可以进行以下操作：");
            System.out.println("##指令“list -n”：查看当前区域病房护士信息");
            System.out.println("##指令“list -p”：查看当前区域患者信息");
            System.out.println("##指令“list -r”：查看当前区域病房护士负责的患者信息");
            System.out.println("##指令“list -b”：查看当前区域病床信息");
            System.out.println("##指令“list -o”：查看当前区域病床对应的患者信息");
            System.out.println("##指令“add”：新增当前治疗区域的病房护士");
            System.out.println("##指令“delete”：删除当前治疗区域的病房护士");
            System.out.println("##指令“logout”：注销");
            System.out.println("##指令“exit”：退出系统");
            System.out.println("##==========");
            System.out.print(">");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (command.equals("list -n"))
            {
                listN();
            }
            else if (command.equals("list -p"))
            {
                listP();
            }
            else if (command.equals("list -r"))
            {
                listR();
            }
            else if (command.equals("list -b"))
            {
                listB();
            }
            else if (command.equals("list -o"))
            {
                listO();
            }
            else if (command.equals("add"))
            {
                add();
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
                System.out.println("##无此指令，请重新输入。");
            }
        }
    }
    
    private void listN()
    {
        ArrayList<User> wardNurses = NurseUtil.getWardNursesByArea(getArea());
        System.out.println("##病房护士信息如下");
        System.out.println("##----------");
        for (User wardNurse: wardNurses)
        {
            System.out.println(String.format("##ID：%s，姓名：%s", wardNurse.getId(), wardNurse.getName()));
        }
        System.out.println("##----------");
    }
    
    private void listP()
    {
    
    }
    
    private void listR()
    {
    
    }
    
    private void listB()
    {
        ArrayList<Bed> beds = BedUtil.getBedsByArea(getArea());
        System.out.println("##病床信息如下");
        System.out.println("##----------");
        for (Bed bed: beds)
        {
            System.out.println(String.format("##病床号：%s，病房号：%s", bed.getId(), bed.getrID()));
        }
        System.out.println("##----------");
    }
    
    private void listO()
    {
    
    }
    
    private void add()
    {
        while(true)
        {
            System.out.println("##请输入需要添加的新用户名，或输入“cancel”取消：");
            System.out.print(">");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();
            if (username.equals("cancel"))
            {
                break;
            }
            else if (username.equals("root"))
            {
                System.out.println("##不能注册root账户，请重试。");
            }
            else if (AccountUtil.usernameExists(username))
            {
                System.out.println("##该用户名已被使用，请重试。");
            }
            else
            {
                User user;
            
                // 输入密码
                System.out.println("##请输入新用户的密码，或输入“cancel”取消：");
                System.out.print(">");
                String password = scanner.nextLine();
                if (password.equals("cancel"))
                {
                    return;
                }
            
                // 输入姓名
                System.out.println("##请输入新用户的姓名，或输入“cancel”取消：");
                System.out.print(">");
                String name = scanner.nextLine();
                if (name.equals("cancel"))
                {
                    return;
                }
            
                user = User.getInstance(IDGenerator.generateID(), username, password, name, getArea(), WardNurse.TYPE);
            
                if (user != null)
                {
                    // 向数据库中加入新用户
                    AccountUtil.addUser(user);
                    System.out.println(String.format("##添加新用户%s成功。", user.getUsername()));
                }
                else
                {
                    System.out.println("##添加新用户失败，请重试。");
                }
            }
        }
    }
    
    private void delete()
    {
        while(true)
        {
            System.out.println("##请输入需要删除的用户名称,或输入“cancel”取消：");
            System.out.print(">");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();
            if (username.equals("cancel"))
            {
                break;
            }
            else if (AccountUtil.usernameExists(username))
            {
                User user = AccountUtil.getUser(username);
                if (user != null)
                {
                    if (user.getArea().equals(getArea()) && user.getType().equals(WardNurse.TYPE))
                    {
                        // 删除数据库中用户信息
                        AccountUtil.removeUser(user);
                        System.out.println(String.format("##删除用户%s成功。", user.getUsername()));
                    }
                    else
                    {
                        System.out.println("##只能删除当前治疗区域的病房护士，请重试。");
                    }
                }
            }
            else
            {
                System.out.println("##用户不存在，请重试。");
            }
        }
    }
}
