import java.util.*;
import java.text.*;

public class Perpustakaan {
    static Scanner scan = new Scanner(System.in);
    static boolean bukuTersedia = false; // sebagai penanda judul buku ditemukan atau tidak
    static Date tanggalPengembalian = new Date();
    static String namaMahasiswa = "";
    static String nimMhs = "";
    static String usernameMhs = "";
    static String passwordMhs = "";
    static String pengguna;
    static boolean login = false;
    static boolean isPetugas = false;
    static final int MAX_BUKU = 100;

    public static void main(String[] args) {
        // Deklarasi dan Inisialisasi Variabel
        String[] nama = new String[10];
        String[] nim = new String[10];
        String[] username = new String[10];
        String[] passwordPengguna = new String[10];
        String[][] dataBuku = new String[100][9]; // batas maksimal input buku

        // Inisialisasi data buku awal
        dataBuku[0] = new String[] { "C#", "A001", "Indira", "2020", "Explorer", "1", "0", null, "false" };
        dataBuku[1] = new String[] { "Java", "A002", "Nafa", "2022", "ex", "5", "0", null, "false" };
        int totalBuku = 2; // Menandai total buku yang sudah ada

        final int MAX_ANTRIAN = 100; // Jumlah maksimal antrian yang dapat ditampung
        String[] antrianPeminjaman = new String[MAX_ANTRIAN];
        int jumlahAntrian = 0;
        String[] riwayatAntrian = new String[MAX_ANTRIAN];
        int jumlahRiwayat = 0;
        int dendaPerMenit = 10000;

        // Buka Sistem
        System.out.println("==========================[ SISTEM PERPUSTAKAAN ]=============================");
        System.out.println("\nSelamat Datang! di Sistem Perpustakaan.");

        // Registrasi
        boolean isRegister = registrasiUser(nama, nim, username, passwordPengguna, pengguna);

        if (isRegister) {
            System.out.println("\nRegistrasi berhasil");
        } else {
            System.out.println("\nRegistrasi gagal");
            return;
        }

        // Login
        boolean isLogin = login(username, passwordPengguna, pengguna);

        if (isLogin) {
            System.out.println("\nLogin berhasil.\n");
        } else {
            System.out.println("\nLogin gagal.\n");
            return;
        }

        // Menu
        boolean type = true;
        while (type) {
            System.out.println("===================================< MENU >==================================\n");
            System.out.println("1. Informasi Buku");
            System.out.println("2. Tambah Data Buku");
            System.out.println("3. Pinjam Buku");
            System.out.println("4. Kembalikan Buku");
            System.out.println("5. Riwayat Antrian");
            System.out.println("6. Hapus Data Buku");
            System.out.println("7. Keluar");
            System.out.print("\nPilih menu (1/2/3/4/5/6/7): ");

            int perintah = scan.nextInt();

            switch (perintah) {

                // Case ke-1 "Informasi Buku" meliputi data buku dan status buku
                case 1:
                    informasiBuku(dataBuku, totalBuku);
                    break;

                // Case ke-2 "Tambah Buku"
                case 2:
                    totalBuku = tambahDataBuku(dataBuku, totalBuku, isPetugas);
                    break;

                // Case ke-3 (Form Peminjaman Buku)
                case 3:
                    pinjamBuku(dataBuku, antrianPeminjaman, riwayatAntrian, namaMahasiswa, nimMhs, MAX_ANTRIAN,
                            jumlahAntrian, jumlahRiwayat);
                    break;

                // Case ke-4 "Form Pengembalian Buku dan Denda"
                case 4:
                    kembalikanBuku(dataBuku, antrianPeminjaman, riwayatAntrian, tanggalPengembalian, dendaPerMenit, isPetugas);
                    break;

                case 5:
                    tampilkanRiwayatAntrian(riwayatAntrian);
                    break;

                case 6:
                    totalBuku = hapusDataBuku(dataBuku, isPetugas, totalBuku);
                    break;

                // Case ke-5 ini perintah logout/keluar
                case 7:
                    System.out.println("\nTerima kasih telah menggunakan sistem perpustakaan.");
                    scan.close();
                    System.exit(0);

                default:
                    System.out.println("\nPilihan tidak valid. Silakan pilih lagi.");
            }
        }
    }

