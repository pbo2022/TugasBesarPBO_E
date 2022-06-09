/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tubesperpustakaan;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ASUS
 */
public class DataPengembalian extends javax.swing.JFrame {
private Statement st; //pilih yang java.sql
private Connection Con;
private ResultSet RsUser;
private ResultSet Rs;
private ResultSet RsPinjam;
private String Tanggalkembali;
private String Tanggalpinjam;
private String Sql="";

public String kode_kembali,kodepinjam,no_pinjam,namaanggota; //untuk deklrasi simpan penjuualan
public int Keterlambatan,jumlahpinjam,Denda,Hasil;//untuk deklrasi simpan penjuualan
    /**
     * Creates new form DataPengembalian
     */
    public DataPengembalian() {
        initComponents();
        KoneksiPengembalian();
        tampilPengembalian("selcet*from tb_pengembalian");
        tampilpeminjaman(Sql);
        PilihKodePinjam();
        kosongkan(); 
        TgldiText();
//        Waktu();
        txtTanggalPinjam.enable(false);
        TXTKETERLAMBATAN.enable(false);
    }
private void KoneksiPengembalian(){
        try {
        Class.forName("com.mysql.jdbc.Driver");    //Jika Tidak Terjadi Error
        Con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_perpustakaan","root","");
            System.out.println("Connection Succes");
            JOptionPane.showMessageDialog(null, "Connection Succes");
        } catch (Exception e) {
            System.out.println("Connection Failed"+e);
            JOptionPane.showMessageDialog(null, "Connection failed");
        }
}
private void PilihKodePinjam(){
        CBKODEPINJAM.removeAllItems();
        CBKODEPINJAM.addItem("Select");
        try {
            String Sql ="SELECT*FROM tb_peminjam";
            Statement st= Con.createStatement();
            RsPinjam= st.executeQuery(Sql);
            while(RsPinjam.next()){
                String AliasKode= RsPinjam.getString("kode_peminjam");
                CBKODEPINJAM.addItem(AliasKode);
            }
        } catch (Exception e) {
    JOptionPane.showMessageDialog(null,
            "Gagal Menampilkan Data\n"+e.getMessage());
    }
}
       public void TgldiText(){
        Date tgl=new Date();
        SimpleDateFormat Kembali=new SimpleDateFormat("yyyy-MM-dd");
        txtTanggalKembali.setText(Kembali.format(tgl));
        txtTanggalPinjam.setText(Kembali.format(tgl));
       }
       
      private void kosongkan(){
        TXTKODEKEMBALI.setText("");
        CBKODEPINJAM.setSelectedItem("Select");
        txtTanggalPinjam.setText("");
        TXTNAMAANGGOTA.setText("");
        TXTJUMLAHPINJAM.setText("");
        TXTKETERLAMBATAN.setText("");
      }

