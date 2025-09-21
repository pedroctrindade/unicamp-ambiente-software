package unicamp.ic.inf335;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoBeanTest {

    private ProdutoBean produto;

    @BeforeEach
    void setUp() {
        produto = new ProdutoBean();
    }

    @Test
    void testConstructorDefault() {
        assertEquals("", produto.getCodigo());
        assertEquals("", produto.getNome());
        assertEquals("", produto.getDescricao());
        assertEquals(0.0, produto.getValor());
        assertEquals("", produto.getEstado());
    }

    @Test
    void testConstructorComParametros() {
        ProdutoBean produtoParam = new ProdutoBean("001", "Notebook", "Notebook Dell", 2500.0, "Novo");

        assertEquals("001", produtoParam.getCodigo());
        assertEquals("Notebook", produtoParam.getNome());
        assertEquals("Notebook Dell", produtoParam.getDescricao());
        assertEquals(2500.0, produtoParam.getValor());
        assertEquals("Novo", produtoParam.getEstado());
    }

    @Test
    void testSettersEGetters() {
        produto.setCodigo("002");
        produto.setNome("Smartphone");
        produto.setDescricao("iPhone 13");
        produto.setValor(3000.0);
        produto.setEstado("Usado");

        assertEquals("002", produto.getCodigo());
        assertEquals("Smartphone", produto.getNome());
        assertEquals("iPhone 13", produto.getDescricao());
        assertEquals(3000.0, produto.getValor());
        assertEquals("Usado", produto.getEstado());
    }

    @Test
    void testCompareTo() {
        ProdutoBean produto1 = new ProdutoBean("001", "Produto1", "Desc1", 100.0, "Novo");
        ProdutoBean produto2 = new ProdutoBean("002", "Produto2", "Desc2", 200.0, "Novo");
        ProdutoBean produto3 = new ProdutoBean("003", "Produto3", "Desc3", 100.0, "Usado");


        assertTrue(produto1.compareTo(produto2) != 0);
        assertTrue(produto2.compareTo(produto1) == 1);
        assertEquals(0, produto1.compareTo(produto3));
    }

    @Test
    void testSerializacao() {

        assertTrue(produto instanceof java.io.Serializable);
        assertNotNull(ProdutoBean.getSerialversionuid());
        assertEquals(1L, ProdutoBean.getSerialversionuid());
    }

    @Test
    void testValoresNulos() {
        produto.setCodigo(null);
        produto.setNome(null);
        produto.setDescricao(null);
        produto.setEstado(null);

        assertNull(produto.getCodigo());
        assertNull(produto.getNome());
        assertNull(produto.getDescricao());
        assertNull(produto.getEstado());
    }
}
