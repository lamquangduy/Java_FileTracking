
import Util.Client.SocketClient;
import Util.WatchFile;
import static Util.WatchFile.path;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JOptionPane;


public class Client extends javax.swing.JFrame {
    private WatchFile watchFile = null;
    SocketClient socketClient = null;
    private final javax.swing.JTable tbHistory;
    private final JLabel message;
    private String path;

    public Client(String path) {
        message = new JLabel();
        jScrollPane1 = new JScrollPane();
        tbHistory = new JTable();
        socketClient = new SocketClient();
        this.path = path;
        initComponents();
        jScrollPane1.setViewportView(tbHistory);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LB_IP = new javax.swing.JLabel();
        LB_Port = new javax.swing.JLabel();
        TF_IP = new javax.swing.JTextField();
        TF_Port = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        BT_Connect = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client");

        LB_IP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LB_IP.setText("IP:");

        LB_Port.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LB_Port.setText("Port:");

        BT_Connect.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BT_Connect.setText("Kết nối");
        BT_Connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ConnectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LB_IP)
                            .addComponent(LB_Port))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TF_IP, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(TF_Port)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(BT_Connect, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LB_IP)
                            .addComponent(TF_IP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TF_Port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LB_Port))
                        .addGap(18, 18, 18)
                        .addComponent(BT_Connect)
                        .addGap(0, 205, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BT_ConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_ConnectActionPerformed
        if(BT_Connect.getText().equals("Kết nối")){
            if(!socketClient.connect(TF_IP.getText(), TF_Port.getText())){
                JOptionPane.showMessageDialog(this, "Kết nối thất bại.", "Thông báo", JOptionPane.ERROR_MESSAGE);
                //message.setText("kết nối thất bại.");
                return;
            }
            BT_Connect.setText("Ngắt kết nối");
            TF_IP.setEnabled(false);
            TF_Port.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Kết nối thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            watchFile = new WatchFile(path, socketClient, tbHistory);
        }else{
            BT_Connect.setText("Kết nối");
            TF_Port.setEnabled(true);
            TF_IP.setEnabled(true);
            socketClient.sendMessage("quit");
            watchFile.terminate();
        }
    }//GEN-LAST:event_BT_ConnectActionPerformed


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
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\NetWorking\\Data1").setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BT_Connect;
    private javax.swing.JLabel LB_IP;
    private javax.swing.JLabel LB_Port;
    private javax.swing.JTextField TF_IP;
    private javax.swing.JTextField TF_Port;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
