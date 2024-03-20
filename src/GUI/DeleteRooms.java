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

public class DeleteRooms extends JFrame implements ActionListener {
    private static final Logger log = LogManager.getLogger(DeleteRooms.class);
    private JLabel centralLabel, idLabel;
    private JButton deleteRoom;
    private JComboBox idText;

    public DeleteRooms() {
        setTitle("Delete Rooms");
        setSize(300 , 200);
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
        centralLabel = new JLabel("Delete a room");
        centralLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(centralLabel , gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        idLabel = new JLabel("Room Number :");
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(idLabel , gbc);
        gbc.gridx = 1;
        idText = new JComboBox();
        add(idText , gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        deleteRoom = new JButton("Delete Room");
        deleteRoom.addActionListener(this);
        add(deleteRoom , gbc);


    }

    private void populateRoomsComboBox() {
        ActionListener cbActionListener = e -> {
            JComboBox comboBox = (JComboBox) e.getSource();
            Integer selectedRoomNumber = (Integer) comboBox.getSelectedItem();
            if (selectedRoomNumber != null) {
                //TODO Here I should have something if the selectedRoomNumber != null
            }
        };
        idText.removeActionListener(cbActionListener);

        idText.removeAllItems();
        try {
            RoomController roomController = new RoomController();
            List<Room> roomList = roomController.getAllRooms();
            for (Room room : roomList) {
                idText.addItem(room.getRoomNumber());
            }
        } catch (Exception e) {
            log.error("Error populating rooms combo box: ", e);
        }

        idText.addActionListener(cbActionListener);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteRoom) {
            Object selectedRoomNumberObject = idText.getSelectedItem();
            if (selectedRoomNumberObject != null) {
                int selectedRoomNumber = (int) selectedRoomNumberObject;
                RoomController roomController = new RoomController();
                boolean isDeleted = roomController.deleteRoomByNumber(selectedRoomNumber);
                if (isDeleted) {
                    JOptionPane.showMessageDialog(this, "The room was successfully removed");
                    populateRoomsComboBox();
                } else {
                    JOptionPane.showMessageDialog(this, "There was a problem deleting the room");
                }
            }
        }
    }


}
