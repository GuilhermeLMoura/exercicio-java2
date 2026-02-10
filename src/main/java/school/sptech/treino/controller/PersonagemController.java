package school.sptech.treino.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.treino.dto.PersonagemConsultaDto;
import school.sptech.treino.dto.PersonagemCriacaoDto;
import school.sptech.treino.dto.PersonagemMapper;
import school.sptech.treino.entity.Personagem;
import school.sptech.treino.repository.PersonagemRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personagens")
@RequiredArgsConstructor
public class PersonagemController {


    private final PersonagemRepository personagemRepository;


    @GetMapping
    public ResponseEntity<List<PersonagemConsultaDto>> listagem() {
        List<Personagem> personagems =  personagemRepository.findAll();

        if (personagems.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<PersonagemConsultaDto> listaAux = PersonagemMapper.toDto(personagems);
        return ResponseEntity.ok(listaAux);
    }



    @GetMapping("/{id}")
    public ResponseEntity<PersonagemConsultaDto> buscaPorId(@PathVariable Integer id) {
        Optional<Personagem> personagemOpt = personagemRepository.findById(id);

        if (personagemOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PersonagemConsultaDto persoDto = PersonagemMapper.toDto(personagemOpt.get());

        return ResponseEntity.ok(persoDto);
    }

    @PostMapping
    public ResponseEntity<PersonagemConsultaDto> cria(@RequestBody @Valid PersonagemCriacaoDto personagemCriacaoDto) {

        Personagem personagem = PersonagemMapper.toEntity(personagemCriacaoDto);

        Personagem personagemSalvo = personagemRepository.save(personagem);

        PersonagemConsultaDto persoListDto = PersonagemMapper.toDto(personagemSalvo);

        return ResponseEntity.created(null).body(persoListDto);
    }

    @GetMapping("/codinome")
    public ResponseEntity<List<PersonagemConsultaDto>> buscarPorCodinomeAproximado(@RequestParam String termo) {
        List<Personagem> personagens = personagemRepository.findByCodinomeContainsIgnoreCase(termo);

        if (personagens.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<PersonagemConsultaDto> consultaDto = PersonagemMapper.toDto(personagens);

        return ResponseEntity.ok(consultaDto);
    }


    @GetMapping("/nascidos-apos")
    public ResponseEntity<List<PersonagemConsultaDto>> buscarNascidosApos(@RequestParam LocalDate data) {
        List<Personagem> personagens = personagemRepository.buscaEntreDatas(data);

        if (personagens.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<PersonagemConsultaDto> consultaDto = PersonagemMapper.toDto(personagens);

        return ResponseEntity.ok(consultaDto);
    }


    @GetMapping("/top-3")
    public ResponseEntity<List<PersonagemConsultaDto>> buscarTop3() {
        List<Personagem> personagens = personagemRepository.top3PersonagensMaisForte();

        if (personagens.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<PersonagemConsultaDto> consultaDto = PersonagemMapper.toDto(personagens);


        return ResponseEntity.ok(consultaDto);
    }

    @GetMapping("/menor-poder")
    public ResponseEntity<Double> buscarMenorPoder() {
        Double poder = personagemRepository.personagensMenosForte();

        if (poder == null) {
            poder = 0.0;
            return ResponseEntity.ok(poder);
        }

        return ResponseEntity.ok(poder);
    }
}
