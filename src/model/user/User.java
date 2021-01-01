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
    
    public abstract void routine();
}
