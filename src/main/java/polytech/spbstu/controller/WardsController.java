package polytech.spbstu.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import polytech.spbstu.dto.WardDto;
import polytech.spbstu.entity.PeopleEntity;
import polytech.spbstu.entity.WardsEntity;
import polytech.spbstu.repos.PeopleRepository;
import polytech.spbstu.repos.WardRepository;
import polytech.spbstu.utils.ValidationUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootApplication(scanBasePackages = {"polytech.spbstu.repos", "polytech.spbstu.entity"})
@RestController
@RequestMapping("/polyclinic/spbstu/users/ward")
public class WardsController {

    private final WardRepository wardRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public WardsController(WardRepository wardRepository,
                           PeopleRepository peopleRepository) {
        this.wardRepository = wardRepository;
        this.peopleRepository = peopleRepository;
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity addWard(@ModelAttribute final WardsEntity wardsEntity) {
        if (ValidationUtils.valid(wardsEntity)) {
            wardRepository.save(wardsEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WardDto> getAllWards() {
        final List<WardsEntity> all = wardRepository.findAll();
        List<WardDto> wardDtoList = new ArrayList<>();
        all.forEach(ward -> {
            final List<PeopleEntity> peopleEntity = peopleRepository.findPeopleEntitiesByWardsByWardId(ward);
            WardDto wardDto = WardDto.fromWard(ward);
            wardDto.setPeopleEntities(peopleEntity);
            wardDtoList.add(wardDto);
        });
        return wardDtoList;
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WardsEntity> getWard(@RequestParam(value = "id", required = false) final Integer id) {
        if (id == null || id <= 0) {
            return wardRepository.findAll();
        }
        return Collections.singletonList(wardRepository.findById(id).orElse(null));
    }

    @GetMapping(value = "get/{id}")
    public ResponseEntity<WardDto> getWardById(@PathVariable("id") Integer id) {
        final Optional<WardsEntity> wardOptional = wardRepository.findById(id);
        return wardOptional.map(wardsEntity -> new ResponseEntity<>(WardDto.fromWard(wardsEntity), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<WardDto> updateWard(@ModelAttribute final WardsEntity wardsEntity) {
        if (ValidationUtils.valid(wardsEntity)) {
            final Optional<WardsEntity> wardOptional = wardRepository.findById(wardsEntity.getId());
            if (wardOptional.isPresent()) {
                final WardsEntity ward = wardOptional.get();
                ward.setMaxCount(wardsEntity.getMaxCount());
                ward.setName(wardsEntity.getName());
                wardRepository.flush();
                WardDto wardDto = WardDto.fromWard(ward);
                return new ResponseEntity<>(wardDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity removeWard(@ModelAttribute final WardsEntity wardsEntity) {
        if (ValidationUtils.valid(wardsEntity)) {
            final WardsEntity ward = wardRepository.findWardsEntitiesByName(wardsEntity.getName());
            if (ward == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                wardRepository.delete(wardsEntity);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
