package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.util.Calendar;

public final class Negociacao {

	private final BigDecimal preco;
	private final int quantidade;
	private final Calendar data;

	public Negociacao(BigDecimal preco, int quantidade, Calendar data) {

		if (data == null) {
			throw new IllegalArgumentException("data nao pode ser nula");
		}

		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Calendar getData() {
		return (Calendar) data.clone();
	}

	public BigDecimal getVolume() {
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	@Override
	public String toString() {
		return String.format(
				"[Preço %s, Quantidade %d, Data %3$td/%3$tm/%3$tY]", preco,
				quantidade, data);
	}

	public boolean isMesmoDia(Calendar outraData) {
		return data.get(Calendar.DAY_OF_MONTH) == outraData
				.get(Calendar.DAY_OF_MONTH)
				&& data.get(Calendar.MONTH) == outraData.get(Calendar.MONTH)
				&& data.get(Calendar.YEAR) == outraData.get(Calendar.YEAR);
	}
}