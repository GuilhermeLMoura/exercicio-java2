package school.sptech.treino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.treino.entity.Personagem;

import java.time.LocalDate;
import java.util.List;

public interface PersonagemRepository extends JpaRepository<Personagem, Integer> {

    List<Personagem> findByCodinomeContainsIgnoreCase(String nome);

    @Query("SELECT p FROM Personagem p WHERE p.dataNascimento > :data")
    List<Personagem> buscaEntreDatas(LocalDate data);

    @Query("SELECT p FROM Personagem p ORDER BY poder DESC LIMIT 3")
    List<Personagem> top3PersonagensMaisForte();

    @Query("SELECT poder FROM Personagem p ORDER BY poder LIMIT 1")
    Double personagensMenosForte();
}
