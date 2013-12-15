package br.com.caelum.argentum.modelo;

import java.util.List;

public class SerieTemporal {

	private final List<Candle> candles;

	public SerieTemporal(List<Candle> candles) {

		if (candles == null) {
			throw new IllegalArgumentException("candles não pode ser nulo");
		}

		this.candles = candles;
	}

	public Candle getCandle(int i) {
		if (i > getTotal() - 1) {
			throw new IllegalArgumentException(
					"candle buscada deve estar dentro da quantidade total");
		}
		return this.candles.get(i);
	}

	public int getTotal() {
		return this.candles.size();
	}
}