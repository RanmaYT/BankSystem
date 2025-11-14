package DTOs.UserDTOs;

import Model.NivelUsuarioEnum;
import Model.UsuarioAbstrato;

public class UserDTO {
    private String nome;
    private String email;
    private String cpf;
    private NivelUsuarioEnum cargo;

    public UserDTO(UsuarioAbstrato user){
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
        this.cargo = user.getTipo();
    }

    @Override
    public String toString(){
        return String.format("Nome: %s\n" +
                "Email: %s\n" +
                "CPF: %s\n" +
                "Cargo: %s",
                nome, email, cpf, cargo.toString());
    }
}
