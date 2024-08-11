package com.stream.app.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "streamapp-videos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Video {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "videoid")
    @Id
    private String videoId;
    private String title;
    private String description;
    private String contentType;
    private String filePath;
//    @ManyToOne
//    private Course course;
}
