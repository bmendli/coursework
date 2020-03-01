package polytech.spbstu.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class RoleEntityTest {

    private final static long TEST_ID = 123456L;
    private final static String TEST_USERNAME = "test_username";
    private final static String TEST_PASSWORD = "test_password";
    private final static String TEST_CONFIRM_PASSWORD = "test_confirm_password";
    private final static String TEST_NAME = "test_name";
    private final static List<UserEntity> TEST_USER_ENTITIES;

    private static RoleEntity roleEntity;

    static {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(TEST_ID);
        userEntity.setUsername(TEST_USERNAME);
        userEntity.setPassword(TEST_PASSWORD);
        userEntity.setConfirmPassword(TEST_CONFIRM_PASSWORD);
        TEST_USER_ENTITIES = Collections.singletonList(userEntity);
    }

    @BeforeAll
    static void createInstance() {
        roleEntity = new RoleEntity();
        roleEntity.setId(TEST_ID);
        roleEntity.setName(TEST_NAME);
        roleEntity.setUserEntities(TEST_USER_ENTITIES);
    }

    @Test
    public void testEntityMethods() {
        assertEquals(roleEntity.getId(), TEST_ID);
        assertEquals(roleEntity.getName(), TEST_NAME);
        assertEquals(roleEntity.getUserEntities(), TEST_USER_ENTITIES);
    }

    @Test
    public void testBaseMethods() {
        final RoleEntity roleEntityFirst = new RoleEntity();
        roleEntityFirst.setId(TEST_ID);
        roleEntityFirst.setName(TEST_NAME);
        roleEntityFirst.setUserEntities(TEST_USER_ENTITIES);
        assertEquals(roleEntity, roleEntityFirst);
        assertEquals(roleEntity.toString(), roleEntityFirst.toString());
        assertEquals(roleEntity, roleEntityFirst);

        final RoleEntity roleEntitySecond = new RoleEntity();
        roleEntitySecond.setId(232123L);
        roleEntitySecond.setName("TEST_NAsxsdwdwME");
        roleEntitySecond.setUserEntities(List.of());
        assertNotEquals(roleEntity, roleEntitySecond);
        assertNotEquals(roleEntity.toString(), roleEntitySecond.toString());
        assertNotEquals(roleEntity, roleEntitySecond);

        final RoleEntity roleEntityThird = new RoleEntity();
        roleEntityThird.setId(232123L);
        roleEntityThird.setName(TEST_NAME);
        roleEntityThird.setUserEntities(TEST_USER_ENTITIES);
        assertNotEquals(roleEntity, roleEntityThird);
        assertNotEquals(roleEntity.toString(), roleEntityThird.toString());
        assertNotEquals(roleEntity, roleEntityThird);

        final RoleEntity roleEntityFourth = new RoleEntity();
        roleEntityFourth.setId(TEST_ID);
        roleEntityFourth.setName("dwde");
        roleEntityFourth.setUserEntities(TEST_USER_ENTITIES);
        assertNotEquals(roleEntity, roleEntityFourth);
        assertNotEquals(roleEntity.toString(), roleEntityFourth.toString());
        assertNotEquals(roleEntity, roleEntityFourth);

        final RoleEntity roleEntityFifth = new RoleEntity();
        roleEntityFifth.setId(TEST_ID);
        roleEntityFifth.setName(TEST_NAME);
        roleEntityFifth.setUserEntities(List.of());
        assertNotEquals(roleEntity, roleEntityFifth);
        assertNotEquals(roleEntity.toString(), roleEntityFifth.toString());
        assertNotEquals(roleEntity, roleEntityFifth);
    }
}