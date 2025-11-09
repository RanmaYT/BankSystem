package Mappers;

import DTOs.ExtratoBancarioDTO;
import DTOs.OperacaoExtratavelDTO;
import Model.ExtratoBancario;
import Model.OperacaoExtratavel;

public class ExtratoMapper {
    public ExtratoBancarioDTO converterEmDTO(ExtratoBancario extratoBancario){
        ExtratoBancarioDTO extratoDTO = new ExtratoBancarioDTO();

        for(OperacaoExtratavel operacaoExtratavel : extratoBancario.getOperacoesRealizadas()) {
            extratoDTO.adicionarOperacao(new OperacaoExtratavelDTO(operacaoExtratavel.getNomeOperacao(), operacaoExtratavel.getValorDaOperacao()));
        }

        return extratoDTO;
    }
}
