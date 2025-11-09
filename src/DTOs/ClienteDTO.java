package DTOs;

import Model.Cliente;

public class ClienteDTO extends UserDTO{
    private double rendaMensal;

    public ClienteDTO(Cliente cliente) {
        super(cliente);
        this.rendaMensal = cliente.getRendaMensal();
    }
}
