package med.voll.api.domain.medico;

import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    boolean existsByEmail(String email);

    boolean existsByCrm(String crm);

    @Query("""
    SELECT DISTINCT m FROM medicos m
    LEFT JOIN Agenda a ON a.medico = m AND a.dataHora = :dataHora
    WHERE m.ativo = true
      AND m.especialidade = :especialidade
      AND a.id IS NULL
""")
    List<Medico> findMedicosDisponiveis(@Param("dataHora") LocalDateTime dataHora,
                                        @Param("especialidade") Especialidade especialidade);


    Page<Medico> findAllByAtivoFalse(Pageable paginacao);
}
