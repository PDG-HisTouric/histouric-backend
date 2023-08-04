package com.pdg.histouric;

import com.pdg.histouric.model.CRUDPermission;
import com.pdg.histouric.model.HistouricUser;
import com.pdg.histouric.model.Role;
import com.pdg.histouric.repository.PermissionRepository;
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
	CommandLineRunner commandLineRunner(PermissionRepository permissionRepository,
										RoleRepository roleRepository,
										UserRepository userRepository,
										PasswordEncoder encoder) {
		CRUDPermission permission1 = CRUDPermission.builder()
				.id(UUID.fromString("f218a75c-c6af-4f1e-a2c6-b2b47f1a0678"))
				.name("CRU BIC")
				.create(false)
				.read(true)
				.update(false)
				.delete(false)
				.build();

		ArrayList<CRUDPermission> permissions = new ArrayList<>();
		permissions.add(permission1);
		Role role1 = Role.builder()
				.id(UUID.fromString("12952e84-63c3-461d-91bd-72a09d584919"))
				.name("Gestor de turismo cultural")
				.description("Encargado de administrar las rutas")
				.permissions(permissions)
				.build();

		ArrayList<Role> roles = new ArrayList<>();
		roles.add(role1);
		HistouricUser histouricUser = HistouricUser.builder()
				.id(UUID.fromString("a7f042d7-618d-4723-be88-b799314063e7"))
				.username("Pepito")
				.email("pepito@gmail.com")
				.password(encoder.encode("password"))
				.roles(roles)
				.build();

		return args -> {
			permissionRepository.save(permission1);
			roleRepository.save(role1);
			userRepository.save(histouricUser);
		};
	}

}
