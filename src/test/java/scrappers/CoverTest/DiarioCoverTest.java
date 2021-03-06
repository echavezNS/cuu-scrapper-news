package scrappers.CoverTest;

import org.junit.Before;
import org.junit.Test;
import scrappers.Services.StubService;
import scrappers.scrapperCover.CoverPage;
import scrappers.scrapperCover.DiarioCover;
import services.HtmlProcess;

import java.io.IOException;
import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by echavez on 6/23/16.
 */
public class DiarioCoverTest extends StubService implements CommonCoverTest {

    private String url = DiarioCover.page;
    private HashSet<String> hash;
    private CoverPage cover;

    @Before
    public void setup() throws IOException {

        HtmlProcess htmlProcessStub = this.getAndSetStub("/stubCover/stubDiarioCover.html", this.url);
        cover = new DiarioCover(htmlProcessStub);
        hash = cover.getArticlesLinks();
    }

    @Test
    @Override
    public void itShouldMatchWithRegexPattern() {

        String one = "http://eldiariodechihuahua.mx/Local/2016/06/22/detienen-a-100-personas-provienen-algunas-de-oaxaca-y-del-sur-del-pais/";
        assertTrue(cover.isLink(one));
        String two = "http://www.eldiariodechihuahua.mx/Local/2016/22/detienen-a-100-personas-provienen-algunas-de-oaxaca-y-del-sur-del-pais/";
        assertFalse(cover.isLink(two));

    }

    @Test
    @Override
    public void itShouldGetAtLeastOneNew() {
        assertTrue(hash.size() > 0);

    }
}
