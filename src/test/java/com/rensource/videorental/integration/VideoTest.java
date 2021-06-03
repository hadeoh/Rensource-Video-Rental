package com.rensource.videorental.integration;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class VideoTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void return_list_videos_with_page_size_number() throws Exception {
        mockMvc.perform(get("/videos?page=1&size=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", Matchers.hasSize(2)))
                .andExpect(jsonPath("errors", Matchers.blankOrNullString()));
    }

    @Test
    public void return_list_videos_without_page_size_number() throws Exception {
        mockMvc.perform(get("/videos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", Matchers.hasSize(5)))
                .andExpect(jsonPath("errors", Matchers.blankOrNullString()));
    }
}
