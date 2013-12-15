package br.com.caelum.argentum.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class NegociacaoTest {

	@Test
	public void dataDaNegociacaoEhImutavel() {

		// se criar um negocio no dia 15...
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 15);
		Negociacao n = new Negociacao(BigDecimal.TEN, 5, c);

		// ainda que eu tente mudar a data para 20...
		n.getData().set(Calendar.DAY_OF_MONTH, 20);

		// ele continua no dia 15.
		assertEquals(15, n.getData().get(Calendar.DAY_OF_MONTH));
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoCriaNegociacaoComDataNula() {
		new Negociacao(BigDecimal.TEN, 5, null);
	}

	@Test
	public void mesmoMilissegundoEhDoMesmoDia() {

		Calendar agora = Calendar.getInstance();

		Calendar mesmoMomento = (Calendar) agora.clone();

		Negociacao negociacao = new Negociacao(BigDecimal.valueOf(40.0), 100,
				agora);

		assertTrue(negociacao.isMesmoDia(mesmoMomento));
	}

	@Test
	public void comHorariosDiferentesEhNoMesmoDia() {

		// usando GregorianCalendar(ano, mes, dia, hora, minuto)
		Calendar manha = new GregorianCalendar(2011, 10, 20, 8, 30);
		Calendar tarde = new GregorianCalendar(2011, 10, 20, 15, 30);

		Negociacao negociacao = new Negociacao(BigDecimal.valueOf(40.0), 100,
				manha);

		assertTrue(negociacao.isMesmoDia(tarde));
	}

	@Test
	public void mesmoDiaMasMesesDiferentesNaoSaoDoMesmoDia() {

		// usando GregorianCalendar(ano, mes, dia, hora, minuto)
		Calendar setembro = new GregorianCalendar(2011, 10, 20, 8, 30);
		Calendar outubro = new GregorianCalendar(2011, 11, 20, 15, 30);

		Negociacao negociacao = new Negociacao(BigDecimal.valueOf(40.0), 100,
				setembro);

		assertFalse(negociacao.isMesmoDia(outubro));
	}

	@Test
	public void mesmoDiaEMesMasAnosDiferentesNaoSaoDoMesmoDia() {

		// usando GregorianCalendar(ano, mes, dia, hora, minuto)
		Calendar _2011 = new GregorianCalendar(2011, 10, 20, 8, 30);
		Calendar _2012 = new GregorianCalendar(2012, 10, 20, 15, 30);

		Negociacao negociacao = new Negociacao(BigDecimal.valueOf(40.0), 100,
				_2011);

		assertFalse(negociacao.isMesmoDia(_2012));
	}

}