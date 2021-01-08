/**
 * 患者
 */

package model.patient;

public class Patient
{
    public static String LIFE_RECOVERED = "康复出院";
    public static String LIFE_ILL = "在院治疗";
    public static String LIFE_DEAD = "病亡";
    public static String ILL_MODERATE = "轻症";
    public static String ILL_SERIOUS = "重症";
    public static String ILL_SEVERE = "危重症";
    
    private String id;
    private String name;
    private String residentID;
    private String address;
    private String lifeState;
    private String illState;
    private String bID; // 床位号
    private String rID; // 房间号
    private String uID; // 护士号
    
    public Patient(String id, String name, String residentID, String address,
                   String lifeState, String illState, String bID, String rID, String uID)
    {
        this.id = id;
        this.name = name;
        this.residentID = residentID;
        this.address = address;
        this.lifeState = lifeState;
        this.illState = illState;
        this.bID = bID;
        this.rID = rID;
        this.uID = uID;
    }
    
    public String getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getResidentID()
    {
        return residentID;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public String getLifeState()
    {
        return lifeState;
    }
    
    public String getIllState()
    {
        return illState;
    }
    
    public String getbID()
    {
        return bID;
    }
    
    public String getrID()
    {
        return rID;
    }
    
    public String getuID()
    {
        return uID;
    }
    
    public static boolean isValidLifeState(String lifeState)
    {
        return (lifeState.equals(LIFE_RECOVERED) || lifeState.equals(LIFE_ILL) || lifeState.equals(LIFE_DEAD));
    }
    
    
    public static boolean isValidIllState(String illState)
    {
        return (illState.equals(ILL_MODERATE) || illState.equals(ILL_SERIOUS) || illState.equals(ILL_SEVERE));
    }
}
