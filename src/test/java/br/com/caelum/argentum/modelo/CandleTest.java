package br.com.caelum.argentum.modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class CandleTest {

	private BigDecimal abertura;
	private BigDecimal fechamento;
	private BigDecimal minimo;
	private BigDecimal maximo;
	private BigDecimal volume;
	private Calendar hoje;

	@Before
	public void setUp() {
		abertura = BigDecimal.TEN;
		fechamento = BigDecimal.valueOf(20);
		minimo = BigDecimal.TEN;
		maximo = BigDecimal.valueOf(20);
		volume = BigDecimal.valueOf(1000);
		hoje = Calendar.getInstance();
	}

	@Test(expected = IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMenorQueMinimo() {

		minimo = maximo.multiply(BigDecimal.TEN);

		new Candle(abertura, fechamento, minimo, maximo, volume, hoje);
	}

	@Test(expected = IllegalArgumentException.class)
	public void dataNaoPodeSerNula() {

		hoje = null;

		new Candle(abertura, fechamento, minimo, maximo, volume, hoje);
	}

	@Test(expected = IllegalArgumentException.class)
	public void aberturaNaoPodeSerNula() {

		abertura = null;

		new Candle(abertura, fechamento, minimo, maximo, volume, hoje);
	}

	@Test(expected = IllegalArgumentException.class)
	public void fechamentoNaoPodeSerNulo() {

		fechamento = null;

		new Candle(abertura, fechamento, minimo, maximo, volume, hoje);
	}

	@Test(expected = IllegalArgumentException.class)
	public void minimoNaoPodeSerNulo() {

		minimo = null;

		new Candle(abertura, fechamento, minimo, maximo, volume, hoje);
	}

	@Test(expected = IllegalArgumentException.class)
	public void maximoNaoPodeSerNulo() {

		maximo = null;

		new Candle(abertura, fechamento, minimo, maximo, volume, hoje);
	}

	@Test(expected = IllegalArgumentException.class)
	public void volumeNaoPodeSerNulo() {

		volume = null;

		new Candle(abertura, fechamento, minimo, maximo, volume, hoje);
	}

	@Test(expected = IllegalArgumentException.class)
	public void aberturaNaoPodeSerNegativa() {

		abertura = abertura.multiply(BigDecimal.valueOf(-1));

		new Candle(abertura, fechamento, minimo, maximo, volume, hoje);
	}

	@Test(expected = IllegalArgumentException.class)
	public void fechamentoNaoPodeSerNegativo() {

		fechamento = fechamento.multiply(BigDecimal.valueOf(-1));

		new Candle(abertura, fechamento, minimo, maximo, volume, hoje);
	}

	@Test(expected = IllegalArgumentException.class)
	public void minimoNaoPodeSerNegativo() {

		minimo = minimo.multiply(BigDecimal.valueOf(-1));

		new Candle(abertura, fechamento, minimo, maximo, volume, hoje);
	}

	@Test(expected = IllegalArgumentException.class)
	public void maximoNaoPodeSerNegativo() {

		maximo = maximo.multiply(BigDecimal.valueOf(-1));

		new Candle(abertura, fechamento, minimo, maximo, volume, hoje);
	}

	@Test(expected = IllegalArgumentException.class)
	public void volumeNaoPodeSerNegativo() {

		volume = volume.multiply(BigDecimal.valueOf(-1));

		new Candle(abertura, fechamento, minimo, maximo, volume, hoje);
	}

	@Test
	public void quandoAberturaIgualFechamentoEhAlta() {

		abertura = fechamento;

		Candle candle = new Candle(abertura, fechamento, minimo,
				maximo, volume, hoje);

		assertTrue(candle.isAlta());

		assertFalse(candle.isBaixa());
	}
}