/**
 * 主治医生
 */

package model.user;

import service.patient.NurseUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class Doctor extends User
{
    public static final String TYPE = "主治医生";
    
    Doctor(String id, String username, String password, String name, String area)
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
            System.out.println("##主治医生可以进行以下操作：");
            System.out.println("##指令“list -n”：查看当前区域护士长及病房护士信息");
            System.out.println("##指令“list -p”：查看当前区域患者信息");
            System.out.println("##指令“list -r”：查看当前区域病房护士负责的患者信息");
            System.out.println("##指令“add”：新增当前治疗区域的病人的核酸检测单");
            System.out.println("##指令“revise”：修改当前治疗区域的病人的病情评级和生命状态");
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
            else if (command.equals("add"))
            {
                add();
            }
            else if (command.equals("revise"))
            {
                revise();
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
        User chiefNurse = NurseUtil.getChiefNurseByArea(getArea());
        ArrayList<User> wardNurses = NurseUtil.getWardNursesByArea(getArea());
        System.out.println("##护士长信息如下");
        System.out.println("##----------");
        System.out.println(String.format("##ID：%s，姓名：%s", chiefNurse.getId(), chiefNurse.getName()));
        System.out.println("##----------");
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
    
    private void add()
    {
    
    }
    
    private void revise()
    {
    
    }
}
