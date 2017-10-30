package xmppclient.jingle;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.XMPPConnection;

/**
 * Sends Jingle file transfers
 * Used for testing Jingle file transfer 
 * @author Lee Boynton (323326)
 */
public class TestSender
{
    /**
     * Logs into the server, sends a session request to a user
     */
    public TestSender()
    {
        try
        {
            XMPPConnection.DEBUG_ENABLED = true;
            XMPPConnection connection = new XMPPConnection("praveen");
            connection.connect();
            connection.login("test_user1", "test_user1", "home");
            JingleManager jingleManager = new JingleManager(connection);
            jingleManager.createOutgoingSession("test_user2@praveen/home", "file.mp3");
        }
        catch (Exception ex)
        {
            Logger.getLogger(TestSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates a new test sender
     * @param args not used
     */
    public static void main(String args[])
    {
        new TestSender();
    }
}
