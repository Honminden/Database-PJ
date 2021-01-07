/**
 * 急诊护士
 */

package model.user;

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
    
    }
}
