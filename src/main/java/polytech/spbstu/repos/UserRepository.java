package polytech.spbstu.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import polytech.spbstu.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}
