package polytech.spbstu.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polytech.spbstu.entity.PeopleEntity;

@Repository
public interface PeopleRepository extends JpaRepository<PeopleEntity, Integer> {
}
