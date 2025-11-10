package DTOs;

import Model.Cliente;

public class ClienteDTO extends UserDTO{
    private double rendaMensal;

    public ClienteDTO(Cliente cliente) {
        super(cliente);
        this.rendaMensal = cliente.getRendaMensal();
    }

    @Override
    public String toString(){
        String textoFormatado = super.toString();

        textoFormatado += String.format("\nRenda mensal: %.2f", rendaMensal);

        return textoFormatado;
    }
}
