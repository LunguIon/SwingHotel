package GUI;

import Controller.RoomController;
import Model.Room;
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
import java.util.List;

public class MainWindow extends JFrame implements ActionListener {
    private static final Logger log = LogManager.getLogger(MainWindow.class);
    BackgroundPanel backgroundPanel;
    private JTable roomsTable;
    private JButton displayButton;
    private JMenuBar menuBar;
    private JMenu roomMenu;
    private JMenuItem addMenuItem, displayMenuItem, deleteMenuItem, updateMenuItem;
    private JLabel welcome;

    public MainWindow() {
        setTitle("Hotel Booking System");
        setSize(800 , 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            URL imageURL = getClass().getResource("/resources/hotel.jpeg");
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
        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5 , 5 , 5 , 5);
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        roomMenu = new JMenu("Rooms");
        menuBar.add(roomMenu);
        addMenuItem = new JMenuItem("Add Room");
        addMenuItem.addActionListener(this);
        roomMenu.add(addMenuItem);

        displayMenuItem = new JMenuItem("Display Room");
        displayMenuItem.addActionListener(this);
        roomMenu.add(displayMenuItem);

        deleteMenuItem = new JMenuItem("Delete Room");
        deleteMenuItem.addActionListener(this);
        roomMenu.add(deleteMenuItem);

        updateMenuItem = new JMenuItem("Update Room");
        updateMenuItem.addActionListener(this);
        roomMenu.add(updateMenuItem);

        // Add the "Hotel Booking System" label at the center
        welcome = new JLabel("Hotel Booking System");
        welcome.setForeground(Color.white);
        welcome.setFont(new Font("SansSerif" , Font.BOLD , 20));
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5; // Span all columns
        gbc.anchor = GridBagConstraints.CENTER; // Center alignment
        backgroundPanel.add(welcome , gbc);

        RoomController roomController = new RoomController();
        List<Room> rooms = roomController.getAllRooms();
        roomsTable = new JTable(new RoomTableModel(rooms));

        gbc.gridx = 1;
        gbc.gridy = 1; // Start from the next row
        gbc.gridwidth = 4; // Span 4 columns
        gbc.gridheight = 4; // Span 4 rows
        gbc.weightx = 1.0; // Expand horizontally
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontal and vertical directions
        backgroundPanel.add(new JScrollPane(roomsTable), gbc);

        // Reset grid height and other constraints
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addMenuItem) {
            SwingUtilities.invokeLater(() -> {
                new AddRooms().setVisible(true);
            });
        } else if (e.getSource() == displayMenuItem) {
            log.info("Not implemented yet!");
        } else if (e.getSource() == deleteMenuItem) {
            SwingUtilities.invokeLater(() -> {
                new DeleteRooms().setVisible(true);
            });

        } else if (e.getSource() == updateMenuItem) {
            SwingUtilities.invokeLater(() -> {
                new UpdateRooms().setVisible(true);
            });
        }
    }


}
