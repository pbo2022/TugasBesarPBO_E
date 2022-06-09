/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tubesperpustakaan;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author ASUS
 */
public class DataPeminjam extends javax.swing.JFrame {
private Statement st;
private Connection Con;
private ResultSet RsBuku;
private ResultSet RsAnggota;
private ResultSet Rs;
private ResultSet RsPetugas;
private ResultSet RsJual;
private ResultSet RsDetail;
private ResultSet RsPeminjaman;
public String kodepinjam,kodepetugas,namapetugas,kodeanggota,namaanggota,Tanggal;
public int jumlahpinjam;
private String tampilan="yyyy-MM-dd";
private String Sql="";
    /**
     * Creates new form DataPeminjam
     */
    public DataPeminjam() {
        initComponents();
        KoneksiPeminjam();
        tampilpeminjaman();
        PilihPetugas();
        PilihBuku();
        PilihAnggota();
        Waktu();
    }
private void KoneksiPeminjam(){
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
private void PilihPetugas(){
        CBPETUGAS.removeAllItems();
        CBPETUGAS.addItem("Select");
        try {
            String Sql ="SELECT*FROM tb_petugas";
            Statement st= Con.createStatement();
            RsPetugas= st.executeQuery(Sql);
            while(RsPetugas.next()){
            String AliasKode= RsPetugas.getString("kode_Petugas");
            CBPETUGAS.addItem(AliasKode);
        }
        } catch (Exception e) {
    JOptionPane.showMessageDialog(null,
            "Gagal Menampilkan Id Pelanggan\n"+e.getMessage());
}
  }
     private void PilihBuku(){
        CBBUKU.removeAllItems();
        CBBUKU.addItem("Select");
        try {
            String Sql ="SELECT*FROM tb_buku";
            Statement st= Con.createStatement();
            RsBuku= st.executeQuery(Sql);
            while(RsBuku.next()){
            String AliasKode= RsBuku.getString("kode_buku");
            CBBUKU.addItem(AliasKode);
        }
        } catch (Exception e) {
    JOptionPane.showMessageDialog(null,
            "Gagal Menampilkan Id Pelanggan\n"+e.getMessage());
}
     }
     private void PilihAnggota(){
        CBANGGOTA.removeAllItems();
        CBANGGOTA.addItem("Select");
        try {
            String Sql ="SELECT*FROM tb_anggota";
            Statement st= Con.createStatement();
            RsAnggota= st.executeQuery(Sql);
            while(RsAnggota.next()){
            String AliasKode= RsAnggota.getString("kode_anggota");
            CBANGGOTA.addItem(AliasKode);
        }
        } catch (Exception e) {
    JOptionPane.showMessageDialog(null,
            "Gagal Menampilkan Id Pelanggan\n"+e.getMessage());
}
     }
    private void prosestambah(){
        try {
            DefaultTableModel tableModel =(DefaultTableModel)TPINJAM.getModel();
            String[]data = new String[4];
            data[0]= String.valueOf(CBBUKU.getSelectedItem());
            data[1]= TXTJUDULBUKU.getText();
            data[2]= TXTPENGARANG.getText();
            data[3]= TXTJUMLAHPINJAM.getText();
            tableModel.addRow(data);            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error masukkan data \n"+e.getMessage());
        }
    }    
     private void Total(){
      int jumlahBaris = TPINJAM.getRowCount();
        int ttlpinjam = 0, jlhpinjam=0;
        int Jlhtotalpinjam;
        
        TableModel tblmodel;
        tblmodel = TPINJAM.getModel();
        for (int i=0; i<jumlahBaris; i++){
            Jlhtotalpinjam=Integer.parseInt(tblmodel.getValueAt(i, 3).toString());
                jlhpinjam=Jlhtotalpinjam+jlhpinjam;
                }
        TXTTOTALPINJAM.setText(String.valueOf(jlhpinjam));
     }
     public void Waktu(){
        Date tgl=new Date();
        JTANGGAL.setDate(tgl);
     }
      private void simpandetail(){
        int jumlah_baris=TPINJAM.getRowCount();
        if (jumlah_baris == 0) {
            JOptionPane.showMessageDialog(rootPane, "Table is empty!!");
        } else {
            try {
                int i=0;
                while(i<jumlah_baris){
                    st.executeUpdate("insert into tb_detailpeminjaman"
                            + "(kode_pinjam,kode_buku,judul_buku,pengarang,jumlah_pinjam)"
                            + "values('"+TXTKODEPINJAM.getText()+"',"
                            + "'"+TPINJAM.getValueAt(i,0)+"',"
                            + "'"+TXTJUDULBUKU.getText()+"',"
                            + "'"+TXTPENGARANG.getText()+"',"
                            + "'"+TXTJUMLAHPINJAM.getText()+"')");
                    i++;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Gagal Menyimpan..!!"+e);
            }
        }
    }
        
  private void tampilpeminjaman(){
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
               TPINJAM1.setModel(grid);
               TPINJAM1.enable(false);
               BTNSIMPAN.requestFocus();
           }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Tampil"+e);
        }
    }    
  
