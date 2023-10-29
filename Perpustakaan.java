import java.util.*;
import java.text.*;

public class Perpustakaan {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String namaMhs = "Indira";
        int nim = 234150001;

        String usernameMhs = "234150001";
        String passwordMhs = "123";
        boolean login = false;

        String[] judul = {"C#", "Java"};
        String[] kode_buku = {"A001", "A002"};
        String[] pengarang = {"Indira", "Nafa"};
        int[] thn_terbit = {2020, 2022};
        String[] penerbit = {"Explorer", "ex"};
        int[] stok = {1, 5};
        int[] jmlPeminjaman = {0, 0};
        Date[] tglPeminjaman = {null, null};
        boolean[] statusPinjam = {false, false};

        int dendaPerMenit = 10000;
        int batasWaktuPeminjaman = 1;
        Date tanggalPengembalian = new Date();

        System.out.println("SISTEM PERPUSTAKAAN");

        // Login
        while (!login){
            System.out.print("\nMasukkan Username anda: ");
            String username = scan.next();
            System.out.print("Masukkan Password anda: ");
            String password = scan.next();
            if (username.equals(usernameMhs) && password.equals(passwordMhs)){
                System.out.println("Login berhasil. Selamat datang, " + namaMhs + "!");
                login = true;
            } else {
                System.out.println("Login gagal. Cek kembali username dan password Anda!");
            }
        }

        boolean type = true;
        while (type) {

        System.out.println("\n========================================================================================================================");
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
                for(int i = 0; i < judul.length; i++){
                    System.out.println((i + 1) + ". " + judul[i]);
                }
                break;
            case 2:
                System.out.println("\nTAMBAH BUKU");
                System.out.print("Masukkan judul buku\t: ");
                String title = scan.next();
                System.out.print("Masukkan kode buku\t: ");
                String code = scan.next();
                System.out.print("Masukkan nama pengarang\t: ");
                String author= scan.next();
                System.out.print("Masukkan tahun terbit\t: ");
                int year = scan.nextInt();
                System.out.print("Masukkan nama penerbit\t: ");
                String publisher = scan.next();
                System.out.print("Masukkan jumlah stok\t: ");
                int stock = scan.nextInt();

                System.out.println("Judul buku: " + title + ", " + "Kode buku: " + code + ", "
                        + "Nama pengarang: " + author + ", " + "Tahun terbit: " + year + ", "
                        + "Nama penerbit: " + publisher + ", " + "Stok: " + stock + " Berhasil ditambahkan");
                break;

                // jika pilih case 3/menu 3 maka akan menjalankan perintah case 3 dengan
                // memasukkan judul buku
                // nah case 3 ini bisa disebut sebagai transaksi peminjaman
            case 3:
                System.out.println("\nFORM PEMINJAMAN");
                System.out.println("Nama Mahasiswa\t: " + namaMhs);
                System.out.println("NIM\t\t: " + nim);
                System.out.print("\nMasukkan judul buku yang ingin dipinjam: ");
                scan.nextLine();
                String judulPinjam = scan.nextLine();
                for(int i = 0; i<judul.length; i++){
                    if (judulPinjam.equalsIgnoreCase(judul[i])) {
                        if (stok[i] > 0 && !statusPinjam[i]) {
                            statusPinjam[i] = true;
                            jmlPeminjaman[i]++;
                            stok[i]--;
                            System.out.println("Jumlah Stok Buku yang dipinjam: " + jmlPeminjaman[i]); // status
                            Date tanggalPeminjaman = new Date();
                            // Date tanggalPengembalian = new Date();
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(tanggalPengembalian);
                            calendar.add(Calendar.MINUTE, 1); // Tambahkan 1 menit untuk tanggal pengembalian
                            tanggalPengembalian = calendar.getTime();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            System.out.println("Tanggal Peminjaman\t: " + dateFormat.format(tanggalPeminjaman));
                            System.out.println("Tanggal Pengembalian\t: " + dateFormat.format(tanggalPengembalian));
                            System.out.println("Buku " + judul[i] + " berhasil dipinjam.");
                            System.out.println("Judul buku: " + judul[i] + ", " + "Kode buku: " + kode_buku[i] + ", "
                                + "Stok: " + stok[i]);
                        }else{
                            System.out.println("Stok buku " + judul[i] + " tidak tersedia atau buku sedang dipinjam");
                        }
                    }
                }
                break;

                // Nahh case 4 ini hampir sama kek case 2 kita tinggal masukno judul buku yang
                // mau dikembalikan
                // case 4 ini bisa disebut sebagai transaksi pengembalian
            case 4:
                System.out.println("\nFORM PENGEMBALIAN");
                System.out.print("Masukkan judul buku yang ingin dikembalikan: ");
                // scan.nextLine(); // Membersihkan buffer
                String judulKembali = scan.next();
                for (int i = 0; i < judul.length; i++) {
                    if (judulKembali.equalsIgnoreCase(judul[i])) {
                        if (statusPinjam[i]) {
                            statusPinjam[i] = false;
                            stok[i]++;
                            System.out.println("Buku " + judul[i] + " berhasil dikembalikan.");
                            System.out.println("Judul buku: " + judul[i] + ", " + "Kode buku: " + kode_buku[i] + ", "
                                + "Stok: " + stok[i]);
                            long selisihMenit = (new Date().getTime() - tanggalPengembalian.getTime()) / (60 * 1000);
                        if (selisihMenit > 0) {
                            double denda = dendaPerMenit * selisihMenit;
                            System.out.println("Denda yang harus dibayar: Rp. " + denda);
                        }
                    } else {
                        System.out.println("Buku " + judul[i] + " tidak sedang dipinjam.");
                    }
                }
                }
                break;

                // kalau case 5 itu perintah buat menampilkan info buku, dari judul buku, nama
                // penerbit, nama pengarang, dll
            case 5:
                System.out.println("\nINFORMASI BUKU:");
                for (int i = 0; i < judul.length; i++) {
                    System.out.println((i + 1)+". "+ "Judul buku: " + judul[i] + ", " + "Kode buku: " + kode_buku[i] + ", "
                        + "Nama pengarang: " + pengarang[i] + ", " + "Tahun terbit: " + thn_terbit[i] + ", "
                        + "Nama penerbit: "
                        + penerbit[i] + ", " + "Stok: " + stok[i]);
                    if (statusPinjam[i]) {
                        System.out.println((" (Buku Sedang Dipinjam) "));
                    } else {
                        System.out.println(" (Buku Tersedia) ");
                    }
                }
                break;

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
