package com.model.repository;


import com.model.entities.Role;
import com.model.entities.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepository implements Repository<User> {

    private final Map<Long, User> userMap = new HashMap<>();

    public static final AtomicLong id = new AtomicLong(System.currentTimeMillis());

    public UserRepository() {
        userMap.put(1L, new User(1L,"Alisa","qwerty", Role.USER));
        userMap.put(2L, new User(2L,"Bob","", Role.GUEST));
        userMap.put(3L, new User(3L,"Carl","admin", Role.ADMIN));
        userMap.put(4L, new User(4L,"Smith","admin", Role.ADMIN));
    }

    @Override
    public Collection<User> getAll() {
        return userMap.values();
    }

    @Override
    public Optional<User> get(Long id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public void create(User entity) {
        entity.setId(id.incrementAndGet());
        update(entity);
    }

    @Override
    public void update(User entity) {
        userMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(User entity) {
        userMap.remove(entity.getId());
    }

    public Optional<User> getUserByUsername(String username) {
        return userMap.values().stream()
            .filter(user -> user.getLogin().equals(username))
            .findFirst();
    }
}
