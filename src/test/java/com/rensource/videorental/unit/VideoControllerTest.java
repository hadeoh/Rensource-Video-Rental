package com.rensource.videorental.unit;

import com.rensource.videorental.controllers.VideoController;
import com.rensource.videorental.enums.VideoGenre;
import com.rensource.videorental.enums.VideoType;
import com.rensource.videorental.models.Video;
import com.rensource.videorental.services.videos.VideoService;
import com.rensource.videorental.testconfigurations.TestConfig;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(VideoController.class)
public class VideoControllerTest {

    @MockBean
    VideoService videoService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void it_should_return_videos_list() throws Exception {
        Video video = new Video("Mortal Kombat", VideoType.REGULAR, VideoGenre.ACTION);
        List<Video> videos = Arrays.asList(video);

        Mockito.when(videoService.listVideos(1,2)).thenReturn(videos);

        mockMvc.perform(get("/videos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors", Matchers.blankOrNullString()));
    }
}
