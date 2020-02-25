package polytech.spbstu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import polytech.spbstu.repos.DiagnosisRepository;
import polytech.spbstu.repos.PeopleRepository;
import polytech.spbstu.repos.WardRepository;

@SpringBootApplication(scanBasePackages = {"polytech.spbstu.repos", "polytech.spbstu.entity"})

@RestController
class PolyclinicController {

    private final PeopleRepository peopleRepository;
    private final WardRepository wardRepository;
    private final DiagnosisRepository diagnosisRepository;

    @Autowired
    public PolyclinicController(PeopleRepository peopleRepository, WardRepository wardRepository, DiagnosisRepository diagnosisRepository) {
        this.peopleRepository = peopleRepository;
        this.wardRepository = wardRepository;
        this.diagnosisRepository = diagnosisRepository;
    }

}
