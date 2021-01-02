/**
 * 护士长
 */

package model.user;

public class ChiefNurse extends User
{
    
    public ChiefNurse(String username, String password)
    {
        super(username, password);
    }
    
    @Override
    public String getType()
    {
        return "ChiefNurse";
    }
    
    @Override
    public void routine()
    {
    
    }
}
