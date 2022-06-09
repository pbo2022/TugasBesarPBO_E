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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ASUS
 */
public class ANGGOTA extends javax.swing.JFrame {
private Statement st;
private Connection Con;
private ResultSet Rs;
private String tampilan="yyyy-MM-dd";
private String Sql="";
private String host;
public String kodeanggota,namaanggota,jeniskelamin,tanggallahir,tempatlahir,alamat,nohp,Tanggal;
    /**
     * Creates new form ANGGOTA
     */
    public ANGGOTA() {
        initComponents();
        Koneksianggota();
        TampilAnggota("SELECT*FROM tb_anggota");
        kosongkan();    
    }
private void Koneksianggota(){
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
private void TampilAnggota(String Sql){
        DefaultTableModel  grid=new DefaultTableModel();
        grid.addColumn("No");
        grid.addColumn("Kode Anggota");
        grid.addColumn("Nama Anggota");
        grid.addColumn("Jenis Kelamin");
        grid.addColumn("Tanggal Lahir");
        grid.addColumn("Tempat Lahir");
        grid.addColumn("Alamat");
        grid.addColumn("No.Hp");
        try {
            int i=1;
            st=Con.createStatement();
            Rs=st.executeQuery("SELECT*FROM tb_anggota");
            while (Rs.next()){
                grid.addRow(new Object[]{
                    (""+i++),Rs.getString(1),
                    Rs.getString(2),
                    Rs.getString(3),
                    Rs.getString(4),
                    Rs.getString(5),
                    Rs.getString(6),
                    Rs.getString(7)
            });
               TANGGOTA.setModel(grid);
               TANGGOTA.enable(false);
               //BTNTAMBAH.requestFocus();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Gagal Tampil: "+e);
        }
     }
private void kosongkan (){
         TXTKODEANGGOTA.setText("");
         TXTNAMAANGGOTA.setText("");
         CBJENISKELAMIN.addItem("Select");
         JTANGGAL.setDate(null);
         TXTTEMPATLAHIR.setText("");
         TXTALAMAT.setText("");
         TXTNOHP.setText("");
         TXTKODEANGGOTA.requestFocus();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TXTKODEANGGOTA = new javax.swing.JTextField();
        TXTNAMAANGGOTA = new javax.swing.JTextField();
        TXTTEMPATLAHIR = new javax.swing.JTextField();
        CBJENISKELAMIN = new javax.swing.JComboBox<>();
        TXTALAMAT = new javax.swing.JTextField();
        TXTNOHP = new javax.swing.JTextField();
        BTNSIMPAN = new javax.swing.JButton();
        BTNEDIT = new javax.swing.JButton();
        BTNHAPUS = new javax.swing.JButton();
        BTNBATAL = new javax.swing.JButton();
        BTNKELUAR = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TANGGOTA = new javax.swing.JTable();
        JTANGGAL = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/userddd.png"))); // NOI18N
        jLabel1.setText(" ANGGOTA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("KODE ANGGOTA");

        jLabel3.setText("NAMA ANGGOTA");

        jLabel4.setText("JENIS KELAMIN");

        jLabel5.setText("TANGGAL LAHIR");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel6.setText("TEMPAT LAHIR");

        jLabel7.setText("ALAMAT");

        jLabel8.setText("NO HP");

        TXTKODEANGGOTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTKODEANGGOTAActionPerformed(evt);
            }
        });
        TXTKODEANGGOTA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXTKODEANGGOTAKeyPressed(evt);
            }
        });

        TXTTEMPATLAHIR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TXTTEMPATLAHIRMouseClicked(evt);
            }
        });

        CBJENISKELAMIN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "LAKI - LAKI", "PEREMPUAN" }));

        BTNSIMPAN.setText("SIMPAN");
        BTNSIMPAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSIMPANActionPerformed(evt);
            }
        });

        BTNEDIT.setText("EDIT");
        BTNEDIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNEDITActionPerformed(evt);
            }
        });

        BTNHAPUS.setText("HAPUS");
        BTNHAPUS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNHAPUSActionPerformed(evt);
            }
        });

        BTNBATAL.setText("BATAL");
        BTNBATAL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNBATALActionPerformed(evt);
            }
        });

        BTNKELUAR.setText("KELUAR");
        BTNKELUAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNKELUARActionPerformed(evt);
            }
        });

        TANGGOTA.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(TANGGOTA);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(CBJENISKELAMIN, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TXTTEMPATLAHIR)
                                    .addComponent(JTANGGAL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TXTKODEANGGOTA, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(TXTNAMAANGGOTA))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TXTALAMAT, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(TXTNOHP))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(BTNSIMPAN, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BTNEDIT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BTNHAPUS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BTNBATAL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BTNKELUAR))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TXTKODEANGGOTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTALAMAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TXTNAMAANGGOTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTNOHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CBJENISKELAMIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(JTANGGAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(TXTTEMPATLAHIR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNSIMPAN)
                    .addComponent(BTNEDIT)
                    .addComponent(BTNHAPUS)
                    .addComponent(BTNBATAL)
                    .addComponent(BTNKELUAR))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void TXTKODEANGGOTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTKODEANGGOTAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTKODEANGGOTAActionPerformed

    private void TXTKODEANGGOTAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTKODEANGGOTAKeyPressed
        // TODO add your handling code here:
        kodeanggota=TXTKODEANGGOTA.getText();
        int A = evt.getKeyCode();
        if (A==10){
            try {
                Sql="SELECT * FROM tb_anggota WHERE Kode_anggota='"+kodeanggota+"'";
                st=Con.createStatement();
                Rs=st.executeQuery(Sql);
                while(Rs.next()){
                    TXTNAMAANGGOTA.setText(Rs.getString(2));
                    CBJENISKELAMIN.setSelectedItem(Rs.getString(3));
                    JTANGGAL.setDate(Rs.getDate(4));
                    TXTTEMPATLAHIR.setText(Rs.getString(5));
                    TXTALAMAT.setText(Rs.getString(6));
                    TXTNOHP.setText(Rs.getString(7));
                    BTNSIMPAN.enable(false);
                    BTNEDIT.enable(true);
                    BTNHAPUS.enable(true);
                }
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, "Data Not Found\n"+e.getMessage());
                TXTNAMAANGGOTA.requestFocus();
            }
        }
    }//GEN-LAST:event_TXTKODEANGGOTAKeyPressed

    private void TXTTEMPATLAHIRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TXTTEMPATLAHIRMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTTEMPATLAHIRMouseClicked

    private void BTNSIMPANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSIMPANActionPerformed
        // TODO add your handling code here:
        kodeanggota=TXTKODEANGGOTA.getText();
        namaanggota=TXTNAMAANGGOTA.getText();
        jeniskelamin=CBJENISKELAMIN.getItemAt(CBJENISKELAMIN.getSelectedIndex());
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String Tanggal = String.valueOf (fm.format(JTANGGAL.getDate()));
        tempatlahir=TXTTEMPATLAHIR.getText();
        alamat=TXTALAMAT.getText();
        nohp=TXTNOHP.getText();
        try {
            Sql="INSERT INTO tb_anggota"
            + "(kode_anggota,nama_anggota,jenis_kelamin,tanggal_lahir,tempat_lahir,alamat,nohp)"
            + "VALUES('"+ kodeanggota+"',"
            + "'"+namaanggota+"',"
            + "'"+jeniskelamin+"',"
            + "'"+Tanggal+"',"
            + "'"+tempatlahir+"',"
            + "'"+alamat+"',"
            + "'"+nohp+"')";
            st=Con.createStatement();
            st.execute(Sql);
            kosongkan();
            TampilAnggota("SELECT*FROM tb_anggota");
            JOptionPane.showMessageDialog(null, "Saving Succses");
            //BTNTAMBAH.show();
            BTNSIMPAN.enable(true);

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,"Data Tidak Tersimpan\n"+e.getMessage());
        }

    }//GEN-LAST:event_BTNSIMPANActionPerformed

    private void BTNEDITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNEDITActionPerformed
        // TODO add your handling code here:
        kodeanggota=TXTKODEANGGOTA.getText();
        namaanggota=TXTNAMAANGGOTA.getText();
        jeniskelamin=CBJENISKELAMIN.getItemAt(CBJENISKELAMIN.getSelectedIndex());
        tempatlahir=TXTTEMPATLAHIR.getText();
        alamat=TXTALAMAT.getText();
        nohp=TXTNOHP.getText();
        try { Sql="UPDATE tb_anggota SET nama_anggota='"+namaanggota+"',"
            + "jenis_kelamin='"+jeniskelamin+"',"
            + "tanggal_lahir='"+Tanggal+"',"
            + "tempat_lahir='"+tempatlahir+"',"
            + "alamat='"+alamat+"',"
            + "nohp='"+nohp+"'"
            + "WHERE Kode_anggota='"+kodeanggota+"'";
            st=Con.createStatement();
            st.execute(Sql);
            kosongkan();
            TampilAnggota("SELECT*FROM tb_anggota");
            JOptionPane.showMessageDialog(null, "Edit Succses");
            //BTNTAMBAH.show();
            BTNSIMPAN.show();
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,"Data Tidak Di Perbaharui\n"+e.getMessage());
        }
    }//GEN-LAST:event_BTNEDITActionPerformed

    private void BTNHAPUSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNHAPUSActionPerformed
        // TODO add your handling code here:
        kodeanggota=TXTKODEANGGOTA.getText();
        try { Sql="DELETE FROM tb_anggota "
            + "WHERE Kode_anggota='" +kodeanggota+ "'";
            st=Con.createStatement();
            st.execute(Sql);
            kosongkan();
            TampilAnggota("SELECT*FROM tb_anggota");
            JOptionPane.showMessageDialog(null, "Delete Succses");
            //BTNTAMBAH.show();
            BTNSIMPAN.show();
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,"Data Tidak Di Hapus\n"+e.getMessage());
        }
    }//GEN-LAST:event_BTNHAPUSActionPerformed

    private void BTNBATALActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNBATALActionPerformed
        // TODO add your handling code here:
        kosongkan();
    }//GEN-LAST:event_BTNBATALActionPerformed

    private void BTNKELUARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNKELUARActionPerformed
        // TODO add your handling code here:
        new MenuUtama().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BTNKELUARActionPerformed

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
            java.util.logging.Logger.getLogger(ANGGOTA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ANGGOTA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ANGGOTA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ANGGOTA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ANGGOTA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNBATAL;
    private javax.swing.JButton BTNEDIT;
    private javax.swing.JButton BTNHAPUS;
    private javax.swing.JButton BTNKELUAR;
    private javax.swing.JButton BTNSIMPAN;
    private javax.swing.JComboBox<String> CBJENISKELAMIN;
    private com.toedter.calendar.JDateChooser JTANGGAL;
    private javax.swing.JTable TANGGOTA;
    private javax.swing.JTextField TXTALAMAT;
    private javax.swing.JTextField TXTKODEANGGOTA;
    private javax.swing.JTextField TXTNAMAANGGOTA;
    private javax.swing.JTextField TXTNOHP;
    private javax.swing.JTextField TXTTEMPATLAHIR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
