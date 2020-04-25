package polytech.spbstu.controller;

import java.util.Random;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import polytech.spbstu.Application;
import polytech.spbstu.entity.WardsEntity;
import polytech.spbstu.repos.WardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class WardsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WardRepository repository;

    private final char[] symbols = new char[]{'q', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd',
            'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I',
            'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '0'};
    private final Random random = new Random();
    private final StringBuilder stringBuilder = new StringBuilder();

    @Test
    public void testRequest() throws Exception {

        WardsEntity wardsEntity = generateRandomWards();

        //check correct adding generated WardEntity
        mvc.perform(post("/polyclinic/spbstu/users/ward/add")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", String.valueOf(wardsEntity.getId()))
                .param("name", wardsEntity.getName())
                .param("maxCount", String.valueOf(wardsEntity.getMaxCount())))
                .andDo(print())
                .andExpect(status().isOk());

        //check adding WardEntity with incorrect name
        mvc.perform(post("/polyclinic/spbstu/users/ward/add")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", String.valueOf(wardsEntity.getId()))
                .param("name", "")
                .param("maxCount", String.valueOf(wardsEntity.getMaxCount())))
                .andDo(print())
                .andExpect(status().isConflict());

        //check adding WardEntity with incorrect maxCount
        mvc.perform(post("/polyclinic/spbstu/users/ward/add")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", String.valueOf(wardsEntity.getId()))
                .param("name", wardsEntity.getName())
                .param("maxCount", "-1"))
                .andDo(print())
                .andExpect(status().isConflict());

        //check exist ward
        mvc.perform(get("/polyclinic/spbstu/users/ward/get/34")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", is("nomer6")))
                .andExpect(jsonPath("maxCount", is(5)));

        //check not exist ward
        mvc.perform(get("/polyclinic/spbstu/users/ward/get/9876543")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        //check update exist ward with correct params
        final int maxCount = random.nextInt(5) + 5;
        final String name = generateName();
        mvc.perform(post("/polyclinic/spbstu/users/ward/update")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", "36")
                .param("name", name)
                .param("maxCount", String.valueOf(maxCount)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id", is(36)))
                .andExpect(jsonPath("name", is(name)))
                .andExpect(jsonPath("maxCount", is(maxCount)));

        //check update not exist ward
        mvc.perform(post("/polyclinic/spbstu/users/ward/update")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", "99999")
                .param("name", "clecs;cms;cm;dc")
                .param("maxCount", "7654"))
                .andDo(print())
                .andExpect(status().isNoContent());

        //check update incorrect ward
        mvc.perform(post("/polyclinic/spbstu/users/ward/update")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", "-1")
                .param("name", "")
                .param("maxCount", "-1"))
                .andDo(print())
                .andExpect(status().isNotAcceptable());

        //check delete exist ward
        mvc.perform(post("/polyclinic/spbstu/users/ward/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", String.valueOf(wardsEntity.getId()))
                .param("name", wardsEntity.getName())
                .param("maxCount", String.valueOf(wardsEntity.getMaxCount())))
                .andDo(print())
                .andExpect(status().isOk());

        //check delete not exist ward
        mvc.perform(post("/polyclinic/spbstu/users/ward/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", "999")
                .param("name", generateName())
                .param("maxCount", "999"))
                .andDo(print())
                .andExpect(status().isNoContent());

        //check delete incorrect ward
        mvc.perform(post("/polyclinic/spbstu/users/ward/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", "-1")
                .param("name", "")
                .param("maxCount", "-1"))
                .andDo(print())
                .andExpect(status().isNotAcceptable());
    }

    private WardsEntity generateRandomWards() {
        WardsEntity wardsEntity = new WardsEntity();
        wardsEntity.setName(generateName());
        wardsEntity.setMaxCount(random.nextInt(10) + 5);
        wardsEntity.setId(-1);
        return wardsEntity;
    }

    private String generateName() {
        for (int i = 0; i < random.nextInt(10) + 10; i++) {
            int index = random.nextInt(symbols.length);
            if (index == 0) {
                index = 1;
            } else if (index == symbols.length) {
                index--;
            }
            stringBuilder.append(symbols[index]);
        }
        final String name = stringBuilder.toString();
        stringBuilder.setLength(0);
        return name;
    }
}