package com.rensource.videorental.repositories;

import com.rensource.videorental.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
    Video findByTitle(String title);
}
