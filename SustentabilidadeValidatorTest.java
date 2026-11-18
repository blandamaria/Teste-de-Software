package validacao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SustentabilidadeValidatorTest {

    // --- TESTES DE DESLIGAMENTO DE LUZES ---

    @Test
    @DisplayName("Deve permitir desligar luz que está atualmente LIGADA")
    void devePermitirDesligarLuzLigada() {
        assertTrue(SustentabilidadeValidator.validarDesligamentoLuz("Corredor Bloco A", "LIGADA", "DESLIGADA"));
        assertTrue(SustentabilidadeValidator.validarDesligamentoLuz("Garagem Subsolo", "ligada", "desligada"));
    }

    @Test
    @DisplayName("Deve rejeitar desligamento se a luz já estiver DESLIGADA ou se o local for vazio")
    void deveRejeitarDesligamentoInvalido() {
        assertFalse(SustentabilidadeValidator.validarDesligamentoLuz("Corredor Bloco A", "DESLIGADA", "DESLIGADA"));
        assertFalse(SustentabilidadeValidator.validarDesligamentoLuz("", "LIGADA", "DESLIGADA"));
        assertFalse(SustentabilidadeValidator.validarDesligamentoLuz(null, "LIGADA", "DESLIGADA"));
    }

    // --- TESTES DE REGRA DE META MENSAL ---

    @Test
    @DisplayName("Deve confirmar meta mensal atingida quando consumo for menor ou igual à meta")
    void deveAtingirMetaMensal() {
        assertTrue(SustentabilidadeValidator.verificarMetaMensalAtingida(850.0, 1000.0)); // abaixo da meta
        assertTrue(SustentabilidadeValidator.verificarMetaMensalAtingida(1000.0, 1000.0)); // exatamente na meta
    }

    @Test
    @DisplayName("Deve indicar meta não atingida quando consumo ultrapassar a meta mensal")
    void deveFalharMetaMensalQuandoExceder() {
        assertFalse(SustentabilidadeValidator.verificarMetaMensalAtingida(1250.0, 1000.0));
    }

    @Test
    @DisplayName("Deve lançar exceção para valores negativos ou meta zerada")
    void deveRejeitarValoresInvalidosNaMeta() {
        assertThrows(IllegalArgumentException.class, () -> {
            SustentabilidadeValidator.verificarMetaMensalAtingida(-50.0, 1000.0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            SustentabilidadeValidator.verificarMetaMensalAtingida(500.0, 0);
        });
    }
}