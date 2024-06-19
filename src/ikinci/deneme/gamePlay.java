/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ikinci.deneme;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.Duration;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author HP
 */
public class gamePlay extends javax.swing.JFrame {

    private boolean collisionHandled = false;
    private Timer hareketTimer;
    private Timer lahmacunTimer;
    private Timer eklemeTimer;
    private Timer sureTimer;
    private int sure;
    private final int step = 5;
    private JLabel sureLabel;

    // Düþman listesi
    /**
     * Creates new form gamePlay
     */
    public gamePlay() {

        initComponents();

         baslat();
    }

   private void baslat() {
       
       
        sureTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sure++;
            }
        });
        sureTimer.start();

        hareketTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hareketEt();
                kontrolEt();
            }
        });
        hareketTimer.start();

        eklemeTimer = new Timer(4000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 5; i++) {
                    JLabel label = new JLabel();
                    label.setSize(30, 26);
                    label.setOpaque(true);
                    label.setBackground(Color.black);
                    
                    oyunIci.add(label);
                    rastgeleKonum(label);
                }
                oyunIci.revalidate();
                oyunIci.repaint();
            }
        });
        eklemeTimer.start();
    }

 private void kontrolEt() {
        boolean collisionDetected = false;

        for (Component c : oyunIci.getComponents()) {
            if (c instanceof JLabel && c != character) {
                JLabel atis = (JLabel) c;
                for (Component other : oyunIci.getComponents()) {
                    if (other instanceof JLabel && other != atis && other != character) {
                        JLabel hedef = (JLabel) other;
                        if (atis.getBounds().intersects(hedef.getBounds())) {
                            oyunIci.remove(atis);
                            oyunIci.remove(hedef);
                            oyunIci.revalidate();
                            oyunIci.repaint();
                            return;
                        }
                    }
                }
            }
        }

        for (Component other : oyunIci.getComponents()) {
            if (other instanceof JLabel && other != character) {
                JLabel label = (JLabel) other;
                if (character.getBounds().intersects(label.getBounds())) {
                    collisionDetected = true;
                    break;
                }
            }
        }

        if (collisionDetected && !collisionHandled) {
            collisionHandled = true;
            hareketTimer.stop();
            eklemeTimer.stop();
            sureTimer.stop(); // Sayaç timer'ýný durdur
            JOptionPane.showMessageDialog(null, "Oyunu kaybettiniz! Skor: " + sure + " saniye", "Oyun Bitti", JOptionPane.INFORMATION_MESSAGE);
            karakterSecimi k1 = new karakterSecimi();
            k1.setVisible(true);
            this.dispose(); // Mevcut oyun penceresini kapat
        }
    }



   private void rastgeleKonum(JLabel label) {
        int panelWidth = oyunIci.getWidth() - label.getWidth();
        int panelHeight = oyunIci.getHeight() - label.getHeight();
        int x = (int) (Math.random() * panelWidth);
        int y = (int) (Math.random() * panelHeight);
        label.setLocation(x, y);
    }

   private void hareketEt() {
        int yavasStep = 2;
        for (Component c : oyunIci.getComponents()) {
            if (c instanceof JLabel && !c.equals(character)) {
                JLabel label = (JLabel) c;
                int labelX = label.getX();
                int labelY = label.getY();
                if (labelX < character.getX()) {
                    labelX += yavasStep;
                } else if (labelX > character.getX()) {
                    labelX -= yavasStep;
                }
                if (labelY < character.getY()) {
                    labelY += yavasStep;
                } else if (labelY > character.getY()) {
                    labelY -= yavasStep;
                }
                label.setLocation(labelX, labelY);
            }
        }
    }
   private void restartGame() {
        collisionHandled = false;
        oyunIci.removeAll();
        oyunIci.add(character);
        character.setLocation(210, 140);
        oyunIci.revalidate();
        oyunIci.repaint();
        baslat();
    }



    public JLabel getCharacter() {
        return character;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        oyunIci = new javax.swing.JPanel();
        character = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        oyunIci.setBackground(new java.awt.Color(102, 255, 255));
        oyunIci.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                oyunIciMouseClicked(evt);
            }
        });
        oyunIci.setLayout(null);

        character.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\Desktop\\Adsýz.png")); // NOI18N
        character.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                characterKeyPressed(evt);
            }
        });
        oyunIci.add(character);
        character.setBounds(350, 180, 40, 70);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(oyunIci, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(oyunIci, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        int x = character.getX();
        int y = character.getY();
        int step = 100;
            
        switch (evt.getKeyChar()) {
            case 'w':
                y -= step;
                break;
            case 'a':
                x -= step;
                break;
            case 's':
                y += step;
                break;
            case 'd':
                x += step;
                break;
        }
        if (x >= 0 && y >= 0 && x + character.getWidth() <= oyunIci.getWidth() && y + character.getHeight() <= oyunIci.getHeight()) {
            character.setLocation(x, y);
        }

    }//GEN-LAST:event_formKeyPressed

    private void oyunIciMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oyunIciMouseClicked
        // TODO add your handling code here:
        
        int mouseX = evt.getX();
        int mouseY = evt.getY();
        JLabel atis = new JLabel();
        atis.setBounds(mouseX, mouseY, 20, 20);
        atis.setOpaque(true);
        atis.setBackground(Color.RED);
        oyunIci.add(atis);
        oyunIci.revalidate();
        oyunIci.repaint();
        kontrolEt();


    }//GEN-LAST:event_oyunIciMouseClicked

    private void characterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_characterKeyPressed
        // TODO add your handling code here:
         int x = character.getX();
        int y = character.getY();
        int step = 100;
            
        switch (evt.getKeyChar()) {
            case 'w':
                y -= step;
                break;
            case 'a':
                x -= step;
                break;
            case 's':
                y += step;
                break;
            case 'd':
                x += step;
                break;
        }
        if (x >= 0 && y >= 0 && x + character.getWidth() <= oyunIci.getWidth() && y + character.getHeight() <= oyunIci.getHeight()) {
            character.setLocation(x, y);
        }
    }//GEN-LAST:event_characterKeyPressed

    //.

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
            java.util.logging.Logger.getLogger(gamePlay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gamePlay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gamePlay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gamePlay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gamePlay().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel character;
    public javax.swing.JPanel oyunIci;
    // End of variables declaration//GEN-END:variables

}
