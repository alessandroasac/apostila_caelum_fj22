package br.com.caelum.argentum.modelo;

import org.junit.Test;

import br.com.caelum.argentum.indicadores.GeradorDeSerie;

public class SerieTemporalTest {

	@Test(expected = IllegalArgumentException.class)
	public void candlesNaoPodeSerNulo() {
		new SerieTemporal(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void candleBuscadaDeveEstarDentroDaQuantidadeTotal() {

		SerieTemporal serie = GeradorDeSerie.criaSerie(150);

		serie.getCandle(1);
	}

}