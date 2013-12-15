package br.com.caelum.argentum.indicadores;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelPonderada implements Indicador {

	private int tamanhoIntervaloDias;
	private Indicador outroIndicador;

	public MediaMovelPonderada(Integer tamanhoIntervaloDias,
			Indicador outroIndicador) {
		this.tamanhoIntervaloDias = tamanhoIntervaloDias;
		this.outroIndicador = outroIndicador;
	}

	@Override
	public BigDecimal calcula(int posicao, SerieTemporal serie) {
		BigDecimal soma = BigDecimal.ZERO;
		int peso = 1;

		for (int i = posicao - 2; i <= posicao; i++) {
			soma = soma.add(outroIndicador.calcula(i, serie).multiply(
					BigDecimal.valueOf(peso)));
			peso++;
		}
		return soma.divide(BigDecimal.valueOf(tamanhoIntervaloDias),
				RoundingMode.HALF_UP);
	}

	@Override
	public String toString() {
		return "MMP-" + outroIndicador.toString();
	};
}