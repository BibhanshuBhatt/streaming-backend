package com.stream.app.controller;


import com.stream.app.entities.Video;
import com.stream.app.playload.CustomMessage;
import com.stream.app.services.VideoService;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/videos")
@CrossOrigin("*")
public class VideoController {

     VideoService videoService;


    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description
    ) {

        Video video = new Video();
        video.setTitle(title);
        video.setDescription(description);
        video.setVideoId(UUID.randomUUID().toString());

         videoService.save(video, file);
        return null;

//        if (savedVideo != null) {
//            return ResponseEntity
//                    .status(HttpStatus.OK)
//                    .body(video);
//        } else {
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(CustomMessage.builder()
//                            .message("Video not uploaded ")
//                            .success(false)
//                            .build()
//                    );
//        }


    }
   //getallvideos
    @GetMapping
   public List<Video>getAll(){
        return videoService.getAll();
   }



//   stream vido
    //http://localhost:8080/api/v1/videos/stream/3521252
    @GetMapping("/stream/{videoId}")
        public ResponseEntity<Resource> stream(
           @PathVariable String videoId )
    {

        Video video=videoService.get(videoId);
       String contentType= video.getContentType();
       String filePath= video.getFilePath();
       Resource resource=new FileSystemResource(filePath);
       if (contentType==null){
           contentType="application/octed-stream";
       }
       return ResponseEntity.ok()
               .contentType(MediaType.parseMediaType(contentType)).body(resource);
        }
}