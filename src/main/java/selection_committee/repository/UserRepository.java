package selection_committee.repository;

import selection_committee.model.User;
import selection_committee.util.CRUDRepository;

public interface UserRepository extends CRUDRepository<User> {

    User getByEmail(String email);

    User updateByAdmin(int id, User user);
}
