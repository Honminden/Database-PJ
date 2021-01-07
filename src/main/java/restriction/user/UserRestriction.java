/**
 * 对创建和修改用户的限制
 */

package restriction.user;

import model.user.ChiefNurse;
import model.user.Doctor;
import model.user.User;
import service.sql.SQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRestriction
{
    public static boolean checkUserAreaRestriction(String type, String area)
    {
        if (type.equals(Doctor.TYPE) || type.equals(ChiefNurse.TYPE))
        {
            try
            {
                // 查找特定区域的医生
                Connection con = SQLUtil.getConnection();
                PreparedStatement findDoctorInArea = con.prepareStatement("select * from user where post=? " +
                        "and area=?");
                findDoctorInArea.setString(1, type);
                findDoctorInArea.setString(2, area);
                try (ResultSet userFound = findDoctorInArea.executeQuery())
                {
                    boolean found = userFound.next();
                    con.close();
                    return found;
                }
            }
            catch (Exception e)
            {
                SQLUtil.handleExceptions(e);
            }
        }
        
        return true;
    }
}
