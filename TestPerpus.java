import java.util.*;
import java.text.*;
import java.util.Scanner;

public class TestPerpus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String namaMhs;
        int nim;

        String judul1 = "Tutorial Serenity Basic";
        String kode_buku1 = "A001";
        String pengarang1 = "Indira";
        int thn_terbit1 = 2020;
        String penerbit1 = "Explorer";
        int stok1 = 5;

        String judul2 = "Panduan Pemrograman Java";
        String kode_buku2 = "A002";
        String pengarang2 = "Aurah";
        int thn_terbit12 = 2020;
        String penerbit2 = "Explorer";
        int stok2 = 5;

        // kode program tampilkan daftar buku
        System.out.println("DAFTAR BUKU");
        System.out.println("1. " + judul1);
        System.out.println("2. " + judul2);

        // kode program tampilkan detail buku
        System.out.println("\nDETAIL BUKU");
        System.out.println("1. " + "Judul buku: " + judul1 + ", " + "Kode buku: " + kode_buku1 + ", "
                + "Nama pengarang: " + pengarang1 + ", " + "Tahun terbit: " + thn_terbit1 + ", " + "Nama penerbit: "
                + penerbit1 + ", " + "Stok: " + stok1);
        System.out.println("2. " + "Judul buku: " + judul2 + ", " + "Kode buku: " + kode_buku2 + ", "
                + "Nama pengarang: " + pengarang2 + ", " + "Tahun terbit: " + thn_terbit12 + ", " + "Nama penerbit: "
                + penerbit2 + ", " + "Stok: " + stok2);

        // kode program fitur transaksi peminjaman
        System.out.println("\nFORM PEMINJAMAN BUKU");
        System.out.println("Masukkan judul buku yang ingin dipinjam: ");
        judul1 = sc.nextLine();
        System.out.println("Nama Peminjam: ");
        namaMhs = sc.nextLine();
        System.out.println("NIM Peminjam: ");
        nim = sc.nextInt();
        System.out.println("Buku " + judul1 + " Berhasil dipinjam");
        stok1--;
        Date tanggalPeminjaman = new Date();
        Date tanggalPengembalian = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tanggalPengembalian);
        calendar.add(Calendar.DAY_OF_YEAR, 14); // Tambahkan 14 hari untuk tanggal pengembalian
        tanggalPengembalian = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Tanggal Peminjaman: " + dateFormat.format(tanggalPeminjaman));
        System.out.println("Tanggal Pengembalian: " + dateFormat.format(tanggalPengembalian));

        // kode program tampilkan cek stok buku setelah transaksi peminjaman
        System.out.println("\nKETERSEDIAAN BUKU");
        System.out.println("1. " + "Judul buku: " + judul1 + ", " + "Kode buku: " + kode_buku1 + ", " + "Stok: " + stok1);
        System.out.println("2. " + "Judul buku: " + judul2 + ", " + "Kode buku: " + kode_buku2 + ", " + "Stok: " + stok2);

        // kode program fitur transaksi pengembalian
        System.out.println("\nFORM PENGEMBALIAN BUKU");
        System.out.println("Masukkan judul buku yang ingin dikembalikan: ");
        judul1 = sc.next();
        stok1++;
        System.out.println("Buku " + judul1 + " telah dikembalikan.");

        // kode program tampilkan cek stok setelah transaksi pengembalian
        System.out.println("\nDetail Buku");
        System.out.println("1. " + "Judul buku: " + judul1 + ", " + "Kode buku: " + kode_buku1 + ", " + "Stok: " + stok1);
        System.out.println("2. " + "Judul buku: " + judul2 + ", " + "Kode buku: " + kode_buku2 + ", " + "Stok: " + stok2);
    }
}
