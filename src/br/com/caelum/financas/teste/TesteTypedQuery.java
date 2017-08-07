package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteTypedQuery {

	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
		Conta conta = new Conta();
		conta.setId(2);

		// Query da média
		TypedQuery<Double> query = em.createNamedQuery("MediaPorDiaETipo", Double.class);
		query.setParameter("pConta", conta);

		List<Double> medias = query.getResultList();

		for (Double media : medias) {
			System.out.println("A média é: " + media);
		}

		em.getTransaction().commit();

		em.close();
	}

}
