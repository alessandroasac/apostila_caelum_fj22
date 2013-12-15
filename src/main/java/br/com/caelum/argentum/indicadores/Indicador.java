package br.com.caelum.argentum.indicadores;

import java.math.BigDecimal;

import br.com.caelum.argentum.modelo.SerieTemporal;

public interface Indicador {

	public abstract BigDecimal calcula(int posicao, SerieTemporal serie);

}