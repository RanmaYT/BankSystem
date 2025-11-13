package Model;

public class Admin extends UsuarioAbstrato {
    private String matricula;

    public Admin (String nome, String senha, String email, String cpf, String matricula){
        super(nome, senha, email, cpf, NivelUsuarioEnum.Admin);
        this.matricula = matricula;
    }
}