    // Function Registrasi
    public static boolean registrasiUser(String[] nama, String[] nim, String[] username, String[] passwordPengguna,
            String pengguna) {

        System.out.println("\n============================< FORM REGISTRASI >===============================");
        System.out.print("\nApakah Anda petugas atau mahasiswa? (petugas/mahasiswa): ");
        pengguna = scan.next();

        if (pengguna.equalsIgnoreCase("petugas")) {
            // Registrasi sebagai petugas
            System.out.print("\nMasukkan Nickname: ");
            String namaPetugas = scan.next();
            System.out.print("Masukkan Username: ");
            String usernamePetugas = scan.next();
            System.out.print("Masukkan Password: ");
            String passwordPetugas = scan.next();

            // Simpan informasi petugas ke dalam array
            for (int i = 0; i < nama.length; i++) {
                if (nama[i] == null) {
                    nama[i] = namaPetugas;
                    username[i] = usernamePetugas;
                    passwordPengguna[i] = passwordPetugas;
                    isPetugas = true;
                    break;
                }
            }

            return true; // Registrasi berhasil sebagai petugas
        } else if (pengguna.equalsIgnoreCase("mahasiswa")) {
            // Registrasi sebagai mahasiswa
            System.out.print("\nMasukkan Nama    : ");
            String namaMahasiswa = scan.next();
            System.out.print("Masukkan Username: ");
            String usernameMhs = scan.next();
            System.out.print("Masukkan Password: ");
            String passwordMhs = scan.next();

            for (int i = 0; i < nama.length; i++) {
                if (nama[i] == null) {
                    nama[i] = namaMahasiswa;
                    nim[i] = nimMhs;
                    username[i] = usernameMhs;
                    passwordPengguna[i] = passwordMhs;
                    break;
                }
            }

            return true; // Registrasi berhasil sebagai mahasiswa
        } else {
            return false; // Registrasi gagal karena inputan tidak valid
        }
    }

    // Function Login
    public static boolean login(String[] username, String[] passwordPengguna, String pengguna) {

        System.out.println("\n==================================< LOGIN >==================================");
        boolean isLogin = false;
        while (!isLogin) {
            System.out.print("\nMasukkan Username anda: ");
            String user = scan.next();
            System.out.print("Masukkan Password anda: ");
            String password = scan.next();

            for (int i = 0; i < username.length; i++) {
                if (user.equals(username[i]) && password.equals(passwordPengguna[i])) {
                    isLogin = true; // Jika login berhasil
                    break;
                }
            }

            if (!isLogin) {
                System.out.println("\nUsername atau password salah. Silakan coba lagi!");
            }
        }

        return true; // Login berhasil
    }

    // Fungsi untuk menampilkan informasi buku
    public static void informasiBuku(String[][] dataBuku, int totalBuku) {

        System.out.println("\n=============================< INFORMASI BUKU >==============================\n");
        for (int i = 0; i < totalBuku; i++) {
            String[] book = dataBuku[i];
            System.out.println(
                    (i + 1) + ". " + "Judul buku\t: " + book[0] + "\n   Kode buku\t: " + book[1] +
                            "\n   Pengarang\t: " + book[2] + "\n   Tahun terbit\t: " + book[3] + 
                            "\n   Penerbit\t: " + book[4] + "\n   Stok\t\t: " + book[5]);
            boolean statusPinjam = Boolean.parseBoolean(book[8]);
            if (statusPinjam) {
                System.out.println(("   (Buku Sedang Dipinjam)\n"));
            } else {
                System.out.println("   (Buku Tersedia)\n");
            }
        }
    }

    // Fungsi untuk menambah data buku
    public static int tambahDataBuku(String[][] dataBuku, int totalBuku, boolean isPetugas) {
        // Jika petugas maka akan mengakses fitur Tambah Data
        if (isPetugas) {
            if (totalBuku < MAX_BUKU) { // Cek apakah batas maksimal buku belum tercapai
                System.out.println("\n===============================< TAMBAH BUKU >===============================\n");
                // Meminta pengguna untuk memasukkan detail buku baru
                System.out.print("Masukkan judul buku\t: ");
                String title = scan.next();
                System.out.print("Masukkan kode buku\t: ");
                String code = scan.next();
                System.out.print("Masukkan nama pengarang\t: ");
                String author = scan.next();
                System.out.print("Masukkan tahun terbit\t: ");
                String year = scan.next();
                System.out.print("Masukkan nama penerbit\t: ");
                String publisher = scan.next();
                System.out.print("Masukkan jumlah stok\t: ");
                String stock = scan.next();
                String totalStock = stock;

                // Tambahkan buku baru ke dalam array dataBuku
                dataBuku[totalBuku] = new String[] { title, code, author, year, publisher, stock, "0", null, "false", totalStock};

                System.out.println("\nBuku berhasil ditambahkan.\n");
                return totalBuku + 1; // Tingkatkan jumlah buku yang sudah ditambahkan
            } else {
                System.out.println("Maaf, ruang untuk menambahkan buku baru sudah penuh.\n");
            }
        } else {
            // Jika bukan Petugas maka akan muncul keterangan seperti di bawah ini
            System.out.println("\nMaaf, Anda tidak memiliki akses untuk fitur ini.\n");
        }
        return totalBuku; // Kembalikan jumlah buku tanpa perubahan jika tidak ditambahkan buku baru
    }

