package com.example.demovideo.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/video")
public class VideoController {

    @GetMapping("/stream")
    public ResponseEntity<byte[]> streamVideo() throws IOException {
        Path videoPath = Paths.get("D:\\學習IDEA\\demovideo\\backend\\demovideo\\video\\sample.mp4");

        if (!Files.exists(videoPath) || !Files.isRegularFile(videoPath)) {
            throw new IOException("Video file not found.");
        }

        FileSystemResource videoResource = new FileSystemResource(videoPath.toFile());
        byte[] videoBytes = Files.readAllBytes(videoResource.getFile().toPath());

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("video/mp4"))
                .body(videoBytes);
    }
}
