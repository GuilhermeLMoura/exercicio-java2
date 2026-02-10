package school.sptech.treino.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class PersonagemConsultaDto {

    private Integer id;
    private String nome;
    private String codinome;
    private String habilidade;
    private Double poder;
    private LocalDate dataNascimento;


    public String getClassificacao () {
        if (poder >= 10.0 && poder < 50.0) {
            return "Fraco";
        }
        if (poder >= 50.0 && poder < 100.0) {
            return "Mediano";
        }
        return "Super Poderoso";
    }

    public int getIdadeAproximada () {
        LocalDate dataAtual = LocalDate.now();
        return dataAtual.getYear() - dataNascimento.getYear();
    }

}
