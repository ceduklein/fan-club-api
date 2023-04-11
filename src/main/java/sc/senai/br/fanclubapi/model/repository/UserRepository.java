package sc.senai.br.fanclubapi.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sc.senai.br.fanclubapi.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
		
	boolean existsByEmail(String email);
	
	boolean existsByCpf(String cpf);
	
	@Query("from User u where u.id != :id and u.email = :email")
	Optional<User> findByEmailWhere(@Param("id") Long id, @Param("email") String email);
}
