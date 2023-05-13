package sc.senai.br.fanclubapi.dto;

import lombok.Data;

@Data
public class PasswordDTO {

	private String oldPassword;
	private String password;
	private String passwordConfirmation;
}
