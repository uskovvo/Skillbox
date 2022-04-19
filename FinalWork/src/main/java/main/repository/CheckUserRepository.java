package main.repository;

import main.model.users.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckUserRepository extends CrudRepository<Users, Integer> {
}
