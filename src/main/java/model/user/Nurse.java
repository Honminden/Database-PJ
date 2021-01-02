/**
 * 护士
 */

package model.user;

public class Nurse extends User
{
    public Nurse(String username, String password)
    {
        super(username, password);
    }
    
    @Override
    public String getType()
    {
        return "Nurse";
    }
    
    @Override
    public void routine()
    {
    
    }
}