    // Fungsi untuk proses Peminjaman Buku
    public static void pinjamBuku(String[][] dataBuku, String[] antrianPeminjaman,
            String[] riwayatAntrian, String namaMahasiswa, String nimMhs, int MAX_ANTRIAN, int jumlahAntrian,
            int jumlahRiwayat) {

        System.out.println("\n=============================< FORM PEMINJAMAN >=============================\n");
        System.out.print("Masukkan judul buku yang ingin dipinjam: ");
        scan.nextLine();
        String judulPinjam = scan.nextLine();
        boolean bukuTersedia = false;
        
        for (int i = 0; i < dataBuku.length; i++) {
            String[] book = dataBuku[i];
            if (judulPinjam.equalsIgnoreCase(book[0])) {
                bukuTersedia = true;
                System.out.print("Masukkan Nama Mahasiswa: ");
                namaMahasiswa = scan.nextLine();
                System.out.print("Masukkan NIM Mahasiswa: ");
                nimMhs = scan.nextLine();
                boolean statusPinjam = Boolean.parseBoolean(book[8]);
                if (Integer.parseInt(book[5]) > 0 && !statusPinjam) {
                    book[8] = "true";
                    book[6] = String.valueOf(Integer.parseInt(book[6]) + 1);
                    book[5] = String.valueOf(Integer.parseInt(book[5]) - 1);
                    Date tanggalPeminjaman = new Date();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(tanggalPengembalian);
                    calendar.add(Calendar.MINUTE, 1);
                    tanggalPengembalian = calendar.getTime();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    System.out.print("_____________________________________________________________________________\n\n");
                    System.out.println("Nama Mahasiswa\t: " + namaMahasiswa);
                    System.out.println("NIM Mahasiswa\t: " + nimMhs);
                    System.out.println("\nJudul buku\t\t: " + book[0] + "\nKode buku\t\t: " + book[1]);
                    System.out.println("Tanggal Peminjaman\t: " + dateFormat.format(tanggalPeminjaman));
                    System.out.println("Tanggal Pengembalian\t: " + dateFormat.format(tanggalPengembalian));
                    System.out.println("\nBuku " + book[0] + " berhasil dipinjam.");
                    System.out.println("______________________________________________________________________________\n");
                    System.out.println("Stok yang tersedia: " + book[5] + "\n");
                } else {
                    System.out.println("Stok buku " + book[0] + " tidak tersedia atau buku sedang dipinjam\n");
                    System.out.print("Apakah Anda ingin masuk antrian peminjaman? (ya/tidak): ");
                    String jawaban = scan.next();
                    if (jumlahAntrian < MAX_ANTRIAN && jawaban.equalsIgnoreCase("ya")) {
                        antrianPeminjaman[jumlahAntrian] = judulPinjam;
                        jumlahAntrian++;
                        riwayatAntrian[jumlahRiwayat] = judulPinjam;
                        jumlahRiwayat++;
                        System.out.println("Anda telah masuk ke antrian peminjaman untuk buku " + judulPinjam + "\n");
                    }else
                    System.out.println("");
                }
            }
        }

        if (!bukuTersedia) {
            System.out.println("Maaf, buku dengan judul '" + judulPinjam + "' tidak ditemukan\n");
        }
    }

