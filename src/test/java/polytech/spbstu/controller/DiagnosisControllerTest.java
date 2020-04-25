package polytech.spbstu.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.http.MediaType;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import polytech.spbstu.entity.DiagnosisEntity;
import polytech.spbstu.repos.DiagnosisRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(DiagnosisController.class)
public class DiagnosisControllerTest {

    @MockBean
    private DiagnosisRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testReturn200() throws Exception {
        final DiagnosisEntity diagnosisEntity = new DiagnosisEntity();
        diagnosisEntity.setId(17);
        diagnosisEntity.setName("User");
        given(repository.findDiagnosisEntityByName(any())).willReturn(diagnosisEntity);

        mockMvc.perform(get("/polyclinic/spbstu/diagnosis/get")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(diagnosisEntity.getName())));
    }
}