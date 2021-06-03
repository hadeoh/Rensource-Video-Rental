package com.rensource.videorental.models;

import com.rensource.videorental.enums.VideoGenre;
import com.rensource.videorental.enums.VideoType;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "Videos")
@Entity
@Data
@ToString
@RequiredArgsConstructor
public class Video extends Audit implements Serializable {

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VideoType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VideoGenre genre;

    @Column(name = "maximum_age")
    private Integer maximumAge;

    @Column(name = "release_year")
    private Integer releaseYear;

    public Video(String title, VideoType type, VideoGenre genre) {
        this.title = title;
        this.type = type;
        this.genre = genre;
    }

    public Video(String title, VideoType type, VideoGenre genre, Integer releaseYear) {
        this.title = title;
        this.type = type;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

}