    // Fungsi untuk proses Pengembalian Buku
    public static void kembalikanBuku(String[][] dataBuku, String[] antrianPeminjaman,
            String[] riwayatAntrian, Date tanggalPengembalian, int dendaPerMenit, boolean isPetugas) {

        if (isPetugas) {
            System.out.println("\n============================< FORM PENGEMBALIAN >============================\n");
            System.out.print("Masukkan judul buku yang ingin dikembalikan: ");
            String judulKembali = scan.next();
            boolean bukuTersedia = false;
            for (int i = 0; i < dataBuku.length; i++) {
                String[] book = dataBuku[i];
                if (judulKembali.equalsIgnoreCase(book[0])) {
                    bukuTersedia = true;
                    System.out.print("Masukkan Nama Mahasiswa\t: ");
                    namaMahasiswa = scan.next();
                    System.out.print("Masukkan NIM Mahasiswa\t: ");
                    nimMhs = scan.next();
                    boolean statusPinjam = Boolean.parseBoolean(book[8]);
                    if (statusPinjam) {
                        book[8] = "false";
                        book[5] = String.valueOf(Integer.parseInt(book[5]) + 1);
                        System.out.print("_____________________________________________________________________________\n");
                        System.out.println("\nNama Mahasiswa\t: " + namaMahasiswa);
                        System.out.println("NIM Mahasiswa\t: " + nimMhs);
                        System.out.println("\nJudul buku\t: " + book[0] + "\nKode buku\t: " + book[1]);
                        System.out.println("\nBuku " + book[0] + " berhasil dikembalikan.");
                        long selisihMenit = (new Date().getTime() - tanggalPengembalian.getTime()) / (60 * 1000);
                        if (selisihMenit > 0) {
                            double denda = dendaPerMenit * selisihMenit;
                            System.out.println("Denda yang harus dibayar: Rp. " + denda);
                        }
                        System.out.println("______________________________________________________________________________\n");
                        System.out.println("Stok yang tersedia: " + book[5] + "\n");
                        hapusRiwayatAntrian(riwayatAntrian, judulKembali);
                    } else {
                        System.out.println("Buku " + book[0] + " tidak sedang dipinjam.");
                    }
                }
            }
            if (!bukuTersedia) {
            System.out.println("Maaf, buku dengan judul '" + judulKembali + "' tidak ditemukan\n");
            }
        }
    }
    // Fungsi untuk menghapus riwayat antrian peminjaman
    public static void hapusRiwayatAntrian(String[] riwayatAntrian, String judulKembali) {
        boolean bukuDitemukan = false;
        for (int j = 0; j < riwayatAntrian.length; j++) {
            if (judulKembali.equalsIgnoreCase(riwayatAntrian[j])) {
                bukuDitemukan = true;
                // Geser elemen setelahnya ke posisi sebelumnya
                for (int k = j; k < riwayatAntrian.length - 1; k++) {
                    riwayatAntrian[k] = riwayatAntrian[k + 1];
                }
                // Kosongkan elemen terakhir
                riwayatAntrian[riwayatAntrian.length - 1] = null;
                break;
            }
        }
    }

    // Fungsi untuk menampilkan riwayat antrian peminjaman
    public static void tampilkanRiwayatAntrian(String[] riwayatAntrian) {

        System.out.println("\n=======================< RIWAYAT ANTRIAN PEMINJAMAN >========================\n");

        // Mengecek apakah riwayat antrian kosong
        if (riwayatAntrian.length == 0 || riwayatAntrian[0] == null) {
            System.out.println("Riwayat antrian kosong.\n");
        } else {
            for (int i = 0; i < riwayatAntrian.length; i++) {
                if (riwayatAntrian[i] != null) {
                    System.out.println((i + 1) + ". " + riwayatAntrian[i]);
                    System.out.println("");
                }
            }
        }
    }

    // Fungsi untuk Menghapus Data Buku
    public static int hapusDataBuku(String[][] dataBuku, boolean isPetugas, int totalBuku) {
        if (isPetugas) {
            System.out.println("\n================================< HAPUS BUKU >===============================\n");
            System.out.print("Masukkan judul buku yang ingin dihapus: ");
            String judulHapus = scan.next();
            boolean bukuDihapus = false;

            for (int i = 0; i < totalBuku; i++) {
                String[] book = dataBuku[i];
                if (judulHapus.equalsIgnoreCase(book[0])) {
                    // Menghapus data buku dengan menggeser elemen setelahnya ke posisi sebelumnya
                    for (int j = i; j < totalBuku - 1; j++) {
                        dataBuku[j] = dataBuku[j + 1];
                    }
                    // Mengosongkan elemen terakhir
                    dataBuku[totalBuku - 1] = new String[dataBuku[0].length];
                    bukuDihapus = true;
                    System.out.println("Buku dengan judul '" + judulHapus + "' berhasil dihapus.\n");
                    totalBuku--; // Kurangkan jumlah buku yang ada
                    break;
                }
            }

            if (!bukuDihapus) {
                System.out.println("Buku dengan judul '" + judulHapus + "' tidak ditemukan.\n");
            }
        } else {
            System.out.println("Maaf, Anda tidak memiliki akses untuk fitur ini.\n");
        }

        return totalBuku; // Kembalikan totalBuku yang baru setelah menghapus
    }
}
