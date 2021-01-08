/**
 * 急诊护士负责确诊患者的收治
 */

package model.user;

import model.patient.Patient;
import service.patient.PatientUtil;

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
            System.out.println("##指令“transfer”：将待转移患者转入对应区域");
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
                System.out.println("##无此指令，请重新输入。");
            }
        }
    }
    
    private void list()
    {
        while(true)
        {
            //筛选条件：是否可以出院，是否待转移，所在区域，生命状态，病情评级
            Scanner scanner = new Scanner(System.in);
            System.out.println("##请输入筛选条件，或输入“cancel”取消：");
            System.out.println("##（是否可以出院(全部/是/否) 是否待转移(全部/是/否) " +
                    "所在区域(全部/隔离区域/轻症区域/重症区域/危重症区域) " +
                    "生命状态(全部/康复出院/在院治疗/病亡) 病情评级(全部/轻症/重症/危重症)）");
            System.out.print(">");
            
            String optionCanLeave = scanner.next();
            if (optionCanLeave.equals("cancel"))
            {
                break;
            }
            else if (!(optionCanLeave.equals("全部") || optionCanLeave.equals("是") || optionCanLeave.equals("否")))
            {
                System.out.println("##是否可以出院:无此筛选条件，请重新输入。");
                continue;
            }
    
            String optionShouldTransfer = scanner.next();
            if (optionShouldTransfer.equals("cancel"))
            {
                break;
            }
            else if (!(optionShouldTransfer.equals("全部") || optionShouldTransfer.equals("是") ||
                    optionShouldTransfer.equals("否")))
            {
                System.out.println("##是否待转移:无此筛选条件，请重新输入。");
                continue;
            }
    
            String optionArea = scanner.next();
            if (optionArea.equals("cancel"))
            {
                break;
            }
            else if (!(optionArea.equals("全部") || optionArea.equals("隔离区域") || optionArea.equals("轻症区域") ||
                    optionArea.equals("重症区域") || optionArea.equals("危重症区域")))
            {
                System.out.println("##所在区域:无此筛选条件，请重新输入。");
                continue;
            }
    
            String optionLifeState = scanner.next();
            if (optionLifeState.equals("cancel"))
            {
                break;
            }
            else if (!(optionLifeState.equals("全部") || optionLifeState.equals("康复出院") ||
                    optionLifeState.equals("在院治疗") || optionLifeState.equals("病亡")))
            {
                System.out.println("##生命状态:无此筛选条件，请重新输入。");
                continue;
            }
    
            String optionIllState = scanner.next();
            if (optionIllState.equals("cancel"))
            {
                break;
            }
            else if (!(optionIllState.equals("全部") || optionIllState.equals("轻症") ||
                    optionIllState.equals("重症") || optionIllState.equals("危重症")))
            {
                System.out.println("##病情评级:无此筛选条件，请重新输入。");
                continue;
            }
    
            // 查询特定患者信息
            ArrayList<Patient> patients = PatientUtil.getPatients(optionCanLeave, optionShouldTransfer,
                    optionArea, "全部", optionLifeState, optionIllState);
            System.out.println("##查询患者信息如下");
            System.out.println("##----------");
            for (Patient patient: patients)
            {
                System.out.println(String.format("##患者ID：%s, 姓名：%s, 身份证号：%s, 家庭住址：%s, 生命状态：%s",
                        patient.getId(), patient.getName(),
                        patient.getResidentID(), patient.getAddress(), patient.getLifeState()));
                if (patient.getLifeState().equals(Patient.LIFE_ILL))
                {
                    String canLeave = (patient.isCanLeave()) ? "是" : "否";
                    String shouldTransfer = (patient.shouldTransfer()) ? "是" : "否";
                    System.out.println(String.format("##......病情评级：%s, 是否可以出院：%s, 是否待转移：%s",
                            patient.getIllState(), canLeave, shouldTransfer));
                    if (patient.getArea() == null)
                    {
                        System.out.println("##......所在区域：隔离区域");
                    }
                    else
                    {
                        System.out.println(String.format("##......所在区域：%s, 房间号：%s, 床号：%s, 护士号：%s, 护士名：%s",
                                patient.getArea(), patient.getResidentID(), patient.getbID(),
                                patient.getuID(), patient.getuName()));
                    }
                }
            }
            System.out.println("##----------");
            
            // 列出可以出院和待转移人数
            int countCanLeave = 0;
            int countShouldTransfer = 0;
            
            for (Patient patient: patients)
            {
                if (patient.isCanLeave())
                {
                    countCanLeave++;
                }
                if (patient.shouldTransfer())
                {
                    countShouldTransfer++;
                }
            }
            
            if (countCanLeave > 0 || countShouldTransfer > 0)
            {
                System.out.println(String.format("##【提醒】当前有%d名患者可以出院，有%d名患者等待转移",
                        countCanLeave, countShouldTransfer));
                System.out.println("##----------");
            }
        }
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
