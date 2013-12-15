package br.com.caelum.argentum.indicadores;

import java.lang.reflect.Constructor;

public class IndicadorFactory {

	private static final String PACKAGE = "br.com.caelum.argentum.indicadores.";
	private String nomeIndicador;
	private String nomeMedia;
	private Integer tamanhoIntervaloDias;

	public IndicadorFactory(String nomeIndicador, String nomeMedia,
			Integer tamanhoIntervaloDias) {
		this.nomeIndicador = nomeIndicador;
		this.nomeMedia = nomeMedia;
		this.tamanhoIntervaloDias = tamanhoIntervaloDias;
	}

	public Indicador getIndicador() {

		try {
			Indicador indicador = (Indicador) Class.forName(
					PACKAGE + this.nomeIndicador).newInstance();

			if (this.nomeMedia != null && !this.nomeMedia.isEmpty()) {
				Constructor<?> constructor = Class.forName(
						PACKAGE + this.nomeMedia).getConstructor(Integer.class,
						Indicador.class);

				indicador = (Indicador) constructor.newInstance(
						tamanhoIntervaloDias, indicador);
			}

			return indicador;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
