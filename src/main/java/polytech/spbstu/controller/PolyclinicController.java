package polytech.spbstu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import polytech.spbstu.repos.DiagnosisRepository;
import polytech.spbstu.repos.PeopleRepository;
import polytech.spbstu.repos.WardRepository;

@SpringBootApplication(scanBasePackages = {"polytech.spbstu.repos", "polytech.spbstu.entity"})

@RestController
@RequestMapping("/polyclinic/spbstu")
class PolyclinicController {

    @Autowired
    private PeopleRepository peopleRepository;
    @Autowired
    private WardRepository wardRepository;
    @Autowired
    private DiagnosisRepository diagnosisRepository;

}
