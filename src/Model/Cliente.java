package Model;

public class Cliente extends Usuario {
    private double rendaMensal;

    public Cliente(String nome, String senha, String email, String cpf, double rendaMensal) {
        super(nome, senha, email, cpf, NivelUsuario.Cliente);
        this.rendaMensal = rendaMensal;
    }

    @Override
    public String converterParaStringArmazenavel() {
        return "";
    }
}
