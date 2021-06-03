package com.rensource.videorental.services.videos;

import com.rensource.videorental.models.Video;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VideoService {
    List<Video> listVideos(int pageNumber, int pageSize);
    Video getVideo(String title);
}
