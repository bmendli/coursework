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
import polytech.spbstu.entity.DiagnosisEntity;
import polytech.spbstu.repos.DiagnosisRepository;
import polytech.spbstu.utils.ValidationUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootApplication(scanBasePackages = {"polytech.spbstu.repos", "polytech.spbstu.entity"})

@RestController
@RequestMapping("/polyclinic/spbstu/diagnosis")
public class DiagnosisController {

    private static final String DIAGNOSIS_ADDED = "Diagnosis successfully added";
    private static final String DIAGNOSIS_NOT_ADDED = "Diagnosis cannot add";
    private static final String DIAGNOSIS_DELETED = "Diagnosis deleted";
    private static final String DIAGNOSIS_NOT_DELETED = "Diagnosis cannot delete";
    private static final String DIAGNOSIS_UPDATED = "Diagnosis updated";
    private static final String DIAGNOSIS_NOT_UPDATED_EMPTY = "Diagnosis cannot update because he has incorrect params";
    private static final String DIAGNOSIS_NOT_UPDATED_NOT_EXIST = "Diagnosis cannot update because he doesn't exist";

    private final DiagnosisRepository diagnosisRepository;

    @Autowired
    public DiagnosisController(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addDiagnosis(@RequestBody final DiagnosisEntity diagnosisEntity) {
        if (ValidationUtils.valid(diagnosisEntity)) {
            diagnosisRepository.save(diagnosisEntity);
            return DIAGNOSIS_ADDED;
        }
        return DIAGNOSIS_NOT_ADDED;
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DiagnosisEntity> getDiagnosis(@RequestParam(value = "id", required = false) final Integer id) {
        if (id == null || id <= 0) {
            return diagnosisRepository.findAll();
        }
        return Collections.singletonList(diagnosisRepository.findById(id).orElse(null));
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateDiagnosis(@RequestBody final DiagnosisEntity diagnosisEntity) {
        if (ValidationUtils.valid(diagnosisEntity)) {
            final Optional<DiagnosisEntity> diagnosisOptional = diagnosisRepository.findById(diagnosisEntity.getId());
            if (diagnosisOptional.isPresent()) {
                final DiagnosisEntity diagnosis = diagnosisOptional.get();
                diagnosis.setName(diagnosisEntity.getName());
                diagnosisRepository.flush();
                return DIAGNOSIS_UPDATED;
            }
            return DIAGNOSIS_NOT_UPDATED_EMPTY;
        }
        return DIAGNOSIS_NOT_UPDATED_NOT_EXIST;
    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String removeDiagnosis(@RequestBody final DiagnosisEntity diagnosisEntity) {
        if (ValidationUtils.valid(diagnosisEntity)) {
            diagnosisRepository.delete(diagnosisEntity);
            return DIAGNOSIS_DELETED;
        }
        return DIAGNOSIS_NOT_DELETED;
    }
}
