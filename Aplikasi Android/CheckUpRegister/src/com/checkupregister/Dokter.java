package com.checkupregister;

public class Dokter {

    private String id_dokter;
    private String nama_dokter;
    private String hari_dokter;
    private String jam;

    public void setDokterId (String id_dokter)
    {
        this.id_dokter = id_dokter;
    }

    public String getDokterId()
    {
        return id_dokter;
    }

    public void setDokterNama (String nama_dokter)
    {
        this.nama_dokter = nama_dokter;
    }

    public String getDokterNama()
    {
        return nama_dokter;
    }

    public void setDokterHari (String hari_dokter)
    {
        this.hari_dokter = hari_dokter;
    }

    public String getDokterHari()
    {
        return hari_dokter;
    }
    
    public void setDokterJam (String jam)
    {
        this.jam = jam;
    }

    public String getDokterJam()
    {
        return jam;
    }
}
