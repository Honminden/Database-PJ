/**
 * 护士长
 */

package model.user;

public class ChiefNurse extends User
{
    public static final String TYPE = "护士长";
    
    ChiefNurse(String id, String username, String password, String name, String area)
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
