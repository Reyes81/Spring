
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repositories.UserRepository;


@Service
@Transactional
public class UserService {
	
	@Autowired UserRepository us;
	
	public Optional<User> findByUsernameAndPassword(String username, String password) {
		return this.us.findByUsernameAndPassword(username, password);
	}
	
	public Optional<User> findByUsername(String username) {
		return this.us.findByUsername(username);
	}
	
	public Optional<User> findById(String id){
		Optional<User> user = this.us.findById(id);
		if(user.isEmpty()) return Optional.empty();
		user.get().setPassword(null);
		return user;
	}
	
	public List<User> findAll() {
		List<User> users = (List<User>) this.us.findAll();
		return users.stream().map(u -> {u.setPassword(null); return u;}).collect(Collectors.toList());
	}
	
	public User create(User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return this.us.save(user);
	}
	
	public void delete(String id) {
		this.us.deleteById(id);
	}
	
	public void deleteAll() {
		this.us.deleteAll();
	}
	
}

