package GUI;

import Model.Room;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class RoomTableModel extends AbstractTableModel {
    private final List<Room> rooms;
    private final String[] columnNames = {"Room Number", "Type", "Price", "Image URL", "Reserved"};

    public RoomTableModel(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public int getRowCount() {
        return rooms.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Room room = rooms.get(rowIndex);
        switch (columnIndex) {
            case 0: return room.getRoomNumber();
            case 1: return room.getType();
            case 2: return room.getPrice();
            case 3: return room.getImageUrl();
            case 4: return room.isRoomReserved();
            default: return null;
        }
    }
}
