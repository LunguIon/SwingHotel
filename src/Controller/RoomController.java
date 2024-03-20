package Controller;

import DAO.RoomDAO;
import Model.Room;
import Utils.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

//TODO Change implementation for delete and update rooms, so for update
//TODO we should have a combobox for update and a combobox for delete
public class RoomController {
    private static final Logger log = LogManager.getLogger(RoomController.class);
    private RoomDAO roomDAO;

    public boolean addRoom(Room room) {
        boolean isAdded = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            roomDAO = new RoomDAO(session);
            Room savedRoom = roomDAO.save(room);
            isAdded = savedRoom != null;
        } catch (Exception e) {
            log.error("Error while adding a room: " , e);
        }
        return isAdded;
    }

    public boolean deleteRoom(Room room) {
        Transaction transaction = null;
        boolean isDeleted = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            roomDAO = new RoomDAO(session);
            roomDAO.delete(room);
            transaction.commit();
            isDeleted = true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            log.error("Error deleting the room: " , e);
            isDeleted = false;
        }
        return isDeleted;
    }

    public List<Room> getAllRooms() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            roomDAO = new RoomDAO(session);
            return roomDAO.findAll();
        } catch (Exception e) {
            log.error("Error retrieving all rooms: " , e);
            return Collections.emptyList();
        }
    }

    public Room getRoomByNumber(int roomNumber) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            roomDAO = new RoomDAO(session);
            return roomDAO.findByRoomNumber(roomNumber);
        } catch (Exception e) {
            log.error("Error retrieving room by number: " , e);
            return null;
        }
    }

    public boolean deleteRoomByNumber(int roomNumber) {
        boolean isDeleted = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            roomDAO = new RoomDAO(session);
            isDeleted = roomDAO.deleteByRoomNumber(roomNumber);
        } catch (Exception e) {
            log.error("Error while deleting room by number: " , e);
        }
        return isDeleted;
    }


}
