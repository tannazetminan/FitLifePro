package com.example.fitlifepro;

public class Video
{
    private String VideoName;

    private String VideoUrl;

    // Getter & Setter for VideoName
    public String getVideoName() {
        return VideoName;
    }

    public void setVideoName(String videoName) {
        VideoName = videoName;
    }

    // Getter & Setter for VideoUrl
    public String getVideoUrl() {
        return VideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        VideoUrl = videoUrl;
    }

    // Constructor
    public Video(String videoName, String videoUrl) {
        VideoName = videoName;
        VideoUrl = videoUrl;
    }
}
