package mello.cbrcalc.dao;

import mello.cbrcalc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
