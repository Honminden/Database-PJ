/**
 * 患者
 */

package model.patient;

public class Patient
{
    private String id;
    private String name;
    private String residentID;
    private String address;
    private String lifeState;
    private String illState;
    
    public Patient(String id, String name, String residentID, String address, String lifeState, String illState)
    {
        this.id = id;
        this.name = name;
        this.residentID = residentID;
        this.address = address;
        this.lifeState = lifeState;
        this.illState = illState;
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
}
