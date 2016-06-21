package com.checkupregister;

public class History {

    private String id;
    private String user;
    private String dokter;
    private String status;

    public void setId (String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public void setUser (String user)
    {
        this.user = user;
    }

    public String getUser()
    {
        return user;
    }

    public void setDokter (String dokter)
    {
        this.dokter = dokter;
    }

    public String getDokter()
    {
        return dokter;
    }
    
    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
}
