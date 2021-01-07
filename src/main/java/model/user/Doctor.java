/**
 * 主治医生
 */

package model.user;

public class Doctor extends User
{
    public static final String TYPE = "主治医生";
    
    Doctor(String id, String username, String password, String name, String area)
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
