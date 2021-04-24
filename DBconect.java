package com.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DBconect {
    public static void main(String[] args) {
        // function untuk menyambungkan dengan koneksi postgresql
        Scanner input = new Scanner(System.in);
        String jdbcURL = "jdbc:postgresql://95.216.171.129:5432/bigdata";
        String isInput = "Y";
        
        //function buat user untuk mendapatkan akses data cilsilify
        System.out.print("\nUsername : ");
        String username = input.nextLine(); //bigdata01
        System.out.print("Password : ");
        String password = input.nextLine(); //Bigdata01!

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password); //Making Connection
            System.out.println("\nYou are connected to PostgreSQL Server");
            System.out.println("Selamat datang di aplikasi kirim pesan kepada user Cilsylify");
            while (isInput.equals("Y")) {
                if (isInput.equalsIgnoreCase("Y")) {             
                    System.out.println("\n==============================MESSAGE INTERFACE=============================");
                    System.out.println("[1] Berikan pesan kepada user yang membayar pada 'AWAL TAHUN' sebesar 10%");
                    System.out.println("[2] Berikan Pesan kepada pengguna yang membayar pada 'AKHIR TAHUN' sebesar 20%");
                    System.out.println("[3] Berikan Penawaran kepada user yang 'BELUM MEMBAYAR' sebesar 15%");
                    System.out.println("[0] Tidak ingin mengirim pesan\n");
                    System.out.print("Silahkan Masukkan Pilihan Anda : ");
                    Integer pilih_pesan = input.nextInt();
                    
                    //Coding untuk menampilkan hasil ketika user memilih pilihan 1,2,3 dll.
                    //Pilihan pada angka 1       
                    if (pilih_pesan == 1){
                    String sql = "SELECT invoiceid, *FROM public.annisa_awal_tahun";
                    Statement statement = connection.createStatement();
                    ResultSet result = statement.executeQuery(sql);
                    while(result.next()){
                        String invoiceid = result.getString("invoiceid");
                        System.out.println("Terima kasih atas pembayaran Anda pada invoice " + invoiceid + ". Anda berhak mendapatkan diskon awal tahun sebanyak 10%!");
                        }

                    //pilihan pada angka 2
                    }else if (pilih_pesan == 2){
                        String sql = "SELECT invoiceid, *FROM public.akhir_tahun_annisa";
                        Statement statement = connection.createStatement();
                        ResultSet result = statement.executeQuery(sql);
                        while(result.next()){
                            String invoiceid = result.getString("invoiceid");
                            System.out.println("Terima kasih atas pembayaran Anda pada invoice " + invoiceid + ". Anda berhak mendapatkan diskon akhir tahun sebanyak 20%!");
                        }
                        
                    //pilihan pada angka 3
                    }else if (pilih_pesan == 3){
                        String sql = "SELECT invoiceid, *FROM public.annisa_belumbayar";
                        Statement statement = connection.createStatement();
                        ResultSet result = statement.executeQuery(sql);
                        while(result.next()){
                            String invoiceid = result.getString("invoiceid");
                            System.out.println("Yuk segera bayar tagihan invoice " + invoiceid  +", dan dapatkan diskon 15%!");
                        }

                    //pilihan pada angka 0
                    }else if (pilih_pesan == 0){
                        System.out.println("\n====Karena Anda tidak ingin mengirim pesan maka Anda keluar dari server=====");
                        break; //menghentikan looping while dan langsung mengeksekusi baris code selanjutnya (baris 90)
                        
                    }
                //Untuk memberikan pilihan aktivitas pada pilihan "MESSAGE INTERFACE" yang dieksekusi
                //jika "Y" maka looping while "MESSAGE INTERFACE" akan berjalan kembali
                System.out.print("\nApakah anda ingin mengirim pesan lagi ? (Y/N) : ");
                isInput = input.next();
                if (isInput.equalsIgnoreCase("N")){
                    System.out.println("\n=========Karena Anda tidak ingin mengirim pesan lagi maka Anda keluar dari server=========");
                }
            }
            }
        connection.close();
     }
         catch (SQLException e) {
            System.out.println("Error in connecting to PostgreSQL Server");
            e.printStackTrace();
        }
    }
}
