package model.user;

public abstract class User
{
    private String username;
    private String password;
    
    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername()
    {
        return this.username;
    }
    public String getPassword()
    {
        return this.password;
    }
    public String getType()
    {
        return "User";
    }
    
    public abstract void routine();
    
    public static User getInstance(String username, String password, String type)
    {
        if (type.equals("Doctor"))
        {
            return new Doctor(username, password);
        }
        else if (type.equals("ChiefNurse"))
        {
            return new ChiefNurse(username, password);
        }
        else if (type.equals("EmergencyNurse"))
        {
            return new EmergencyNurse(username, password);
        }
        else if (type.equals("WardNurse"))
        {
            return new WardNurse(username, password);
        }
        else
        {
            return null;
        }
    }
}
