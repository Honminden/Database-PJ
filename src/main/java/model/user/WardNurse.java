/**
 * 病房护士
 */

package model.user;

public class WardNurse extends Nurse
{
    public WardNurse(String username, String password)
    {
        super(username, password);
    }
    
    @Override
    public String getType()
    {
        return "WardNurse";
    }
    
    @Override
    public void routine()
    {
        super.routine();
    }
}
