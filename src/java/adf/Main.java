package adf;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.util.logging.Logger;
import java.util.logging.Level;

import adf.service.AdfService;

/**
 * User: Alan P. Sexton
 * Date: 16-Mar-2008
 * Time: 19:59:24
 */
public class Main
{
    private static final Logger LOG = Logger.getLogger(Main.class.getCanonicalName());

    public static void main(String[] args)
    {

        LOG.fine("Started");

                JOptionPane pane = new JOptionPane("Create new database? Selecting 'No' will delete database",
                                           JOptionPane.QUESTION_MESSAGE,
                                           JOptionPane.YES_NO_OPTION,
                                           null);
        JDialog d = pane.createDialog(null, "Database Creation");
        d.setVisible(true);

        Integer returnValue = (Integer) pane.getValue();
        d.dispose();

        if (returnValue == null)
        {
            System.err.println("Program was cancelled. Will exit");
            System.exit(1);
        }

        String command = "create";

        if (returnValue != JOptionPane.YES_OPTION) {
            command = "drop";
        }

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AdfService adfService = (AdfService) context.getBean("adfService");

        try
        {
            if ("create".equals(command))
                adfService.createDB();
            else if ("drop".equals(command))
                adfService.dropDB();
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
            LOG.log(Level.INFO, "Error", e);
            System.exit(1);
        }
        System.exit(0);
    }

    /**
     * Shows a dialog box for the user to enter a password . Note that using this
     * method means that the application must be terminated by calling <code>System.exit(0)</code>
     * to ensure that all the Swing threads created by this method are terminated and the JVM
     * can exit.
     * <p/>
     * Exit the program if the user clicked on the <code>CANCEL</code> button or on
     * the window close button.
     *
     * @return The <code>String</code> value of the password entered.
     */
    public static String getPassword()
    {
        JPasswordField password = new JPasswordField(20);

        final Object[] fields = {"Enter your database password:", password};
        JOptionPane pane = new JOptionPane(fields,
                                           JOptionPane.QUESTION_MESSAGE,
                                           JOptionPane.OK_CANCEL_OPTION,
                                           null);
        JDialog d = pane.createDialog(null, "Database Access");
        d.setVisible(true);

        Integer returnValue = (Integer) pane.getValue();
        d.dispose();

        if (returnValue == null || returnValue != JOptionPane.OK_OPTION)
        {
            System.err.println("Password entry cancelled: program will exit");
            System.exit(1);
        }

        char[] value = password.getPassword();
        return new String(value);
    }

}