package br.com.caelum.argentum.modelo;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import br.com.caelum.argentum.comparator.NegociacaoDataComparator;

public class CandleFactory {

	public Candle constroiCandleParaData(Calendar data,
			List<Negociacao> negociacoes) {

		BigDecimal maximo = negociacoes.isEmpty() ? ZERO
				: valueOf(Double.MIN_VALUE);
		BigDecimal minimo = negociacoes.isEmpty() ? ZERO
				: valueOf(Double.MAX_VALUE);
		BigDecimal volume = ZERO;

		for (Negociacao negociacao : negociacoes) {
			volume = volume.add(negociacao.getVolume());

			BigDecimal preco = negociacao.getPreco();
			if (preco.compareTo(maximo) > 0) {
				maximo = preco;
			}

			if (preco.compareTo(minimo) < 0) {
				minimo = preco;
			}
		}

		BigDecimal abertura = negociacoes.isEmpty() ? ZERO : negociacoes.get(0)
				.getPreco();
		BigDecimal fechamento = negociacoes.isEmpty() ? ZERO : negociacoes.get(
				negociacoes.size() - 1).getPreco();

		return new Candle(abertura, fechamento, minimo, maximo, volume, data);
	}

	public List<Candle> constroiCandles(List<Negociacao> todasNegociacoes) {

		Collections.sort(todasNegociacoes, new NegociacaoDataComparator());

		List<Candle> candles = new ArrayList<Candle>();

		List<Negociacao> negociacoesDoDia = new ArrayList<Negociacao>();
		Calendar dataAtual = todasNegociacoes.get(0).getData();

		for (Negociacao negociacao : todasNegociacoes) {
			if (negociacao.getData().before(dataAtual)) {
				throw new IllegalStateException("negociações em ordem errada");
			}
			// se não for mesmo dia, fecha candle e reinicia variáveis
			if (!negociacao.isMesmoDia(dataAtual)) {
				criaEGuardaCandle(candles, negociacoesDoDia, dataAtual);
				negociacoesDoDia = new ArrayList<Negociacao>();
				dataAtual = negociacao.getData();
			}
			negociacoesDoDia.add(negociacao);
		}
		criaEGuardaCandle(candles, negociacoesDoDia, dataAtual);

		return candles;
	}

	private void criaEGuardaCandle(List<Candle> candles,
			List<Negociacao> negociacoesDoDia, Calendar dataAtual) {
		Candle candleDoDia = constroiCandleParaData(dataAtual, negociacoesDoDia);
		candles.add(candleDoDia);
	}
}