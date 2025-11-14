package Mappers;

import DTOs.UserDTOs.ClienteDTO;
import Model.Cliente;

public class UsersMapper {
    public ClienteDTO clienteToDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO(cliente);
        return clienteDTO;
    }
}
