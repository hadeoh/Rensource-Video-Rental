package com.rensource.videorental.dtos;

import com.rensource.videorental.enums.VideoGenre;
import com.rensource.videorental.enums.VideoType;
import lombok.Data;

@Data
public class VideoDto {

    private String title;
    private VideoType type;
    private VideoGenre genre;
}
