package Controller;

import DAO.UserDAO;
import Utils.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AuthenticationController {
    private static final Logger log = LogManager.getLogger(AuthenticationController.class);
    private UserDAO userDAO;
    private Session session;

    public AuthenticationController() {
        this.session = HibernateUtil.getSessionFactory().openSession();
        this.userDAO = new UserDAO(session);
    }

    public boolean authenticate(String username, String password) {
        boolean result = userDAO.check(username, password);
        return result;
    }

    public void close() {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}
