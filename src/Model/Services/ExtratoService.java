package Model.Services;

import DTOs.ExtratoBancarioDTO;
import Mappers.ExtratoMapper;
import Model.Conta;
import Model.ExtratoBancario;
import SingletonRepositories.ExtratoRepository;
import SingletonSession.SessionManager;

public class ExtratoService {
    private ExtratoMapper extratoMapper;
    private ExtratoRepository extratoRepository;
    private SessionManager sessionManager;

    public ExtratoService(ExtratoMapper mapper, ExtratoRepository extratoRepo, SessionManager sessionManager) {
        this.extratoMapper = mapper;
        this.extratoRepository = extratoRepo;
        this.sessionManager = sessionManager;
    }

    public ExtratoBancarioDTO pegarExtratoDoUsuarioLogado(){
        Conta conta = sessionManager.getContaAtiva();

        ExtratoBancario extrato = extratoRepository.pegarPorTitular(conta.getEmailTitular());

        return extratoMapper.converterEmDTO(extrato);
    }
}
