package codigo01;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

public class TesteContatoJpa {

	public static void main(String[] args) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			
			// cria um contato e ajusta seus valores
			Contato contato = new Contato();

			Scanner scan = new Scanner(System.in);
			System.out.print("\nDigite o nome:");
			contato.setNome(scan.nextLine());
			System.out.print("\nDigite o endereço:");
			contato.setEndereco(scan.nextLine());
			System.out.print("\nDigite o telefone:");
			contato.setTelefone(scan.nextLine());
			
			// adiciona um contato no banco
			tx.begin();
			em.persist(contato);
			tx.commit();
			
			// listar os contatos
			Query query = em.createQuery("select e from Contato e");
			List<Contato> listaContatos = query.getResultList();
			for (Contato c : listaContatos) {
				System.out.printf("%20s %20s %20s", c.getNome(), c.getEndereco(), c.getTelefone());
				System.out.println();
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getCause());
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		
	}
}
