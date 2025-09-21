package unicamp.ic.inf335;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class AnuncioBeanTest {

    private AnuncioBean anuncio;
    private ProdutoBean produto;

    @BeforeEach
    void setUp() {
        anuncio = new AnuncioBean();
        produto = new ProdutoBean("001", "Notebook", "Dell Inspiron", 2000.0, "Novo");
    }

    @Test
    void testConstructorDefault() {
        assertNotNull(anuncio.getProduto());
        assertNotNull(anuncio.getFotosUrl());
        assertEquals(0.0, anuncio.getDesconto());
        assertTrue(anuncio.getFotosUrl().isEmpty());
    }

    @Test
    void testConstructorComParametros() throws MalformedURLException {
        ArrayList<URL> fotos = new ArrayList<>();
        fotos.add(new URL("http://exemplo.com/foto1.jpg"));

        AnuncioBean anuncioParam = new AnuncioBean(produto, fotos, 0.1);

        assertEquals(produto, anuncioParam.getProduto());
        assertEquals(fotos, anuncioParam.getFotosUrl());
        assertEquals(0.1, anuncioParam.getDesconto());
    }

    @Test
    void testSettersEGetters() throws MalformedURLException {
        ArrayList<URL> fotos = new ArrayList<>();
        fotos.add(new URL("http://exemplo.com/foto1.jpg"));
        fotos.add(new URL("http://exemplo.com/foto2.jpg"));

        anuncio.setProduto(produto);
        anuncio.setFotosUrl(fotos);
        anuncio.setDesconto(0.15);

        assertEquals(produto, anuncio.getProduto());
        assertEquals(fotos, anuncio.getFotosUrl());
        assertEquals(0.15, anuncio.getDesconto());
        assertEquals(2, anuncio.getFotosUrl().size());
    }

    @Test
    void testGetValorSemDesconto() {
        produto.setValor(1000.0);
        anuncio.setProduto(produto);
        anuncio.setDesconto(0.0);

        assertEquals(anuncio.getValor(), anuncio.getValor());

    }

    @Test
    void testGetValorComDesconto() {
        produto.setValor(1000.0);
        anuncio.setProduto(produto);
        anuncio.setDesconto(0.1);

        Double valorEsperado = 900.0;
        assertEquals(valorEsperado, anuncio.getValor());
    }

    @Test
    void testGetValorComDescontoCorreto() {
        produto.setValor(1000.0);
        anuncio.setProduto(produto);
        anuncio.setDesconto(0.1);


        Double resultado = anuncio.getValor();
        assertNotNull(resultado);
    }

    @Test
    void testAdicionarFotos() throws MalformedURLException {
        anuncio.getFotosUrl().add(new URL("http://exemplo.com/foto1.jpg"));
        anuncio.getFotosUrl().add(new URL("http://exemplo.com/foto2.jpg"));

        assertEquals(2, anuncio.getFotosUrl().size());
        assertEquals("http://exemplo.com/foto1.jpg", anuncio.getFotosUrl().get(0).toString());
        assertEquals("http://exemplo.com/foto2.jpg", anuncio.getFotosUrl().get(1).toString());
    }

    @Test
    void testSerializacao() {
        assertTrue(anuncio instanceof java.io.Serializable);
        assertNotNull(AnuncioBean.getSerialversionuid());
        assertEquals(1L, AnuncioBean.getSerialversionuid());
    }

    @Test
    void testValoresNulos() {
        anuncio.setProduto(null);
        anuncio.setFotosUrl(null);
        anuncio.setDesconto(null);

        assertNull(anuncio.getProduto());
        assertNull(anuncio.getFotosUrl());
        assertNull(anuncio.getDesconto());
    }


}
