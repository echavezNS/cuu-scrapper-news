package models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import services.HtmlProcess;
import utils.UtilFunctions;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by echavez on 5/20/16.
 */
public abstract class Article extends UtilFunctions {

    protected String title;
    protected Set<String> thumbnail = new HashSet<String>();;
    protected String content;
    protected String author;
    protected String pageUrl;
    protected String category;
    protected Video video;
    protected Date date;
    protected int sourceId;
    protected Enum page;
    protected Document html;
    protected Boolean hasVideo;

    protected HashSet<String> tags = new HashSet<String>();

    public Article(String pageUrl, HtmlProcess htmlProcess) throws IOException {
        this.html = htmlProcess.getHtml(pageUrl);
        this.pageUrl = pageUrl;
        setTitle();
        setThumbnail();
        setContent();
        setHasVideo();
    }

    public Boolean getHasVideo() {
        return hasVideo;
    }

    public void setHasVideo() {
        Document aux = Jsoup.parse(this.content);
        Elements iframe = aux.select("iframe");
        if(iframe.isEmpty()) {
            this.hasVideo = false;
        } else {
            String src = iframe.attr("src");
            if(!src.isEmpty()) {
                Video video = new Video(src);
                this.hasVideo = true;
                this.video = video;
            } else {
                this.hasVideo = false;
            }
        }
    };

    public int getSourceId() {
        return this.sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public HashSet<String> getTags(){
        return this.tags;
    }

    public void setTags(){}

    public Set<String> getThumbnail() {
        return thumbnail;
    }

    abstract public void setTitle();

    abstract public void setThumbnail();

    public String getTitle() {
        return title;
    }


    public String getContent() {
        return content;
    }

    abstract public void setContent();

    public String getCategory() {
        return category;
    }

    public void setCategory() {}

    public String getAuthor() {
        return author;
    }

    public void setAuthor() {}

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo() {}

    public Date getDate() {
        return date;
    }

    public void setDate() {
        this.date = date;
    }

    public Enum getPage() {
        return page;
    }

    public void setPage(Enum page) {
        this.page = page;
    }

}
