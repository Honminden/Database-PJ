/**
 * 病房护士
 */

package model.user;

public class WardNurse extends User
{
    public static final String TYPE = "病房护士";
    
    WardNurse(String id, String username, String password, String name, String area)
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
