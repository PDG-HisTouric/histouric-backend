package com.pdg.histouric;

import com.pdg.histouric.model.HistouricUser;
import com.pdg.histouric.model.Role;
import com.pdg.histouric.repository.RoleRepository;
import com.pdg.histouric.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.UUID;

@SpringBootApplication
public class HistouricApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistouricApplication.class, args);
	}

	@Bean
	@Profile("!test")
	CommandLineRunner commandLineRunner(RoleRepository roleRepository,
										UserRepository userRepository,
										PasswordEncoder encoder) {
		Role gestorRole = Role.builder()
				.id(UUID.fromString("12952e84-63c3-461d-91bd-72a09d584919"))
				.name("GESTOR")
				.description("Encargado de administrar las rutas")
				.build();

		ArrayList<Role> userRoles = new ArrayList<>();
		HistouricUser histouricUser = HistouricUser.builder()
				.id(UUID.fromString("a7f042d7-618d-4723-be88-b799314063e7"))
				.username("Pepito")
				.email("pepito@gmail.com")
				.password(encoder.encode("password"))
				.roles(userRoles)
				.build();

		return args -> {
			Role roleCreated = roleRepository.save(gestorRole);
			userRoles.add(roleCreated);
			userRepository.save(histouricUser);
		};
	}

}
