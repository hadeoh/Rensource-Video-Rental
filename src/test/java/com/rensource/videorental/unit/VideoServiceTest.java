package com.rensource.videorental.unit;

import com.rensource.videorental.enums.VideoGenre;
import com.rensource.videorental.enums.VideoType;
import com.rensource.videorental.models.Video;
import com.rensource.videorental.repositories.VideoRepository;
import com.rensource.videorental.services.videos.VideoServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private VideoServiceImpl videoService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private List<Video> createVideos() {
        List<Video> videos = new ArrayList<>();

        Video video1 = new Video("Death", VideoType.REGULAR, VideoGenre.HORROR);
        Video video2 = new Video("Peaky Blinders", VideoType.NEW_RELEASE, VideoGenre.ACTION, 2019);

        videos.addAll(Arrays.asList(video1, video2));

        return videos;
    }

    @Test
    public void it_should_get_list_of_videos() {
        videoRepository.saveAll(createVideos());
        when(videoService.listVideos(1,2)).thenReturn(createVideos());
        assertEquals(2, createVideos().size());
        verify(videoRepository, times(1)).saveAll(createVideos());
    }

    @Test
    public void it_should_get_video() {
        Video video = createVideos().get(0);
        videoRepository.save(video);
        when(videoService.getVideo("Death")).thenReturn(video);
        assertEquals("Death", video.getTitle());
        verify(videoRepository, times(1)).save(video);
    }
}
