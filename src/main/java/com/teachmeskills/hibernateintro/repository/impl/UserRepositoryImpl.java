package com.teachmeskills.hibernateintro.repository.impl;

import com.teachmeskills.hibernateintro.config.ConfigSessionFactory;
import com.teachmeskills.hibernateintro.model.User;
import com.teachmeskills.hibernateintro.repository.BaseRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserRepositoryImpl implements BaseRepository<User> {
    @Override
    public List<User> findAll() {
        Session session = ConfigSessionFactory.getSessionFactory().openSession();

        return (List<User>) session.createQuery("from User ").getResultList();
    }

    @Override
    public User findById(long id) {
        Session session = ConfigSessionFactory.getSessionFactory().openSession();
        return session.get(User.class, id);
    }

    @Override
    public boolean create(User entity) {
        Transaction transaction = null;
        try (Session session = ConfigSessionFactory.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return true;
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return false;
    }

    @Override
    public User update(User entity) {
        Transaction transaction = null;
        try (Session session = ConfigSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return session.get(User.class, entity.getId());
        } catch (Exception exc) {
            if (transaction != null) {
                transaction.rollback();
            }

        }
        return entity;
    }

    @Override
    public boolean deleteById(long id) {
        Transaction transaction = null;
        try (Session session = ConfigSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return false;
    }
}
