import java.util.*;
import java.text.*;
import java.util.Scanner;

public class Perpustakaan {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String namaMhs = "Indira";
        String password;
        int nim = 234150001;

        String judul1 = "C#";
        String kode_buku1 = "A001";
        String pengarang1 = "Indira";
        int thn_terbit1 = 2020;
        String penerbit1 = "Explorer";
        int stok1 = 5;

        String judul2 = "Java";
        String kode_buku2 = "A002";
        String pengarang2 = "Aurah";
        int thn_terbit2 = 2020;
        String penerbit2 = "Explorer";
        int stok2 = 5;

        int dendaPerMenit = 10000;
        int batasWaktuPeminjaman = 1;
        Date tanggalPengembalian = new Date();

        System.out.println("SISTEM PERPUSTAKAAN");

        // Login
        System.out.print("\nMasukkan Username anda: ");
        nim = scan.nextInt();
        System.out.print("Masukkan Password anda: ");
        password = scan.next();

        if (nim == 234150001 && password.equals("123")) {
            System.out.println("Login Berhasil! SELAMAT DATANG " + namaMhs);
        } else {
            System.out.println("Login Gagal! Username atau Password salah");
        }

        boolean type = true;
        while (type) {

            System.out.println("\nMenu:");
            System.out.println("1. Tampilkan Daftar Buku");
            System.out.println("2. Tambah Data Buku");
            System.out.println("3. Pinjam Buku");
            System.out.println("4. Kembalikan Buku");
            System.out.println("5. Tampilkan Informasi Buku");
            System.out.println("6. Keluar");
            System.out.print("\nPilih menu (1/2/3/4/5/6): ");

            int perintah = scan.nextInt();

            switch (perintah) {
                // jika pilih case 1 /menu 1 maka akan menjalankan perintah case 1 dengan
                // memunculkan daftar bukunya
                case 1:
                    System.out.println("\nDAFTAR BUKU:");
                    System.out.println("1. " + judul1);
                    System.out.println("2. " + judul2);
                    // break;
                case 2:
                    System.out.println("\nTAMBAH BUKU");
                    System.out.print("Masukkan judul buku\t: ");
                    String judul = scan.next();
                    System.out.print("Masukkan kode buku\t: ");
                    String kode_buku = scan.next();
                    System.out.print("Masukkan nama pengarang\t: ");
                    String pengarang = scan.next();
                    System.out.print("Masukkan tahun terbit\t: ");
                    int thn_terbit = scan.nextInt();
                    System.out.print("Masukkan nama penerbit\t: ");
                    String penerbit = scan.next();
                    System.out.print("Masukkan jumlah stok\t: ");
                    int stok = scan.nextInt();

                    System.out.println("Judul buku: " + judul + ", " + "Kode buku: " + kode_buku + ", "
                            + "Nama pengarang: " + pengarang + ", " + "Tahun terbit: " + thn_terbit + ", "
                            + "Nama penerbit: " + penerbit + ", " + "Stok: " + stok + " Berhasil ditambahkan");

                    // jika pilih case 3/menu 3 maka akan menjalankan perintah case 3 dengan
                    // memasukkan judul buku
                    // nahhh case 3 ini bisa disebut sebagai transaksi peminjaman
                case 3:
                    System.out.println("\nFORM PEMINJAMAN");
                    System.out.println("Nama Mahasiswa\t: " + namaMhs);
                    System.out.println("NIM\t\t: " + nim);
                    System.out.print("\nMasukkan judul buku yang ingin dipinjam: ");
                    scan.nextLine();
                    String judulPinjam = scan.nextLine();
                    if (judulPinjam.equalsIgnoreCase(judul1)) {
                        if (stok1 > 0) {
                            stok1--;
                            Date tanggalPeminjaman = new Date();
                            // Date tanggalPengembalian = new Date();
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(tanggalPengembalian);
                            calendar.add(Calendar.MINUTE, 1); // Tambahkan 1 menit untuk tanggal pengembalian
                            tanggalPengembalian = calendar.getTime();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            System.out.println("Tanggal Peminjaman\t: " + dateFormat.format(tanggalPeminjaman));
                            System.out.println("Tanggal Pengembalian\t: " + dateFormat.format(tanggalPengembalian));
                            System.out.println("Buku " + judul1 + " berhasil dipinjam.");
                            System.out.println("Judul buku: " + judul1 + ", " + "Kode buku: " + kode_buku1 + ", "
                                    + "Stok: " + stok1);
                        } else {
                            System.out.println("Stok buku " + judul1 + " habis.");
                        }
                    } else if (judulPinjam.equalsIgnoreCase(judul2)) {
                        if (stok2 > 0) {
                            stok2--;
                            Date tanggalPeminjaman = new Date();
                            // Date tanggalPengembalian = new Date();
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(tanggalPengembalian);
                            calendar.add(Calendar.MINUTE, 1); // Tambahkan 1 menit untuk tanggal pengembalian
                            tanggalPengembalian = calendar.getTime();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            System.out.println("Tanggal Peminjaman\t: " + dateFormat.format(tanggalPeminjaman));
                            System.out.println("Tanggal Pengembalian\t: " + dateFormat.format(tanggalPengembalian));
                            System.out.println("Buku " + judul2 + " berhasil dipinjam.");
                            System.out.println("Judul buku: " + judul2 + ", " + "Kode buku: " + kode_buku2 + ", "
                                    + "Stok: " + stok2);
                        } else {
                            System.out.println("Stok buku " + judul2 + " habis.");
                        }
                    } else {
                        System.out.println("Buku dengan judul " + judulPinjam + " tidak ditemukan.");
                    }
                    // break;

                    // Nahh case 3 ini hampir sama kek case 2 kita tinggal masukno judul buku yang
                    // mau dikembalikan
                    // case 3 ini bisa disebut sebagai transaksi pengembalian
                case 4:
                    System.out.println("\nFORM PENGEMBALIAN");
                    System.out.print("Masukkan judul buku yang ingin dikembalikan: ");
                    // scan.nextLine(); // Membersihkan buffer
                    String judulKembali = scan.next();
                    if (judulKembali.equalsIgnoreCase(judul1)) {
                        if (stok1 > 0) {
                            stok1++;
                            System.out.println("Buku " + judul1 + " berhasil dikembalikan.");
                            System.out.println("Judul buku: " + judul1 + ", " + "Kode buku: " + kode_buku1 + ", "
                                    + "Stok: " + stok1);
                            long selisihMenit = (new Date().getTime() - tanggalPengembalian.getTime()) / (60 * 1000);
                            if (selisihMenit > 0) {
                                double denda = dendaPerMenit * selisihMenit;
                                System.out.println("Denda yang harus dibayar: Rp. " + denda);
                            }
                        } else {
                            System.out.println("Stok buku " + judul1 + " habis.");
                        }
                    } else if (judulKembali.equalsIgnoreCase(judul2)) {
                        if (stok2 > 0) {
                            stok2++;
                            System.out.println("Buku " + judul2 + " berhasil dikembalikan.");
                            System.out.println("Judul buku: " + judul2 + ", " + "Kode buku: " + kode_buku2 + ", "
                                    + "Stok: " + stok2);
                            long selisihMenit = (new Date().getTime() - tanggalPengembalian.getTime()) / (60 * 1000);
                            if (selisihMenit > 0) {
                                double denda = dendaPerMenit * selisihMenit;
                                System.out.println("Denda yang harus dibayar: Rp. " + denda);
                            }
                        } else {
                            System.out.println("Stok buku " + judul2 + " habis.");
                        }
                    } else {
                        System.out.println("Buku dengan judul " + judulKembali + " tidak ditemukan.");
                    }
                    // break;

                    // lek case 4 itu perintah nampilin info buku, dari judul buku, nama penerbit,
                    // nama pengarang, dll
                case 5:
                    System.out.println("\nINFORMASI BUKU:");
                    System.out.println("1. " + "Judul buku: " + judul1 + ", " + "Kode buku: " + kode_buku1 + ", "
                            + "Nama pengarang: " + pengarang1 + ", " + "Tahun terbit: " + thn_terbit1 + ", "
                            + "Nama penerbit: "
                            + penerbit1 + ", " + "Stok: " + stok1);
                    System.out.println("2. " + "Judul buku: " + judul2 + ", " + "Kode buku: " + kode_buku2 + ", "
                            + "Nama pengarang: " + pengarang2 + ", " + "Tahun terbit: " + thn_terbit2 + ", "
                            + "Nama penerbit: "
                            + penerbit2 + ", " + "Stok: " + stok2);
                    // break;

                    // case 5 ini perintah logout/keluar
                case 6:
                    System.out.println("\nTerima kasih telah menggunakan sistem perpustakaan.");
                    scan.close();
                    System.exit(0);

                    // default itu jika semua perintah salah maka bakal menjalankan perintah dari
                    // default, outputnya bakal keluar tulisan "pilihan tidak valid bla bla bla"
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        }

    }
}
