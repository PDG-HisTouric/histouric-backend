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
		Role tourismManagerRole = Role.builder()
				.id(UUID.fromString("12952e84-63c3-461d-91bd-72a09d584919"))
				.name("TOURISM_MANAGER")
				.description("In charge of managing the routes")
				.build();

		Role adminRole = Role.builder()
				.id(UUID.fromString("a10ca735-d50f-4685-943b-7b621f0e544d"))
				.name("ADMIN")
				.description("In charge of managing the users")
				.build();

		Role researcher = Role.builder()
				.id(UUID.fromString("1dd9a1e1-2531-49ae-b53e-a3b9e778b3cd"))
				.name("RESEARCHER")
				.description("In charge of managing the routes")
				.build();

		ArrayList<Role> adminUserRoles = new ArrayList<>();
		HistouricUser adminUser = HistouricUser.builder()
				.id(UUID.fromString("a7f042d7-618d-4723-be88-b799314063e7"))
				.nickname("Juan Esteban")
				.email("admin@gmail.com")
				.password(encoder.encode("password"))
				.roles(adminUserRoles)
				.build();

		HistouricUser adminUser1 = HistouricUser.builder()
				.id(UUID.fromString("b7f042d7-618d-4723-be88-b799314063e7"))
				.nickname("Juan Camilo")
				.email("admin1@gmail.com")
				.password(encoder.encode("password1"))
				.roles(adminUserRoles)
				.build();

		ArrayList<Role> tourismManagerUserRoles = new ArrayList<>();
		HistouricUser tourismManagerUser = HistouricUser.builder()
				.id(UUID.fromString("d052bbc3-4cf8-4add-977a-aee0734e353b"))
				.nickname("Juan Pablo")
				.email("tourism_manager@gmail.com")
				.password(encoder.encode("password"))
				.roles(tourismManagerUserRoles)
				.build();

		ArrayList<Role> researcherUserRoles = new ArrayList<>();
		HistouricUser reasearcherUser = HistouricUser.builder()
				.id(UUID.fromString("20ffef18-7d49-40f2-97f1-98bc6cb199a2"))
				.nickname("Andres Felipe")
				.email("researcher@gmail.com")
				.password(encoder.encode("password"))
				.roles(researcherUserRoles)
				.build();

		return args -> {
			Role tourismManagerRoleCreated = roleRepository.save(tourismManagerRole);
			tourismManagerUserRoles.add(tourismManagerRoleCreated);
			userRepository.save(tourismManagerUser);

			Role adminRoleCreated = roleRepository.save(adminRole);
			adminUserRoles.add(adminRoleCreated);
			userRepository.save(adminUser);
			userRepository.save(adminUser1);

			Role researcherRoleCreated = roleRepository.save(researcher);
			researcherUserRoles.add(researcherRoleCreated);
			userRepository.save(reasearcherUser);
		};
	}

}
