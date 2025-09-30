# postest4PBO

## Profil
---
- **Nama:** Ghendida Gantari Ayari
- **NIM:** 2409116080
- **Tema:** Manajemen Daftar Hewan Di Kebun Binatang

## Deskripsi
---
Program ini adalah sebuah program sederhana CRUD dengan tema manajemen daftar hewan kebun binatang. Di program ini user dapat membuat, membaca, mengedit, dan menghapus data hewannya.

## Main
```
package main;

import java.util.Scanner;

import model.SuaraHewanJava;
import service.HewanService;
import model.Hewan;
import model.Mamalia;
import model.Burung;
import model.SuaraHewanJava;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        HewanService hewanService = new HewanService();
        int pilihan;

        do {
            System.out.println("\n=== Manajemen Daftar Hewan Kebun Binatang ===");
            System.out.println("1. Tambah Hewan");
            System.out.println("2. Lihat Daftar Hewan");
            System.out.println("3. Ubah Data Hewan");
            System.out.println("4. Hapus Hewan");
            System.out.println("5. Suara Hewan");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1: hewanService.tambahHewan(); break;
                case 2: hewanService.lihatHewan(); break;
                case 3: hewanService.ubahHewan(); break;
                case 4: hewanService.hapusHewan(); break;
                case 5:
                    hewanService.tambahHewan(new Mamalia("Singa", "Karnivora", "Savanna", 8, true));
                    hewanService.tambahHewan(new Burung("Merpati", "Aves", "Kota", 2, true));
                    System.out.println("\n>> Contoh suara hewan:");
                    for (Hewan h : hewanService.getDaftarHewan()) {
                        if (h instanceof SuaraHewanJava) {
                            ((SuaraHewanJava) h).bersuara();
                            h.infoTambahan();
                        }
                    }
                    break;
                case 6: System.out.println(">> Terima kasih, program selesai."); break;
                default: System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 6);
      

        input.close();
    }
}
```
Ini adalah class utama tempat program dijalankan.
Menyediakan menu untuk user: tambah hewan, lihat hewan, ubah, hapus, suara hewan, keluar.
Input dipakai untuk membaca pilihan user.
Menggunakan HewanService untuk mengelola daftar hewan.
Pada menu 5, ditambahkan contoh Polymorphism: membuat objek Mamalia dan Burung lalu memanggil method bersuara() sesuai jenis objeknya.

## Package Model

### Class Burung

```
package model;

public class Burung extends Hewan implements SuaraHewanJava {
    private boolean bisaTerbang;

    public Burung(String nama, String jenis, String habitat, float umur, boolean bisaTerbang) {
        super(nama, jenis, habitat, umur);
        this.bisaTerbang = bisaTerbang;
    }

    public boolean isBisaTerbang() { return bisaTerbang; }
    public void setBisaTerbang(boolean bisaTerbang) { this.bisaTerbang = bisaTerbang; }

    @Override
    public void infoTambahan() {
        System.out.println("Burung ini " + (bisaTerbang ? "bisa terbang tinggi." : "tidak bisa terbang."));
    }

    @Override
    public void bersuara() {
        System.out.println(getNama() + " berkicau: Cuit... Cuit...");
    }

    @Override
    public String toString() {
        return super.toString() + " | Burung [Bisa Terbang: " + (bisaTerbang ? "Ya" : "Tidak") + "]";
    }
}

```
Claas Burung adalah turunan dari Hewan dan juga implementasi dari interface SuaraHewanJava.
Menambah atribut khusus: bisaTerbang.
Meng-override method infoTambahan() untuk menjelaskan apakah burung bisa terbang.
Meng-override method bersuara() untuk menampilkan bunyi burung: Cuit… Cuit….
Meng-override toString() supaya menampilkan informasi detail burung ketika dicetak.
Inilah polymorphism melalui overriding.

### Class Hewan

```
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
```

