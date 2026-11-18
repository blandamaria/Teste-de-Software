package uc6;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RadarSustentabilidadeUnitTest {

    public double calcularPercentualRadar(double valorRealizado, double metaMensal) {
        if (metaMensal <= 0 || valorRealizado <= 0) {
            return 0.0;
        }
        double percentual = (valorRealizado / metaMensal) * 100.0;
        return Math.min(100.0, Math.round(percentual * 100.0) / 100.0);
    }

    @Test
    public void ct10_DeveRetornarZeroQuandoMetaOuRegistrosForemZero() {
        assertEquals(0.0, calcularPercentualRadar(50.0, 0.0), 0.001);
        assertEquals(0.0, calcularPercentualRadar(0.0, 100.0), 0.001);
        assertEquals(0.0, calcularPercentualRadar(0.0, 0.0), 0.001);
    }

    @Test
    public void deveCalcularPercentualCorretamenteComMetaValida() {
        assertEquals(75.0, calcularPercentualRadar(75.0, 100.0), 0.001);
    }
}