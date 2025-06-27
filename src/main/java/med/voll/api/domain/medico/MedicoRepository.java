package med.voll.api.domain.medico;

import io.micrometer.observation.ObservationFilter;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
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


//    boolean existsByIdAndAtivoTrue(Long id);

    boolean existsByIdAndAtivoTrue(Long idMedico);


    @Query(value = """
                SELECT m FROM Medico m
                WHERE m.especialidade = :especialidade
                AND m.ativo = true
                AND m.id NOT IN (
                    SELECT c.medico.id FROM Consulta c
                    WHERE c.dataHora = :data
                )
                ORDER BY FUNCTION('RAND')
                LIMIT 1
            """, nativeQuery = true)
    Medico escolherMedicoAleatorioLivreNaData(@Param("especialidade") Especialidade especialidade, @Param("dataHora") LocalDateTime data);

    Page<Medico> findAllByAtivoFalse(Pageable paginacao);



}
