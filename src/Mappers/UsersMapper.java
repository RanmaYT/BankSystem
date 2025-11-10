package Mappers;

import DTOs.ClienteDTO;
import Model.Cliente;

public class UsersMapper {
    public ClienteDTO clienteToDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO(cliente);
        return clienteDTO;
    }
}
