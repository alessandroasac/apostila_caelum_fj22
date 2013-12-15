package br.com.caelum.argentum.indicadores;

import java.math.BigDecimal;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class IndicadorAbertura implements Indicador {

	@Override
	public BigDecimal calcula(int posicao, SerieTemporal serie) {
		return serie.getCandle(posicao).getAbertura();
	}

	@Override
	public String toString() {
		return "Abertura";
	}
}
