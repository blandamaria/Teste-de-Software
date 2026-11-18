package ecocondo.agua;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ContaAguaRegrasTest {
    @Test
    void ct03_deveRejeitarValorELitragemNegativosOuZero() {
        assertThrows(IllegalArgumentException.class,
            () -> ContaAguaRegras.validarConta(-50.0, 10000.0));
        assertThrows(IllegalArgumentException.class,
            () -> ContaAguaRegras.validarConta(0.0, 10000.0));
        assertThrows(IllegalArgumentException.class,
            () -> ContaAguaRegras.validarConta(350.0, -1.0));
        assertThrows(IllegalArgumentException.class,
            () -> ContaAguaRegras.validarConta(350.0, 0.0));
        assertDoesNotThrow(() -> ContaAguaRegras.validarConta(350.0, 12000.0));
    }
    @Test
    void ct05_deveCalcularVariacaoPercentualMensal() {
        assertEquals(20.0, ContaAguaRegras.variacaoPercentual(120.0, 100.0), 0.001);  // aumento
        assertEquals(-25.0, ContaAguaRegras.variacaoPercentual(75.0, 100.0), 0.001);  // redução
        assertEquals(0.0, ContaAguaRegras.variacaoPercentual(100.0, 100.0), 0.001);
    }
}