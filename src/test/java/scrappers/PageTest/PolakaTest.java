package scrappers.PageTest;

import models.Article;
import org.junit.Before;
import org.junit.Test;
import scrappers.Services.StubService;
import scrappers.scrapperPage.Polaka;
import services.HtmlProcess;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by echavez on 6/25/16.
 */
public class PolakaTest extends StubService implements CommonTest {

    public String url = "http://www.lapolaka.com/efecto-uber-en-juarez/";
    public Article article;

    @Before
    @Override
    public void setup() throws IOException {

        HtmlProcess htmlProcessStub = this.getAndSetStub("/stubPage/stubPolaka.html", this.url);
        this.article = new Polaka(this.url, htmlProcessStub);

    }

    @Test
    @Override
    public void itShouldGetTheTitle() {
        assertEquals("Efecto Uber en Juarez", this.article.getTitle());
    }

    @Test
    @Override
    public void itShouldGetTheContent() {
        assertEquals("<p>CIUDAD JUÁREZ.- Dos taxistas fueron acribillados afuera de su casa en la colonia Granjero, justo al comenzar operaciones la compañía Uber.</p>\n" +
                "<p>Pistoleros llegaron hasta el cruce de las calles Tercera e Hidalgo y dispararon contra los dos choferes que estaban afuera.</p>\n" +
                "<p>Los dos sujetos quedaron lesionados y agentes municipales que llegaron solicitaron el apoyo médico.</p>\n" +
                "<p>Los lesionados fueron llevados a un hospital particular en estado grave.</p>\n" +
                "<p>A pesar del aparatoso operativo montado en la zona, no se reportó la detención de los responsables.</p>\n" +
                "<p><img class=\"alignnone size-full wp-image-297287\" src=\"http://www.lapolaka.com/wp-content/uploads/2016/06/image-27.jpeg\" alt=\"image\" width=\"1600\" height=\"900\" srcset=\"http://www.lapolaka.com/wp-content/uploads/2016/06/image-27.jpeg 1600w, http://www.lapolaka.com/wp-content/uploads/2016/06/image-27-450x253.jpeg 450w, http://www.lapolaka.com/wp-content/uploads/2016/06/image-27-768x432.jpeg 768w, http://www.lapolaka.com/wp-content/uploads/2016/06/image-27-950x534.jpeg 950w, http://www.lapolaka.com/wp-content/uploads/2016/06/image-27-696x392.jpeg 696w, http://www.lapolaka.com/wp-content/uploads/2016/06/image-27-1068x601.jpeg 1068w, http://www.lapolaka.com/wp-content/uploads/2016/06/image-27-747x420.jpeg 747w\" sizes=\"(max-width: 1600px) 100vw, 1600px\"></p>\n", this.article.getContent());
    }

    @Test
    public void itShouldGetTheThumbnail() {
        Set<String> thumbnails = new HashSet<>();
        thumbnails.add("http://www.lapolaka.com/wp-content/uploads/2016/06/image-28-696x392.jpeg");

        assertEquals(thumbnails, this.article.getThumbnail());
    }

    @Test
    public void itShouldGetTheDate() {
        Date expectedDate = new Date("Fri Jun 24 18:14:19 UTC 2016");
        assertEquals(expectedDate, this.article.getDate());
    }

    @Test
    public void itShouldGetTheTags() {
        Set<String> tags = new HashSet<>();
        tags.add("Taxistas");
        tags.add("El Granjero");
        tags.add("Baleados");
        tags.add("Lesionados");
        assertEquals(tags, this.article.getTags());
    }

    @Test
    public void itShouldGetTheCategory() {
        assertEquals("De ocho", this.article.getCategory());
    }
}
