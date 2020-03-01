package polytech.spbstu.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WardsEntityTest {

    private final static int TEST_ID = 1234;
    private final static int TEST_MAX_COUNT = 126;
    private final static String TEST_NAME = "test_name";

    private static WardsEntity wardsEntity;

    @BeforeAll
    static void createInstance() {
        wardsEntity = new WardsEntity();
        wardsEntity.setId(TEST_ID);
        wardsEntity.setName(TEST_NAME);
        wardsEntity.setMaxCount(TEST_MAX_COUNT);
    }

    @Test
    public void testEntityMethods() {
        assertEquals(wardsEntity.getId(), TEST_ID);
        assertEquals(wardsEntity.getName(), TEST_NAME);
        assertEquals(wardsEntity.getMaxCount(), TEST_MAX_COUNT);
    }

    @Test
    public void testBaseMethods() {
        WardsEntity wardsEntityFirst = new WardsEntity();
        wardsEntityFirst.setId(TEST_ID);
        wardsEntityFirst.setName(TEST_NAME);
        wardsEntityFirst.setMaxCount(TEST_MAX_COUNT);
        assertEquals(wardsEntity.hashCode(), wardsEntityFirst.hashCode());
        assertEquals(wardsEntity.toString(), wardsEntityFirst.toString());
        assertEquals(wardsEntity, wardsEntityFirst);

        WardsEntity wardsEntitySecond = new WardsEntity();
        wardsEntitySecond.setId(76543);
        wardsEntitySecond.setName("edede");
        wardsEntitySecond.setMaxCount(65432);
        assertNotEquals(wardsEntity.hashCode(), wardsEntitySecond.hashCode());
        assertNotEquals(wardsEntity.toString(), wardsEntitySecond.toString());
        assertNotEquals(wardsEntity, wardsEntitySecond);

        WardsEntity wardsEntityThird = new WardsEntity();
        wardsEntityThird.setId(543);
        wardsEntityThird.setName(TEST_NAME);
        wardsEntityThird.setMaxCount(TEST_MAX_COUNT);
        assertNotEquals(wardsEntity.hashCode(), wardsEntityThird.hashCode());
        assertNotEquals(wardsEntity.toString(), wardsEntityThird.toString());
        assertNotEquals(wardsEntity, wardsEntityThird);

        WardsEntity wardsEntityFourth = new WardsEntity();
        wardsEntityFourth.setId(TEST_ID);
        wardsEntityFourth.setName("ededededed");
        wardsEntityFourth.setMaxCount(TEST_MAX_COUNT);
        assertNotEquals(wardsEntity.hashCode(), wardsEntityFourth.hashCode());
        assertNotEquals(wardsEntity.toString(), wardsEntityFourth.toString());
        assertNotEquals(wardsEntity, wardsEntityFourth);

        WardsEntity wardsEntityFifth = new WardsEntity();
        wardsEntityFifth.setId(TEST_ID);
        wardsEntityFifth.setName(TEST_NAME);
        wardsEntityFifth.setMaxCount(543);
        assertNotEquals(wardsEntity.hashCode(), wardsEntityFifth.hashCode());
        assertNotEquals(wardsEntity.toString(), wardsEntityFifth.toString());
        assertNotEquals(wardsEntity, wardsEntityFifth);
    }
}