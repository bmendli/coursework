package polytech.spbstu.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class DiagnosisEntityTest {

    private final static int TEST_ID = 1234;
    private final static String TEST_NAME = "test_name";

    private static DiagnosisEntity diagnosisEntity;

    @BeforeAll
    static void createInstance() {
        diagnosisEntity = new DiagnosisEntity();
        diagnosisEntity.setId(TEST_ID);
        diagnosisEntity.setName(TEST_NAME);
    }

    @Test
    public void testEntityMethods() {
        assertEquals(diagnosisEntity.getId(), TEST_ID);
        assertEquals(diagnosisEntity.getName(), TEST_NAME);
    }

    @Test
    public void testBaseMethods() {
        DiagnosisEntity diagnosisEntityFirst = new DiagnosisEntity();
        diagnosisEntityFirst.setId(TEST_ID);
        diagnosisEntityFirst.setName(TEST_NAME);
        assertEquals(diagnosisEntity.hashCode(), diagnosisEntityFirst.hashCode());
        assertEquals(diagnosisEntity.toString(), diagnosisEntityFirst.toString());
        assertEquals(diagnosisEntity, diagnosisEntityFirst);

        DiagnosisEntity diagnosisEntitySecond = new DiagnosisEntity();
        diagnosisEntitySecond.setId(432);
        diagnosisEntitySecond.setName("ededed");
        assertNotEquals(diagnosisEntity.hashCode(), diagnosisEntitySecond.hashCode());
        assertNotEquals(diagnosisEntity.toString(), diagnosisEntitySecond.toString());
        assertNotEquals(diagnosisEntity, diagnosisEntitySecond);

        DiagnosisEntity diagnosisEntityThird = new DiagnosisEntity();
        diagnosisEntityThird.setId(321);
        diagnosisEntityThird.setName(TEST_NAME);
        assertNotEquals(diagnosisEntity.hashCode(), diagnosisEntityThird.hashCode());
        assertNotEquals(diagnosisEntity.toString(), diagnosisEntityThird.toString());
        assertNotEquals(diagnosisEntity, diagnosisEntityThird);

        DiagnosisEntity diagnosisEntityFourth = new DiagnosisEntity();
        diagnosisEntityFourth.setId(TEST_ID);
        diagnosisEntityFourth.setName("we3e");
        assertNotEquals(diagnosisEntity.hashCode(), diagnosisEntityFourth.hashCode());
        assertNotEquals(diagnosisEntity.toString(), diagnosisEntityFourth.toString());
        assertNotEquals(diagnosisEntity, diagnosisEntityFourth);
    }
}