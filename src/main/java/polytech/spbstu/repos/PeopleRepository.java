package polytech.spbstu.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import polytech.spbstu.entity.PeopleEntity;

public interface PeopleRepository extends JpaRepository<PeopleEntity, Integer> {
}
