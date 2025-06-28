package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/consulta")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendamentoDeConsulta agenda;

    @Autowired
    private ConsultaRepository consultaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = agenda.agendar(dados);
        System.out.println(dados);
        return ResponseEntity.ok(dto);
    }


    @GetMapping
    public ResponseEntity<Page<Consulta>> listar(@PageableDefault(size = 10) Pageable pageable) {
        var page = consultaRepository.findAll(pageable);
        return ResponseEntity.ok(page);
    }


    @DeleteMapping("/{dataHora}")
    @Transactional
    public ResponseEntity delete(@PathVariable LocalDateTime dataHora) {
        consultaRepository.deleteByDataHora(dataHora);
        return ResponseEntity.noContent().build();
    }
}
