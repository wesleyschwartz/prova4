package com.compass.repository;

import com.compass.model.Associado;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static com.compass.util.AssociadoCreator.criarAssociadoParaSalvar;


@DataJpaTest
class AssociadoRepositoryTest {
    @Autowired
    private AssociadoRepository associadoRepository;

    @Test
    @DisplayName("Salvar associado no BD")
    void saveAssociadoQuandoSucesso() {
        Associado associadoToSave = criarAssociadoParaSalvar();
        Associado associadoSalved = this.associadoRepository.save(associadoToSave);

        Assertions.assertThat(associadoSalved).isNotNull();
        Assertions.assertThat(associadoSalved.getId()).isNotNull();
        Assertions.assertThat(associadoSalved.getNome()).isEqualTo(associadoToSave.getNome());
    }

    @Test
    @DisplayName("Atualizar associado no BD")
    void updateAssociadoQuandoSucesso() {
        Associado associadoToUpdate = criarAssociadoParaSalvar();
        associadoToUpdate.setNome("Testador");
        Associado associadoUpdated = this.associadoRepository.save(associadoToUpdate);

        Assertions.assertThat(associadoUpdated).isNotNull();
        Assertions.assertThat(associadoUpdated.getId()).isNotNull();
        Assertions.assertThat(associadoUpdated.getNome()).isEqualTo(associadoUpdated.getNome());
    }

    @Test
    @DisplayName("Deletar associado do BD")
    void deleteAssociadoQuandoSucesso() {
        Associado associadoToDelete = criarAssociadoParaSalvar();
        Associado associadoDeleted = this.associadoRepository.save(associadoToDelete);

        this.associadoRepository.delete(associadoToDelete);
        Optional<Associado> associadoOptional = this.associadoRepository.findById(associadoToDelete.getId());

        Assertions.assertThat(associadoOptional.isEmpty());
    }

    @Test
    @DisplayName("Encontrar associados por cargo no BD")
    void retornaListaDeCargosQuandoSucesso() {
        Associado associadoToFindByCargo = criarAssociadoParaSalvar();
        Associado associadoFound = this.associadoRepository.save(associadoToFindByCargo);

        List<Associado> byCargo = this.associadoRepository.findByCargo(associadoFound.getCargo());

        Assertions.assertThat(byCargo).isNotEmpty();
        Assertions.assertThat(byCargo).contains(associadoToFindByCargo);
    }

    @Test
    @DisplayName("Não salvar quando nome do associado é vazio ou nulo")
    void naoSaveAssociadoQuandoNomeVazio() {
        Associado associadoToSave = criarAssociadoParaSalvar();
        associadoToSave.setNome("");

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy(() -> this.associadoRepository.save(associadoToSave));

    }
    @Test
    @DisplayName("Associa no partido")
    void associaNoPartido() {
        Associado associadoToSave = criarAssociadoParaSalvar();

        associadoToSave.setNome("");

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy(() -> this.associadoRepository.save(associadoToSave));

    }




}