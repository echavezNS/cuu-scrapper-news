package scrappers.PageTest;

import models.Article;
import org.junit.Before;
import org.junit.Test;
import scrappers.Services.StubService;
import scrappers.scrapperPage.Tiempo;
import services.HtmlProcess;

import javax.persistence.Table;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by echavez on 5/21/16.
 */
public class TiempoTest extends StubService implements CommonTest {

    private final String url = "http://tiempo.com.mx/noticia/34340-desbandada_en_el_prd_renuncian/1";
    private Article article;
    private Article articleVideo;
    private HtmlProcess htmlProcessStub;

    @Before
    public void setup() throws IOException {

        htmlProcessStub = this.getAndSetStub("/stubPage/stubTiempo.html", this.url);
        this.article = new Tiempo(this.url, htmlProcessStub);

    }

    @Test
    public void itShouldAddTheIframeWhenIsAVideo() throws IOException {
        this.htmlProcessStub = this.getAndSetStub("/stubPage/stubTiempoVideo.html", this.url);
        this.articleVideo = new Tiempo(this.url, htmlProcessStub);
        String expected = "<iframe width=\"560\" height=\"315\" src=\"//www.youtube.com/embed/zwGCLCVoRmA?wmode=opaque\" frameborder=\"0\"></iframe>";
        assertEquals(this.articleVideo.getContent(), expected);
    }

    @Test
    public void itShouldGiveMeTheThumbnailAsTheOriginalArticle(){

        Set<String> hash = new HashSet<>();
        hash.add("http://assets.tiempo.com.mx/uploads/imagen/imagen/43903/image.jpeg");
        assertEquals(hash,this.article.getThumbnail());

    }

    @Test
    public void itShouldGiveMeTheTagsAsTheOriginalArticle(){

        Set<String> hash = new HashSet<String>();

        hash.add("Sucesión 2016");
        hash.add("Política");
        hash.add("Chihuahua");

        assertEquals(hash, this.article.getTags());

    }

    @Test
    @Override
    public void itShouldGetTheTitle() {
        assertEquals("Desbandada en el PRD; renuncian 500 integrantes y van con Maru".trim(), article.getTitle());
    }

    @Test
    @Override
    public void itShouldGetTheContent() {
        String content =  "<p>500 miembros del PRD e intregrantes de la Red Fuerza Chihuahua presentarán mañana su renuncia ante el partido del Sol Azteca y anunciaron su adhesión a la campaña de Maru Campos.</p>\n" +
                "\n" +
                "<p></p><p> </p><p>Daniel Chávez, coordinador de dicho movimiento, mencionó que ante la crisis que vive el Partido de la Revolución Democrática y la falta de oportunidades dentro de dicho partido político, decidieron dejar dicho instituto político y unir ante la campaña de Maru Campos.</p><p>Los coordinadores de sector del PRD destruyeron frente a los medios de comunicación las credenciales de afiliación y mostraron también las cartas de renuncia.</p><p>\"El PRD no tiene liderazgo y sufre decaída fuerte, tan así que tuvieron que traer a candidato externo para poder levantar, por lo que ante la invitación hablamos con toda nuestra gente que son 500 personas y por unanimidad fuimos con Maru\": dijo.<br><br></p><p></p>";
        assertEquals(content, this.article.getContent());
    }

    @Test
    public void itShouldGetTheCategory(){
        assertEquals("Local", this.article.getCategory());
    }

    @Test
    public void itShouldGetTheDate() {
        Date expectedDate = new Date("Sun May 22 00:00:00 UTC 2016");
        assertEquals(expectedDate, this.article.getDate());
    }

    @Test
    public void itShouldGetTheAuthor() {
        String expectedAuthor = "Pedro Fierro S.";
        assertEquals(expectedAuthor, this.article.getAuthor());
    }
}
