import java.util.*;
import java.text.*;
import java.util.Scanner;

public class Perpustakaan {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String namaMhs;
        int nim;

        String judul1 = "C#";
        String kode_buku1 = "A001";
        String pengarang1 = "Indira";
        int thn_terbit1 = 2020;
        String penerbit1 = "Explorer";
        int stok1 = 5;

        String judul2 = "Java";
        String kode_buku2 = "A002";
        String pengarang2 = "Aurah";
        int thn_terbit12 = 2020;
        String penerbit2 = "Explorer";
        int stok2 = 5;

        boolean type = true;
        while (type) {
            
        System.out.println("\nMenu:");
        System.out.println("1. Tampilkan Daftar Buku");
        System.out.println("2. Pinjam Buku");
        System.out.println("3. Kembalikan Buku");
        System.out.println("4. Tampilkan Informasi Buku");
        System.out.println("5. Keluar");
        System.out.print("Pilih menu (1/2/3/4/5): ");

        int perintah = scan.nextInt();

            switch (perintah) {
                // jika pilih case 1 /menu 1 maka akan menjalankan perintah case 1 dengan
                // memunculkan daftar bukunya
                case 1:
                    System.out.println("\nDaftar Buku:");
                    System.out.println("1. " + judul1);
                    System.out.println("2. " + judul2);
                    break;
                // jika pilih case 2/menu 2 maka akan menjalankan perintah case 2 dengan
                // memasukkan judul buku
                // nahhh case 2 ini bisa disebut sebagai transaksi peminjaman
                case 2:
                    System.out.print("Masukkan judul buku yang ingin dipinjam: ");
                    scan.nextLine();
                    String judulPinjam = scan.nextLine();
                    if (judulPinjam.equalsIgnoreCase(judul1)) {
                        if (stok1 > 0) {
                            stok1--;
                            Date tanggalPeminjaman = new Date();
                            Date tanggalPengembalian = new Date();
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(tanggalPengembalian);
                            calendar.add(Calendar.DAY_OF_YEAR, 7); // Tambahkan 7 hari untuk tanggal pengembalian
                            tanggalPengembalian = calendar.getTime();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            System.out.println("Tanggal Peminjaman: " + dateFormat.format(tanggalPeminjaman));
                            System.out.println("Tanggal Pengembalian: " + dateFormat.format(tanggalPengembalian));
                            System.out.println("Buku " + judul1 + " berhasil dipinjam.");
                            System.out.println("Judul buku: " + judul1 + ", " + "Kode buku: " + kode_buku1 + ", " + "Stok: " + stok1);
                        } else {
                            System.out.println("Stok buku " + judul1 + " habis.");
                        }
                    } else if (judulPinjam.equalsIgnoreCase(judul2)) {
                        if (stok2 > 0) {
                            stok2--;
                            Date tanggalPeminjaman = new Date();
                            Date tanggalPengembalian = new Date();
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(tanggalPengembalian);
                            calendar.add(Calendar.DAY_OF_YEAR, 14); // Tambahkan 14 hari untuk tanggal pengembalian
                            tanggalPengembalian = calendar.getTime();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            System.out.println("Tanggal Peminjaman: " + dateFormat.format(tanggalPeminjaman));
                            System.out.println("Tanggal Pengembalian: " + dateFormat.format(tanggalPengembalian));
                            System.out.println("Buku " + judul2 + " berhasil dipinjam.");
                            System.out.println("Judul buku: " + judul2 + ", " + "Kode buku: " + kode_buku2 + ", " + "Stok: " + stok2);
                        } else {
                            System.out.println("Stok buku " + judul2 + " habis.");
                        }
                    } else {
                        System.out.println("Buku dengan judul " + judulPinjam + " tidak ditemukan.");
                    }
                    break;
                // Nahh case 3 ini hampir sama kek case 2 kita tinggal masukno judul buku yang
                // mau dikembalikan
                // case 3 ini bisa disebut sebagai transaksi pengembalian
                case 3:
                    System.out.print("Masukkan judul buku yang ingin dikembalikan: ");
                    scan.nextLine(); // Membersihkan buffer
                    String judulKembali = scan.nextLine();
                    if (judulKembali.equalsIgnoreCase(judul1)) {
                        if (stok1 > 0) {
                            stok1++;
                            System.out.println("Buku " + judul1 + " berhasil diKembalikan.");
                            System.out.println("Judul buku: " + judul1 + ", " + "Kode buku: " + kode_buku1 + ", " + "Stok: " + stok1);
                        } else {
                            System.out.println("Stok buku " + judul1 + " habis.");
                        }
                    } else if (judulKembali.equalsIgnoreCase(judul2)) {
                        if (stok2 > 0) {
                            stok2++;
                            System.out.println("Buku " + judul2 + " berhasil dikembalikan.");
                            System.out.println("Judul buku: " + judul2 + ", " + "Kode buku: " + kode_buku2 + ", " + "Stok: " + stok2);
                        } else {
                            System.out.println("Stok buku " + judul2 + " habis.");
                        }
                    } else {
                        System.out.println("Buku dengan judul " + judulKembali + " tidak ditemukan.");
                    }
                    break;

                // lek case 4 itu perintah nampilin info buku, dari judul buku, nama penerbit,
                // nama pengarang, dll
                case 4:
                    System.out.println("\nInformasi Buku:");
                    System.out.println("1. " + "Judul buku: " + judul1 + ", " + "Kode buku: " + kode_buku1 + ", " + "Stok: " + stok1);
                    System.out.println("2. " + "Judul buku: " + judul2 + ", " + "Kode buku: " + kode_buku2 + ", " + "Stok: " + stok2);
                    break;

                // case 5 ini perintah logout/keluar
                case 5:
                    System.out.println("Terima kasih telah menggunakan sistem perpustakaan.");
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
