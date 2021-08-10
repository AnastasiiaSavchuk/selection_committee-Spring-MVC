package selection_committee.repository.impl;

import org.springframework.stereotype.Component;
import selection_committee.model.User;
import selection_committee.model.enums.Role;
import selection_committee.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final List<User> list = new ArrayList<>();

    @Override
    public List<User> getAll() {
        return list;
    }

    @Override
    public User getByEmail(String email) {
        return list.stream()
                .filter(u -> u.getEmail().equals(email)).findFirst()
                .orElseThrow(() -> new RuntimeException("User is not found!"));
    }

    @Override
    public User getById(int id) {
        return list.stream()
                .filter(u -> u.getId() == id).findFirst()
                .orElseThrow(() -> new RuntimeException("User is not found!"));
    }

    @Override
    public User create(User user) {
        user.setRole(Role.USER);
        user.setBlocked(false);
        list.add(user);
        return user;
    }

    @Override
    public User update(int id, User user) {
        boolean isDeleted = list.removeIf(u -> u.getId() == id);
        if (isDeleted) {
            user.setRole(Role.USER);
            user.setBlocked(false);
            list.add(user);
        } else {
            throw new RuntimeException("User is not found!");
        }
        return user;
    }

    @Override
    public User updateByAdmin(int id, User user) {
        boolean isDeleted = list.removeIf(u -> u.getId() == id);
        if (isDeleted) {
            user.setRole(Role.USER);
            user.setBlocked(true);
            list.add(user);
        } else {
            throw new RuntimeException("User is not found!");
        }
        return user;
    }

    @Override
    public void delete(int id) {
        list.removeIf(u -> u.getId() == id);
    }
}
