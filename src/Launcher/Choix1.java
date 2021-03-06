/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Launcher;

import static Launcher.Launcher.deleteDirectory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aniss
 */
public class Choix1 extends javax.swing.JFrame {
    public Launcher launcher;
    public File jxtaPath;
    private ArrayList al = new ArrayList();
    private ArrayList liste = new ArrayList();

    /**
     * Creates new form Choix
     */
    public Choix1(Launcher athis,ArrayList listex) {
        this.launcher = athis;
        this.liste = listex;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Yes = new javax.swing.JButton();
        No = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Voulez-vous supprimer la cofiguration précédente?");

        Yes.setText("Oui");
        Yes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YesActionPerformed(evt);
            }
        });

        No.setText("Non");
        No.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    NoActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(Choix1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(94, 94, 94))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(Yes)
                .addGap(97, 97, 97)
                .addComponent(No)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Yes)
                    .addComponent(No))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void YesActionPerformed(java.awt.event.ActionEvent evt) { 
        this.setVisible(false);
        if(System.getProperty("os.name").contains("Windows")){
            
            getPath(new File("./"),".jxta");
            
        }else{
            if(System.getProperty("os.name").contains("Linux")){
                System.out.println("hello linux!!!");
                getPath(new File("/home/"),"Matrix.txt");
            }
        }
         if(!al.isEmpty()){
             jxtaPath = new File(al.get(0).toString());
         
        
        boolean supprimer = deleteDirectory(jxtaPath);
        if(supprimer){
            System.out.println("Suppression réussie");
        }
             
         }
         
         Launcher myLauncher = new Launcher();
         myLauncher.run();
    }                                   

    private void NoActionPerformed(java.awt.event.ActionEvent evt) throws IOException {  
        
        this.setVisible(false);
        Frame_set myFrame_set = new Frame_set(launcher);
        myFrame_set.setVisible(true);
        
    }                                  

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
            java.util.logging.Logger.getLogger(Choix.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Choix.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Choix.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Choix.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Choix().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify                     
    private javax.swing.JButton No;
    private javax.swing.JButton Yes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration  
    
    public void getPath(File f,String s){
     if(f.getName().equals(s)){
          al.add(f.getPath());
     }
        
 
		File[] liste_fils = f.listFiles();
 
		if(liste_fils!=null)
		{
			for(int i=0;i<liste_fils.length;i++)
			{
				getPath(liste_fils[i],s);
			}
                       
                     
        
    
                }
     }
    
     static public boolean deleteDirectory(File path) {
        boolean resultat = true;

        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    resultat &= deleteDirectory(files[i]);
                } else {
                    resultat &= files[i].delete();
                }
            }
        }
        resultat &= path.delete();
        return (resultat);
    }
}
