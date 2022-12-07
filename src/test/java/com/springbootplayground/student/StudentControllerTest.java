package com.springbootplayground.student;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static java.util.Calendar.APRIL;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @Before
    public void init() {
        StudentEntity studentEntity = new StudentEntity(
                1L,
                "Jamal",
                "jamal@gmail.com",
                LocalDate.of(1999, Month.APRIL, 3));
        when(studentRepository.findById(1L)).thenReturn(Optional.of(studentEntity));
    }

    @WithMockUser("USER")
    @Test
    public void find_login_ok() throws Exception {

        mockMvc.perform(get("/api/v1/student/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect( jsonPath("$.id", is(1)))
                .andExpect( jsonPath("$.name", is("Jamal")))
                .andExpect( jsonPath("$.email", is("jamal@gmail.com")))
                .andExpect( jsonPath("$.dob", is("1999-04-03")))
                .andExpect( jsonPath("$.age", is(23)));
    }

    @Test
    public void find_nologin_401() throws Exception {
        mockMvc.perform(get("/api/v1/student/1"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }


}
