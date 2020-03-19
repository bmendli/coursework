package polytech.spbstu.entity;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UserEntityTest {

    private final static long TEST_ID = 999L;
    private final static String TEST_USERNAME = "test_username";
    private final static String TEST_PASSWORD = "test_password";
    private final static String TEST_CONFIRM_PASSWORD = "test_confirm_password";
    private final static String TEST_NAME = "test_admin";
    private final static List<RoleEntity> TEST_ROLES;

    private static UserEntity userEntity;

    static {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(TEST_ID);
        roleEntity.setName(TEST_NAME);
        TEST_ROLES = Collections.singletonList(roleEntity);
    }

    @BeforeAll
    static void createInstance() {
        userEntity = new UserEntity();
        userEntity.setId(TEST_ID);
        userEntity.setUsername(TEST_USERNAME);
        userEntity.setPassword(TEST_PASSWORD);
        userEntity.setConfirmPassword(TEST_CONFIRM_PASSWORD);
        userEntity.setRoleEntities(TEST_ROLES);
    }

    @Test
    public void testEntityMethods() {
        assertEquals(userEntity.getId(), TEST_ID);
        assertEquals(userEntity.getUsername(), TEST_USERNAME);
        assertEquals(userEntity.getPassword(), TEST_PASSWORD);
        assertEquals(userEntity.getConfirmPassword(), TEST_CONFIRM_PASSWORD);
        assertEquals(userEntity.getRoleEntities(), TEST_ROLES);
    }

    @Test
    public void testBaseMethods() {
        UserEntity testUserEntityFirst = new UserEntity();
        testUserEntityFirst.setId(TEST_ID);
        testUserEntityFirst.setUsername(TEST_USERNAME);
        testUserEntityFirst.setPassword(TEST_PASSWORD);
        testUserEntityFirst.setConfirmPassword(TEST_CONFIRM_PASSWORD);
        testUserEntityFirst.setRoleEntities(TEST_ROLES);
        assertEquals(userEntity.hashCode(), testUserEntityFirst.hashCode());
        assertEquals(userEntity.toString(), testUserEntityFirst.toString());
        assertEquals(userEntity, testUserEntityFirst);

        final RoleEntity role = new RoleEntity();
        role.setId(0);
        role.setName("");
        UserEntity testUserEntitySecond = new UserEntity();
        testUserEntitySecond.setId(123456);
        testUserEntitySecond.setUsername("werdtgferdgrf");
        testUserEntitySecond.setPassword("sdgtrfesdgredgrs");
        testUserEntitySecond.setConfirmPassword("srdgrgdregdgd");

        testUserEntitySecond.setRoleEntities(Collections.singletonList(role));
        assertNotEquals(userEntity.hashCode(), testUserEntitySecond.hashCode());
        assertNotEquals(userEntity.toString(), testUserEntitySecond.toString());
        assertNotEquals(userEntity, testUserEntitySecond);

        UserEntity testUserEntityThird = new UserEntity();
        testUserEntityThird.setId(1234);
        testUserEntityThird.setUsername(TEST_USERNAME);
        testUserEntityThird.setPassword(TEST_PASSWORD);
        testUserEntityThird.setConfirmPassword(TEST_CONFIRM_PASSWORD);
        testUserEntityThird.setRoleEntities(TEST_ROLES);
        assertNotEquals(userEntity.hashCode(), testUserEntityThird.hashCode());
        assertNotEquals(userEntity.toString(), testUserEntityThird.toString());
        assertNotEquals(userEntity, testUserEntityThird);

        UserEntity testUserEntityFourth = new UserEntity();
        testUserEntityFourth.setId(TEST_ID);
        testUserEntityFourth.setUsername("effef");
        testUserEntityFourth.setPassword(TEST_PASSWORD);
        testUserEntityFourth.setConfirmPassword(TEST_CONFIRM_PASSWORD);
        testUserEntityFourth.setRoleEntities(TEST_ROLES);
        assertNotEquals(userEntity.hashCode(), testUserEntityFourth.hashCode());
        assertNotEquals(userEntity.toString(), testUserEntityFourth.toString());
        assertNotEquals(userEntity, testUserEntityFourth);

        UserEntity testUserEntityFifth = new UserEntity();
        testUserEntityFifth.setId(TEST_ID);
        testUserEntityFifth.setUsername(TEST_USERNAME);
        testUserEntityFifth.setPassword("edeafesfe");
        testUserEntityFifth.setConfirmPassword(TEST_CONFIRM_PASSWORD);
        testUserEntityFifth.setRoleEntities(TEST_ROLES);
        assertNotEquals(userEntity.hashCode(), testUserEntityFifth.hashCode());
        assertNotEquals(userEntity.toString(), testUserEntityFifth.toString());
        assertNotEquals(userEntity, testUserEntityFifth);

        UserEntity testUserEntitySixth = new UserEntity();
        testUserEntitySixth.setId(TEST_ID);
        testUserEntitySixth.setUsername(TEST_USERNAME);
        testUserEntitySixth.setPassword(TEST_PASSWORD);
        testUserEntitySixth.setConfirmPassword("efrseffsefsef");
        testUserEntitySixth.setRoleEntities(TEST_ROLES);
        assertNotEquals(userEntity.hashCode(), testUserEntitySixth.hashCode());
        assertNotEquals(userEntity.toString(), testUserEntitySixth.toString());
        assertNotEquals(userEntity, testUserEntitySixth);

        UserEntity testUserEntitySeventh = new UserEntity();
        testUserEntitySeventh.setId(TEST_ID);
        testUserEntitySeventh.setUsername(TEST_USERNAME);
        testUserEntitySeventh.setPassword(TEST_PASSWORD);
        testUserEntitySeventh.setConfirmPassword(TEST_CONFIRM_PASSWORD);
        testUserEntitySeventh.setRoleEntities(Collections.singletonList(role));
        assertNotEquals(userEntity.hashCode(), testUserEntitySeventh.hashCode());
        assertNotEquals(userEntity.toString(), testUserEntitySeventh.toString());
        assertNotEquals(userEntity, testUserEntitySeventh);
    }
}