package queries;

import entities.Motorista;
import entities.Tipo_Passageiro;

import javax.persistence.*;
import java.time.LocalDate;

public class TestQueries{
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("LocadoraVeiculos-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Insertion inserts = new Insertion(entityManager);
        Deletion deletions = new Deletion(entityManager);
        ClientQueries clientQueries = new ClientQueries(entityManager);

        deletions.clearAllTables();
        inserts.populateTables();
        clientQueries.sampleQuery();

        // Testes

        //INSERT INTO Tipo_veiculo
        // (cod_tipo, horas_limpeza, horas_revisao, tamanho, num_lugares, num_portas,
        // ar_condicionado, radio, mp3, cd, dir_hidr, cambio_auto) VALUES
        //('M2', 5, 10, 'M', 5, 4, 'S', 'N', 'N', 'N', 'S', 'S');

        Tipo_Passageiro tipo_passageiro = new Tipo_Passageiro();
        tipo_passageiro.setCod_tipo("M2");
        tipo_passageiro.setHoras_limpeza(5);
        tipo_passageiro.setHoras_revisao(10);
        tipo_passageiro.setTamanho("M");
        tipo_passageiro.setNum_lugares(5);
        tipo_passageiro.setNum_portas(4);
        tipo_passageiro.setAr_condicionado(true);
        tipo_passageiro.setRadio(false);
        tipo_passageiro.setMp3(false);
        tipo_passageiro.setCd(false);
        tipo_passageiro.setDir_hidr(true);
        tipo_passageiro.setCambio_auto(true);

        inserts.insertEntity(tipo_passageiro);

        // FALHANDO POR ERRO DE CONSTRAINT
/*        Motorista m = new Motorista();
        m.setCod_motorista(1);
        m.setNum_habili(1234567890L);
        m.setVencimento_habili(LocalDate.of(2020,10,10));
        m.setIdent_motorista(1234567980L);

        inserts.insertMotoristaForPessoaFisicaUsingCpfAndName(m, "Fulano de Tal", 123142L);*/

        entityManager.close();
        entityManagerFactory.close();
    }
}
