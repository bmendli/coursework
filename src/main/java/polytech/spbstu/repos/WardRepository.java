package polytech.spbstu.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import polytech.spbstu.entity.WardsEntity;

public interface WardRepository extends JpaRepository<WardsEntity, Integer> {
}
