package GUI;

import Controller.RoomController;
import Model.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRooms extends JFrame implements ActionListener {
    Logger log = LogManager.getLogger(AddRooms.class);
    private JLabel addRoom;
    private JLabel roomType;
    private JLabel roomPrice;
    private JLabel imgUrl;
    private JLabel roomReservation, roomNumber;
    private JTextField roomNumberText, roomTypeText, roomPriceText, imgUrlText;
    private JButton addButton;
    private JComboBox roomReservationText;

    public AddRooms() {
        setTitle("Add rooms");
        setSize(300 , 300);
        setLocationRelativeTo(null);
        initUI(); // Initialize UI components
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5 , 5 , 5 , 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        addRoom = new JLabel("Add Room Form");
        addRoom.setHorizontalAlignment(SwingConstants.CENTER);
        add(addRoom , gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        roomNumber = new JLabel("Room Number: ");
        add(roomNumber , gbc);
        gbc.gridx = 1;
        roomNumberText = new JTextField(10); // Create text field for room number
        add(roomNumberText , gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        roomType = new JLabel("Type of the room : ");
        add(roomType , gbc);
        gbc.gridx = 1;
        roomTypeText = new JTextField(10); // Create text field for room type
        add(roomTypeText , gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        roomPrice = new JLabel("Price of the room : ");
        add(roomPrice , gbc);
        gbc.gridx = 1;
        roomPriceText = new JTextField(10); // Create text field for room price
        add(roomPriceText , gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        imgUrl = new JLabel("Image URL : ");
        add(imgUrl , gbc);
        gbc.gridx = 1;
        imgUrlText = new JTextField(10); // Create text field for image URL
        add(imgUrlText , gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        roomReservation = new JLabel("Room reservation : ");
        add(roomReservation , gbc);
        gbc.gridx = 1;
        roomReservationText = new JComboBox();
        roomReservationText.addItem("true");
        roomReservationText.addItem("false");
        add(roomReservationText , gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        addButton = new JButton("Add Room");
        addButton.addActionListener(this);
        add(addButton , gbc);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            try {
                RoomController roomController = new RoomController();
                Room room = new Room(Integer.parseInt(roomNumberText.getText()) ,
                        roomTypeText.getText() ,
                        Double.parseDouble(roomPriceText.getText()) ,
                        imgUrlText.getText() ,
                        Boolean.parseBoolean((String) roomReservationText.getSelectedItem()));
                boolean isAdded = roomController.addRoom(room);
                if (isAdded) {
                    JOptionPane.showMessageDialog(this , "The room has successfully been added!");
                } else {
                    JOptionPane.showMessageDialog(this , "Failed to add the room. Please check the details and try again." , "Error" , JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException numberFormatException) {
                log.error("Invalid input for price, please enter a valid number" + numberFormatException);
                JOptionPane.showMessageDialog(this , "Invalid input for price. Please enter a valid number" , "Input Error" , JOptionPane.ERROR_MESSAGE);

            } catch (Exception exception) {
                log.error("An error occurred. Please try again");
                JOptionPane.showMessageDialog(this , "An error occurred. Please try again" , "Error" , JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
