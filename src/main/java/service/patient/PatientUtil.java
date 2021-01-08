/**
 * 实现患者信息管理功能
 */

package service.patient;

import model.patient.Patient;
import model.user.User;
import service.sql.SQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PatientUtil
{
    public static ArrayList<Patient> getPatients(String area, String lifeState, String illState)
    {
        
        ArrayList<Patient> patients = new ArrayList<>();
        /*
        try
        {
            Connection con = SQLUtil.getConnection();
            PreparedStatement findPatients;
            // 获取患者信息、对应护士id、对应床位id和所属区域
            String basicSelect = "select * from patient left join nurse_for_patient on patient.p_id=nurse_for_patient.p_id left join bed_for_patient on patient.p_id=bed_for_patient.p_id";
            final String ALL = "全部";
            boolean areaIsAll = area.equals(ALL);
            boolean lifeStateIsAll = lifeState.equals(ALL);
            boolean illStateIsAll = illState.equals(ALL);
            if (areaIsAll && lifeStateIsAll && illStateIsAll)
            {
                findPatients = con.prepareStatement(basicSelect);
            }
            else if (areaIsAll && lifeStateIsAll)
            {
                findPatients = con.prepareStatement(basicSelect + " where ill_state=?");
                findPatients.setString(1, illState);
            }
            else if (areaIsAll && illStateIsAll)
            {
                findPatients = con.prepareStatement(basicSelect + " where life_state=?");
                findPatients.setString(1, lifeState);
            }
            else if (lifeStateIsAll && illStateIsAll)
            {
                findPatients = con.prepareStatement(basicSelect + " where area=?");
                findPatients.setString(1, actualArea);
            }
            else if (areaIsAll)
            {
                findPatients = con.prepareStatement(basicSelect + " where life_state=? and ill_state=?");
                findPatients.setString(1, lifeState);
                findPatients.setString(2, illState);
            }
            else if (lifeStateIsAll)
            {
                findPatients = con.prepareStatement(basicSelect + " where area=? and ill_state=?");
                findPatients.setString(1, actualArea);
                findPatients.setString(2, illState);
            }
            else if (illStateIsAll)
            {
                findPatients = con.prepareStatement(basicSelect + " where area=? and life_state=?");
                findPatients.setString(1, actualArea);
                findPatients.setString(2, lifeState);
            }
            else
            {
                findPatients = con.prepareStatement(basicSelect + " where area=? " +
                        "and life_state=? and ill_state=?");
                findPatients.setString(1, actualArea);
                findPatients.setString(2, lifeState);
                findPatients.setString(3, illState);
            }
            
            // 获取特定患者
            try (ResultSet patientsFound = findPatients.executeQuery())
            {
                while (patientsFound.next())
                {
                    String id = patientsFound.getString("p_id");
                    String name = patientsFound.getString("name");
                    String residentID = patientsFound.getString("resident_ID");
                    String address = patientsFound.getString("address");
                    String newLifeState = patientsFound.getString("life_state");
                    String newIllState = patientsFound.getString("ill_state");
                    String bID = patientsFound.getString("b_id");
                    String rID = patientsFound.getString("r_id");
                    String uID = patientsFound.getString("u_id");
                    Patient patient = new Patient(id, name, residentID, address,
                            newLifeState, newIllState, bID, rID, uID);
                    if (patient != null)
                    {
                        patients.add(patient);
                    }
                }
            }
        
            con.close();
        }
        catch (Exception e)
        {
            SQLUtil.handleExceptions(e);
        }
        */
        
        return patients;
    }
    
    
}
