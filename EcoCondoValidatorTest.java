package validacao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EcoCondoValidatorTest {

    // --- TESTES DE CÁLCULO DE PONTOS DE RECICLAGEM ---

    @Test
    @DisplayName("Deve calcular corretamente os pontos para quantidades positivas")
    void deveCalcularPontosReciclagemValidos() {
        assertEquals(20, EcoCondoValidator.calcularPontosReciclagem("plastico", 2.0));
        assertEquals(15, EcoCondoValidator.calcularPontosReciclagem("papel", 3.0));
        assertEquals(30, EcoCondoValidator.calcularPontosReciclagem("metal", 2.0));
    }

    @Test
    @DisplayName("Deve rejeitar quantidade igual a zero ou negativa")
    void deveRejeitarQuantidadeMenorOuIgualAZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            EcoCondoValidator.calcularPontosReciclagem("plastico", 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            EcoCondoValidator.calcularPontosReciclagem("vidro", -5.5);
        });
    }

    // --- TESTES DE VALIDAÇÃO DE CNPJ ---

    @Test
    @DisplayName("Deve aceitar CNPJs válidos com ou sem máscara")
    void deveAceitarCnpjValido() {
        assertTrue(EcoCondoValidator.validarCNPJ("11.222.333/0001-81"));
        assertTrue(EcoCondoValidator.validarCNPJ("11222333000181"));
    }

    @Test
    @DisplayName("Deve rejeitar CNPJs inválidos, repetidos, vazios ou nulos")
    void deveRejeitarCnpjInvalido() {
        assertFalse(EcoCondoValidator.validarCNPJ("11.222.333/0001-99")); // dígito verificador errado
        assertFalse(EcoCondoValidator.validarCNPJ("00.000.000/0000-00")); // números repetidos
        assertFalse(EcoCondoValidator.validarCNPJ("123456")); // tamanho incorreto
        assertFalse(EcoCondoValidator.validarCNPJ(""));
        assertFalse(EcoCondoValidator.validarCNPJ(null));
    }
}