package school.sptech.treino.dto;

import school.sptech.treino.entity.Personagem;

import java.util.List;

public class PersonagemMapper {

    public static Personagem toEntity(PersonagemCriacaoDto personagemCriacaoDto) {
        if (personagemCriacaoDto == null) {
            return null;
        }

        Personagem personagem = new Personagem();

        personagem.setNome(personagemCriacaoDto.getNome());
        personagem.setCodinome(personagemCriacaoDto.getCodinome());
        personagem.setHabilidade(personagemCriacaoDto.getHabilidade());
        personagem.setPoder(personagemCriacaoDto.getPoder());
        personagem.setDataNascimento(personagemCriacaoDto.getDataNascimento());

        return personagem;
    }

    public static PersonagemConsultaDto toDto(Personagem personagem) {
        if (personagem == null) {
            return null;
        }

        PersonagemConsultaDto listagemDto = new PersonagemConsultaDto();
        listagemDto.setId(personagem.getId());
        listagemDto.setNome(personagem.getNome());
        listagemDto.setCodinome(personagem.getCodinome());
        listagemDto.setHabilidade(personagem.getHabilidade());
        listagemDto.setPoder(personagem.getPoder());
        listagemDto.setDataNascimento(personagem.getDataNascimento());

        return listagemDto;
    }

    public static List<PersonagemConsultaDto> toDto(List<Personagem> personagens) {
        return personagens.stream().map(PersonagemMapper::toDto).toList();
    }
}
