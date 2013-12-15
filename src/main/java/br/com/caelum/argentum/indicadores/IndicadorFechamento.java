package br.com.caelum.argentum.indicadores;

import java.math.BigDecimal;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class IndicadorFechamento implements Indicador {

	@Override
	public BigDecimal calcula(int posicao, SerieTemporal serie) {
		return serie.getCandle(posicao).getFechamento();
	}

	@Override
	public String toString() {
		return "Fechamento";
	}
}
