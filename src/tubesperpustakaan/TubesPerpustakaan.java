/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tubesperpustakaan;

/**
 *
 * @author ASUS
 */
public class TubesPerpustakaan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Loading awal = new Loading();
        awal.setVisible(true);
        frm_buat_login lg = new frm_buat_login();
        
        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(50);
                awal.loadingnum1.setText(Integer.toString(i) + "%");
                awal.loadingbar.setValue(i);
                if(i==100) {
                    awal.setVisible(false);
                    lg.setVisible(true);
                }
            }
        } catch (Exception e) {
            
        }

    }
    
}
