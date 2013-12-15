package br.com.caelum.argentum.indicadores;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelSimples implements Indicador {

	private int tamanhoIntervaloDias;
	private Indicador outroIndicador;

	public MediaMovelSimples(Integer tamanhoIntervaloDias,
			Indicador outroIndicador) {
		this.tamanhoIntervaloDias = tamanhoIntervaloDias;
		this.outroIndicador = outroIndicador;
	}

	@Override
	public BigDecimal calcula(int posicao, SerieTemporal serie) {
		BigDecimal soma = BigDecimal.ZERO;
		for (int i = posicao - 2; i <= posicao; i++) {
			soma = soma.add(outroIndicador.calcula(i, serie));
		}
		return soma.divide(BigDecimal.valueOf((long) tamanhoIntervaloDias),
				RoundingMode.HALF_UP);
	}

	@Override
	public String toString() {
		return "MMS-" + outroIndicador.toString();
	}
}