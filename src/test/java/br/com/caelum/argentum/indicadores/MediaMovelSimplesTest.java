package br.com.caelum.argentum.indicadores;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelSimplesTest {

	@Test
	public void sequenciaSimplesDeCandlesComIndicadorFechamento() throws Exception {
		SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 3, 4, 5, 4,
				3);
		Indicador mms = new MediaMovelSimples(3, new IndicadorFechamento());

		assertEquals(valueOf(2.0), mms.calcula(2, serie));
		assertEquals(valueOf(3.0), mms.calcula(3, serie));
		assertEquals(valueOf(10.0).divide(valueOf(3), HALF_UP),
				mms.calcula(4, serie));
		assertEquals(valueOf(11.0).divide(valueOf(3), HALF_UP),
				mms.calcula(5, serie));
		assertEquals(valueOf(4.0), mms.calcula(6, serie));
		assertEquals(valueOf(13.0).divide(valueOf(3), HALF_UP),
				mms.calcula(7, serie));
		assertEquals(valueOf(4.0), mms.calcula(8, serie));
	}
	
	@Test
	public void sequenciaSimplesDeCandlesComIndicadorAbertura() throws Exception {
		SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 3, 4, 5, 4,
				3);
		Indicador mms = new MediaMovelSimples(3, new IndicadorAbertura());

		assertEquals(valueOf(2.0), mms.calcula(2, serie));
		assertEquals(valueOf(3.0), mms.calcula(3, serie));
		assertEquals(valueOf(10.0).divide(valueOf(3), HALF_UP),
				mms.calcula(4, serie));
		assertEquals(valueOf(11.0).divide(valueOf(3), HALF_UP),
				mms.calcula(5, serie));
		assertEquals(valueOf(4.0), mms.calcula(6, serie));
		assertEquals(valueOf(13.0).divide(valueOf(3), HALF_UP),
				mms.calcula(7, serie));
		assertEquals(valueOf(4.0), mms.calcula(8, serie));
	}
}