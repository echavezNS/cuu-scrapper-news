package scrappers.scrapperCover;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import enums.PagesList;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import services.HtmlProcess;
import utils.Scrapper;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;import java.util.regex.Pattern;

/**
 * Created by echavez on 6/15/16.
 */
public class TiempoCover extends CoverPage{

    public final static String page = (String) PagesList.getRootUrls().get(PagesList.pages.TIEMPO);

    public final String regex = new String("\\/\\w+\\/\\d+-\\w+\\/\\d");

    public TiempoCover(HtmlProcess htmlProcess) throws IOException {
        super(page,htmlProcess);
    }

    public boolean isLink(String href) {
        return href.matches(this.regex);
    }

    @Override
    public HashSet<String> getArticlesLinks(){
        HashSet<String> auxlinks = new HashSet<String>();

        if(this.getHtml() != null){
            Elements elements = this.getHtml().select("a");

            for(Element link: elements){
                String stringLink = link.attr("href");
                if(this.isLink(stringLink)){

                    auxlinks.add(page+stringLink);

                }
            }
        }

        return auxlinks;
    }
}
