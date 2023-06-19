package auth.dao;

import auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    public Boolean existsByEmail(String email);
    public Optional<User> findByUsername(String username);

    public User save(User user);
}
