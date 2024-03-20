package DAO;

import Model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDAO implements Dao<User> {
    public static Logger log = LogManager.getLogger(UserDAO.class);

    private final EntityManager entityManager;

    public UserDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean check(String username , String password) {
        try {
            String jpql = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password";
            TypedQuery<User> query = entityManager.createQuery(jpql , User.class);
            query.setParameter("username" , username);
            query.setParameter("password" , password);
            User user = query.getSingleResult();

            return user != null;
        } catch (NoResultException e) {
            log.error("No user found with the provided credentials" , e);
            return false;
        } catch (Exception e) {
            log.error("You can't connect to the database" , e);
            return false;
        }
    }
}
