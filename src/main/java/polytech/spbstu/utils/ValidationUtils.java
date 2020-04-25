package polytech.spbstu.utils;

import java.util.List;
import polytech.spbstu.entity.DiagnosisEntity;
import polytech.spbstu.entity.PeopleEntity;
import polytech.spbstu.entity.WardsEntity;

public class ValidationUtils {

    public static boolean valid(WardsEntity ward) {
        return !(ward == null
                || ward.getMaxCount() <= 0
                || ward.getName().isBlank());
    }

    public static boolean valid(DiagnosisEntity diagnosis) {
        return !(diagnosis == null
                || diagnosis.getName().isBlank());
    }

    public static boolean valid(PeopleEntity people) {
        return !(people == null
                || people.getFirstName().isBlank()
                || people.getLastName().isBlank()
                || people.getFatherName().isBlank()
                || !valid(people.getWardsByWardId())
                || !valid(people.getDiagnosisByDiagnosisId()));
    }

    public static boolean haveSpot(WardsEntity wardsEntity, List<PeopleEntity> peopleEntities) {
        return wardsEntity.getMaxCount() > peopleEntities.size();
    }
}
