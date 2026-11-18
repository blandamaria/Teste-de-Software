package validacao;

public class SustentabilidadeValidator {

    // 1. Validação de desligamento de luzes (exige local válido, status válido e impede desligar luz já desligada)
    public static boolean validarDesligamentoLuz(String local, String statusAtual, String novoStatus) {
        if (local == null || local.trim().isEmpty()) {
            return false;
        }
        if (statusAtual == null || novoStatus == null) {
            return false;
        }

        String atual = statusAtual.trim().toUpperCase();
        String novo = novoStatus.trim().toUpperCase();

        // Só permite desligar se a luz estiver atualmente LIGADA
        return atual.equals("LIGADA") && novo.equals("DESLIGADA");
    }

    // 2. Regra de meta mensal (verifica se o consumo atual ficou dentro ou abaixo da meta estabelecida)
    public static boolean verificarMetaMensalAtingida(double consumoAtual, double metaMensal) {
        if (consumoAtual < 0 || metaMensal <= 0) {
            throw new IllegalArgumentException("Consumo não pode ser negativo e a meta deve ser maior que zero.");
        }
        return consumoAtual <= metaMensal;
    }

    // Calcula o percentual atingido em relação à meta mensal
    public static double calcularPercentualMeta(double consumoAtual, double metaMensal) {
        if (consumoAtual < 0 || metaMensal <= 0) {
            throw new IllegalArgumentException("Valores inválidos para cálculo da meta.");
        }
        return (consumoAtual / metaMensal) * 100.0;
    }
}