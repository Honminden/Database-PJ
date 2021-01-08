/**
 * 急诊护士负责确诊患者的收治
 */

package model.user;

import model.patient.Patient;

import java.util.ArrayList;
import java.util.Scanner;

public class EmergencyNurse extends User
{
    public static final String TYPE = "急诊护士";
    
    EmergencyNurse(String id, String username, String password, String name, String area)
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
            System.out.println("##急诊护士可以进行以下操作：");
            System.out.println("##指令“list”：查看患者信息");
            System.out.println("##指令“add”：录入新患者信息");
            System.out.println("##指令“transfer”：将等待患者转入治疗区");
            System.out.println("##指令“logout”：注销");
            System.out.println("##指令“exit”：退出系统");
            System.out.println("##==========");
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
            else if (command.equals("transfer"))
            {
                transfer();
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
        /*
        ArrayList<Patient> patients;
        System.out.println("##全部患者信息如下");
        System.out.println("##----------");
        for (Patient patient: patients)
        {
            System.out.println(String.format("##患者ID：%s, 姓名：%s, 身份证号：%s, 家庭住址：%s, 生命状态：%s, 病情评级：%s",
                    user.getUsername(), user.getPassword(), user.getName(), user.getType(), user.getArea()));
        }
        System.out.println("##----------");
         */
    }
    
    private void add()
    {
        /*
        while(true)
        {
            Scanner scanner = new Scanner(System.in);
            Patient patient;
            
            // 输入姓名
            System.out.println("##请输入患者的姓名，或输入“cancel”取消：");
            System.out.print(">");
            String name = scanner.nextLine();
            if (name.equals("cancel"))
            {
                return;
            }
    
            // 输入身份证号
            System.out.println("##请输入患者的身份证号，或输入“cancel”取消：");
            System.out.print(">");
            String residentID = scanner.nextLine();
            if (residentID.equals("cancel"))
            {
                return;
            }
    
            // 输入家庭住址
            System.out.println("##请输入患者的家庭住址，或输入“cancel”取消：");
            System.out.print(">");
            String address = scanner.nextLine();
            if (address.equals("cancel"))
            {
                return;
            }
            
            // 输入病情评级
            String illState;
            while (true)
            {
                System.out.println("##请选择患者的病情评级（轻症/重症/危重症），或输入“cancel”取消：");
                System.out.print(">");
                illState = scanner.nextLine();
                if (illState.equals("cancel"))
                {
                    return;
                }
                else if (Patient.isValidIllState(illState))
                {
                    break;
                }
                else
                {
                    System.out.println("##无此病情评级，请重试。");
                }
            }
    
            if (UserRestriction.checkUserAreaRestriction(type, area))
            {
                System.out.println(String.format("##【root账户权限操作】当前区域已有%s，请重试", type));
                continue;
            }
    
            user = User.getInstance(IDGenerator.generateID(), username, password, name, area, type);
    
            if (user != null)
            {
                // 向数据库中加入新用户
                AccountUtil.addUser(user);
                System.out.println(String.format("##【root账户权限操作】添加新用户%s成功。", user.getUsername()));
            }
            else
            {
                System.out.println("##【root账户权限操作】添加新用户失败，请重试。");
            }
        }
        */
    }
    
    private void transfer()
    {
    
    }
}
