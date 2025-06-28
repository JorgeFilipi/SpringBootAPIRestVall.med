package med.voll.api.domain.medico;

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

    boolean existsByIdAndAtivoTrue(Long idMedico);

    Page<Medico> findAllByAtivoFalse(Pageable paginacao);

    @Query("""
    SELECT m FROM medicos m 
    WHERE m.ativo = true
    AND m.especialidade = :especialidade
    AND m.id NOT IN (
        SELECT c.medico.id FROM Consulta c WHERE c.dataHora = :dataHora
    )
""")
    List<Medico> findDisponiveis(@Param("especialidade") Especialidade especialidade,
                                 @Param("dataHora") LocalDateTime dataHora);

}
