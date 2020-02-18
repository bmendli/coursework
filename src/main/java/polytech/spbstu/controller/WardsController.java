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
import polytech.spbstu.entity.WardsEntity;
import polytech.spbstu.repos.WardRepository;
import polytech.spbstu.utils.ValidationUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootApplication(scanBasePackages = {"polytech.spbstu.repos", "polytech.spbstu.entity"})

@RestController
@RequestMapping("/polyclinic/spbstu/ward")
public class WardsController {

    private static final String WARD_ADDED = "Ward successfully added";
    private static final String WARD_NOT_ADDED = "Ward cannot add";
    private static final String WARD_DELETED = "Ward deleted";
    private static final String WARD_NOT_DELETED = "Ward cannot delete";
    private static final String WARD_UPDATED = "Ward updated";
    private static final String WARD_NOT_UPDATED_EMPTY = "Ward cannot update because he has incorrect params";
    private static final String WARD_NOT_UPDATED_NOT_EXIST = "Ward cannot update because he doesn't exist";

    @Autowired
    private WardRepository wardRepository;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addWard(@RequestBody final WardsEntity wardsEntity) {
        if (ValidationUtils.valid(wardsEntity)) {
            wardRepository.save(wardsEntity);
            return WARD_ADDED;
        }
        return WARD_NOT_ADDED;
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WardsEntity> getWard(@RequestParam(value = "id", required = false) final Integer id) {
        if (id == null || id <= 0) {
            return wardRepository.findAll();
        }
        return Collections.singletonList(wardRepository.findById(id).orElse(null));
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateWard(@RequestBody final WardsEntity wardsEntity) {
        if (ValidationUtils.valid(wardsEntity)) {
            final Optional<WardsEntity> wardOptional = wardRepository.findById(wardsEntity.getId());
            if (wardOptional.isPresent()) {
                final WardsEntity ward = wardOptional.get();
                ward.setMaxCount(wardsEntity.getMaxCount());
                ward.setName(wardsEntity.getName());
                wardRepository.flush();
                return WARD_UPDATED;
            }
            return WARD_NOT_UPDATED_NOT_EXIST;
        }
        return WARD_NOT_UPDATED_EMPTY;
    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String removeWard(@RequestBody final WardsEntity wardsEntity) {
        if (ValidationUtils.valid(wardsEntity)) {
            wardRepository.delete(wardsEntity);
            return WARD_DELETED;
        }
        return WARD_NOT_DELETED;
    }
}
