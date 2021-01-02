/**
 * 急诊护士
 */

package model.user;

public class EmergencyNurse extends Nurse
{
    public EmergencyNurse(String username, String password)
    {
        super(username, password);
    }
    
    @Override
    public String getType()
    {
        return "EmergencyNurse";
    }
    
    @Override
    public void routine()
    {
        super.routine();
    }
}
