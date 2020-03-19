package polytech.spbstu.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polytech.spbstu.entity.PeopleEntity;
import polytech.spbstu.entity.WardsEntity;

@Repository
public interface PeopleRepository extends JpaRepository<PeopleEntity, Integer> {

    List<PeopleEntity> findPeopleEntitiesByWardsByWardId(WardsEntity ward);
}
