package polytech.spbstu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import polytech.spbstu.entity.DiagnosisEntity;
import polytech.spbstu.repos.DiagnosisRepository;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"polytech.spbstu.repos", "polytech.spbstu.entity"})

@RestController
@RequestMapping("/polyclinic/spbstu/diagnosis")
public class DiagnosisController {

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @RequestMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DiagnosisEntity> mainD() {
        DiagnosisEntity diagnosisEntity = new DiagnosisEntity();
        diagnosisEntity.setName("privet");
        diagnosisRepository.save(diagnosisEntity);
        return diagnosisRepository.findAll();
    }
}
