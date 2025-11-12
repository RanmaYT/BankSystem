package Model;

public class Cliente extends Usuario {
    private double rendaMensal;

    public Cliente(String nome, String senha, String email, String cpf, double rendaMensal) {
        super(nome, senha, email, cpf, NivelUsuario.Cliente);
        this.rendaMensal = rendaMensal;
    }

    @Override
    public String converterParaStringArmazenavel() {
        String textoArmazenavel = String.format("rendaMensal=%.2f}", rendaMensal);
        String textoPai = super.converterParaStringArmazenavel();

        textoArmazenavel = textoPai.replace("}", ";") + textoArmazenavel.replace(",", ".");

        return textoArmazenavel;
    }

    public double getRendaMensal(){
        return  rendaMensal;
    }
}
