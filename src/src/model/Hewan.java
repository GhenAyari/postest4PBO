package model;

public abstract class Hewan {
    private String nama;
    private String jenis;
    private String habitat;
    private float umur;

    public Hewan(String nama, String jenis, String habitat, float umur) {
        this.nama = nama;
        this.jenis = jenis;
        this.habitat = habitat;
        this.umur = umur;
    }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }

    public String getHabitat() { return habitat; }
    public void setHabitat(String habitat) { this.habitat = habitat; }

    public float getUmur() { return umur; }
    public void setUmur(float umur) { this.umur = umur; }

    // abstraction
    public abstract void infoTambahan();

    @Override
    public String toString() {
        return "Nama: " + nama + " | Jenis: " + jenis + " | Habitat: " + habitat + " | Umur: " + umur + " tahun";
    }
}