  private void kosongkan(){
        TXTKODEPINJAM.setText("");
        JTANGGAL.setDate(null);
        CBANGGOTA.setSelectedItem(null);
        TXTNAMAANGGOTA.setText("");
        TXTALAMAT.setText("");
        TXTNOHP.setText("");
        CBPETUGAS.setSelectedItem(null);
        TXTNAMAPETUGAS.setText("");
        TXTJABATAN.setText("");
        CBBUKU.setSelectedItem(null);
        TXTJUDULBUKU.setText("");
        TXTPENGARANG.setText("");
        TXTJUMLAHPINJAM.setText("");
  }
  
  private void hapustable() {
    DefaultTableModel model = (DefaultTableModel)TPINJAM.getModel();

    while (model.getRowCount() > 0){
        for (int i = 0; i < model.getRowCount(); ++i){
            model.removeRow(i);
        }
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
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        CBPETUGAS = new javax.swing.JComboBox<>();
        TXTNAMAPETUGAS = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TXTJABATAN = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        TXTNAMAANGGOTA = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        CBANGGOTA = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        TXTALAMAT = new javax.swing.JTextField();
        TXTNOHP = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        TXTJUDULBUKU = new javax.swing.JTextField();
        CBBUKU = new javax.swing.JComboBox<>();
        TXTPENGARANG = new javax.swing.JTextField();
        TXTJUMLAHPINJAM = new javax.swing.JTextField();
        BTNTAMBAH = new javax.swing.JButton();
        BtnKurang = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TXTKODEPINJAM = new javax.swing.JTextField();
        JTANGGAL = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        TPINJAM = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        TXTTOTALPINJAM = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TPINJAM1 = new javax.swing.JTable();
        BTNSIMPAN = new javax.swing.JButton();
        BTNEDIT = new javax.swing.JButton();
        BTNHAPUS = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/742593936.png"))); // NOI18N
        jLabel7.setText("DATA PEMINJAM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel7)
                .addContainerGap(697, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 153, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INPUT PETUGAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        CBPETUGAS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBPETUGAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBPETUGASActionPerformed(evt);
            }
        });

        jLabel3.setText("KODE PETUGAS");

        jLabel4.setText("NAMA PETUGAS");

        jLabel8.setText("JABATAN ");

        TXTJABATAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTJABATANActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CBPETUGAS, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TXTNAMAPETUGAS, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(TXTJABATAN))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CBPETUGAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTNAMAPETUGAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(TXTJABATAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 153, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INPUT DATA ANGGOTA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jLabel5.setText("KODE ANGGOTA");

        jLabel6.setText("NAMA ANGGOTA");

        CBANGGOTA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBANGGOTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBANGGOTAActionPerformed(evt);
            }
        });

        jLabel9.setText("ALAMAT");

        jLabel10.setText("NO.HP");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CBANGGOTA, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TXTNAMAANGGOTA, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TXTNOHP)
                            .addComponent(TXTALAMAT, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(CBANGGOTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTNAMAANGGOTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(TXTALAMAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(TXTNOHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 153, 0));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INPUT DATA BUKU", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jLabel11.setText("KODEBUKU");

        jLabel12.setText("JUDUL BUKU");

        jLabel13.setText("PENGARANG");

        jLabel14.setText("JUMLAH PINJAM");

        CBBUKU.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBBUKU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBBUKUActionPerformed(evt);
            }
        });

        BTNTAMBAH.setText("+");
        BTNTAMBAH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNTAMBAHActionPerformed(evt);
            }
        });

        BtnKurang.setText("-");
        BtnKurang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKurangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CBBUKU, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TXTJUMLAHPINJAM, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(BTNTAMBAH)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnKurang, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                                    .addComponent(TXTJUDULBUKU)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TXTPENGARANG, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(CBBUKU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(TXTJUDULBUKU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(TXTPENGARANG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(TXTJUMLAHPINJAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNTAMBAH)
                    .addComponent(BtnKurang)))
        );

        jPanel2.setBackground(new java.awt.Color(255, 153, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("KODE PINJAM");

        jLabel2.setText("TANGGAL PINJAM");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(54, 54, 54))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(35, 35, 35)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TXTKODEPINJAM, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(JTANGGAL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(289, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TXTKODEPINJAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(JTANGGAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TPINJAM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "KODE BUKU", "JUDUL BUKU", "PENGARANG", "JUMLAH PINJAM"
            }
        ));
        jScrollPane1.setViewportView(TPINJAM);

        jLabel15.setText("TOTAL PINJAM");

        TPINJAM1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(TPINJAM1);

        BTNSIMPAN.setText("SIMPAN");
        BTNSIMPAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSIMPANActionPerformed(evt);
            }
        });

        BTNEDIT.setText("EDIT");

        BTNHAPUS.setText("HAPUS");
        BTNHAPUS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNHAPUSActionPerformed(evt);
            }
        });

        jButton4.setText("BATAL");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("KELUAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TXTTOTALPINJAM, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BTNSIMPAN)
                                .addGap(18, 18, 18)
                                .addComponent(BTNEDIT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BTNHAPUS)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(TXTTOTALPINJAM, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BTNSIMPAN)
                            .addComponent(BTNEDIT)
                            .addComponent(BTNHAPUS)
                            .addComponent(jButton4)
                            .addComponent(jButton5))))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CBPETUGASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBPETUGASActionPerformed
        // TODO add your handling code here:
        try {
            Sql="select * from tb_petugas where kode_petugas='"+CBPETUGAS.getSelectedItem().toString()+"'";
            st=Con.createStatement();
            RsPetugas=st.executeQuery(Sql);
            while(RsPetugas.next()){
                TXTNAMAPETUGAS.setText(RsPetugas.getString("nama_petugas"));//sesuaikan di database, atau bisa di ubah menjadi("nama_pelanggan")
                TXTJABATAN.setText(RsPetugas.getString("jabatan"));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_CBPETUGASActionPerformed

    private void TXTJABATANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTJABATANActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTJABATANActionPerformed

    private void CBANGGOTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBANGGOTAActionPerformed
        // TODO add your handling code here:
        try {
            Sql="select * from tb_anggota where kode_anggota='"+CBANGGOTA.getSelectedItem().toString()+"'";
            st=Con.createStatement();
            RsAnggota=st.executeQuery(Sql);
            while(RsAnggota.next()){
                TXTNAMAANGGOTA.setText(RsAnggota.getString("nama_anggota"));//sesuaikan di database, atau bisa di ubah menjadi("nama_pelanggan")
                TXTALAMAT.setText(RsAnggota.getString("alamat"));
                TXTNOHP.setText(RsAnggota.getString("nohp"));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_CBANGGOTAActionPerformed

    private void CBBUKUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBBUKUActionPerformed
        // TODO add your handling code here:
        try {
            Sql="select * from tb_buku where kode_buku='"+CBBUKU.getSelectedItem().toString()+"'";
            st=Con.createStatement();
            RsBuku=st.executeQuery(Sql);
            while(RsBuku.next()){
                TXTJUDULBUKU.setText(RsBuku.getString("judul_buku"));//sesuaikan di database, atau bisa di ubah menjadi("nama_pelanggan")
                TXTPENGARANG.setText(RsBuku.getString("pengarang"));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_CBBUKUActionPerformed

    private void BTNTAMBAHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNTAMBAHActionPerformed
        // TODO add your handling code here:
        prosestambah();
        Total();
    }//GEN-LAST:event_BTNTAMBAHActionPerformed

    private void BtnKurangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKurangActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)TPINJAM.getModel();
        int row = TPINJAM.getSelectedRow();
        if (row>=0) {
            int ok = JOptionPane.showConfirmDialog(null, "You sure you want to Delete","Message",JOptionPane.YES_NO_OPTION);

            if (ok==0){
                model.removeRow(row);
            }
        }
        Total();
    }//GEN-LAST:event_BtnKurangActionPerformed

    private void BTNSIMPANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSIMPANActionPerformed
        // TODO add your handling code here:
        kodepinjam=TXTKODEPINJAM.getText();
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String Tanggal = String.valueOf (fm.format(JTANGGAL.getDate()));
        kodepetugas=CBPETUGAS.getItemAt(CBPETUGAS.getSelectedIndex()).toString();
        namapetugas=TXTNAMAPETUGAS.getText();
        kodeanggota=CBANGGOTA.getItemAt(CBANGGOTA.getSelectedIndex()).toString();
        namaanggota=TXTNAMAANGGOTA.getText();
        jumlahpinjam=Integer.parseInt(TXTTOTALPINJAM.getText());
        simpandetail();
        try {
            Sql="insert into tb_peminjam"
            +"(kode_peminjam,tanggal_pinjam,kode_petugas,nama_petugas,kode_anggota,nama_anggota,jumlah_pinjam)"
            +"values('"+kodepinjam+"',"
            + "'"+Tanggal+"',"
            + "'"+kodepetugas+"',"
            + "'"+namapetugas+"',"
            + "'"+kodeanggota+"',"
            + "'"+namaanggota+"',"
            + "'"+jumlahpinjam+"')";
            st=Con.createStatement();
            st.execute(Sql);
            kosongkan();

            tampilpeminjaman();
            JOptionPane.showMessageDialog(null, "Data successfully saved");
            hapustable();
            BTNTAMBAH.show();
            BTNSIMPAN.show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data is not successfully saved, Data that you entered is incorrect"+e.getMessage());
        }
    }//GEN-LAST:event_BTNSIMPANActionPerformed

    private void BTNHAPUSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNHAPUSActionPerformed
        // TODO add your handling code here:
        kodepinjam=TXTKODEPINJAM.getText();
        try {
            Sql="delete from tb_peminjam "
            + "where kode_peminjam='"+kodepinjam+"'";
            st=Con.createStatement();
            st.execute(Sql);
            kosongkan();
            tampilpeminjaman();
            JOptionPane.showMessageDialog(null, "Data has been Removed");
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Data is not deleted !!"+e.getMessage());

        }
    }//GEN-LAST:event_BTNHAPUSActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        kosongkan();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        new MenuUtama().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(DataPeminjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataPeminjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataPeminjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataPeminjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataPeminjam().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNEDIT;
    private javax.swing.JButton BTNHAPUS;
    private javax.swing.JButton BTNSIMPAN;
    private javax.swing.JButton BTNTAMBAH;
    private javax.swing.JButton BtnKurang;
    private javax.swing.JComboBox<String> CBANGGOTA;
    private javax.swing.JComboBox<String> CBBUKU;
    private javax.swing.JComboBox<String> CBPETUGAS;
    private com.toedter.calendar.JDateChooser JTANGGAL;
    private javax.swing.JTable TPINJAM;
    private javax.swing.JTable TPINJAM1;
    private javax.swing.JTextField TXTALAMAT;
    private javax.swing.JTextField TXTJABATAN;
    private javax.swing.JTextField TXTJUDULBUKU;
    private javax.swing.JTextField TXTJUMLAHPINJAM;
    private javax.swing.JTextField TXTKODEPINJAM;
    private javax.swing.JTextField TXTNAMAANGGOTA;
    private javax.swing.JTextField TXTNAMAPETUGAS;
    private javax.swing.JTextField TXTNOHP;
    private javax.swing.JTextField TXTPENGARANG;
    private javax.swing.JTextField TXTTOTALPINJAM;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
