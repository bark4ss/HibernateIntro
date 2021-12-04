package com.teachmeskills.hibernateintro.service.impl;

import com.teachmeskills.hibernateintro.model.User;
import com.teachmeskills.hibernateintro.repository.BaseRepository;
import com.teachmeskills.hibernateintro.repository.impl.UserRepositoryImpl;
import com.teachmeskills.hibernateintro.service.BaseService;

import java.util.List;

public class UserServiceImpl implements BaseService<User> {

    private final BaseRepository<User> repository = new UserRepositoryImpl();

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(long id) {
        return repository.findById(id);
    }

    @Override
    public boolean create(User entity) {
        return repository.create(entity);
    }

    @Override
    public User update(User entity) {
        return repository.update(entity);
    }

    @Override
    public boolean deleteById(long id) {
        return repository.deleteById(id);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        return repository.findByLoginAndPassword(login, password);
    }
}
