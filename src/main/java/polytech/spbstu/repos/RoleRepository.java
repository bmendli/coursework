package polytech.spbstu.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import polytech.spbstu.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);
}
