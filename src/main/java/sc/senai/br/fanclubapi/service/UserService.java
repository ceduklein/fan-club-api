package sc.senai.br.fanclubapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sc.senai.br.fanclubapi.exception.RulesException;
import sc.senai.br.fanclubapi.model.entity.User;
import sc.senai.br.fanclubapi.model.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public User save() throws RulesException {
		return null;
	}
}
