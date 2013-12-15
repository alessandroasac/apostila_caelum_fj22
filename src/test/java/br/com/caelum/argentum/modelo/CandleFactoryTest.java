package br.com.caelum.argentum.modelo;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

public class CandleFactoryTest {

	@Test
	public void sequenciaSimplesDeNegociacoes() {

		Calendar hoje = Calendar.getInstance();

		BigDecimal precoNegociacao1 = BigDecimal.valueOf(40.5);
		BigDecimal precoNegociacao2 = BigDecimal.valueOf(45.0);
		BigDecimal precoNegociacao3 = BigDecimal.valueOf(39.8);
		BigDecimal precoNegociacao4 = BigDecimal.valueOf(42.3);

		BigDecimal volumeEsperado = BigDecimal.valueOf(16760.0);

		Negociacao negociacao1 = new Negociacao(precoNegociacao1, 100, hoje);
		Negociacao negociacao2 = new Negociacao(precoNegociacao2, 100, hoje);
		Negociacao negociacao3 = new Negociacao(precoNegociacao3, 100, hoje);
		Negociacao negociacao4 = new Negociacao(precoNegociacao4, 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2,
				negociacao3, negociacao4);

		CandleFactory fabrica = new CandleFactory();
		Candle candle = fabrica.constroiCandleParaData(hoje, negociacoes);

		assertEquals(precoNegociacao1, candle.getAbertura());
		assertEquals(precoNegociacao4, candle.getFechamento());
		assertEquals(precoNegociacao3, candle.getMinimo());
		assertEquals(precoNegociacao2, candle.getMaximo());
		assertEquals(volumeEsperado, candle.getVolume());
	}

	@Test
	public void semNegociacoesGeraCandleComZeros() {

		Calendar hoje = Calendar.getInstance();

		List<Negociacao> negociacoes = Arrays.asList();

		CandleFactory fabrica = new CandleFactory();
		Candle candle = fabrica.constroiCandleParaData(hoje, negociacoes);

		assertEquals(BigDecimal.ZERO, candle.getVolume());
		assertEquals(BigDecimal.ZERO, candle.getAbertura());
		assertEquals(BigDecimal.ZERO, candle.getFechamento());
		assertEquals(BigDecimal.ZERO, candle.getMaximo());
		assertEquals(BigDecimal.ZERO, candle.getMinimo());
	}

	@Test
	public void apenasUmaNegociacaoGeraCandleComValoresIguais() {

		Calendar hoje = Calendar.getInstance();

		BigDecimal precoNegociacao1 = BigDecimal.valueOf(40.5);

		BigDecimal volumeEsperado = BigDecimal.valueOf(4050.0);

		Negociacao negociacao1 = new Negociacao(precoNegociacao1, 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1);

		CandleFactory fabrica = new CandleFactory();
		Candle candle = fabrica.constroiCandleParaData(hoje, negociacoes);

		assertEquals(precoNegociacao1, candle.getAbertura());
		assertEquals(precoNegociacao1, candle.getFechamento());
		assertEquals(precoNegociacao1, candle.getMinimo());
		assertEquals(precoNegociacao1, candle.getMaximo());
		assertEquals(volumeEsperado, candle.getVolume());
	}

	@Test
	public void paraNegociacoesDeTresDiasDistintosGeraTresCandles() {

		Calendar hoje = Calendar.getInstance();

		BigDecimal precoNegociacao1 = BigDecimal.valueOf(40.5);
		BigDecimal precoNegociacao2 = BigDecimal.valueOf(45.0);
		BigDecimal precoNegociacao3 = BigDecimal.valueOf(39.8);
		BigDecimal precoNegociacao4 = BigDecimal.valueOf(42.3);
		BigDecimal precoNegociacao5 = BigDecimal.valueOf(48.8);
		BigDecimal precoNegociacao6 = BigDecimal.valueOf(49.3);
		BigDecimal precoNegociacao7 = BigDecimal.valueOf(51.8);
		BigDecimal precoNegociacao8 = BigDecimal.valueOf(52.3);

		Negociacao negociacao1 = new Negociacao(precoNegociacao1, 100, hoje);
		Negociacao negociacao2 = new Negociacao(precoNegociacao2, 100, hoje);
		Negociacao negociacao3 = new Negociacao(precoNegociacao3, 100, hoje);
		Negociacao negociacao4 = new Negociacao(precoNegociacao4, 100, hoje);

		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 1);