    private void tampilPengembalian(String sql){
        DefaultTableModel grid=new DefaultTableModel();
        grid.addColumn("No");
        grid.addColumn("Kode Pengembalian");
        grid.addColumn("Tanggal_Pengembalian");
        grid.addColumn("Kode Pinjam");
        grid.addColumn("Tanggal Pinjam");
        grid.addColumn("nama anggota");
        grid.addColumn("jumlah pinjam");
        grid.addColumn("keterlambatan");
        grid.addColumn("denda");
        try {
           int i=1;
           st=Con.createStatement();
           Rs=st.executeQuery("SELECT*FROM tb_pengembalian");
           while (Rs.next()){
               grid.addRow(new Object[]{
                   (""+i++),Rs.getString(1),Rs.getString(2),Rs.getString(3),
                    Rs.getString(4),Rs.getString(5),Rs.getString(6),Rs.getString(7),Rs.getString(8),
               });
               TPENGEMBALIAN.setModel(grid);
               TPENGEMBALIAN.enable(false);
              //BtnTambah.requestFocus();
           }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Tampil"+e);
        }
}
        private void tampilpeminjaman(String Sql){
        DefaultTableModel grid=new DefaultTableModel();
        grid.addColumn("No");
        grid.addColumn("Kode_pinjam");
        grid.addColumn("Tanggal_Pinjam");
        grid.addColumn("Kode_Petugas");
        grid.addColumn("Nama_petugas");
        grid.addColumn("Kode Anggota");
        grid.addColumn("Nama_Anggota");
        grid.addColumn("Jumlah Pinjam");
        try {
           int i=1;
           st=Con.createStatement();
           Rs=st.executeQuery("SELECT*FROM tb_peminjam");
           while (Rs.next()){
               grid.addRow(new Object[]{
                   (""+i++),Rs.getString(1),Rs.getString(2),Rs.getString(3),
                    Rs.getString(4),Rs.getString(5),Rs.getString(6),Rs.getString(7)
               });
               TdetailPengembalian.setModel(grid);
               TdetailPengembalian.enable(false);
               Btnsimpan.requestFocus();
           }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Tampil"+e);
        }
    }    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        TXTKODEKEMBALI = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        CBKODEPINJAM = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        TXTNAMAANGGOTA = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TXTJUMLAHPINJAM = new javax.swing.JTextField();
        txtTanggalPinjam = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TPENGEMBALIAN = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        TdetailPengembalian = new javax.swing.JTable();
        Btnsimpan = new javax.swing.JButton();
        BtnEdit = new javax.swing.JButton();
        BtnHapus = new javax.swing.JButton();
        BtnBatal = new javax.swing.JButton();
        BtnKeluar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtTanggalKembali = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TXTKETERLAMBATAN = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtdenda = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/PENGEMBALIAN.png"))); // NOI18N
        jLabel1.setText("DATA PENGEMBALIAN BUKU PERPUSTAKAAN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 153, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("KODE PENGEMBALIAN");

        TXTKODEKEMBALI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXTKODEKEMBALIKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(TXTKODEKEMBALI, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TXTKODEKEMBALI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 153, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("NAMA ANGGOTA");

        jLabel4.setText("KODE PEMINJAM");

        CBKODEPINJAM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBKODEPINJAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBKODEPINJAMActionPerformed(evt);
            }
        });

        jLabel5.setText("Tgl/PINJAM");

        jLabel7.setText("JUMLAH PINJAM");

        TXTJUMLAHPINJAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTJUMLAHPINJAMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CBKODEPINJAM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXTNAMAANGGOTA, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(txtTanggalPinjam)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(56, 76, Short.MAX_VALUE)
                        .addComponent(TXTJUMLAHPINJAM, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CBKODEPINJAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(TXTNAMAANGGOTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(TXTJUMLAHPINJAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        TPENGEMBALIAN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "KODE PENGEMBALIAN", "TANGGAL KEMBALI", "KODE PINJAM", "TANGGAL PINJAM", "NAMA ANGGOTA", "JUMLAH PINJAM", "KETERLAMBATAN", "DENDA"
            }
        ));
        jScrollPane1.setViewportView(TPENGEMBALIAN);

        TdetailPengembalian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(TdetailPengembalian);

        Btnsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/save.png"))); // NOI18N
        Btnsimpan.setText("SIMPAN");
        Btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnsimpanActionPerformed(evt);
            }
        });

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/edit-16x16.png"))); // NOI18N
        BtnEdit.setText("EDIT");
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/HAPUS.png"))); // NOI18N
        BtnHapus.setText("HAPUS");
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/refresh-16x16.png"))); // NOI18N
        BtnBatal.setText("BATAL");
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/exit.png"))); // NOI18N
        BtnKeluar.setText("KELUAR");
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });

        jLabel3.setText("Tgl/ PENGEMBALIAN");

        txtTanggalKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTanggalKembaliActionPerformed(evt);
            }
        });

        jLabel8.setText("KETERLAMBATAN");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("DENDA");

        jLabel11.setText("Keterangan :");

        jLabel13.setText("Ketelambatan ");

        jLabel14.setText("3000/hari");

        txtdenda.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        txtdenda.setText("0,");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtdenda, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTanggalKembali, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11)
                            .addComponent(jLabel14)))
                    .addComponent(TXTKETERLAMBATAN))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Btnsimpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnHapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnBatal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnKeluar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtTanggalKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(TXTKETERLAMBATAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14))
                            .addComponent(txtdenda))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btnsimpan)
                    .addComponent(BtnEdit)
                    .addComponent(BtnHapus)
                    .addComponent(BtnBatal)
                    .addComponent(BtnKeluar))
                .addGap(0, 17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TXTKODEKEMBALIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTKODEKEMBALIKeyPressed
        // TODO add your handling code here:
        kode_kembali=TXTKODEKEMBALI.getText();
        int A = evt.getKeyCode();
        if (A==10){
            try {
                Sql="SELECT * FROM tb_pengembalian WHERE Kode_pengembalian='"+kode_kembali+"'";
                st=Con.createStatement();
                Rs=st.executeQuery(Sql);
                while(Rs.next()){
                    CBKODEPINJAM.setSelectedItem(Rs.getString(3));
                    txtTanggalKembali.setText(Rs.getString(2));
                    TXTNAMAANGGOTA.setText(Rs.getString(4));
                    TXTJUMLAHPINJAM.setText(Rs.getString(5));
                    txtTanggalKembali.setText(Rs.getString(6));
                    TXTKETERLAMBATAN.setText(Rs.getString(7));
                    txtdenda.setText(Rs.getString(8));
                    Btnsimpan.enable(false);
                    BtnEdit.enable(true);
                    BtnHapus.enable(true);
                    CBKODEPINJAM.enable(false);
                    TXTNAMAANGGOTA.enable(false);
                    TXTJUMLAHPINJAM.enable(false);
                    txtTanggalKembali.enable(false);
                }
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, "Data Not Found\n"+e.getMessage());
                TXTKODEKEMBALI.requestFocus();
            }
        }
    }//GEN-LAST:event_TXTKODEKEMBALIKeyPressed

    private void CBKODEPINJAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBKODEPINJAMActionPerformed
        // TODO add your handling code here:
        try {
            Sql="select * from tb_peminjam where kode_peminjam='"+CBKODEPINJAM.getSelectedItem().toString()+"'";
            st=Con.createStatement();
            RsPinjam=st.executeQuery(Sql);
            while(RsPinjam.next()){
                txtTanggalPinjam.setText(RsPinjam.getString("tanggal_pinjam"));
                TXTNAMAANGGOTA.setText(RsPinjam.getString("nama_anggota"));
                TXTJUMLAHPINJAM.setText(RsPinjam.getString("jumlah_pinjam"));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_CBKODEPINJAMActionPerformed

    private void TXTJUMLAHPINJAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTJUMLAHPINJAMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTJUMLAHPINJAMActionPerformed

    private void BtnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnsimpanActionPerformed
        // TODO add your handling code here:
        kode_kembali=TXTKODEKEMBALI.getText();
        Tanggalkembali=txtTanggalKembali.getText();
        kodepinjam=CBKODEPINJAM.getItemAt(CBKODEPINJAM.getSelectedIndex());
        Tanggalpinjam=txtTanggalPinjam.getText();
        namaanggota=TXTNAMAANGGOTA.getText();
        jumlahpinjam=Integer.parseInt(TXTJUMLAHPINJAM.getText());
        Keterlambatan=Integer.parseInt(TXTKETERLAMBATAN.getText());
        Denda=Integer.parseInt(txtdenda.getText());
        try {
            Sql="INSERT INTO tb_pengembalian"
            + "(Kode_pengembalian,tanggal_pengembalian,kode_pinjam,tanggal_pinjam,nama_anggota,jumlah_pinjam,keterlambatan,denda)"
            + "VALUES('"+ kode_kembali+"',"
            + "'"+Tanggalkembali+"',"
            + "'"+kodepinjam+"',"
            + "'"+Tanggalpinjam+"',"
            + "'"+namaanggota+"',"
            + "'"+jumlahpinjam+"',"
            + "'"+Keterlambatan+"',"
            + "'"+Denda+"')";
            st=Con.createStatement();
            st.execute(Sql);
            kosongkan();
            tampilPengembalian("SELECT*FROM tb_pengembalian");
            JOptionPane.showMessageDialog(null, "Saving Succses");
            Btnsimpan.enable(true);

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,"Data Tidak Tersimpan\n"+e.getMessage());
        }
    }//GEN-LAST:event_BtnsimpanActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        // TODO add your handling code here:
        kosongkan();
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        // TODO add your handling code here:
        new MenuUtama().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void txtTanggalKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTanggalKembaliActionPerformed
        // TODO add your handling code here:
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        try{

            //Konversi dari string ke tanggal
            java.util.Date tanggalpinjam = df.parse(txtTanggalPinjam.getText());
            java.util.Date tanggalkembali = df.parse(txtTanggalKembali.getText());

            //Tgl di konversi ke milidetik
            long Hari1 = tanggalpinjam.getTime();
            long Hari2 = tanggalkembali.getTime();
            long diff = Hari2 - Hari1;
            long Lama = diff / (24 * 60 * 60 * 1000);

            TXTKETERLAMBATAN.setText(Long.toString(Lama));

            Keterlambatan=Integer.parseInt(TXTKETERLAMBATAN.getText());
            //denda=Integer.parseInt(TxtDenda.getText());

            Hasil=3000*Keterlambatan;

            txtdenda.setText(String.valueOf(Hasil));
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtTanggalKembaliActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DataPengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataPengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataPengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataPengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataPengembalian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBatal;
    private javax.swing.JButton BtnEdit;
    private javax.swing.JButton BtnHapus;
    private javax.swing.JButton BtnKeluar;
    private javax.swing.JButton Btnsimpan;
    private javax.swing.JComboBox<String> CBKODEPINJAM;
    private javax.swing.JTable TPENGEMBALIAN;
    private javax.swing.JTextField TXTJUMLAHPINJAM;
    private javax.swing.JTextField TXTKETERLAMBATAN;
    private javax.swing.JTextField TXTKODEKEMBALI;
    private javax.swing.JTextField TXTNAMAANGGOTA;
    private javax.swing.JTable TdetailPengembalian;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtTanggalKembali;
    private javax.swing.JTextField txtTanggalPinjam;
    private javax.swing.JLabel txtdenda;
    // End of variables declaration//GEN-END:variables
}
