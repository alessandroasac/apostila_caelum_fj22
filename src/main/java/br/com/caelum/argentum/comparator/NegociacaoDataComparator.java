package br.com.caelum.argentum.comparator;

import java.util.Comparator;

import br.com.caelum.argentum.modelo.Negociacao;

public class NegociacaoDataComparator implements Comparator<Negociacao> {

	@Override
	public int compare(Negociacao o1, Negociacao o2) {
		return o1.getData().compareTo(o2.getData());
	}
}