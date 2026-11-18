package validacao;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinanceiroRankingValidatorTest {

    // --- TESTES DE SALDO E RANKING ---

    @Test
    @DisplayName("Deve calcular corretamente o saldo de pontos disponíveis")
    void deveCalcularSaldoPontos() {
        assertEquals(150, FinanceiroRankingValidator.calcularSaldoPontos(200, 50));
        assertEquals(0, FinanceiroRankingValidator.calcularSaldoPontos(100, 100));
    }

    @Test
    @DisplayName("Deve bloquear resgate maior que o saldo ou valores negativos")
    void deveRejeitarSaldoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            FinanceiroRankingValidator.calcularSaldoPontos(80, 100); // saldo insuficiente
        });

        assertThrows(IllegalArgumentException.class, () -> {
            FinanceiroRankingValidator.calcularSaldoPontos(-10, 0); // valor negativo
        });
    }

    @Test
    @DisplayName("Deve calcular corretamente a posição do morador no ranking")
    void deveCalcularPosicaoRanking() {
        List<Integer> pontuacoes = Arrays.asList(120, 350, 210, 90);

        assertEquals(1, FinanceiroRankingValidator.calcularPosicaoRanking(350, pontuacoes)); // 1º lugar
        assertEquals(2, FinanceiroRankingValidator.calcularPosicaoRanking(210, pontuacoes)); // 2º lugar
        assertEquals(4, FinanceiroRankingValidator.calcularPosicaoRanking(90, pontuacoes));  // 4º lugar
    }

    // --- TESTES DE BLOQUEIO DE CONTA DUPLICADA NO MESMO MÊS ---

    @Test
    @DisplayName("Deve permitir cadastrar conta mensal quando ainda não existe registro no mês")
    void devePermitirContaNovoMes() {
        List<String> contasExistentes = Arrays.asList("11222333000181-09/2026", "11222333000181-10/2026");

        assertTrue(FinanceiroRankingValidator.permitirCadastroContaMensal("11222333000181", "11/2026", contasExistentes));
    }

    @Test
    @DisplayName("Deve bloquear cadastro de conta duplicada no mesmo mês para o mesmo condomínio")
    void deveBloquearContaDuplicadaMesmoMes() {
        List<String> contasExistentes = Arrays.asList("11222333000181-09/2026", "11222333000181-10/2026");

        assertFalse(FinanceiroRankingValidator.permitirCadastroContaMensal("11222333000181", "10/2026", contasExistentes));
        assertFalse(FinanceiroRankingValidator.permitirCadastroContaMensal("", "11/2026", contasExistentes));
        assertFalse(FinanceiroRankingValidator.permitirCadastroContaMensal("11222333000181", null, contasExistentes));
    }
}