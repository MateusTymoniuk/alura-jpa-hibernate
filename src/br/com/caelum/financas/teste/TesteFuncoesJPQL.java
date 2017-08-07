package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteFuncoesJPQL {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
		Conta conta = new Conta();
		conta.setId(2);

		// Query da soma
		String jpqlSoma = "select sum(m.valor) from Movimentacao m where m.conta = :pConta" + " and m.tipo = :pTipo";
		Query query = em.createQuery(jpqlSoma);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);

		// Query da média
		String jpqlMedia = "select avg(m.valor) from Movimentacao m where m.conta = :pConta" + " and m.tipo = :pTipo";
		Query query2 = em.createQuery(jpqlMedia);
		query2.setParameter("pConta", conta);
		query2.setParameter("pTipo", TipoMovimentacao.SAIDA);

		// Query do valor máx
		String jpqlMax = "select max(m.valor) from Movimentacao m where m.conta = :pConta";
		Query query3 = em.createQuery(jpqlMax);
		query3.setParameter("pConta", conta);

		// Query do valor máx
		String jpqlQtdeMov = "select count(m.valor) from Movimentacao m where m.conta = :pConta";
		Query query4 = em.createQuery(jpqlQtdeMov);
		query4.setParameter("pConta", conta);

		BigDecimal soma = (BigDecimal) query.getSingleResult();
		Double media = (Double) query2.getSingleResult();
		BigDecimal max = (BigDecimal) query3.getSingleResult();
		Long qtdeMov = (Long) query4.getSingleResult();

		System.out.println("A soma é: " + soma);
		System.out.println("A média é: " + media);
		System.out.println("O valor máximo é: " + max);
		System.out.println("A quantidade de movimentações desta conta é: " + qtdeMov);

		em.getTransaction().commit();

		em.close();
	}

}
