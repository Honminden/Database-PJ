/**
 * 主治医生
 */

package model.user;

public class Doctor extends User
{
    public Doctor(String username, String password)
    {
        super(username, password);
    }
    
    @Override
    public String getType()
    {
        return "Doctor";
    }
    
    @Override
    public void routine()
    {
    
    }
}
