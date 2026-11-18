package validacao;

public class EcoCondoValidator {

    // 1. Cálculo de pontos de reciclagem (rejeita quantidade <= 0)
    public static int calcularPontosReciclagem(String tipoMaterial, double quantidadeKg) {
        if (quantidadeKg <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        if (tipoMaterial == null || tipoMaterial.trim().isEmpty()) {
            throw new IllegalArgumentException("O tipo de material é obrigatório.");
        }

        int pontosPorKg;
        switch (tipoMaterial.trim().toLowerCase()) {
            case "plastico":
                pontosPorKg = 10;
                break;
            case "papel":
                pontosPorKg = 5;
                break;
            case "vidro":
                pontosPorKg = 8;
                break;
            case "metal":
                pontosPorKg = 15;
                break;
            default:
                pontosPorKg = 3; // outros recicláveis
        }

        return (int) Math.round(quantidadeKg * pontosPorKg);
    }

    // 2. Validação completa de CNPJ (formato, tamanho, dígitos repetidos e dígitos verificadores)
    public static boolean validarCNPJ(String cnpj) {
        if (cnpj == null) {
            return false;
        }

        // Remove pontos, barra e traço
        String numeros = cnpj.replaceAll("[^0-9]", "");

        // Verifica se tem 14 dígitos ou se todos são iguais (ex: 00000000000000)
        if (numeros.length() != 14 || numeros.matches("(\\d)\\1{13}")) {
            return false;
        }

        try {
            // Cálculo do 1º Dígito Verificador
            int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int soma = 0;
            for (int i = 0; i < 12; i++) {
                soma += Character.getNumericValue(numeros.charAt(i)) * pesos1[i];
            }
            int resto = soma % 11;
            int digito1 = (resto < 2) ? 0 : (11 - resto);

            if (digito1 != Character.getNumericValue(numeros.charAt(12))) {
                return false;
            }

            // Cálculo do 2º Dígito Verificador
            int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            soma = 0;
            for (int i = 0; i < 13; i++) {
                soma += Character.getNumericValue(numeros.charAt(i)) * pesos2[i];
            }
            resto = soma % 11;
            int digito2 = (resto < 2) ? 0 : (11 - resto);

            return digito2 == Character.getNumericValue(numeros.charAt(13));
        } catch (Exception e) {
            return false;
        }
    }
}