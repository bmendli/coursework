package polytech.spbstu.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import polytech.spbstu.entity.DiagnosisEntity;

public interface DiagnosisRepository extends JpaRepository<DiagnosisEntity, Integer> {
}
