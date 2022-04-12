package com.compass.repository;

import com.compass.model.Partido;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static com.compass.util.PartidoCreator.criarPartidoParaSalvar;


@DataJpaTest
class PartidoRepositoryTest {
    @Autowired
    private PartidoRepository partidoRepository;
    @Test
    @DisplayName("Salvar partido no BD")
    void savePartidoQuandoSucesso(){
        Partido partidoToSave = criarPartidoParaSalvar
();
        Partido PartidoSalved = this.partidoRepository.save(partidoToSave);

        Assertions.assertThat(PartidoSalved).isNotNull();
        Assertions.assertThat(PartidoSalved.getId()).isNotNull();
        Assertions.assertThat(PartidoSalved.getNomePartido()).isEqualTo(partidoToSave.getNomePartido());
    }
    @Test
    @DisplayName("Atualizar partido no BD")
    void updatePartidoQuandoSucesso(){
        Partido partidoToUpdate = criarPartidoParaSalvar
();
        partidoToUpdate.setNomePartido("Testador");
        Partido partidoUpdated = this.partidoRepository.save(partidoToUpdate);

        Assertions.assertThat(partidoUpdated).isNotNull();
        Assertions.assertThat(partidoUpdated.getId()).isNotNull();
        Assertions.assertThat(partidoUpdated.getNomePartido()).isEqualTo(partidoUpdated.getNomePartido());
    }

    @Test
    @DisplayName("Deletar partido do BD")
    void deletePartidoQuandoSucesso(){
        Partido partidoToDelete = criarPartidoParaSalvar
();
        Partido partidoDeleted = this.partidoRepository.save(partidoToDelete);

        this.partidoRepository.delete(partidoToDelete);
        Optional<Partido> partidoOptional =   this.partidoRepository.findById(partidoToDelete.getId());

        Assertions.assertThat(partidoOptional.isEmpty());
    }
    @Test
    @DisplayName("Encontrar partidos por ideologia no BD")
    void retornaListaDeCargosQuandoSucesso(){
        Partido partidoToFindByIdeologia = criarPartidoParaSalvar
();
        Partido partidoFound = this.partidoRepository.save(partidoToFindByIdeologia);
        List<Partido> byIdeologia = this.partidoRepository.findByIdeologia(partidoFound.getIdeologia());

        Assertions.assertThat(byIdeologia).isNotEmpty();
        Assertions.assertThat(byIdeologia).contains(partidoToFindByIdeologia);
    }

    @Test
    @DisplayName("Não salvar quando nome do partido é vazio")
    void naoSavePartidoQuandoNomeVazio(){
        Partido partidoToSave = criarPartidoParaSalvar();
        partidoToSave.setNomePartido("");
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(()->this.partidoRepository.save(partidoToSave));

    }

}