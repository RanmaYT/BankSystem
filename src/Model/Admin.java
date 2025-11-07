package Model;

public class Admin extends Usuario{
    private String matricula;

    public Admin (String nome, String senha, String email, String cpf, String matricula){
        super(nome, senha, email, cpf, NivelUsuario.Admin);
        this.matricula = matricula;
    }

    @Override
    public String converterParaStringArmazenavel() {
        return "";
    }
}
