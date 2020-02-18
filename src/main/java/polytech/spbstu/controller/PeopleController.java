package polytech.spbstu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import polytech.spbstu.entity.DiagnosisEntity;
import polytech.spbstu.entity.PeopleEntity;
import polytech.spbstu.entity.WardsEntity;
import polytech.spbstu.repos.DiagnosisRepository;
import polytech.spbstu.repos.PeopleRepository;
import polytech.spbstu.repos.WardRepository;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"polytech.spbstu.repos", "polytech.spbstu.entity"})

@RestController
@RequestMapping("/polyclinic/spbstu/people")
class PeopleController {

    @Autowired
    private PeopleRepository peopleRepository;
    @Autowired
    private WardRepository wardRepository;
    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @GetMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PeopleEntity> addPeople() {
        WardsEntity wardsEntity = new WardsEntity();
        wardsEntity.setName("defrdgf");
        wardsEntity.setMaxCount(12345);
        DiagnosisEntity diagnosisEntity = new DiagnosisEntity();
        diagnosisEntity.setName("wderfdgtf");

        PeopleEntity peopleEntity = new PeopleEntity();
        peopleEntity.setDiagnosisByDiagnosisId(diagnosisEntity);
        peopleEntity.setWardsByWardId(wardsEntity);
        peopleEntity.setFirstName("edfedef");
        peopleEntity.setLastName("wfwdwdw");
        peopleEntity.setFatherName("wdwdwdwd");
        diagnosisRepository.save(diagnosisEntity);
        wardRepository.save(wardsEntity);
        peopleRepository.save(peopleEntity);
        return peopleRepository.findAll();
    }
}
