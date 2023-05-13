package sc.senai.br.fanclubapi.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sc.senai.br.fanclubapi.dto.PasswordDTO;
import sc.senai.br.fanclubapi.dto.UserDTO;
import sc.senai.br.fanclubapi.exception.RulesException;
import sc.senai.br.fanclubapi.model.entity.User;
import sc.senai.br.fanclubapi.model.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	@Autowired
	private PasswordEncoder encoder;
	
	public User salvar(UserDTO dto) throws RulesException {
		validarNomeEEmail(dto.getNome(), dto.getEmail());
		validarCPF(dto.getCpf());
		validarSenha(dto.getPassword());
		
		if (repository.existsByEmail(dto.getEmail())) 
			throw new RulesException("Email já cadastrado.");
		
		User savedUser = new User();
		savedUser.setNome(dto.getNome());
		savedUser.setCpf(dto.getCpf());
		savedUser.setDataNascimento(dto.getDataNascimento());
		savedUser.setEmail(dto.getEmail());
		savedUser.setTelefone(dto.getTelefone());
		savedUser.setPassword(encoder.encode(dto.getPassword()));
		
		return repository.save(savedUser);
	}
	
	public User autenticar(String email, String password) throws RulesException {
		Optional<User> user = repository.findByEmail(email);
		if (!user.isPresent())
			throw new RulesException("Email ou senha incorretos.");

		if (encoder.matches(password, user.get().getPassword()))
			return user.get();
		
		throw new RulesException("Email ou senha incorretos.");
	}
	
	public List<User> listar(Long admin_id) throws RulesException {
		validarPermissao(admin_id);
		return repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	public User buscarPorId(Long id) throws RulesException {
		Optional<User> user = repository.findById(id);
		if (!user.isPresent())
			throw new RulesException("Usuário não encontrado.");
		
		return user.get();
	}
	
	public void setAdmin(Long user_id, Long admin_id) throws RulesException {
		validarPermissao(admin_id);
		
		User user = validarUsuario(user_id);
		user.setAdmin(!user.isAdmin());
		repository.save(user);
	}
	
	public User emitirCarteira(Long user_id, Long admin_id) throws RulesException {
		validarPermissao(admin_id);
		
		User user = validarUsuario(user_id);
		user.setCarteirinhaEmitida(!user.isCarteirinhaEmitida());
		return repository.save(user);
	}
	
	public User enviarCarteira(Long user_id, Long admin_id) throws RulesException {
		validarPermissao(admin_id);
		
		User user = validarUsuario(user_id);
		if (!user.isCarteirinhaEmitida())
			throw new RulesException("Carteirinha não foi emitida.");
			
		user.setCarteirinhaEnviada(!user.isCarteirinhaEnviada());
		return repository.save(user);
	}
	
	public User atualizar(Long id, UserDTO dto) throws RulesException {
		User user = validarUsuario(id);

		validarNomeEEmail(dto.getNome(), dto.getEmail());
		
		if (repository.findByEmailWhere(id, dto.getEmail()).isPresent())
			throw new RulesException("Email já cadastrado.");
		
		user.setNome(dto.getNome());
		user.setEmail(dto.getEmail());
		user.setTelefone(dto.getTelefone());
				
		return repository.save(user);
	}
	
	public void delete(Long id) throws RulesException {
		Optional<User> user = repository.findById(id);
		if (!user.isPresent()) {
			throw new RulesException("User not found.");
		}
		
		repository.delete(user.get());
	}
	
	public void changePassword(Long id, PasswordDTO dto) throws RulesException {
		Optional<User> user = repository.findById(id);
		System.out.println(dto);
		if (!user.isPresent()) {
			throw new RulesException("User not found.");
		}
		
		if (!encoder.matches(dto.getOldPassword(), user.get().getPassword())) {
			throw new RulesException("Old password is incorrect.");
		}
		
		if (!dto.getPassword().equals(dto.getPasswordConfirmation())) {
			throw new RulesException("The new password and its confirmation do not match.");
		}
			
		user.get().setPassword(encoder.encode(dto.getPassword()));
		repository.save(user.get());
	}
	
	private void validarNomeEEmail(String nome, String email) throws RulesException {
		if (nome == null || nome.length() < 4) 
			throw new RulesException("Nome inválido.");
		
		if (!validarEmail(email)) 
			throw new RulesException("Email inválido.");
	}
	
	private boolean validarEmail(String email) {
	    if (email != null && email.length() > 0) {
	        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(email);
	        if (matcher.matches()) {
	            return true;
	        }
	    }
	    return false;
	}
	
	private void validarCPF(String cpf) throws RulesException {
		if (cpf.length() != 11)
			throw new RulesException("CPF inválido.");
		
		if (repository.existsByCpf(cpf))
			throw new RulesException("CPF já cadastrado.");
	}
	
	private void validarSenha(String password) throws RulesException {
		if (password == null || password.length() < 4)
			throw new RulesException("Senha deve conter pelo menos 4 caracteres.");
	}
	
	private void validarPermissao(Long admin_id) throws RulesException {
		Optional<User> admin = repository.findById(admin_id);
		if (!admin.isPresent() || !admin.get().isAdmin())
			throw new RulesException("Operação não autorizada para este usuário.");
	}
	
	private User validarUsuario(Long user_id) throws RulesException {
		Optional<User> user = repository.findById(user_id);
		if (!user.isPresent())
			throw new RulesException("Usuário não encontrado.");
		
		return user.get();
	}
}
