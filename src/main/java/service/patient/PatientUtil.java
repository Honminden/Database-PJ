/**
 * 实现患者信息管理功能
 */

package service.patient;

import model.patient.Patient;
import service.sql.SQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PatientUtil
{
    public static ArrayList<Patient> getAllPatients()
    {
    
        ArrayList<Patient> patients = new ArrayList<>();
        try
        {
            // 获取全部患者
            Connection con = SQLUtil.getConnection();
            String sql = "select patient.p_ID as p_ID, patient.name as name, patient.resident_ID as resident_ID, patient.address as address, \n" +
                    "patient.life_state as life_state, patient.ill_state as ill_state, \n" +
                    "user.u_ID as u_ID, user.name as u_name, bed.b_ID as b_ID, room.r_ID as r_ID, room.area as area, \n" +
                    "(select max(temp.temperature) as temperature\n" +
                    "from (select * from daily_state where p_ID=patient.p_ID order by date desc limit 3) as temp) as temperature,\n" +
                    "(select result from test_sheet where p_ID=patient.p_ID order by date desc limit 1) as result1,\n" +
                    "(select result from test_sheet where p_ID=patient.p_ID order by date desc limit 1, 1) as result2\n" +
                    "\tfrom patient left join (nurse_for_patient natural join user) \n" +
                    "    on patient.p_ID = nurse_for_patient.p_ID \n" +
                    "    left join (bed_for_patient natural join bed natural join room_in_bed natural join room) \n" +
                    "    on patient.p_ID = bed_for_patient.p_ID";
            PreparedStatement findAllPatients = con.prepareStatement(sql);
            try (ResultSet patientsFound = findAllPatients.executeQuery())
            {
                while (patientsFound.next())
                {
                    String id = patientsFound.getString("p_id");
                    String name = patientsFound.getString("name");
                    String residentID = patientsFound.getString("resident_ID");
                    String address = patientsFound.getString("address");
                    String lifeState = patientsFound.getString("life_state");
                    String illState = patientsFound.getString("ill_state");
                    String uID = patientsFound.getString("u_ID");
                    String uName = patientsFound.getString("u_name");
                    String bID = patientsFound.getString("b_ID");
                    String rID = patientsFound.getString("r_ID");
                    String area = patientsFound.getString("area");
                    String temperature = patientsFound.getString("temperature");
                    String result1 = patientsFound.getString("result1");
                    String result2 = patientsFound.getString("result2");
                    
                    boolean canLeave = Patient.checkCanLeave(illState, temperature, result1, result2);
                    
                    Patient patient = new Patient(id, name, residentID, address, lifeState, illState,
                            canLeave, bID, rID, area, uID, uName);
                    patients.add(patient);
                }
            }
        
            con.close();
        }
        catch (Exception e)
        {
            SQLUtil.handleExceptions(e);
        }
        return patients;
    }
    
    public static ArrayList<Patient> getPatients(String optionCanLeave, String optionShouldTransfer, String optionArea,
                                                 String optionUID, String optionLifeState, String optionIllState)
    {
        ArrayList<Patient> patients = new ArrayList<>();
        final String ALL = "全部";
        boolean canLeaveIsAll = optionCanLeave.equals(ALL);
        boolean shouldTransferIsAll = optionShouldTransfer.equals(ALL);
        boolean areaIsAll = optionArea.equals(ALL);
        boolean uIDIsAll = optionUID.equals(ALL);
        boolean lifeStateIsAll = optionLifeState.equals(ALL);
        boolean illStateIsAll = optionIllState.equals(ALL);
        if (canLeaveIsAll && shouldTransferIsAll && areaIsAll && uIDIsAll && lifeStateIsAll && illStateIsAll)
        {
            return getAllPatients();
        }
        
        try
        {
            // 获取指定患者
            Connection con = SQLUtil.getConnection();
            String basicSelect = "select * from (select patient.p_ID as p_ID, patient.name as name, patient.resident_ID as resident_ID, patient.address as address, \n" +
                    "patient.life_state as life_state, patient.ill_state as ill_state, \n" +
                    "user.u_ID as u_ID, user.name as u_name, bed.b_ID as b_ID, room.r_ID as r_ID, room.area as area, \n" +
                    "(select max(temp.temperature) as temperature\n" +
                    "from (select * from daily_state where p_ID=patient.p_ID order by date desc limit 3) as temp) as temperature,\n" +
                    "(select result from test_sheet where p_ID=patient.p_ID order by date desc limit 1) as result1,\n" +
                    "(select result from test_sheet where p_ID=patient.p_ID order by date desc limit 1, 1) as result2\n" +
                    "\tfrom patient left join (nurse_for_patient natural join user) \n" +
                    "    on patient.p_ID = nurse_for_patient.p_ID \n" +
                    "    left join (bed_for_patient natural join bed natural join room_in_bed natural join room) \n" +
                    "    on patient.p_ID = bed_for_patient.p_ID) as patient_info where p_ID is not null";
            String sql = basicSelect;
            
            // 添加筛选项
            if (!canLeaveIsAll)
            {
                // 可以出院的标准是：病情评级为轻症，连续3天体温低于37.3摄氏度，连续两次核酸检测结果为阴性
                String restrictCanLeave = "(ill_state='轻症' and temperature < 37.3 and " +
                        "result1='阴性' and result2='阴性')";
                if (optionCanLeave.equals("是"))
                {
                    sql += " and " + restrictCanLeave + " is true";
                }
                else if (optionCanLeave.equals("否"))
                {
                    sql += " and " + restrictCanLeave + " is not true";
                }
            }
            
            if (!shouldTransferIsAll)
            {
                //待转移的标准是：在隔离区（所在区域为null），或所在区域与病情评级不符合
                String restrictShouldTransfer = "(area is null or not" +
                        "((ill_state='轻症' and area='轻症区域') or " +
                        "(ill_state='重症' and area='重症区域') or " +
                        "(ill_state='危重症' and area='危重症区域')))";
                if (optionShouldTransfer.equals("是"))
                {
                    sql += " and " + restrictShouldTransfer + " is true";
                }
                else if (optionShouldTransfer.equals("否"))
                {
                    sql += " and " + restrictShouldTransfer + " is not true";
                }
            }
            
            ArrayList<String> preparedArgs = new ArrayList<>();
            
            if (!areaIsAll)
            {
                if (optionArea.equals("隔离区域"))
                {
                    sql += " and area is null";
                }
                else
                {
                    sql += " and area=?";
                    preparedArgs.add(optionArea);
                }
            }
    
            if (!uIDIsAll)
            {
                sql += " and u_id=?";
                preparedArgs.add(optionUID);
            }
    
            if (!lifeStateIsAll)
            {
                sql += " and life_state=?";
                preparedArgs.add(optionLifeState);
            }
    
            if (!illStateIsAll)
            {
                sql += " and ill_state=?";
                preparedArgs.add(optionIllState);
            }
            
            PreparedStatement findCertainPatients = con.prepareStatement(sql);
            int count = 1;
            for (String arg : preparedArgs)
            {
                findCertainPatients.setString(count, arg);
                count++;
            }
            
            System.out.println(findCertainPatients);
            
            try (ResultSet patientsFound = findCertainPatients.executeQuery())
            {
                while (patientsFound.next())
                {
                    String id = patientsFound.getString("p_id");
                    String name = patientsFound.getString("name");
                    String residentID = patientsFound.getString("resident_ID");
                    String address = patientsFound.getString("address");
                    String lifeState = patientsFound.getString("life_state");
                    String illState = patientsFound.getString("ill_state");
                    String uID = patientsFound.getString("u_ID");
                    String uName = patientsFound.getString("u_name");
                    String bID = patientsFound.getString("b_ID");
                    String rID = patientsFound.getString("r_ID");
                    String area = patientsFound.getString("area");
                    String temperature = patientsFound.getString("temperature");
                    String result1 = patientsFound.getString("result1");
                    String result2 = patientsFound.getString("result2");
                
                    boolean canLeave = Patient.checkCanLeave(illState, temperature, result1, result2);
                
                    Patient patient = new Patient(id, name, residentID, address, lifeState, illState,
                            canLeave, bID, rID, area, uID, uName);
                    patients.add(patient);
                }
            }
        
            con.close();
        }
        catch (Exception e)
        {
            SQLUtil.handleExceptions(e);
        }
        
        return patients;
    }
}
