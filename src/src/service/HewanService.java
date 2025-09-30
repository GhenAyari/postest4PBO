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
        System.out.println("");
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
