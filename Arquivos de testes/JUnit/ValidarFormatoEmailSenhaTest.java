package validacao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LoginValidatorTest {

    @Test
    @DisplayName("Deve aceitar e-mail em formato válido")
    void deveAceitarEmailValido() {
        assertTrue(LoginValidator.validarEmail("morador@ecocondo.com.br"));
        assertTrue(LoginValidator.validarEmail("sindico.teste@gmail.com"));
    }

    @Test
    @DisplayName("Deve rejeitar e-mail inválido, vazio ou nulo")
    void deveRejeitarEmailInvalido() {
        assertFalse(LoginValidator.validarEmail("moradorecocondo.com"));
        assertFalse(LoginValidator.validarEmail("morador@"));
        assertFalse(LoginValidator.validarEmail("@ecocondo.com"));
        assertFalse(LoginValidator.validarEmail(""));
        assertFalse(LoginValidator.validarEmail(null));
    }

    @Test
    @DisplayName("Deve aceitar senha com 6 ou mais caracteres")
    void deveAceitarSenhaValida() {
        assertTrue(LoginValidator.validarSenha("123456"));
        assertTrue(LoginValidator.validarSenha("Senha@2026"));
    }

    @Test
    @DisplayName("Deve rejeitar senha curta, vazia ou nula")
    void deveRejeitarSenhaInvalida() {
        assertFalse(LoginValidator.validarSenha("12345"));
        assertFalse(LoginValidator.validarSenha(""));
        assertFalse(LoginValidator.validarSenha("   "));
        assertFalse(LoginValidator.validarSenha(null));
    }

    @Test
    @DisplayName("Deve validar login completo apenas quando e-mail e senha forem válidos")
    void deveValidarLoginCompleto() {
        assertTrue(LoginValidator.validarLogin("usuario@ecocondo.com", "senha123"));
        assertFalse(LoginValidator.validarLogin("usuario_invalido", "senha123"));
        assertFalse(LoginValidator.validarLogin("usuario@ecocondo.com", "123"));
    }
}