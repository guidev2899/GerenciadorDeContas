package com.example.contasapagar.contasapagar.utils;
import com.example.contasapagar.contasapagar.Dtos.EnderecoDto.EnderecoRequestDto;
import com.example.contasapagar.contasapagar.Dtos.UserDto.UserRegisterDto;
import com.example.contasapagar.contasapagar.entities.EnderecoEntity;
import com.example.contasapagar.contasapagar.entities.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class UtilsService {

    public static UserEntity fromUserEntity(UserRegisterDto dto, BCryptPasswordEncoder bCryptPasswordEncoder) {
        UserEntity user = new UserEntity();
        user.setNome(dto.nome());
        user.setEmail(dto.email());
        user.setPassword(bCryptPasswordEncoder.encode(dto.password()));
        return user;
    }

    public static EnderecoEntity fromEnderecoEntity(EnderecoRequestDto dto){
        EnderecoEntity endereco = new EnderecoEntity();
        endereco.setEstado(dto.estado());
        endereco.setCidade(dto.cidade());
        endereco.setRua(dto.rua());
        endereco.setNumero(dto.numero());
        endereco.setCep(dto.cep());
        return endereco;
    }

}
