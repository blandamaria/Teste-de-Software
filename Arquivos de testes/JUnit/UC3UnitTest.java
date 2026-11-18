package ecocondo.testes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UC3UnitTest {

    
    static class ValidadorLoginMock {
        public static boolean isEmailValido(String email) {
            if (email == null || email.isEmpty()) return false;       
            return email.contains("@") && email.lastIndexOf(".") > email.indexOf("@");
        }
    }

    static class ValidadorCondominioMock {
        public static boolean isCnpjValido(String cnpj) {
            if (cnpj == null) return false;
            String cnpjNumeros = cnpj.replaceAll("[^0-9]", "");
            return cnpjNumeros.length() == 14;
        }
    }
   
    @Test
    @DisplayName("CT01: Verificar rejeição de e-mail em formato inválido")
    public void testValidacaoEmailInvalido() {
 
        String emailSemArroba = "sindicoecocondo.com";
        String emailSemDominio = "sindico@";
        String emailValido = "sindico@ecocondo.com"; 

        boolean resultadoSemArroba = ValidadorLoginMock.isEmailValido(emailSemArroba);
        boolean resultadoSemDominio = ValidadorLoginMock.isEmailValido(emailSemDominio);
        boolean resultadoValido = ValidadorLoginMock.isEmailValido(emailValido);

        assertFalse(resultadoSemArroba, "O sistema não deve aceitar e-mail sem '@'.");
        assertFalse(resultadoSemDominio, "O sistema não deve aceitar e-mail sem domínio válido.");
        assertTrue(resultadoValido, "O sistema deve aceitar um e-mail em formato correto.");
    }

    @Test
    @DisplayName("CT04: Verificar rejeição de CNPJ inválido ou menor que 14 dígitos")
    public void testValidacaoCnpjInvalido() {

        String cnpjCurto = "1234567800019"; 
        String cnpjLetras = "12.345.678/0001-XX";
        String cnpjValido = "12345678000199"; 

        boolean resultadoCurto = ValidadorCondominioMock.isCnpjValido(cnpjCurto);
        boolean resultadoLetras = ValidadorCondominioMock.isCnpjValido(cnpjLetras);
        boolean resultadoValido = ValidadorCondominioMock.isCnpjValido(cnpjValido);

        assertFalse(resultadoCurto, "O sistema deve rejeitar CNPJ com menos de 14 dígitos.");
        assertFalse(resultadoLetras, "O sistema deve rejeitar CNPJ contendo letras.");
        assertTrue(resultadoValido, "O sistema deve aceitar um CNPJ apenas com 14 números.");
    }
}