Kelas abstrak dasar yang mewakili hewan secara umum.
Menyimpan atribut: nama, jenis, habitat, umur.
Punya method abstrak infoTambahan() yang wajib diimplementasikan subclass.
Jadi, kelas ini dipakai sebagai blueprint untuk Mamalia, Burung, dan hewan lain.

### Classs Mamalia

```
package model;

public class Mamalia extends Hewan implements SuaraHewanJava {
    private boolean menyusui;

    public Mamalia(String nama, String jenis, String habitat, float umur, boolean menyusui) {
        super(nama, jenis, habitat, umur);
        this.menyusui = menyusui;
    }

    public boolean isMenyusui() { return menyusui; }
    public void setMenyusui(boolean menyusui) { this.menyusui = menyusui; }

    @Override
    public void infoTambahan() {
        System.out.println("Mamalia ini " + (menyusui ? "menyusui anaknya." : "tidak menyusui."));
    }

    @Override
    public void bersuara() {
        System.out.println(getNama() + " mengeluarkan suara: Grrr...");
    }

    @Override
    public String toString() {
        return super.toString() + " | Mamalia [Menyusui: " + (menyusui ? "Ya" : "Tidak") + "]";
    }
}
```
Claas Mamalia juga turunan dari Hewan dan implementasi SuaraHewanJava.
Menambah atribut khusus: menyusui.
Meng-override method infoTambahan() untuk menjelaskan apakah mamalia menyusui.
Meng-override method bersuara() untuk bunyi mamalia: Grrr….
Meng-override toString() supaya ada tambahan info “Menyusui: Ya/Tidak”.
Sama seperti Burung, ini juga salah satu polymorphism karena method yang sama (bersuara) hasilnya beda tergantung objek.


### Interface SuaraHewanJava

```
package model;

public interface SuaraHewanJava {
    void bersuara();
}
```
Interface yang mendefinisikan kontrak method bersuara().
Semua class yang implements SuaraHewanJava (contoh: Burung, Mamalia) wajib menyediakan implementasi method bersuara().
Ini memungkinkan polymorphism karena objek yang berbeda bisa dipanggil lewat tipe SuaraHewanJava.

## Package Service

### Class HewanService

