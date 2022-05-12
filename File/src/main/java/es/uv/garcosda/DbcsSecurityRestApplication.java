package es.uv.garcosda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import es.uv.garcosda.domain.User;
import es.uv.garcosda.services.ImportService;
import es.uv.garcosda.services.UserService;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"es.uv.garcosda.repositories"})
public class DbcsSecurityRestApplication implements ApplicationRunner{

	@Autowired
	UserService us;
	
	@Autowired
	ImportService is;
	
	public static void main(String[] args) {
		SpringApplication.run(DbcsSecurityRestApplication.class, args);
	}
	
	@Override
	public void run(ApplicationArguments args) throws JsonMappingException, JsonProcessingException {
		
	    Resource resource = new ClassPathResource("data.txt");
        is.doImport(resource);
                
        us.deleteAll();
        User u = us.create(new User("admin", "1234", new String[] {"ROLE_USER", "ROLE_ADMIN"}));
        System.out.println(u);
        u = us.create(new User("user", "1234", new String[] {"ROLE_USER"}));
        System.out.println(u);
	}

}