		Negociacao negociacao5 = new Negociacao(precoNegociacao5, 100, amanha);
		Negociacao negociacao6 = new Negociacao(precoNegociacao6, 100, amanha);

		Calendar depois = (Calendar) amanha.clone();
		depois.add(Calendar.DAY_OF_MONTH, 1);

		Negociacao negociacao7 = new Negociacao(precoNegociacao7, 100, depois);
		Negociacao negociacao8 = new Negociacao(precoNegociacao8, 100, depois);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2,
				negociacao3, negociacao4, negociacao5, negociacao6,
				negociacao7, negociacao8);

		CandleFactory fabrica = new CandleFactory();

		List<Candle> candles = fabrica.constroiCandles(negociacoes);

		assertEquals(3, candles.size());
		assertEquals(precoNegociacao1, candles.get(0).getAbertura());
		assertEquals(precoNegociacao4, candles.get(0).getFechamento());
		assertEquals(precoNegociacao5, candles.get(1).getAbertura());
		assertEquals(precoNegociacao6, candles.get(1).getFechamento());
		assertEquals(precoNegociacao7, candles.get(2).getAbertura());
		assertEquals(precoNegociacao8, candles.get(2).getFechamento());
	}

	public void permiteConstruirCandlesComNegociacoesForaDeOrdem() {

		Calendar hoje = Calendar.getInstance();

		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 1);

		Calendar depois = (Calendar) amanha.clone();
		depois.add(Calendar.DAY_OF_MONTH, 1);

		BigDecimal precoNegociacao1 = BigDecimal.valueOf(40.5);
		BigDecimal precoNegociacao2 = BigDecimal.valueOf(45.0);
		BigDecimal precoNegociacao3 = BigDecimal.valueOf(39.8);
		BigDecimal precoNegociacao4 = BigDecimal.valueOf(42.3);
		BigDecimal precoNegociacao5 = BigDecimal.valueOf(48.8);
		BigDecimal precoNegociacao6 = BigDecimal.valueOf(49.3);
		BigDecimal precoNegociacao7 = BigDecimal.valueOf(51.8);
		BigDecimal precoNegociacao8 = BigDecimal.valueOf(52.3);

		Negociacao negociacao1 = new Negociacao(precoNegociacao1, 100, depois);
		Negociacao negociacao2 = new Negociacao(precoNegociacao2, 100, depois);
		Negociacao negociacao3 = new Negociacao(precoNegociacao3, 100, depois);
		Negociacao negociacao4 = new Negociacao(precoNegociacao4, 100, depois);

		Negociacao negociacao5 = new Negociacao(precoNegociacao5, 100, amanha);
		Negociacao negociacao6 = new Negociacao(precoNegociacao6, 100, amanha);

		Negociacao negociacao7 = new Negociacao(precoNegociacao7, 100, hoje);
		Negociacao negociacao8 = new Negociacao(precoNegociacao8, 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2,
				negociacao3, negociacao4, negociacao5, negociacao6,
				negociacao7, negociacao8);

		CandleFactory fabrica = new CandleFactory();

		List<Candle> candles = fabrica.constroiCandles(negociacoes);

		assertEquals(3, candles.size());
		assertEquals(precoNegociacao1, candles.get(0).getAbertura());
		assertEquals(precoNegociacao4, candles.get(0).getFechamento());
		assertEquals(precoNegociacao5, candles.get(1).getAbertura());
		assertEquals(precoNegociacao6, candles.get(1).getFechamento());
		assertEquals(precoNegociacao7, candles.get(2).getAbertura());
		assertEquals(precoNegociacao8, candles.get(2).getFechamento());
	}
}