package Model.Services;

import DTOs.ContaDTOs.ContaDTO;
import Factory.ContaFactory.ContaCorrenteFactory;
import Factory.ContaFactory.ContaPoupancaFactory;
import Factory.ContaFactory.IContaFactory;
import Mappers.ContaMapper;
import Model.ContaAbstrata;

import Model.ExtratoBancario;
import Model.UsuarioAbstrato;

import SingletonRepositories.ContaRepository;
import SingletonRepositories.ExtratoRepository;
import SingletonSession.SessionManager;
import State.ContaBloqueada;
import State.ContaNegativada;
import State.ContaPositiva;

import java.util.ArrayList;

public class ContaService {
    private SessionManager sessionManager;
    private ContaRepository contaRepo;
    private ContaMapper contaMapper;

    public ContaService(SessionManager sessionManager, ContaRepository contaRepo, ContaMapper contaMapper) {
        this.sessionManager = sessionManager;
        this.contaRepo = contaRepo;
        this.contaMapper = contaMapper;
    }

    public void criarConta(UsuarioAbstrato usuario, String tipoConta){
        // Cria a factory baseada no tipo da conta, gambiarra?
        IContaFactory contaFactory = switch (tipoConta) {
            case "Corrente" -> new ContaCorrenteFactory();
            case "Poupança" -> new ContaPoupancaFactory(); //
            default -> null;
        };

        // Retorna caso o tipo de conta seja inválido.
        if(contaFactory == null) {
            System.out.println("Tipo de conta inválido");
            return;
        }

        ContaAbstrata conta = contaFactory.criarConta(usuario);

        if(conta == null) {
            System.out.println("Falha ao criar conta");
            return;
        }

        contaRepo.salvar(conta);

        // Gambiarra: uso direto do extrato, sem injeção de dependência;
        ExtratoBancario extrato = new ExtratoBancario(new ArrayList<>(), usuario.getCpf());
        ExtratoRepository.getInstance().salvar(extrato);
    }

    public void cancelarConta(){
        ContaAbstrata conta = sessionManager.getContaAtiva();
        conta.deletarConta();
    }

    public void bloquearConta(UsuarioAbstrato cliente) {
        ContaAbstrata conta = contaRepo.pegarPorTitular(cliente.getCpf());

        if(!conta.getNomeEstado().equals("bloqueada")) {
            System.out.println("Essa conta já está bloqueada");
            return;
        }

         conta.mudarEstado(new ContaBloqueada());
    }

    public void desbloquearConta(UsuarioAbstrato cliente) {
        ContaAbstrata conta = contaRepo.pegarPorTitular(cliente.getCpf());

        if(!conta.getNomeEstado().equals("bloqueada")) {
            System.out.println("Essa conta não está bloqueada");
            return;
        }

        conta.mudarEstado(conta.getSaldo() >= 0 ? new ContaPositiva() : new ContaNegativada());
    }

    public ContaDTO pegarInfoConta(){
        ContaAbstrata conta = sessionManager.getContaAtiva();

        String tipoConta = conta.getTipoConta();

        return contaMapper.contaToDto(conta, tipoConta);
    }
}
