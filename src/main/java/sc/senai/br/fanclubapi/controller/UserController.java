package sc.senai.br.fanclubapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sc.senai.br.fanclubapi.dto.AdminDTO;
import sc.senai.br.fanclubapi.dto.UserDTO;
import sc.senai.br.fanclubapi.model.entity.User;
import sc.senai.br.fanclubapi.service.UserService;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {
	
	@Autowired
	private UserService service;

	@PostMapping("/cadastro")
	public ResponseEntity<?> salvar(@RequestBody UserDTO dto) {
		try {
			User user = service.salvar(dto);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> autenticar(@RequestBody UserDTO dto) {
		try {
			User user = service.autenticar(dto.getEmail(), dto.getPassword());
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping()
	public ResponseEntity<?> listar(@RequestParam Long admin_id) {
		try {
			List<User> users = service.listar(admin_id);
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable("id") Long id) {
		try {
			User user = service.buscarPorId(id);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PatchMapping("/permissoes/{user_id}")
	public ResponseEntity<?> setAdmin(@PathVariable("user_id") Long user_id, @RequestBody AdminDTO dto) {
		try {
			service.setAdmin(user_id, dto.getAdmin_id());
			return ResponseEntity.ok("Operação realizada com sucesso.");
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> atualizar(@PathVariable("id") Long id, @RequestBody UserDTO dto) {
		try {
			User user = service.atualizar(id, dto);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PatchMapping("/emissao/{user_id}") 
	public ResponseEntity<?> emitir(@PathVariable("user_id") Long user_id, @RequestBody AdminDTO dto) {
		try {
			User user = service.emitirCarteira(user_id, dto.getAdmin_id());
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PatchMapping("/envio/{user_id}") 
	public ResponseEntity<?> enviar(@PathVariable("user_id") Long user_id, @RequestBody AdminDTO dto) {
		try {
			User user = service.enviarCarteira(user_id, dto.getAdmin_id());
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
}
