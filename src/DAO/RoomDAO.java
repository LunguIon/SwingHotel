package DAO;

import Model.Room;
import jakarta.persistence.EntityManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO implements RoomDaoInterface<Room> {

    public static Logger log = LogManager.getLogger(RoomDAO.class);
    private final EntityManager entityManager;

    public RoomDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Room save(Room entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            log.error("Error saving the room: " , e);
            return null;
        }
    }


    @Override
    public void update(Room entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            log.error("Error updating the room");
        }
    }

    @Override
    public void delete(Room entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            log.error("Error deleting the room");
        }
    }

    @Override
    public ArrayList<Room> findAll() {
        try {
            entityManager.getTransaction().begin();
            List<Room> rooms = entityManager.createQuery("FROM Room" , Room.class).getResultList();
            entityManager.getTransaction().commit();
            return new ArrayList<>(rooms);
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            log.error("Error finding all rooms ! " + e);
            return null;
        }
    }

    @Override
    public Room findByRoomNumber(int roomNumber) {
        try {
            entityManager.getTransaction().begin();
            Room room = entityManager.createQuery("SELECT r FROM Room r WHERE r.roomNumber = :roomNumber" , Room.class)
                    .setParameter("roomNumber" , roomNumber)
                    .getSingleResult();
            entityManager.getTransaction().commit();
            return room;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            log.error("Error finding room with roomNumber: " + roomNumber , e);
            return null;
        }
    }


    public boolean deleteByRoomNumber(int roomNumber) {
        boolean isDeleted = false;
        try {
            entityManager.getTransaction().begin();

            Room room = entityManager.createQuery("SELECT r FROM Room r WHERE r.roomNumber = :roomNumber" , Room.class)
                    .setParameter("roomNumber" , roomNumber)
                    .getSingleResult();
            if (room != null) {
                entityManager.remove(room);
                entityManager.getTransaction().commit();
                isDeleted = true;
            }
        } catch (NullPointerException ex){
            log.error("The transaction returned null pointer" + ex);
            if(entityManager.getTransaction() != null && entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
        }
        catch (Exception e) {
            if (entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            log.error("Error deleting the room with room number: " + roomNumber , e);
        }
        return isDeleted;
    }


}
