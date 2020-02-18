package polytech.spbstu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import polytech.spbstu.entity.PeopleEntity;
import polytech.spbstu.repos.DiagnosisRepository;
import polytech.spbstu.repos.PeopleRepository;
import polytech.spbstu.repos.WardRepository;
import polytech.spbstu.utils.ValidationUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootApplication(scanBasePackages = {"polytech.spbstu.repos", "polytech.spbstu.entity"})

@RestController
@RequestMapping("/polyclinic/spbstu/people")
class PeopleController {

    private static final String PEOPLE_ADDED = "People successfully added";
    private static final String PEOPLE_NOT_ADDED = "People cannot add";
    private static final String PEOPLE_DELETED = "People deleted";
    private static final String PEOPLE_NOT_DELETED = "People cannot delete";
    private static final String PEOPLE_UPDATED = "People updated";
    private static final String PEOPLE_NOT_UPDATED_EMPTY = "People cannot update because he has incorrect params";
    private static final String PEOPLE_NOT_UPDATED_NOT_EXIST = "People cannot update because he doesn't exist";

    @Autowired
    private PeopleRepository peopleRepository;
    @Autowired
    private WardRepository wardRepository;
    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addPeople(@RequestBody final PeopleEntity peopleEntity) {
        if (ValidationUtils.valid(peopleEntity)) {
            peopleRepository.save(peopleEntity);
            return PEOPLE_ADDED;
        }
        return PEOPLE_NOT_ADDED;
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PeopleEntity> getPeople(@RequestParam(value = "id", required = false) final Integer id) {
        if (id == null || id <= 0) {
            return peopleRepository.findAll();
        }
        return Collections.singletonList(peopleRepository.findById(id).orElse(null));
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updatePeople(@RequestBody final PeopleEntity peopleEntity) {
        if (ValidationUtils.valid(peopleEntity)) {
            final Optional<PeopleEntity> peopleOptional = peopleRepository.findById(peopleEntity.getId());
            if (peopleOptional.isPresent()) {
                final PeopleEntity people = peopleOptional.get();
                people.setFirstName(people.getFirstName());
                people.setLastName(people.getLastName());
                people.setFatherName(people.getFatherName());
                people.setWardsByWardId(people.getWardsByWardId());
                people.setDiagnosisByDiagnosisId(people.getDiagnosisByDiagnosisId());
                peopleRepository.flush();
                return PEOPLE_UPDATED;
            }
            return PEOPLE_NOT_UPDATED_NOT_EXIST;
        }
        return PEOPLE_NOT_UPDATED_EMPTY;
    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String removePeople(@RequestBody final PeopleEntity peopleEntity) {
        if (ValidationUtils.valid(peopleEntity)) {
            peopleRepository.delete(peopleEntity);
            return PEOPLE_DELETED;
        }
        return PEOPLE_NOT_DELETED;
    }
}
