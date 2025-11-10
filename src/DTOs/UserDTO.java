package DTOs;

import Model.NivelUsuario;
import Model.Usuario;

public class UserDTO {
    private String nome;
    private String email;
    private String cpf;
    private NivelUsuario cargo;

    public UserDTO(Usuario user){
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
        this.cargo = user.getCargo();
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
