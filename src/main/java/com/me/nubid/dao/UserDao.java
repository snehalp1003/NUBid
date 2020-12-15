/**
 * 
 */
package com.me.nubid.dao;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.me.nubid.model.User;

/**
 * @author Snehal Patel
 */
public class UserDao extends Dao {
    
    private static final Logger log = LoggerFactory.getLogger(UserDao.class);
    
    public User addNewUser(User u) {
        try {
            begin();
            User user = new User();
            user.setUserUuid(u.getUserUuid());
            user.setUserEmailAddress(u.getUserEmailAddress());
            user.setUserFirstName(u.getUserFirstName());
            user.setUserLastName(u.getUserLastName());
            user.setUserPassword(u.getUserPassword());
            user.setUserAddress(u.getUserAddress());
            user.setUserPhoneNum(u.getUserPhoneNum());
            user.setUserCollege(u.getUserCollege());
            user.setUserDept(u.getUserDept());       
            getSession().save("User", u);
            commit();
            close();
            return user;
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            log.error("Error while inserting new user into the database");
        }
        return null;
    }
    
    public boolean update(String uuid, User user) {
        try {
            begin();
            
            User u = (User) getSession().load(User.class, uuid);
            if (null != u) {
                getSession().update(uuid, user);
                commit();
                close();
                return true;
            } else {
                rollback();
                log.error("User does not exist in database so cannot update !");
                return false;
            }

        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            log.error("Error while updating user into the database");
        }
        return false;
    }
    
    public User getUser(String userEmail) {
        try {
            Query query;
            begin();
            query = getSession().createQuery("from User where userEmailAddress=:id");
            query.setParameter("id", userEmail);
            User user = (User) query.uniqueResult();

            if (null != user) {
                commit();
                close();
                return user;
            } else {
                rollback();
                log.error("User does not exist");
            }
        } catch (Exception e) {
            rollback();
            log.error("Error while finding the user using email and password");
        }
        return null;
    }
}
