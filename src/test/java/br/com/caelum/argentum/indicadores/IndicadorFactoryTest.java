package br.com.caelum.argentum.indicadores;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IndicadorFactoryTest {

	@Test
	public void testMontaAbertura() {
		String nomeIndicador = "IndicadorAbertura";

		IndicadorFactory indicadorFactory = new IndicadorFactory(nomeIndicador,
				null, null);

		Indicador indicador = indicadorFactory.getIndicador();

		assertTrue(indicador instanceof IndicadorAbertura);
	}

	@Test
	public void testMontaAberturaComMediaMovelSimples() {
		String nomeIndicador = "IndicadorAbertura";

		String nomeMedia = "MediaMovelSimples";

		Integer tamanhoIntervaloDias = 3;

		IndicadorFactory indicadorFactory = new IndicadorFactory(nomeIndicador,
				nomeMedia, tamanhoIntervaloDias);

		Indicador indicador = indicadorFactory.getIndicador();

		assertTrue(indicador instanceof MediaMovelSimples);
	}
}
