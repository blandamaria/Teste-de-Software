package validacao;

import java.util.Collections;
import java.util.List;

public class FinanceiroRankingValidator {

    // 1. Cálculo de saldo de pontos (pontos acumulados menos pontos resgatados)
    public static int calcularSaldoPontos(int pontosGanhos, int pontosResgatados) {
        if (pontosGanhos < 0 || pontosResgatados < 0) {
            throw new IllegalArgumentException("Os pontos não podem ser negativos.");
        }
        if (pontosResgatados > pontosGanhos) {
            throw new IllegalArgumentException("Saldo insuficiente para resgate.");
        }
        return pontosGanhos - pontosResgatados;
    }

    // Cálculo da posição no ranking (1º, 2º, 3º...) com base na lista de pontuações dos moradores
    public static int calcularPosicaoRanking(int pontosMorador, List<Integer> todasPontuacoes) {
        if (todasPontuacoes == null || todasPontuacoes.isEmpty() || !todasPontuacoes.contains(pontosMorador)) {
            throw new IllegalArgumentException("Lista de pontuações inválida ou morador não encontrado.");
        }

        // Ordena do maior para o menor sem alterar a lista original
        List<Integer> ordenada = todasPontuacoes.stream()
                .sorted(Collections.reverseOrder())
                .toList();

        return ordenada.indexOf(pontosMorador) + 1;
    }

    // 2. Bloqueio de conta de água/energia duplicada no mesmo mês/ano para o mesmo condomínio
    public static boolean permitirCadastroContaMensal(String cnpjCondominio, String mesReferencia, List<String> contasJaRegistradas) {
        if (cnpjCondominio == null || cnpjCondominio.trim().isEmpty() ||
            mesReferencia == null || mesReferencia.trim().isEmpty()) {
            return false;
        }

        String chaveNovaConta = cnpjCondominio.trim() + "-" + mesReferencia.trim();

        if (contasJaRegistradas != null && contasJaRegistradas.contains(chaveNovaConta)) {
            return false; // Bloqueia porque já existe conta registrada para esse condomínio nesse mês
        }

        return true;
    }
}