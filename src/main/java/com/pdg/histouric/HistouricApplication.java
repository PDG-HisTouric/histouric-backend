package com.pdg.histouric;

import com.pdg.histouric.model.BIC;
import com.pdg.histouric.model.HistouricUser;
import com.pdg.histouric.model.Nickname;
import com.pdg.histouric.model.Role;
import com.pdg.histouric.repository.BicRepository;
import com.pdg.histouric.repository.NicknameRepository;
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
										BicRepository bicRepository,
										NicknameRepository nicknameRepository,
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
				.email("admin1@gmail.com")
				.password(encoder.encode("password"))
				.roles(adminUserRoles)
				.build();

		HistouricUser adminUser1 = HistouricUser.builder()
				.id(UUID.fromString("b7f042d7-618d-4723-be88-b799314063e7"))
				.nickname("Juan Camilo")
				.email("admin2@gmail.com")
				.password(encoder.encode("password"))
				.roles(adminUserRoles)
				.build();

		ArrayList<Role> tourismManagerUserRoles = new ArrayList<>();
		HistouricUser tourismManagerUser = HistouricUser.builder()
				.id(UUID.fromString("d052bbc3-4cf8-4add-977a-aee0734e353b"))
				.nickname("Juan Pablo")
				.email("t_manager@gmail.com")
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

		BIC ermita = BIC.builder()
				.name("La Ermita")
				.latitude(3.454214005186966)
				.longitude(-76.53206008465833)
				.description("La iglesia La Ermita es un templo católico ubicada en Santiago de Cali, Colombia. Originalmente fue una construcción pajiza de comienzos del siglo XVII, establecida en las cercanías del río Cali y dedicada a Nuestra Señora de la Soledad y al Señor de la Caña.")
				.existss(true)
				.build();
		Nickname nicknameErmita = Nickname.builder()
				.nickname("Ermitita")
				.build();

		BIC antiguoMatadero = BIC.builder()
				.name("Antiguo Matadero de Calí")
				.latitude(3.4415465517324257)
				.longitude(-76.52977456110938)
				.description("El antiguo matadero de Cali es un edificio de estilo neoclásico ubicado en el centro de la ciudad de Cali, Colombia. Fue construido en 1920 y funcionó como matadero hasta 1979. En 1982 fue declarado Monumento Nacional de Colombia.")
				.existss(false)
				.build();
		Nickname nicknameAntiguoMatadero = Nickname.builder()
				.nickname("Antiguo Matadero")
				.build();

		BIC edifioOtero = BIC.builder()
				.name("Edificio Otero")
				.latitude(3.451929471542798)
				.longitude(-76.5319398863662)
				.description("El Edificio Otero es un edificio de estilo neoclásico ubicado en el centro de la ciudad de Cali, Colombia. Fue construido en 1920 y funcionó como matadero hasta 1979. En 1982 fue declarado Monumento Nacional de Colombia.")
				.existss(true)
				.build();
		Nickname nicknameEdificioOtero = Nickname.builder()
				.nickname("Edificio Otero")
				.build();

		BIC iglesiaSanFrancisco = BIC.builder()
				.name("Iglesia San Francisco")
				.latitude(3.4505256236841015)
				.longitude(-76.53364473071245)
				.description("La iglesia de San Francisco es un templo católico ubicado en Santiago de Cali, Colombia. Fue construida en 1747 y es la iglesia más antigua de la ciudad. En 1982 fue declarada Monumento Nacional de Colombia.")
				.existss(true)
				.build();
		Nickname nicknameIglesiaSanFrancisco = Nickname.builder()
				.nickname("Iglesia San Francisco")
				.build();

		BIC plazaCayzedo = BIC.builder()
				.name("Plaza de Cayzedo")
				.latitude( 3.451308237454147)
				.longitude(-76.53219465725122)
				.description("La plaza de Cayzedo es una plaza ubicada en el centro de la ciudad de Cali, Colombia. Fue construida en 1920 y funcionó como matadero hasta 1979. En 1982 fue declarado Monumento Nacional de Colombia.")
				.existss(true)
				.build();

		Nickname nicknamePlazaCayzedo = Nickname.builder()
				.nickname("Plaza de Cayzedo")
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

			BIC ermitaInDB = bicRepository.save(ermita);
			BIC antiguoMataderoInDB = bicRepository.save(antiguoMatadero);
			BIC edifioOteroInDB = bicRepository.save(edifioOtero);
			BIC iglesiaSanFranciscoInDB = bicRepository.save(iglesiaSanFrancisco);
			BIC plazaCayzedoInDB = bicRepository.save(plazaCayzedo);

			nicknameErmita.setBic(ermitaInDB);
			nicknameRepository.save(nicknameErmita);
			nicknameAntiguoMatadero.setBic(antiguoMataderoInDB);
			nicknameRepository.save(nicknameAntiguoMatadero);
			nicknameEdificioOtero.setBic(edifioOteroInDB);
			nicknameRepository.save(nicknameEdificioOtero);
			nicknameIglesiaSanFrancisco.setBic(iglesiaSanFranciscoInDB);
			nicknameRepository.save(nicknameIglesiaSanFrancisco);
			nicknamePlazaCayzedo.setBic(plazaCayzedoInDB);
			nicknameRepository.save(nicknamePlazaCayzedo);
		};
	}

}
