package GUI;

import Controller.RoomController;
import Model.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UpdateRooms extends JFrame implements ActionListener {
    public static final Logger log = LogManager.getLogger(UpdateRooms.class);
    private JLabel addRoom;
    private JLabel roomType;
    private JLabel roomPrice;
    private JLabel imgUrl;
    private JLabel roomReservation;
    private JLabel roomComboBox;
    private JTextField roomTypeText, roomPriceText, imgUrlText;
    private JButton updateRoom;
    private JComboBox rooms, roomReservationText;

    public UpdateRooms() {
        setTitle("Update rooms");
        setSize(300 , 350);
        setLocationRelativeTo(null);
        initUI();
        populateRoomsComboBox();
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
        addRoom = new JLabel("Update Room Form");
        addRoom.setHorizontalAlignment(SwingConstants.CENTER);
        add(addRoom , gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        roomComboBox = new JLabel("Room number : ");
        add(roomComboBox , gbc);
        gbc.gridx = 1;
        rooms = new JComboBox<>();
        add(rooms , gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        //gbc.gridwidth = 1;
        roomType = new JLabel("Type of the room : ");
        add(roomType , gbc);
        gbc.gridx = 1;
        roomTypeText = new JTextField(10);
        add(roomTypeText , gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        roomPrice = new JLabel("Price of the room : ");
        add(roomPrice , gbc);
        gbc.gridx = 1;
        roomPriceText = new JTextField(10);
        add(roomPriceText , gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        imgUrl = new JLabel("Image URL : ");
        add(imgUrl , gbc);
        gbc.gridx = 1;
        imgUrlText = new JTextField(10);
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
        updateRoom = new JButton("Update Room");
        updateRoom.addActionListener(this);
        add(updateRoom , gbc);
    }

    private void populateRoomsComboBox() {
        try {
            RoomController roomController = new RoomController();
            List<Room> roomList = roomController.getAllRooms();
            for (Room room : roomList) {
                rooms.addItem(room.getRoomNumber());
            }
        } catch (Exception e) {
            log.error("Error populating rooms combo box: " , e);
        }
        rooms.addActionListener(e -> {
            JComboBox comboBox = (JComboBox) e.getSource();
            int selectedRoomNumber = (int) comboBox.getSelectedItem();
            fillRoomDetails(selectedRoomNumber);
        });
    }

    private void fillRoomDetails(int roomNumber) {
        try {
            RoomController roomController = new RoomController();
            Room room = roomController.getRoomByNumber(roomNumber);
            if (room != null) {
                roomTypeText.setText(room.getType());
                roomPriceText.setText(String.valueOf(room.getPrice()));
                imgUrlText.setText(room.getImageUrl());
                roomReservationText.setSelectedItem(String.valueOf(room.isRoomReserved()));
            }
        } catch (Exception e) {
            log.error("Error filling room details: " , e);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateRoom) {
            JOptionPane.showMessageDialog(this , "The room was successfully updated");
        }
    }
}
