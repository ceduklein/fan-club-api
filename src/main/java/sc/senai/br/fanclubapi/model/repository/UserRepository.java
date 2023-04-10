package sc.senai.br.fanclubapi.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sc.senai.br.fanclubapi.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
}
