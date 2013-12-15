package br.com.caelum.argentum.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import br.com.caelum.argentum.modelo.Negociacao;

public class LeitorXMLTest {

	@Test
	public void carregaXmlComUmaNegociacaoEmListaUnitaria() {

		String xmlDeTeste = "<list>                         "
				+ "  <negociacao>                           "
				+ "    <preco>43.5</preco>                  "
				+ "    <quantidade>1000</quantidade>        "
				+ "    <data>                               "
				+ "      <time>1322233344455</time>         "
				+ "    </data>                              "
				+ "  </negociacao>                          "
				+ "</list>                                  ";

		LeitorXML leitor = new LeitorXML();

		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());

		List<Negociacao> negociacoes = leitor.carrega(xml);

		assertEquals(1, negociacoes.size());

		Negociacao negociacao = negociacoes.get(0);

		assertEquals(BigDecimal.valueOf(43.5), negociacao.getPreco());
		assertEquals(1000, negociacao.getQuantidade());
	}

	@Test
	public void carregaXmlComZeroNegociacoesEmListaVazia() {

		String xmlDeTeste = "<list>                         "
				+ "</list>                                  ";

		LeitorXML leitor = new LeitorXML();

		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());

		List<Negociacao> negociacoes = leitor.carrega(xml);

		assertTrue(negociacoes.isEmpty());
	}

	@Test
	public void carregaXmlComUmaNegociacaoInvalidaEmListaUnitaria() {

		String xmlDeTeste = "<list>                         "
				+ "  <negociacao>                           "
				// + "    <preco>43.5</preco>                  "
				// + "    <quantidade>1000</quantidade>        "
				+ "    <data>                               "
				+ "      <time>1322233344455</time>         "
				+ "    </data>                              "
				+ "  </negociacao>                          "
				+ "</list>                                  ";

		LeitorXML leitor = new LeitorXML();

		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());

		List<Negociacao> negociacoes = leitor.carrega(xml);

		assertEquals(1, negociacoes.size());

		Negociacao negociacao = negociacoes.get(0);

		assertEquals(null, negociacao.getPreco());
		assertEquals(0, negociacao.getQuantidade());
	}

	@Test
	public void carregaXmlComVariasNegociacoesEmLista() {

		String xmlDeTeste = "<list>                         "
				+ "  <negociacao>                           "
				+ "    <preco>43.5</preco>                  "
				+ "    <quantidade>1000</quantidade>        "
				+ "    <data>                               "
				+ "      <time>1322233344455</time>         "
				+ "    </data>                              "
				+ "  </negociacao>                          "
				+ "  <negociacao>                           "
				+ "    <preco>659.5</preco>                 "
				+ "    <quantidade>4060</quantidade>        "
				+ "    <data>                               "
				+ "      <time>1322233346655</time>         "
				+ "    </data>                              "
				+ "  </negociacao>                          "
				+ "  <negociacao>                           "
				+ "    <preco>784.9</preco>                 "
				+ "    <quantidade>3094</quantidade>        "
				+ "    <data>                               "
				+ "      <time>1322234374655</time>         "
				+ "    </data>                              "
				+ "  </negociacao>                          "
				+ "</list>                                  ";

		LeitorXML leitor = new LeitorXML();

		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());

		List<Negociacao> negociacoes = leitor.carrega(xml);

		assertEquals(3, negociacoes.size());

		Negociacao negociacao1 = negociacoes.get(0);

		assertEquals(BigDecimal.valueOf(43.5), negociacao1.getPreco());
		assertEquals(1000, negociacao1.getQuantidade());

		Negociacao negociacao2 = negociacoes.get(1);

		assertEquals(BigDecimal.valueOf(659.5), negociacao2.getPreco());
		assertEquals(4060, negociacao2.getQuantidade());

		Negociacao negociacao3 = negociacoes.get(2);

		assertEquals(BigDecimal.valueOf(784.9), negociacao3.getPreco());
		assertEquals(3094, negociacao3.getQuantidade());
	}
}