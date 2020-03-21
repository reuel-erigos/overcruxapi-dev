package br.com.crux.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.crux.to.ExtratoTO;
import br.com.crux.to.Movimentacoes;

@Component
public class ExtratoDao {

	public ExtratoDao() {
	}
	
	
	public ExtratoTO getExtrato() {
		ExtratoTO to = new ExtratoTO();
		
		to.setDataAtual(LocalDateTime.now());
		
		to.setNomeBanco("BANCO DE BRASÍLIA");
		to.setNumeroAgencia("54243");
		to.setNumeroBanco("070");
		to.setNumeroConta("765675476");
		
		to.setPeriodoExtrato("16/03/2020 à 18/03/2020");
		
		
		to.setSaldoAnterior(Double.valueOf(1000.00));
		to.setSaldoAtual(Double.valueOf(-2571.00));
		
		List<Movimentacoes> movimentacoes = new ArrayList<>();
		
		Movimentacoes mov01 = new Movimentacoes();
		mov01.setData(LocalDate.of(2020, 03, 16));
		mov01.setDescricao("Compra de cadeiras");
		mov01.setNumero("1010");
		mov01.setSaldo(Double.valueOf(946.00));
		mov01.setValorEntrada(null);
		mov01.setValorSaida(Double.valueOf(54.00));
		movimentacoes.add(mov01);
		
		Movimentacoes mov02 = new Movimentacoes();
		mov02.setData(LocalDate.of(2020, 03, 16));
		mov02.setDescricao("Credito programa");
		mov02.setNumero("25422");
		mov02.setSaldo(Double.valueOf(1596.00));
		mov02.setValorEntrada(Double.valueOf(650.00));
		mov02.setValorSaida(null);
		movimentacoes.add(mov02);

		
		Movimentacoes mov03 = new Movimentacoes();
		mov03.setData(LocalDate.of(2020, 03, 17));
		mov03.setDescricao("Alimentos");
		mov03.setNumero("4125");
		mov03.setSaldo(Double.valueOf(361.00));
		mov03.setValorEntrada(null);
		mov03.setValorSaida(Double.valueOf(1235.00));
		movimentacoes.add(mov03);
		
		
		Movimentacoes mov04 = new Movimentacoes();
		mov04.setData(LocalDate.of(2020, 03, 18));
		mov04.setDescricao("Pagamento");
		mov04.setNumero("55832");
		mov04.setSaldo(Double.valueOf(-71.00));
		mov04.setValorEntrada(null);
		mov04.setValorSaida(Double.valueOf(432.00));
		movimentacoes.add(mov04);		
		
		
		to.setMovimentacoes(movimentacoes);
		
		return to;
	}
	
}
