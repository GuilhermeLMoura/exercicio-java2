package school.sptech.treino.dto;


import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PersonagemCriacaoDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String codinome;

    @NotBlank
    @Size(min = 5, max = 30)
    private String habilidade;

    @NotNull
    @DecimalMin(value = "10.0")
    private Double poder;

    @NotNull
    @PastOrPresent
    private LocalDate dataNascimento;
}
