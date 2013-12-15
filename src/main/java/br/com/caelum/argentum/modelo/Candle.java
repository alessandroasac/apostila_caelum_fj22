package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.util.Calendar;

public final class Candle {

	private final BigDecimal abertura;
	private final BigDecimal fechamento;
	private final BigDecimal minimo;
	private final BigDecimal maximo;
	private final BigDecimal volume;
	private final Calendar data;

	public Candle(BigDecimal abertura, BigDecimal fechamento,
			BigDecimal minimo, BigDecimal maximo, BigDecimal volume,
			Calendar data) {

		if (data == null) {
			throw new IllegalArgumentException("data nao pode ser nula");
		}

		if (abertura == null) {
			throw new IllegalArgumentException("abertura nao pode ser nula");
		}

		if (fechamento == null) {
			throw new IllegalArgumentException("fechamento nao pode ser nulo");
		}

		if (minimo == null) {
			throw new IllegalArgumentException("minimo nao pode ser nulo");
		}

		if (maximo == null) {
			throw new IllegalArgumentException("maximo nao pode ser nulo");
		}

		if (volume == null) {
			throw new IllegalArgumentException("volume nao pode ser nulo");
		}

		if (BigDecimal.ZERO.compareTo(abertura) > 0) {
			throw new IllegalArgumentException("abertura nao pode ser negativa");
		}

		if (BigDecimal.ZERO.compareTo(fechamento) > 0) {
			throw new IllegalArgumentException(
					"fechamento nao pode ser negativo");
		}

		if (BigDecimal.ZERO.compareTo(minimo) > 0) {
			throw new IllegalArgumentException("minimo nao pode ser negativo");
		}

		if (BigDecimal.ZERO.compareTo(maximo) > 0) {
			throw new IllegalArgumentException("maximo nao pode ser negativo");
		}

		if (BigDecimal.ZERO.compareTo(volume) > 0) {
			throw new IllegalArgumentException("volume nao pode ser negativo");
		}

		if (minimo.compareTo(maximo) > 0) {
			throw new IllegalArgumentException(
					"maximo nao pode ser menor que minimo");
		}

		this.abertura = abertura;
		this.fechamento = fechamento;
		this.minimo = minimo;
		this.maximo = maximo;
		this.volume = volume;
		this.data = data;
	}

	public BigDecimal getAbertura() {
		return abertura;
	}

	public BigDecimal getFechamento() {
		return fechamento;
	}

	public BigDecimal getMinimo() {
		return minimo;
	}

	public BigDecimal getMaximo() {
		return maximo;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public Calendar getData() {
		return data;
	}

	public boolean isAlta() {
		return !isBaixa();
	}

	public boolean isBaixa() {
		return abertura.compareTo(fechamento) > 0;
	}

	@Override
	public String toString() {
		return String
				.format("[Abertura %s, Fechamento %s, Mínima %s, Máxima %s, Volume %s, Data %6$td/%6$tm/%6$tY]",
						abertura, fechamento, minimo, maximo, volume, data);
	}
}