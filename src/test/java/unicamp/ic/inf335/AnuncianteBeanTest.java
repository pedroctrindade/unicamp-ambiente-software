package unicamp.ic.inf335;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class AnuncianteBeanTest {

    private AnuncianteBean anunciante;
    private AnuncioBean anuncio1;
    private AnuncioBean anuncio2;
    private ProdutoBean produto1;
    private ProdutoBean produto2;

    @BeforeEach
    void setUp() {
        anunciante = new AnuncianteBean();

        produto1 = new ProdutoBean("001", "Notebook", "Dell Inspiron", 2000.0, "Novo");
        produto2 = new ProdutoBean("002", "Mouse", "Mouse Gamer", 150.0, "Novo");

        anuncio1 = new AnuncioBean();
        anuncio1.setProduto(produto1);
        anuncio1.setDesconto(0.1); // 10% desconto

        anuncio2 = new AnuncioBean();
        anuncio2.setProduto(produto2);
        anuncio2.setDesconto(0.2); // 20% desconto
    }

    @Test
    void testConstructorDefault() {
        assertEquals("", anunciante.getNome());
        assertEquals("", anunciante.getCPF());
        assertNotNull(anunciante.getAnuncios());
        assertTrue(anunciante.getAnuncios().isEmpty());
    }

    @Test
    void testConstructorComParametros() {
        ArrayList<AnuncioBean> anuncios = new ArrayList<>();
        anuncios.add(anuncio1);

        AnuncianteBean anuncianteParam = new AnuncianteBean("João Silva", "123.456.789-00", anuncios);

        assertEquals("João Silva", anuncianteParam.getNome());
        assertEquals("123.456.789-00", anuncianteParam.getCPF());
        assertEquals(anuncios, anuncianteParam.getAnuncios());
        assertEquals(1, anuncianteParam.getAnuncios().size());
    }

    @Test
    void testSettersEGetters() {
        ArrayList<AnuncioBean> anuncios = new ArrayList<>();
        anuncios.add(anuncio1);
        anuncios.add(anuncio2);

        anunciante.setNome("Maria Santos");
        anunciante.setCPF("987.654.321-00");
        anunciante.setAnuncios(anuncios);

        assertEquals("Maria Santos", anunciante.getNome());
        assertEquals("987.654.321-00", anunciante.getCPF());
        assertEquals(anuncios, anunciante.getAnuncios());
        assertEquals(2, anunciante.getAnuncios().size());
    }

    @Test
    void testAddAnuncio() {
        assertEquals(0, anunciante.getAnuncios().size());

        anunciante.addAnuncio(anuncio1);
        assertEquals(1, anunciante.getAnuncios().size());
        assertEquals(anuncio1, anunciante.getAnuncios().get(0));

        anunciante.addAnuncio(anuncio2);
        assertEquals(2, anunciante.getAnuncios().size());
        assertEquals(anuncio2, anunciante.getAnuncios().get(1));
    }

    @Test
    void testRemoveAnuncio() {
        anunciante.addAnuncio(anuncio1);
        anunciante.addAnuncio(anuncio2);
        assertEquals(2, anunciante.getAnuncios().size());

        anunciante.removeAnuncio(0);
        assertEquals(1, anunciante.getAnuncios().size());
        assertEquals(anuncio2, anunciante.getAnuncios().get(0));

        anunciante.removeAnuncio(0);
        assertEquals(0, anunciante.getAnuncios().size());
    }

    @Test
    void testRemoveAnuncioIndiceInvalido() {
        anunciante.addAnuncio(anuncio1);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            anunciante.removeAnuncio(5); // Índice que não existe
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            anunciante.removeAnuncio(-1); // Índice negativo
        });
    }


    @Test
    void testValorMedioAnunciosComUmAnuncio() {
        anunciante.addAnuncio(anuncio1);

        Double valorMedio = anunciante.valorMedioAnuncios();
        Double valorEsperado = anuncio1.getValor();

        assertEquals(valorEsperado, valorMedio);
    }

    @Test
    void testValorMedioAnunciosComVariosAnuncios() {

        ProdutoBean produto3 = new ProdutoBean("003", "Teclado", "Teclado Mecânico", 300.0, "Novo");
        AnuncioBean anuncio3 = new AnuncioBean();
        anuncio3.setProduto(produto3);
        anuncio3.setDesconto(0.5);

        anunciante.addAnuncio(anuncio1);
        anunciante.addAnuncio(anuncio2);
        anunciante.addAnuncio(anuncio3);

        Double valorMedio = anunciante.valorMedioAnuncios();
        Double somaValores = anuncio1.getValor() + anuncio2.getValor() + anuncio3.getValor();
        Double valorEsperado = somaValores / 3.0;

        assertEquals(valorEsperado, valorMedio);
    }

    @Test
    void testValoresNulos() {
        anunciante.setNome(null);
        anunciante.setCPF(null);
        anunciante.setAnuncios(null);

        assertNull(anunciante.getNome());
        assertNull(anunciante.getCPF());
        assertNull(anunciante.getAnuncios());
    }

    @Test
    void testCPFProperty() {

        String cpf = "111.222.333-44";
        anunciante.setCPF(cpf);
        assertEquals(cpf, anunciante.getCPF());
    }

    @Test
    void testAdicionarAnunciosSequencial() {
        assertTrue(anunciante.getAnuncios().isEmpty());

        for (int i = 0; i < 5; i++) {
            ProdutoBean produto = new ProdutoBean("00" + i, "Produto" + i, "Descrição" + i, 100.0 * (i + 1), "Novo");
            AnuncioBean anuncio = new AnuncioBean();
            anuncio.setProduto(produto);
            anuncio.setDesconto(0.1);
            anunciante.addAnuncio(anuncio);
        }

        assertEquals(5, anunciante.getAnuncios().size());
        assertEquals("Produto0", anunciante.getAnuncios().get(0).getProduto().getNome());
        assertEquals("Produto4", anunciante.getAnuncios().get(4).getProduto().getNome());
    }
}