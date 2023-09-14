package br.com.iteris.universidade.DesafioListaTarefas.repository;

import br.com.iteris.universidade.DesafioListaTarefas.domain.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM tarefa"
    )
    List<Tarefa> listarTodos();
}
