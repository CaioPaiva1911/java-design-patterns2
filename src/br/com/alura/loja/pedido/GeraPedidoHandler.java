package br.com.alura.loja.pedido;

import br.com.alura.loja.orcamento.ItemOrcamento;
import br.com.alura.loja.orcamento.Orcamento;
import br.com.alura.loja.pedido.acoes.AcaoAposGerarPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class GeraPedidoHandler {

	private final List<AcaoAposGerarPedido> acoesAposGerarPedido;
	
	// injecao de dependencias para servicos de infra
	public GeraPedidoHandler(List<AcaoAposGerarPedido> acoesAposGerarPedidos) {
		acoesAposGerarPedido = acoesAposGerarPedidos;
	}

	public void executar(GeraPedido geraPedido) {
		Orcamento orcamento = new Orcamento();
		orcamento.adicionarItem(new ItemOrcamento(new BigDecimal("500")));
		Pedido pedido = new Pedido(geraPedido.getCliente(), LocalDateTime.now(), orcamento);

		this.acoesAposGerarPedido.forEach(a -> a.executarAcao(pedido));
	}
	
}
