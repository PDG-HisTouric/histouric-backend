package com.pdg.histouric;

import com.pdg.histouric.model.*;
import com.pdg.histouric.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class HistouricApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistouricApplication.class, args);
	}

	@Bean
	@Profile("!test")
	@Transactional
	CommandLineRunner commandLineRunner(RoleRepository roleRepository,
										UserRepository userRepository,
										BicRepository bicRepository,
										NicknameRepository nicknameRepository,
										HistoryRepository historyRepository,
										AudioRepository audioRepository,
										TextRepository textRepository,
										HistoryImageRepository historyImageRepository,
										VideoRepository videoRepository,
										BICHistoryRepository bicHistoryRepository,
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

		BIC ermita = BIC.builder()
				.name("La Ermita")
				.latitude(3.4578385679577623)
				.longitude(-76.53064306373778)
				.description("La iglesia La Ermita es un templo católico ubicada en Santiago de Cali, Colombia. Originalmente fue una construcción pajiza de comienzos del siglo XVII, establecida en las cercanías del río Cali y dedicada a Nuestra Señora de la Soledad y al Señor de la Caña.")
				.existss(true)
				.build();
		Nickname nicknameErmita = Nickname.builder()
				.nickname("La Ermita")
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

		Audio audioForHistory1 = Audio.builder()
				.needsUrlGen(true)
				.audioUri("audios/3bd1dfe8-f2c5-4890-8cc1-4004786a6a24_the-best-jazz-club-in-new-orleans-164472.mp3")
				.build();

		Audio audioForHistory2 = Audio.builder()
				.needsUrlGen(false)
//				.audioUri("https://drive.google.com/uc?export=view&id=1OEg8XmpVeJVp8OJcQn7kouOfwXopxPF0")
				.audioUri("https://drive.google.com/uc?export=view&id=1oL4xo9LsMKkGQT7-holZEja1onN6IBxt")
				.build();

		Text text1ForHistory1 = Text.builder()
				.content("Primer segmento")
				.startTime(61)
				.build();

		Text text2ForHistory1 = Text.builder()
				.content("Segundo segmento")
				.startTime(151)
				.build();

		Text text1ForHistory2 = Text.builder()
				.content("Segmento 1")
				.startTime(1)
				.build();

		Text text2ForHistory2 = Text.builder()
				.content("Segmento 2")
				.startTime(61)
				.build();

		HistoryImage historyImage1ForHistory1 = HistoryImage.builder()
				.needsUrlGen(true)
				.startTime(61)
				.imageUri("images/ba6bdad8-c4d8-4b45-9f6b-99b98f235827_pngwing.com.png")
				.build();

		HistoryImage historyImage2ForHistory1 = HistoryImage.builder()
				.needsUrlGen(true)
				.startTime(23)
				.imageUri("images/483053aa-64c6-47a6-9025-fcd4f86cbcaa_jeje.jpg")
				.build();

		HistoryImage historyImage1ForHistory2 = HistoryImage.builder()
				.needsUrlGen(false)
				.startTime(23)
				.imageUri("https://drive.google.com/uc?export=view&id=1LwxitD7axSkdzyNQveVNUIl6j1T8J51t")
				.build();

		HistoryImage historyImage2ForHistory2 = HistoryImage.builder()
				.needsUrlGen(false)
				.startTime(62)
				.imageUri("https://drive.google.com/uc?export=view&id=1StUKr1hewd3pTv8fg7r7UY_FHPtzZtWW")
				.build();

		Video videoForHistory1 = Video.builder()
				.needsUrlGen(true)
				.videoUri("videos/28ad1e05-efb7-44a4-95a8-b874ef480c68_168787 (1080p).mp4")
				.build();

		Video videoForHistory2 = Video.builder()
				.needsUrlGen(false)
				.videoUri("https://drive.google.com/uc?export=view&id=1XpZ1yK5sRKD2SrQEC_c-1Nw9ZI_XRj8u")
				.build();

		History history1 = History.builder()
				.title("Historia de prueba 1")
				.audio(audioForHistory1)
				.build();

		History history2 = History.builder()
				.title("Historia de prueba 2")
				.audio(audioForHistory2)
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
			HistouricUser researcherInDB = userRepository.save(reasearcherUser);

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

			History history1InDB = saveHistory(historyRepository, audioRepository, textRepository, historyImageRepository,
					videoRepository, audioForHistory1, text1ForHistory1, text2ForHistory1, historyImage1ForHistory1,
					historyImage2ForHistory1, videoForHistory1, history1, researcherInDB);

			saveHistory(historyRepository, audioRepository, textRepository, historyImageRepository,
					videoRepository, audioForHistory2, text1ForHistory2, text2ForHistory2, historyImage1ForHistory2,
					historyImage2ForHistory2, videoForHistory2, history2, researcherInDB);

			BICHistoryPK bicHistoryPK1 = BICHistoryPK.builder()
					.bicId(ermitaInDB.getId())
					.historyId(history1InDB.getId())
					.build();
			BICHistory bicHistory1 = BICHistory.builder()
					.id(bicHistoryPK1)
					.history(history1InDB)
					.build();
			var temp = bicHistoryRepository.save(bicHistory1);
			ermitaInDB.setBicHistories(List.of(temp));

		};
	}

	private static History saveHistory(HistoryRepository historyRepository,
									   AudioRepository audioRepository,
									   TextRepository textRepository,
									   HistoryImageRepository historyImageRepository,
									   VideoRepository videoRepository,
									   Audio audioForHistory,
									   Text text1ForHistory,
									   Text text2ForHistory,
									   HistoryImage historyImage1ForHistory,
									   HistoryImage historyImage2ForHistory,
									   Video videoForHistory,
									   History history,
									   HistouricUser researcherInDB) {
		audioRepository.save(audioForHistory);
		history.setOwner(researcherInDB);
		History historyInDB = historyRepository.save(history);

		text1ForHistory.setHistory(historyInDB);
		text2ForHistory.setHistory(historyInDB);
		Text text1ForHistory2InDB = textRepository.save(text1ForHistory);
		Text text2ForHistory2InDB = textRepository.save(text2ForHistory);
		List<Text> textsForHistory = new ArrayList<>();
		textsForHistory.add(text1ForHistory2InDB);
		textsForHistory.add(text2ForHistory2InDB);
		history.setTexts(textsForHistory);

		historyImage1ForHistory.setHistory(historyInDB);
		historyImage2ForHistory.setHistory(historyInDB);
		HistoryImage historyImage1ForHistory2InDB = historyImageRepository.save(historyImage1ForHistory);
		HistoryImage historyImage2ForHistory2InDB = historyImageRepository.save(historyImage2ForHistory);
		List<HistoryImage> historyImagesForHistory2 = new ArrayList<>();
		historyImagesForHistory2.add(historyImage1ForHistory2InDB);
		historyImagesForHistory2.add(historyImage2ForHistory2InDB);
		history.setImages(historyImagesForHistory2);

		videoForHistory.setHistory(historyInDB);
		Video videoForHistory2InDB = videoRepository.save(videoForHistory);
		List<Video> videosForHistory2 = new ArrayList<>();
		videosForHistory2.add(videoForHistory2InDB);
		history.setVideos(videosForHistory2);
		return historyInDB;
	}

}
