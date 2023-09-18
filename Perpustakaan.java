package sistem_perpus;
import java.util.*;
import java.text.*;
import java.util.Scanner;

/**
 * Perpustakaan
 */
/**
 * InnerPerpustakaan
 */

class User{
    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

class Buku {
    String judul;
    String kode_buku;
    String pengarang;
    int thn_terbit;
    String penerbit;
    int stok;

    public Buku(String judul, String kode_buku, String pengarang, int thn_terbit, String penerbit, int stok) {
        this.judul = judul;
        this.kode_buku = kode_buku;
        this.pengarang = pengarang;
        this.thn_terbit = thn_terbit;
        this.penerbit = penerbit;
        this.stok = stok;
    }

    //public void pinjam ini biasanya disebut sebagai method pinjam
    public void pinjam() {
        if (stok > 0){
            stok--;
            System.out.println("Buku " + judul + "Berhasil dipinjam");
            Date tanggalPeminjaman = new Date();
            Date tanggalPengembalian = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(tanggalPengembalian);
            calendar.add(Calendar.DAY_OF_YEAR, 14); // Tambahkan 14 hari untuk tanggal pengembalian
            tanggalPengembalian = calendar.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Tanggal Peminjaman: " + dateFormat.format(tanggalPeminjaman));
            System.out.println("Tanggal Pengembalian: " + dateFormat.format(tanggalPengembalian));
        } else {
            System.out.println("Maaf, buku " + judul + " tidak tersedia saat ini.");
        }
    }

    //public void kembali itu juga disebut dengan method kembali
    public void kembali() {
        stok++;
        System.out.println("Buku " + judul + " telah dikembalikan.");
    }

    //public void tampilkan sama aja juga disebut method 
    public void tampilkanInfo() {
        System.out.println("Judul Buku: " + judul);
        System.out.println("Kode Buku: " + kode_buku);
        System.out.println("Pengarang: " + pengarang);
        System.out.println("Tahun Terbit: " + thn_terbit);
        System.out.println("Penerbit: " + penerbit);
        System.out.println("Stok: " + stok);
    }

    //method itu fungsinya biar bisa dipanggil di class perpustakaan, menurutku ben kodingan e kelihatan rapi + perintah e lebih mudah difahami
    
}
 
public class Perpustakaan {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        //Login
        List<User> daftarUser = new ArrayList<>();
        daftarUser.add(new User("Indira", "123")); 

        boolean suksesLogin = false;
        String username;
        String password;

        while (!suksesLogin) {
            System.out.println("Masukkan username: ");
            username = scan.nextLine();
            System.out.println("Masukkan password: ");
            password = scan.nextLine();

            for (User user : daftarUser){
                if (user.username.equals(username) && user.password.equals(password)) {
                    suksesLogin = true;
                    System.out.println("Login berhasil. Selamat datang, " + username + "!");
                    break;
                }
            }
            if (!suksesLogin) {
                System.out.println("Login gagal. Silakan coba lagi.");
            }
        }

        //Setelah login bakal muncul menu 
        Buku buku1 = new Buku("Tutorial Serenity Basic", "A001", "Dira", 2021, "EXploreid", 5);
        Buku buku2 = new Buku("Belajar Bahasa Pemrograman C#", "A001", "Indira", 2021, "EXploreid", 5);
        
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Tampilkan Daftar Buku");
            System.out.println("2. Pinjam Buku");
            System.out.println("3. Kembalikan Buku");
            System.out.println("4. Tampilkan Informasi Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1/2/3/4/5): ");

            int perintah = scan.nextInt();

            switch (perintah) {
                //jika pilih case 1 /menu 1 maka akan menjalankan perintah case 1 dengan memunculkan daftar bukunya
                case 1:
                    System.out.println("\nDaftar Buku:");
                    System.out.println("1. " + buku1.judul);
                    System.out.println("2. " + buku2.judul);
                    break;
                //jika pilih case 2/menu 2 maka akan menjalankan perintah case 2 dengan memasukkan judul buku
                //nahhh case 2 ini bisa disebut sebagai transaksi peminjaman
                case 2:
                    System.out.print("Masukkan judul buku yang ingin dipinjam: ");
                    scan.nextLine();
                    String judul = scan.nextLine();
                    if (judul.equalsIgnoreCase(buku1.judul)) {
                        buku1.pinjam();
                    } else if (judul.equalsIgnoreCase(buku2.judul)) {
                        buku2.pinjam();
                    } else {
                        System.out.println("Buku dengan judul " + judul + " tidak ditemukan.");
                    }
                    break;
                //Nahh case 3 ini hampir sama kek case 2 kita tinggal masukno judul buku yang mau dikembalikan
                //case 3 ini bisa disebut sebagai transaksi pengembalian
                case 3:
                    System.out.print("Masukkan judul buku yang ingin dikembalikan: ");
                    scan.nextLine(); // Membersihkan buffer
                    String judulKembali = scan.nextLine();
                    if (judulKembali.equalsIgnoreCase(buku1.judul)) {
                        buku1.kembali();
                    } else if (judulKembali.equalsIgnoreCase(buku2.judul)) {
                        buku2.kembali();
                    } else {
                        System.out.println("Buku dengan judul " + judulKembali + " tidak ditemukan.");
                    }
                    break;
                
                //lek case 4 itu perintah nampilin info buku, dari judul buku, nama penerbit, nama pengarang, dll
                case 4:
                    System.out.println("\nInformasi Buku:");
                    buku1.tampilkanInfo();
                    buku2.tampilkanInfo();
                    break;

                //case 5 ini perintah logout/keluar
                case 5:
                    System.out.println("Terima kasih telah menggunakan sistem perpustakaan.");
                    scan.close();
                    System.exit(0);
                
                //default itu jika semua perintah salah maka bakal menjalankan perintah dari default, outputnya bakal keluar tulisan "pilihan tidak valid bla bla bla"
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        }

    }
}
