package es.uv.garcosda.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import es.uv.garcosda.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findByUsername(String username);
	Optional<User> findByUsernameAndPassword(String username, String password);
}
