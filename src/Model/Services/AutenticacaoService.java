package Model.Services;

import Exceptions.CredenciaisInvalidaException;
import Model.UsuarioAbstrato;
import SingletonRepositories.ContaRepository;
import SingletonRepositories.UserRepository;
import SingletonSession.SessionManager;

public class AutenticacaoService {
    public boolean verificarLogin(String cpf, String senha) {
        UsuarioAbstrato user = UserRepository.getInstance().pegarPorCpf(cpf);

        return senha.equals(user.getSenha());
    }

    public void logIn(String cpf, String senha){
        if(verificarLogin(cpf, senha)) {
            SessionManager.getInstance().setUsuarioLogado(UserRepository.getInstance().pegarPorCpf(cpf));
            SessionManager.getInstance().setContaAtiva(ContaRepository.getInstance().pegarPorTitular(cpf));
        }
        else { throw new CredenciaisInvalidaException("Credenciais inválidas"); }
    }
}
