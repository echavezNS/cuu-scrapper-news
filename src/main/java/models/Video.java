package models;

import enums.VideoProvidersList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by echavez on 5/20/16.
 */
public class Video {

    private String url;
    private String videoId;
    public Enum provider;

    public Video(String url){
        this.url = url;
    }

    public Video(String url, String video_id, String type) {
        this.url = url;
        this.videoId = video_id;
    }

    public Enum getProvider() {

        if(this.url.matches(".*youtube.*") || this.url.matches(".*youtu.*")){
            return VideoProvidersList.providers.YOUTUBE;
        }

        return VideoProvidersList.providers.OTHER;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideoId() {

        String pattern = "";

        if(this.getProvider() == VideoProvidersList.providers.YOUTUBE){
            pattern  = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        }

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(this.url);
        if(matcher.find()){
            return matcher.group();
        } else {
            return "error";
        }
    }

    public void setVideo_id(String videoId) {
        this.videoId = videoId;
    }


}
