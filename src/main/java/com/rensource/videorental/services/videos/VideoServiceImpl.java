package com.rensource.videorental.services.videos;

import com.rensource.videorental.models.Video;
import com.rensource.videorental.repositories.VideoRepository;
import com.rensource.videorental.services.videos.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    VideoRepository videoRepository;

    @Autowired
    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public List<Video> listVideos(int pageNumber, int pageSize) {
        List<Video> allVideos = new ArrayList<>();
        try {
            Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
            Slice<Video> videos = videoRepository.findAll(pageable);
            allVideos = videos.getContent();
        } catch (Exception e) {
            log.info("An error was encountered while getting the list of videos due to {}", e.getMessage());
        }
        return allVideos;
    }

    @Override
    public Video getVideo(String title) {
        return videoRepository.findByTitle(title);
    }
}
