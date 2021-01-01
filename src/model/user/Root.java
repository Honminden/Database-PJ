/**
 * root账户可以添加新用户
 */

package model.user;

public class Root extends User
{
    public static final String USERNAME = "root";
    public static final String PASSWORD = "123123";
    
    public Root()
    {
        super(USERNAME, PASSWORD);
    }
    
    @Override
    public void routine()
    {
        System.out.println("##【】");
    }
}
