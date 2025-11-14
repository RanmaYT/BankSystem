package DTOs.UserDTOs;

import Model.NivelUsuarioEnum;
import Model.UsuarioAbstrato;

public class UserDTO {
    private String nome;
    private String email;
    private String cpf;
    private NivelUsuarioEnum nivelUsuario;

    public UserDTO(UsuarioAbstrato user){
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
        this.nivelUsuario = user.getTipo();
    }

    @Override
    public String toString(){
        return String.format("Nome: %s\n" +
                "Email: %s\n" +
                "CPF: %s\n" +
                "Tipo de usuário: %s",
                nome, email, cpf, nivelUsuario.toString());
    }
}
