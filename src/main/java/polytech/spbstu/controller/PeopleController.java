package polytech.spbstu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import polytech.spbstu.dto.ResultDto;
import polytech.spbstu.entity.DiagnosisEntity;
import polytech.spbstu.entity.PeopleEntity;
import polytech.spbstu.entity.WardsEntity;
import polytech.spbstu.repos.DiagnosisRepository;
import polytech.spbstu.repos.PeopleRepository;
import polytech.spbstu.repos.WardRepository;
import polytech.spbstu.utils.ValidationUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootApplication(scanBasePackages = {"polytech.spbstu.repos", "polytech.spbstu.entity"})
@RestController
@RequestMapping("/polyclinic/spbstu/users/people")
class PeopleController {

    private static final String PEOPLE_ADDED = "People successfully added";
    private static final String PEOPLE_NOT_ADDED = "People cannot add";
    private static final String PEOPLE_DELETED = "People deleted";
    private static final String PEOPLE_NOT_DELETED = "People cannot delete";
    private static final String PEOPLE_UPDATED = "People updated";
    private static final String PEOPLE_NOT_UPDATED_EMPTY = "People cannot update because he has incorrect params";
    private static final String PEOPLE_NOT_UPDATED_NOT_EXIST = "People cannot update because he doesn't exist";

    private final PeopleRepository peopleRepository;
    private final WardRepository wardRepository;
    private final DiagnosisRepository diagnosisRepository;

    @Autowired
    public PeopleController(PeopleRepository peopleRepository,
                            WardRepository wardRepository,
                            DiagnosisRepository diagnosisRepository) {
        this.peopleRepository = peopleRepository;
        this.wardRepository = wardRepository;
        this.diagnosisRepository = diagnosisRepository;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PeopleEntity> getAllPeople() {
        return peopleRepository.findAll();
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultDto> addPeople(@RequestBody final PeopleEntity peopleEntity) {
        HttpStatus status = HttpStatus.OK;
        ResultDto resultDto = new ResultDto();
        final String diagnosisName = peopleEntity.getDiagnosisByDiagnosisId().getName();
        final String wardName = peopleEntity.getWardsByWardId().getName();
        DiagnosisEntity diagnosisEntity = diagnosisRepository.findDiagnosisEntityByName(diagnosisName);
        WardsEntity wardsEntity = wardRepository.findWardsEntitiesByName(wardName);
        
        if (diagnosisEntity == null) {
            final DiagnosisEntity newDiagnosis = new DiagnosisEntity();
            newDiagnosis.setName(diagnosisName);
            diagnosisEntity = diagnosisRepository.save(newDiagnosis);
        }

        if (wardsEntity == null) {
            final WardsEntity newWard = new WardsEntity();
            newWard.setName(wardName);
            newWard.setMaxCount(5);
            wardsEntity = wardRepository.save(newWard);
        }

        if (ValidationUtils.haveSpot(wardsEntity, peopleRepository.findPeopleEntitiesByWardsByWardId(wardsEntity))) {
            peopleEntity.setWardsByWardId(wardsEntity);
            peopleEntity.setDiagnosisByDiagnosisId(diagnosisEntity);
            peopleRepository.save(peopleEntity);
            resultDto.setResult(true);
        } else {
            resultDto.setResult(false);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(resultDto, status);
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PeopleEntity> getPeople(@RequestParam(value = "id", required = false) final Integer id) {
        if (id == null || id <= 0) {
            return peopleRepository.findAll();
        }
        return Collections.singletonList(peopleRepository.findById(id).orElse(null));
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultDto> updatePeople(@RequestBody final PeopleEntity peopleEntity) {
        return addPeople(peopleEntity);
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
