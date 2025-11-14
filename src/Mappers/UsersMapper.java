package Mappers;

import DTOs.UserDTOs.ClienteDTO;
import DTOs.UserDTOs.UserDTO;
import Model.Cliente;
import Model.UsuarioAbstrato;

public class UsersMapper {
    public UserDTO userToDTO(UsuarioAbstrato usuarioAbstrato) {
        // FERE O PRINCIPIO DO OPEN/CLOSED
        switch (usuarioAbstrato.getTipo().toString()) {
            case "Cliente":
                return new ClienteDTO((Cliente) usuarioAbstrato);
            case "Admin":
                return null;
        }

        return null;
    }
}
