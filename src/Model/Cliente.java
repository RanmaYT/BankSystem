package Model;

public class Cliente extends UsuarioAbstrato {
    private double rendaMensal;

    public Cliente(String nome, String senha, String email, String cpf, double rendaMensal) {
        super(nome, senha, email, cpf, NivelUsuarioEnum.Cliente);
        this.rendaMensal = rendaMensal;
    }

    public double getRendaMensal(){
        return  rendaMensal;
    }
}
