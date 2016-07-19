package hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by echavez on 7/14/16.
 */
@Entity
@Table(name = "tags", uniqueConstraints = {
        @UniqueConstraint(columnNames = "tag_id"),
        @UniqueConstraint(columnNames = "article_id")
})
public class TagModel extends AbstractTimestampEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id", unique = true, nullable = true)
    private int tagId;

    @Column(nullable = false, unique = true)
    private String name;

    public ArticleModel getArticles() {
        return articles;
    }

    public void setArticles(ArticleModel articles) {
        this.articles = articles;
    }

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private ArticleModel articles;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
