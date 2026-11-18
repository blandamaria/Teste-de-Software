package ecocondo.agua;

public class ContaAguaRegras {
    public static void validarConta(Double valor, Double litragem) {
        if (valor == null || litragem == null) {
            throw new IllegalArgumentException("Campos obrigatórios ausentes.");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor da conta deve ser maior que zero.");
        }
        if (litragem <= 0) {
            throw new IllegalArgumentException("A litragem deve ser maior que zero.");
        }
    }
    public static double variacaoPercentual(double atual, double anterior) {
        if (anterior <= 0) {
            throw new IllegalArgumentException("Mês anterior deve ser maior que zero.");
        }
        return ((atual - anterior) / anterior) * 100.0;
    }
}