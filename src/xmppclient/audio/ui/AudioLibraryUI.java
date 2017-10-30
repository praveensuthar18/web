/*
 * AudioLibraryUI.java
 *
 * Created on 25 May 2008, 18:13
 */
package xmppclient.audio.ui;

import xmppclient.audio.*;
import xmppclient.audio.ui.AudioLibraryPanel;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import org.jivesoftware.smack.RosterEntry;
import xmppclient.jingle.JingleManager;

/**
 * This class is used to contain the audio library panel
 * @see AudioLibraryPanel
 * @author  Lee Boynton (323326)
 */
public class AudioLibraryUI extends javax.swing.JFrame
{  
    /** 
     * Initialises the JFrame, adds the audio library panel to itself
     * @param parent The parent JFrame
     * @param audioManager The audio manager
     * @param entry The remote user
     * @param jingleManager The Jingle manager
     */
    public AudioLibraryUI(JFrame parent, AudioManager audioManager, RosterEntry entry, JingleManager jingleManager)
    {
        AudioLibraryPanel panel = new AudioLibraryPanel(audioManager, entry, jingleManager);
        initComponents();
        add(panel, BorderLayout.CENTER);
        pack();
        panel.refresh();
        setLocationRelativeTo(parent);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Audio Library");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
