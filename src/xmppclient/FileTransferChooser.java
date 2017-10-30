/*
 * FileTransferChooser.java
 *
 * Created on 30 April 2008, 20:41
 */

package xmppclient;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.filetransfer.FileTransferManager;
import org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer;

/**
 * A dialog containing a file chooser for choosing a file to transfer to another
 * XMPP user. This class does validation before sending the file to the user.
 * @author  Lee Boynton (323326)
 */
public class FileTransferChooser extends javax.swing.JDialog 
{
    private RosterEntry entry;
    
    /** 
     * Creates new form FileTransferChooser
     * @param parent The JFrame parent of this dialog
     * @param entry The user who should receive the file
     * @param modal If this dialog should be modal
     */
    public FileTransferChooser(java.awt.Frame parent, boolean modal, RosterEntry entry) 
    {
        super(parent, modal);
        this.entry = entry;
        initComponents();
    }
    
    /**
     * Displays the file chooser
     * @return The return value from the file chooser
     */
    public int showFileChooser()
    {
        return fileChooser.showDialog(this, "Send file");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Select file");

        fileChooser.setAccessory(new FileTransferChooserAccessory());
        fileChooser.setApproveButtonText("Send to " + Utils.getNickname(entry));
        fileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserActionPerformed(evt);
            }
        });
        fileChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fileChooserPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void fileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChooserActionPerformed

    if(evt.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) 
    {
        dispose();
        return;
    }
    
    FileTransferManager manager = new FileTransferManager(MainUI.connection);
    OutgoingFileTransfer transfer = null;
    
    try
    {
        transfer = manager.createOutgoingFileTransfer(
                MainUI.connection.getRoster().getPresence(entry.getUser()).getFrom());
    }
    catch(IllegalArgumentException e)
    {
        JOptionPane.showMessageDialog(this, 
                "Could not send file to this user\n" +
                "the user ID was not fully qualified.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        return;
    }

    try
    {
        transfer.sendFile(fileChooser.getSelectedFile(), ((FileTransferChooserAccessory)fileChooser.getAccessory()).getFileDescription());
        new FileTransferUI(transfer);
    }
    catch (InterruptedException ex)
    {
        ex.printStackTrace();
    }        
    catch (XMPPException ex)
    {
        ex.printStackTrace();
    }
    dispose();
}//GEN-LAST:event_fileChooserActionPerformed

private void fileChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fileChooserPropertyChange
    if(evt.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY))
    {
        try
        {
            ((FileTransferChooserAccessory)fileChooser.getAccessory()).setFilename(fileChooser.getSelectedFile().getName());  
        }
        catch(Exception e) {}
    }
}//GEN-LAST:event_fileChooserPropertyChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fileChooser;
    // End of variables declaration//GEN-END:variables

}
