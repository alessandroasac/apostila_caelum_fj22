package br.com.caelum.argentum.indicadores;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelPonderadaTest {

	@Test
	public void sequenciaSimplesDeCandles() {
		SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 5, 6);
		MediaMovelPonderada mmp = new MediaMovelPonderada(6, new IndicadorFechamento());

		// ex: calcula(2): 1*1 + 2*2 +3*3 = 14. Divide por 6, da 14/6
		assertEquals(valueOf(14.0).divide(valueOf(6), HALF_UP), mmp.calcula(2, serie));
		assertEquals(valueOf(20.0).divide(valueOf(6), HALF_UP), mmp.calcula(3, serie));
		assertEquals(valueOf(26.0).divide(valueOf(6), HALF_UP), mmp.calcula(4, serie));
		assertEquals(valueOf(32.0).divide(valueOf(6), HALF_UP), mmp.calcula(5, serie));
	}
}