package DAO;

import java.util.ArrayList;

public interface RoomDaoInterface<T> {
    T save(T entity);

    void update(T entity);

    void delete(T entity);

    ArrayList<T> findAll();

    T findByRoomNumber(int roomNumber);
}
