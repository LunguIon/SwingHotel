package GUI;

import Controller.AuthenticationController;
import Utils.BackgroundPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class LogicFrame extends JFrame implements ActionListener {
    private static final Logger log = LogManager.getLogger(LogicFrame.class);
    private JButton logIn;
    private JLabel username;
    private JLabel password;
    private JLabel wrongPassword;
    private JTextField usernameText;
    private JPasswordField passwordText;
    private BackgroundPanel backgroundPanel;

    public LogicFrame() {
        setTitle("Log In:");
        setSize(333 , 333);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            URL imageURL = getClass().getResource("/resources/pool.jpeg");
            if (imageURL != null) {
                Image backgroundImage = ImageIO.read(imageURL);
                backgroundPanel = new BackgroundPanel(backgroundImage);
                setContentPane(backgroundPanel);
            } else {
                throw new IOException("Resource not found : /resources/hotel.png");
            }
        } catch (IOException e) {
            log.error("Exception : " + e);
        }


        initUI();
    }

    private void initUI() {
        try {
            if (backgroundPanel != null) {
                backgroundPanel.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.insets = new Insets(5 , 5 , 5 , 5);

                // Username label and text field
                gbc.gridx = 0;
                gbc.gridy = 0;
                backgroundPanel.add(username = new JLabel("Username: ") , gbc);
                username.setForeground(Color.white);
                gbc.gridx = 1;
                backgroundPanel.add(usernameText = new JTextField(10) , gbc);

                // Password label and text field
                gbc.gridx = 0;
                gbc.gridy = 1;
                backgroundPanel.add(password = new JLabel("Password: ") , gbc);
                password.setForeground(Color.white);
                gbc.gridx = 1;
                backgroundPanel.add(passwordText = new JPasswordField(10) , gbc);

                // Wrong password label spanning 2 columns
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                backgroundPanel.add(wrongPassword = new JLabel("") , gbc);

                // Log in button
                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                backgroundPanel.add(logIn = new JButton("Log In") , gbc);
                logIn.addActionListener(this);
            } else {
                throw new Exception("Can't initialise the Panel");
            }
        } catch (Exception e) {
            log.error("Exception : " + e);
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Log In")) {
            // Create an instance of the controller.
            // This could also be done once and passed to the view if preferable.
            AuthenticationController authController = new AuthenticationController();

            boolean isAuthenticated = authController.authenticate(usernameText.getText() , passwordText.getText());

            if (isAuthenticated) {
                authController.close();
                SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
                this.dispose(); // Assuming 'this' is a JDialog or JFrame that needs to be closed.
            } else {
                wrongPassword.setOpaque(true);
                wrongPassword.setBackground(Color.white);
                wrongPassword.setForeground(Color.magenta);
                wrongPassword.setText("You entered wrong credentials !!!");
            }
        }
    }


}
