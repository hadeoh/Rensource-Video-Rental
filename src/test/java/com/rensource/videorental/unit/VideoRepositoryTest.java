package com.rensource.videorental.unit;

import com.rensource.videorental.enums.VideoGenre;
import com.rensource.videorental.enums.VideoType;
import com.rensource.videorental.models.Video;
import com.rensource.videorental.repositories.VideoRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VideoRepositoryTest {

    @Autowired
    VideoRepository videoRepository;

    @Test
    public void test_create_read_videos() {
        Video video = new Video("Mortal Kombat", VideoType.REGULAR, VideoGenre.ACTION);
        video = videoRepository.saveAndFlush(video);
        Iterable<Video> videos = videoRepository.findAll();
        Assertions.assertThat(videos).extracting(Video::getTitle).contains("Mortal Kombat");
    }
}
