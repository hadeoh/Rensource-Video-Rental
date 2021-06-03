package com.rensource.videorental.controllers;

import com.rensource.videorental.dtos.VideoDto;
import com.rensource.videorental.responses.Response;
import com.rensource.videorental.services.videos.VideoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    VideoService videoService;
    ModelMapper modelMapper;

    @Autowired
    public VideoController(VideoService videoService, ModelMapper modelMapper) {
        this.videoService = videoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<Response<List<VideoDto>>> listVideos(@RequestParam(defaultValue = "1") Integer page,
                                                               @RequestParam(defaultValue = "5") Integer size) {
        List<VideoDto> videos = modelMapper.map(videoService.listVideos(page, size), new TypeToken<List<VideoDto>>(){}.getType());
        Response<List<VideoDto>> response = new Response<>();
        response.setStatus(HttpStatus.OK);
        response.setMessage("All videos retrieved successfully");
        response.setData(videos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
