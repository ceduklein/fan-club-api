package sc.senai.br.fanclubapi.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

private Long id;
	
	private String nome;
	private String email;
	private String password;
	private String cpf;
	private String telefone;
	private LocalDate dataNascimento;
	
}
