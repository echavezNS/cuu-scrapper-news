package models;

import org.jsoup.nodes.Document;
import services.HtmlProcess;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by echavez on 5/20/16.
 */
public abstract class Article {

    protected String title;
    protected Set<String> thumbnail = new HashSet<String>();;
    protected String content;
    protected String author;
    protected String pageUrl;

    protected String category;
    protected Video video;
    protected Date date;
    protected Enum page;
    protected Document html;

    protected HashSet<String> tags = new HashSet<String>();

    public Article(String pageUrl, HtmlProcess htmlProcess) throws IOException {

        this.html = htmlProcess.getHtml(pageUrl);
        this.pageUrl = pageUrl;
        setTitle();
        setContent();
        setThumbnail();
    }

    public HashSet<String> getTags(){
        return this.tags;
    }

    public void setTags(){}

    public Set<String> getThumbnail() {
        return thumbnail;
    }

    abstract public void setThumbnail();

    public String getTitle() {
        return title;
    }

    abstract public void setTitle();

    public String getContent() {
        return content;
    }

    abstract public void setContent();

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

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

    public void setDate(Date date) {
        this.date = date;
    }

    public Enum getPage() {
        return page;
    }

    public void setPage(Enum page) {
        this.page = page;
    }

}
