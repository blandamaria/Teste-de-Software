package validacao;

import java.util.regex.Pattern;

public class LoginValidator {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    public static boolean validarSenha(String senha) {
        if (senha == null || senha.trim().isEmpty()) {
            return false;
        }
        return senha.length() >= 6;
    }

    public static boolean validarLogin(String email, String senha) {
        return validarEmail(email) && validarSenha(senha);
    }
}