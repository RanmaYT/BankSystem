package Model.Services;

import Model.UsuarioAbstrato;
import SingletonRepositories.UserRepository;

public class UserValidationService {
    public static boolean usuarioValido(UsuarioAbstrato usuario, UserRepository userRepository){
        String cpf = String.format("\"cpf\":\"%s\"", usuario.getCpf());
        String email = String.format("\"email\":\"%s\"", usuario.getEmail());
        return !userRepository.itemJaExiste(cpf) && !userRepository.itemJaExiste(email);
    }
}