```
package service;

import java.util.ArrayList;
import java.util.Scanner;
import model.Hewan;
import model.Mamalia;
import model.Burung;

public class HewanService {
    private ArrayList<Hewan> daftarHewan = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    // getter daftarHewan yang untuk dipakai di Main
    public ArrayList<Hewan> getDaftarHewan() {
        return daftarHewan;
    }

    // Overloading: versi manual (input user)
    public void tambahHewan() {
        System.out.println("Pilih tipe hewan:");
        System.out.println("1. Mamalia");
        System.out.println("2. Burung");
        System.out.println("3. Hewan umum");
        System.out.print("Pilihan: ");
        int pilihan = input.nextInt();
        input.nextLine();

        System.out.print("Nama Hewan   : ");
        String nama = input.nextLine();
        System.out.print("Jenis Hewan  : ");
        String jenis = input.nextLine();
        System.out.print("Habitat Hewan: ");
        String habitat = input.nextLine();
        System.out.print("Umur Hewan   : ");
        float umur = input.nextFloat();
        input.nextLine();

        Hewan hewan;

        switch (pilihan) {
            case 1:
                System.out.print("Apakah menyusui? (true/false): ");
                boolean menyusui = input.nextBoolean();
                input.nextLine();
                hewan = new Mamalia(nama, jenis, habitat, umur, menyusui);
                break;
            case 2:
                System.out.print("Apakah bisa terbang? (true/false): ");
                boolean bisaTerbang = input.nextBoolean();
                input.nextLine();
                hewan = new Burung(nama, jenis, habitat, umur, bisaTerbang);
                break;
            default:
                // default hewan umum (anonymous class karena Hewan abstract)
                hewan = new Hewan(nama, jenis, habitat, umur) {
                    @Override
                    public void infoTambahan() {
                        System.out.println("Hewan umum tanpa info tambahan khusus.");
                    }
                };
        }

        daftarHewan.add(hewan);
        System.out.println("Hewan berhasil ditambahkan!");
    }

    // Overloading: versi langsung dengan parameter
    public void tambahHewan(Hewan hewan) {
        daftarHewan.add(hewan);
        System.out.println("Hewan berhasil ditambahkan lewat parameter!");
    }

    public void lihatHewan() {
        if (daftarHewan.isEmpty()) {
            System.out.println("Belum ada data hewan.");
        } else {
            for (int i = 0; i < daftarHewan.size(); i++) {
                System.out.println((i + 1) + ". " + daftarHewan.get(i));
            }
        }
    }

    public void ubahHewan() {
        lihatHewan();
        if (!daftarHewan.isEmpty()) {
            System.out.print("Pilih nomor hewan yang ingin diubah: ");
            int index = input.nextInt();
            input.nextLine();

            if (index > 0 && index <= daftarHewan.size()) {
                Hewan hewanLama = daftarHewan.get(index - 1);

                System.out.print("Nama Hewan Baru   : ");
                String nama = input.nextLine();
                System.out.print("Jenis Hewan Baru  : ");
                String jenis = input.nextLine();
                System.out.print("Habitat Hewan Baru: ");
                String habitat = input.nextLine();
                System.out.print("Umur Hewan Baru   : ");
                float umur = input.nextFloat();
                input.nextLine();

                if (hewanLama instanceof Mamalia) {
                    System.out.print("Apakah menyusui? (true/false): ");
                    boolean menyusui = input.nextBoolean();
                    input.nextLine();
                    daftarHewan.set(index - 1, new Mamalia(nama, jenis, habitat, umur, menyusui));
                } else if (hewanLama instanceof Burung) {
                    System.out.print("Apakah bisa terbang? (true/false): ");
                    boolean bisaTerbang = input.nextBoolean();
                    input.nextLine();
                    daftarHewan.set(index - 1, new Burung(nama, jenis, habitat, umur, bisaTerbang));
                } else {
                    daftarHewan.set(index - 1, new Hewan(nama, jenis, habitat, umur) {
                        @Override
                        public void infoTambahan() {
                            System.out.println("Hewan umum tanpa info tambahan khusus.");
                        }
                    });
                }

                System.out.println("Data hewan berhasil diubah!");
            } else {
                System.out.println("Nomor tidak valid.");
            }
        }
    }

    public void hapusHewan() {
        lihatHewan();
        if (!daftarHewan.isEmpty()) {
            System.out.print("Pilih nomor hewan yang ingin dihapus: ");
            int index = input.nextInt();
            input.nextLine();

            if (index > 0 && index <= daftarHewan.size()) {
                daftarHewan.remove(index - 1);
                System.out.println("Data hewan berhasil dihapus!");
            } else {
                System.out.println("Nomor tidak valid.");
            }
        }
    }
}
```
ArrayList<Hewan> daftarHewan dipakai untuk menyimpan data hewan.
tambahHewan() (versi input user) → menambah data hewan baru sesuai pilihan user.<br>
tambahHewan(Hewan hewan) (versi parameter) → menambah data langsung lewat objek → ini contoh method overloading.<br>
lihatHewan() → menampilkan semua daftar hewan.<br>
ubahHewan() → mengubah data hewan berdasarkan index.<br>
hapusHewan() → menghapus hewan dari daftar.<br>
Di sinilah OOP Encapsulation, karena semua logika CRUD data hewan dikumpulkan di satu class service.<br>

## Output
<img width="473" height="671" alt="image" src="https://github.com/user-attachments/assets/0261fa5d-77c8-4ca9-894e-8c32801cb1e3" />
<br>
untuk output dan menu sama saja seperti sebelumnya tapi ada tambahan yaitu menu 5 Suara Hewan, untuk menampilkan bagiamana suara hewan dan
penerapan abstraction sekaligus polymorphism.

