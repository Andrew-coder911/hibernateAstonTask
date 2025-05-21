package org.example.userservice.dao;

import org.example.userservice.service.UserDTO;
import org.example.userservice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public final class UserDAO {

    /**
     * Create new user in DB
     * @param userDTO object
     */
    public void createUser(UserDTO userDTO) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(UserMapper.toEntity(userDTO));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    /**
     * return UserDTO object
     * @param id number in table (Primary key)
     * @return UserDTO object
     */
    public UserDTO readUserById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return UserMapper.toDTO(session.get(UserEntity.class, id));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Update information in DB about user
     * @param userDTO object
     */
    public void updateUserData(UserDTO userDTO) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("UPDATE UserEntity m set m.name =:name, m.email =:email, m.age =:age WHERE m.id =:id")
                    .setParameter("id", userDTO.getId())
                    .setParameter("name", userDTO.getName())
                    .setParameter("email", userDTO.getEmail())
                    .setParameter("age", userDTO.getAge())
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    /**
     * Delete user from DB
     * @param id number in table (Primary key)
     */
    public void deleteUser(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("DELETE FROM users WHERE id =:id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    /**
     * return List of UserDTO objects
     * @return List<UserDTO> contains all users in DB
     */
    public List<UserDTO> readAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<UserEntity> userEntities = session
                    .createQuery("FROM UserEntity", UserEntity.class)
                    .getResultList();

            return userEntities.stream()
                    .map(UserMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при чтении пользователей", e);
        }
    }
}
