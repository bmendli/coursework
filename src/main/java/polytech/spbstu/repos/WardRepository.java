package polytech.spbstu.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polytech.spbstu.entity.WardsEntity;

@Repository
public interface WardRepository extends JpaRepository<WardsEntity, Integer> {

    WardsEntity findWardsEntitiesByName(String name);
}
