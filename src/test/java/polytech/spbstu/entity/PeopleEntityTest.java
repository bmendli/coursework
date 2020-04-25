package polytech.spbstu.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PeopleEntityTest {

    private final static int TEST_ID = 1234;
    private final static int TEST_MAX_COUNT = 126;
    private final static String TEST_NAME = "test_name";
    private final static String TEST_FIRST_NAME = "test_first_name";
    private final static String TEST_LAST_NAME = "test_last_name";
    private final static String TEST_FATHER_NAME = "test_father_name";
    private final static WardsEntity TEST_WARD;
    private final static DiagnosisEntity TEST_DIAGNOSIS;

    private static PeopleEntity peopleEntity;

    static {
        TEST_WARD = new WardsEntity();
        TEST_WARD.setId(TEST_ID);
        TEST_WARD.setName(TEST_NAME);
        TEST_WARD.setMaxCount(TEST_MAX_COUNT);

        TEST_DIAGNOSIS = new DiagnosisEntity();
        TEST_DIAGNOSIS.setId(TEST_ID);
        TEST_DIAGNOSIS.setName(TEST_NAME);
    }

    @BeforeAll
    static void createInstance() {
        peopleEntity = new PeopleEntity();
        peopleEntity.setId(TEST_ID);
        peopleEntity.setFirstName(TEST_FIRST_NAME);
        peopleEntity.setLastName(TEST_LAST_NAME);
        peopleEntity.setFatherName(TEST_FATHER_NAME);
        peopleEntity.setDiagnosisByDiagnosisId(TEST_DIAGNOSIS);
        peopleEntity.setWardsByWardId(TEST_WARD);
    }

    @Test
    public void testEntityMethods() {
        assertEquals(peopleEntity.getId(), TEST_ID);
        assertEquals(peopleEntity.getFirstName(), TEST_FIRST_NAME);
        assertEquals(peopleEntity.getLastName(), TEST_LAST_NAME);
        assertEquals(peopleEntity.getFatherName(), TEST_FATHER_NAME);
        assertEquals(peopleEntity.getDiagnosisByDiagnosisId(), TEST_DIAGNOSIS);
        assertEquals(peopleEntity.getWardsByWardId(), TEST_WARD);
    }

    @Test
    public void testBaseMethods() {
        PeopleEntity peopleEntityFirst = new PeopleEntity();
        peopleEntityFirst.setId(TEST_ID);
        peopleEntityFirst.setFirstName(TEST_FIRST_NAME);
        peopleEntityFirst.setLastName(TEST_LAST_NAME);
        peopleEntityFirst.setFatherName(TEST_FATHER_NAME);
        peopleEntityFirst.setDiagnosisByDiagnosisId(TEST_DIAGNOSIS);
        peopleEntityFirst.setWardsByWardId(TEST_WARD);
        assertEquals(peopleEntity.hashCode(), peopleEntityFirst.hashCode());
        assertEquals(peopleEntity.toString(), peopleEntityFirst.toString());
        assertEquals(peopleEntity, peopleEntityFirst);

        PeopleEntity peopleEntitySecond = new PeopleEntity();
        peopleEntitySecond.setId(234);
        peopleEntitySecond.setFirstName("deded");
        peopleEntitySecond.setLastName("drsffsf");
        peopleEntitySecond.setFatherName("efsrfsrfs");
        peopleEntitySecond.setDiagnosisByDiagnosisId(new DiagnosisEntity());
        peopleEntitySecond.setWardsByWardId(new WardsEntity());
        assertNotEquals(peopleEntity.hashCode(), peopleEntitySecond.hashCode());
        assertNotEquals(peopleEntity.toString(), peopleEntitySecond.toString());
        assertNotEquals(peopleEntity, peopleEntitySecond);

        PeopleEntity peopleEntityThird = new PeopleEntity();
        peopleEntityThird.setId(432);
        peopleEntityThird.setFirstName(TEST_FIRST_NAME);
        peopleEntityThird.setLastName(TEST_LAST_NAME);
        peopleEntityThird.setFatherName(TEST_FATHER_NAME);
        peopleEntityThird.setDiagnosisByDiagnosisId(TEST_DIAGNOSIS);
        peopleEntityThird.setWardsByWardId(TEST_WARD);
        assertNotEquals(peopleEntity.hashCode(), peopleEntityThird.hashCode());
        assertNotEquals(peopleEntity.toString(), peopleEntityThird.toString());
        assertNotEquals(peopleEntity, peopleEntityThird);

        PeopleEntity peopleEntityFourth = new PeopleEntity();
        peopleEntityFourth.setId(TEST_ID);
        peopleEntityFourth.setFirstName("dededed");
        peopleEntityFourth.setLastName(TEST_LAST_NAME);
        peopleEntityFourth.setFatherName(TEST_FATHER_NAME);
        peopleEntityFourth.setDiagnosisByDiagnosisId(TEST_DIAGNOSIS);
        peopleEntityFourth.setWardsByWardId(TEST_WARD);
        assertNotEquals(peopleEntity.hashCode(), peopleEntityFourth.hashCode());
        assertNotEquals(peopleEntity.toString(), peopleEntityFourth.toString());
        assertNotEquals(peopleEntity, peopleEntityFourth);

        PeopleEntity peopleEntityFifth = new PeopleEntity();
        peopleEntityFifth.setId(TEST_ID);
        peopleEntityFifth.setFirstName(TEST_FIRST_NAME);
        peopleEntityFifth.setLastName("ededefef");
        peopleEntityFifth.setFatherName(TEST_FATHER_NAME);
        peopleEntityFifth.setDiagnosisByDiagnosisId(TEST_DIAGNOSIS);
        peopleEntityFifth.setWardsByWardId(TEST_WARD);
        assertNotEquals(peopleEntity.hashCode(), peopleEntityFifth.hashCode());
        assertNotEquals(peopleEntity.toString(), peopleEntityFifth.toString());
        assertNotEquals(peopleEntity, peopleEntityFifth);

        PeopleEntity peopleEntitySixth = new PeopleEntity();
        peopleEntitySixth.setId(TEST_ID);
        peopleEntitySixth.setFirstName(TEST_FIRST_NAME);
        peopleEntitySixth.setLastName(TEST_LAST_NAME);
        peopleEntitySixth.setFatherName("swdesefsef");
        peopleEntitySixth.setDiagnosisByDiagnosisId(TEST_DIAGNOSIS);
        peopleEntitySixth.setWardsByWardId(TEST_WARD);
        assertNotEquals(peopleEntity.hashCode(), peopleEntitySixth.hashCode());
        assertNotEquals(peopleEntity.toString(), peopleEntitySixth.toString());
        assertNotEquals(peopleEntity, peopleEntitySixth);

        PeopleEntity peopleEntitySeventh = new PeopleEntity();
        peopleEntitySeventh.setId(TEST_ID);
        peopleEntitySeventh.setFirstName(TEST_FIRST_NAME);
        peopleEntitySeventh.setLastName(TEST_LAST_NAME);
        peopleEntitySeventh.setFatherName(TEST_FATHER_NAME);
        peopleEntitySeventh.setDiagnosisByDiagnosisId(new DiagnosisEntity());
        peopleEntitySeventh.setWardsByWardId(TEST_WARD);
        assertNotEquals(peopleEntity.hashCode(), peopleEntitySeventh.hashCode());
        assertNotEquals(peopleEntity.toString(), peopleEntitySeventh.toString());
        assertNotEquals(peopleEntity, peopleEntitySeventh);

        PeopleEntity peopleEntityEighth = new PeopleEntity();
        peopleEntityEighth.setId(TEST_ID);
        peopleEntityEighth.setFirstName(TEST_FIRST_NAME);
        peopleEntityEighth.setLastName(TEST_LAST_NAME);
        peopleEntityEighth.setFatherName(TEST_FATHER_NAME);
        peopleEntityEighth.setDiagnosisByDiagnosisId(TEST_DIAGNOSIS);
        peopleEntityEighth.setWardsByWardId(new WardsEntity());
        assertNotEquals(peopleEntity.hashCode(), peopleEntityEighth.hashCode());
        assertNotEquals(peopleEntity.toString(), peopleEntityEighth.toString());
        assertNotEquals(peopleEntity, peopleEntityEighth);
    }
}