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
										BICImageRepository bicImageRepository,
										NicknameRepository nicknameRepository,
										HistoryRepository historyRepository,
										AudioRepository audioRepository,
										TextRepository textRepository,
										HistoryImageRepository historyImageRepository,
										VideoRepository videoRepository,
										BICHistoryRepository bicHistoryRepository,
										RouteRepository routeRepository,
										RouteBICHistoryRepository routeBICHistoryRepository,
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
		HistouricUser researcherUser = HistouricUser.builder()
				.id(UUID.fromString("20ffef18-7d49-40f2-97f1-98bc6cb199a2"))
				.nickname("Andres Felipe")
				.email("researcher@gmail.com")
				.password(encoder.encode("password"))
				.roles(researcherUserRoles)
				.build();

		ArrayList<Role> test2Roles = new ArrayList<>();
		HistouricUser test2User = HistouricUser.builder()
				.nickname("Pepito Perez")
				.email("test2@gmail.com")
				.password(encoder.encode("password"))
				.roles(test2Roles)
				.build();

		ArrayList<Role> test3Roles = new ArrayList<>();
		HistouricUser test3User = HistouricUser.builder()
				.nickname("Pepita Perez")
				.email("test3@gmail.com")
				.password(encoder.encode("password"))
				.roles(test3Roles)
				.build();

		BIC cementerioCentral = BIC.builder()
				.name("Cementerio Central")
				.latitude(3.461022788059019)
				.longitude(-76.51843858609764)
				.description("Es el cementerio central de Cali")
				.existss(true)
				.build();
		BICImage image1ForCementerioCentral = BICImage.builder()
				.imageUri("https://drive.google.com/uc?export=view&id=13q82A7M2stAtLr0dO4G111WYoxWD-evO")
				.build();

		BIC parqueArtesanalLomaDeLaCruz = BIC.builder()
				.name("Parque Artesanal Loma de la Cruz")
				.latitude(3.44289992459978)
				.longitude(-76.53722716168555)
				.description("La historia del Parque Artesanal Loma de la Cruz comienza en el año de 1987 cuando la empresa Artesanías de Colombia abre una sucursal en el Valle del Cauca, con el fin de proveer un espacio organizado para los artesanos del lugar. Se inauguró oficialmente el 12 de julio de 1990 con un acuerdo firmado por Artesanías de Colombia y la Alcaldía. En diciembre de 1992 Artesanías de Colombia cierra la sucursal y en 1993 entrega el manejo del parque al municipio. Con el fin de promover las actividades artesanales y las ventas, y con el fin de convertir el lugar en un centro de exposición.")
				.existss(true)
				.build();
		BICImage image1ForparqueArtesanalLomaDeLaCruz = BICImage.builder()
				.imageUri("https://drive.google.com/uc?export=view&id=1r27W7bj4hhDBoQR7rRn1eiQXPcCeBgl3")
				.build();
		BICImage image2ForparqueArtesanalLomaDeLaCruz = BICImage.builder()
				.imageUri("https://drive.google.com/uc?export=view&id=1WqF02jF1X3-kHMesWJu9Fx8Y-qsq007k")
				.build();

		BIC elCerroDeLasTresCruces = BIC.builder()
				.name("El Cerro de las Tres Cruces")
				.latitude(3.4678742504438764)
				.longitude(-76.54547751865275)
				.description("Cerro tutelar de la ciudad de Cali, Colombia ubicado en el corregimiento de Montebello en el área rural del municipio. Está ubicado al noroccidente de la ciudad y tiene 1480 m s. n. m. En su cima se encuentran el monumento de las Tres Cruces, una estación de policía y varias antenas de telecomunicaciones.")
				.existss(true)
				.build();
		BICImage image1ForElCerroDeLasTresCruces = BICImage.builder()
				.imageUri("https://drive.google.com/uc?export=view&id=1tLYOnCcGBbwwo6WBk0_j1MIQq0lkSy25")
				.build();
		BICImage image2ForElCerroDeLasTresCruces = BICImage.builder()
				.imageUri("https://drive.google.com/uc?export=view&id=188QBxGYxsE4JqJJJG6yuFhJXyUQpyL_B")
				.build();

		BIC ermita = BIC.builder()
				.name("La Ermita")
				.latitude(3.4540125684135936)
				.longitude(-76.53178329593457)
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

		Audio audioForLaManoNegra = Audio.builder()
				.needsUrlGen(false)
				.audioUri("https://drive.google.com/uc?export=view&id=12YhT8-_a-bEQJbVH-SgHyah-9CFj3HQD")
				.build();

		List<HistoryImage> historyImagesForLaManoNegra = new ArrayList<>();
		historyImagesForLaManoNegra.add(HistoryImage.builder()
				.needsUrlGen(false)
				.startTime(0)
				.imageUri("https://drive.google.com/uc?export=view&id=1bjakBDD_yavWEE7ngsJlPc2lSMVhntQw")
				.build()
		);
		historyImagesForLaManoNegra.add(HistoryImage.builder()
				.needsUrlGen(false)
				.startTime(195)
				.imageUri("https://drive.google.com/uc?export=view&id=1q-mXbtoMeJjSwptbEJ4FgqIOjngrkcU6")
				.build()
		);


		List<Text> textsForLaManoNegra = new ArrayList<>();
		textsForLaManoNegra.add(Text.builder()
				.startTime(0)
				.content("""
                                Crecencio y Juana se enamoraron: él era un negro rebelde, de esos que tienen “problemas con la autoridad”; ella era una dama, negra también, pero dócil, bella, educada, de buenas maneras; ambos eran esclavos propiedad de Alberto Bujalande, el amo y señor de buena parte de La Chanca, la tierra ubicada en la salida de Cali, por los lados del Camino del Sur.
                                
                                Las buenas maneras de Juana le habían granjeado la confianza de Carmen de la Ronda, la esposa del hacendado, ella la escogió como su dama de compañía y se convirtió en su confidente. Juana le contó de sus amores con Crecencio y ella decidió ser su celestina. Cuando supo que juntos se habían unido en matrimonio en el altar de Piedra Grande, en el Valle del Lili, se alegró por ellos y guardó el secreto. De hecho, ella misma buscó crear espacios para que ese amor fluyera. Corría el año de 1559.
                                """)
				.build());
		textsForLaManoNegra.add(Text.builder()
				.startTime(50)
				.content("""
                        Pero como si se tratara de una tragedia de Shakespeare, la historia pasó de la cima a la sima. Juana le contó que desde la parte alta de la loma ellos escuchaban voces que les llamaban. “Es cosa de Buziraco”, intuyó Carmen, y como esas eran cosas del demonio, decidió contarlo todo, así eso significara la muerte para Crecencio. “Mejor no jugar con las cosas de Dios, mejor no prestarse para cosas del Demonio, los sirvientes de Buziraco no merecen mejor suerte”.
                        
                        La pareja se enteró a tiempo de la traición y escapó. Pero no llegó muy lejos, por los lados de Vijes fueron sorprendidos y llevados a la presencia de su amo. Crescencio, amarrado como un perro, tuvo que presenciar la manera despiadada en que su amada era golpeada por su dueño; no aguantó más, rompió sus ataduras, se abalanzó sobre él y lo molió a puños.
                        """)
				.build());
		textsForLaManoNegra.add(Text.builder()
				.startTime(101)
				.content("""
                        Pero su amo no estaba solo, los demás esclavos lo protegieron. Crecencio fue entonces detenido y como castigo castrado. También se le cortó la mano con la que le había roto la cara al hacendado. Y una vez castigados, ambos fueron abandonados a su suerte, tirados al monte. De Juana luego se supo que fue devorada por las bestias salvajes, ella y el bebé que llevaba en su vientre; de él, que cayó muerto y su cuerpo fue tirado, como si fuera basura, al muladar.
                        
                        Pero la mano del negro no se tiró, no andaba con él; por eso se salvó del basurero. Y por eso mismo, al llegar la noche, cuando la gente pasaba por el sitio de la tragedia, la mano salía de la tierra muy rápido, como intentando agarrar a sus presas. Unos se congelaban, otros se desmayaban y los que más, salían como "alma que lleva el diablo" a contar el cuento.
                        """)
				.build());
		textsForLaManoNegra.add(Text.builder()
				.startTime(152)
				.content("""
                        Con el tiempo, por ahí no pasaba nadie ¿Quién querría exponerse? Hasta los animales salían corriendo del susto. Y la cosa fue a más. Había quienes decían que no les había salido la mano, que la cosa había sido mucho peor, que un negro grande, sucio, manco, “malencarado”, amenazante, con el muñón botando sangre, los había perseguido hasta casi alcanzarlos.
                        
                        Y los vecinos, fervorosos, católicos, temerosos de Dios, para evitar al negro condenado, pusieron una cruz de madera en el sitio. Y siglos más tarde, en 1909, los franciscanos subieron la loma dispuestos a acabar con el problema. Una vez ahí, la exorcizaron, celebraron una misa campal y dejaron como recuerdo de su buena acción un monumento que aún existe, una cruz de cemento con un pedestal de ladrillo. Los curas insistieron, “esta no es más la Loma de la Mano Negra ⸻que es como se llamó por siglos⸻, a partir de ahora, esta es la Loma de la Cruz”.
                        """)
				.build());
		textsForLaManoNegra.add(Text.builder()
				.startTime(211)
				.content("""
                        Había más explicaciones para la presencia de la mano que asustaba en esa loma. Una muy usual era que sí, se había tratado de una historia de amor… pero no entre esclavos. El negro, armado con los poderes del demonio, no solo había conquistado a la hija del patrón, un español, esclavista y rico, sino que además la había “perjudicado”. Y claro, la historia terminaba con el esclavo mutilado y muerto, su cadáver abandonado y el asesino “muerto en extrañas circunstancias” luego de ser espantado por la mano del negro, socialmente el malo de la historia.
                        
                        Otras personas decían que no había tal historia de amor, que se trató de una reprimenda de una madre de temperamento fuerte ⸻esclava, por supuesto⸻, a su hijo. Ella, una mujer de carácter, pero de buenas maneras; él, el peor hijo del mundo. Un buen día ella, cansada de sus mentiras, de sus malas maneras, de sus embustes, de su desobediencia, de sus burlas y de su mal comportamiento, en medio de un regaño con “pela” incluida le había dicho “que te trague la tierra”. Y efectivamente, así había ocurrido. La mano en este caso, cuando la gente pasaba, no estaba saliendo de la tierra, sino evitando entrar en ella. Pero igual asustaba.
                        
                        El monumento que corona el parque artesanal Loma de la Cruz muestra esa convivencia entre superstición y religiosidad tan típica de la sociedad caleña de siempre, esa sociedad que aunque confía en Dios le pone unos corales en el tobillo a los bebes para evitar el mal de ojo. Justo atrás de la cruz que pusieron los franciscanos se ve, como saliendo de la tierra, una mano negra que casi nadie nota.
                        """)
				.build());

		History laManoNegra = History.builder()
				.title("La Mano Negra")
				.audio(audioForLaManoNegra)
				.build();

		Audio audioForTiemposDeBuziraco = Audio.builder()
				.needsUrlGen(false)
				.audioUri("https://drive.google.com/uc?export=view&id=1myfyDIgk6y0vuL0nXoGfaFj2uBSXkL3_")
				.build();

		List<HistoryImage> historyImagesForTiemposDeBuziraco = new ArrayList<>();

		historyImagesForTiemposDeBuziraco.add(HistoryImage.builder()
				.needsUrlGen(false)
				.startTime(0)
				.imageUri("https://drive.google.com/uc?export=view&id=1hsP3Vio-Pb0BZVy8H3QobZfN_8QlkFBu")
				.build()
		);

		historyImagesForTiemposDeBuziraco.add(HistoryImage.builder()
				.needsUrlGen(false)
				.startTime(281)
				.imageUri("https://drive.google.com/uc?export=view&id=18Zw8GJB2UHgTIPP6Ra1LewTxqHHfI8xX")
				.build()
		);

		List<Text> textsForTiemposDeBuziraco = new ArrayList<>();

		textsForTiemposDeBuziraco.add(Text.builder()
				.startTime(0)
				.content("""
                        Los blancos decían que llegó de España, como un demonio desterrado, oculto en un barco; los negros corregían, para sí mismos por su puesto, “es el espíritu que nos acompaña en nuestro infortunio”. Solo en algo coincidían, Buziraco, en los años mil quinientos... había establecido su domicilio en el cerro de la Popa en Cartagena.
                        
                        “Allá adoran al demonio”, escuchó decir Fray Alonso de la Cruz... y decidió verificar por sí mismo, subió el cerro y encontró a un grupo de negros, indios y cimarrones desnudos, entregados al placer y a la lujuria, bailando con desenfreno, como solo se puede hacer con el diablo adentro. “Están adorando a Buziraco”, concluyó, y para remediarlo, tomó el ídolo que lo representaba ―un macho cabrío de oro, con torso humano―, ý lo arrojó al mar.
                        """)
				.build());
		textsForTiemposDeBuziraco.add(Text.builder()
				.startTime(42)
				.content("""
                        Pero el demonio no murió –ángeles y demonios son eternos–, se repuso de las magulladuras de su ego y tomó rumbo a Buenaventura. Una vez ahí, subió la cordillera y llegó a Cali. Y su presencia se empezó a notar. “Trae alegría y fiesta”, pensaban algunos; “atenta contra las buenas costumbres”, decían otros. Y de pronto ya no era solo la fiesta, todo lo malo que pasaba en Cali era culpa de Buziraco: “por eso hay tanto incendio, por eso hay tanto robo, por eso hay tan poca fe”.
                        
                        Y el ´personaje se volvió visible... “vuela sobre el pico de la loma en las noches sin luna... y huele a azufre”. Y con el tiempo llegó a tener un aspecto uniforme: “es enorme, tiene alas de murciélago y cola de reptil”. Y cuando las rondas de negros cantaban en sus lenguas ancestrales, y exorcizando su trágico destino entonaban sus tamboras, se encendían las alertas: “están adorando a Buziraco”. Y ese culto al demonio tenía consecuencias, “mira las lluvias, mira el incendio, mira la viruela, mira la lepra, mira la fiebre de tabardillo, mira la plaga”. En fin, cualquier cosa mala que pasaba en Cali tenía la misma causa, el culto al Buziraco.
                        """)
				.build());
		textsForTiemposDeBuziraco.add(Text.builder()
				.startTime(103)
				.content("""
                        —¡Paganos! Algo debe hacer la Iglesia! —se decía. Y la Iglesia escuchó.
                        
                        Fray Vicente y Fray Juan Costa solucionaron el problema sin tener que batirse a duelo con el demonio. En 1837 hicieron tres cruces de guadua y las pusieron en la cima del cerro. Y para evitar su desgaste, de cuando en cuando organizaban el cambio de alguno de sus tramos. Su solución funcionó hasta la noche de la sangrienta retoma de Cali. Aquel 24 de diciembre de 1876, cuando Cali fue saqueada, ultrajada y quemada en nombre del orden, las tres cruces fueron quemadas también. Y aunque quemarlas fue obra del radicalismo liberal, la versión en la calle fue otra: al estar desgastadas, habían perdido su poder, circunstancia que aprovechó Buziraco para liberarse, dejar el cerro, envolver la ciudad en llamas y llenarla de esos bárbaros que la destrozaron. Algunos incluso afirmaban haberle escuchado decir que un día arrojaría el cerro sobre la ciudad.
                        
                        Las cruces se armaron de nuevo, pero está vez se institucionalizó su mantenimiento. Cada 3 de mayo, el Día de la Cruz, el de los mil Jesús, se cambiarían las guaduas sin importar su estado. Y se haría en medio de un acto de fe. Para ello, desde el día anterior, el sacristán de Santa Rosa, acompañado de Garrón de Puerco, recorría la ciudad invitando a la peregrinación al cerro. Y subía mucha gente, a pie, por la ladera, repitiendo Jesús mil veces. Y eso funcionó hasta el 7 de junio de 1925, ese día un violento terremoto causó destrozos en buena parte de los templos ... y de pasó tumbó las cruces.
                        """)
				.build());
		textsForTiemposDeBuziraco.add(Text.builder()
				.startTime(188)
				.content("""
                        —¿Será que Buziraco se liberó y trató de destruir la ciudad?
                        
                        El padre Collazos, párroco de Santa Rosa, entendió que el cambio de guaduas no era suficiente ¡Hay que hacer algo más permanente, algo grande, imponente, visible desde cualquier parte de Cali, algo que ni el agua ni el viento ni el fuego puedan destruir, algo con lo que todos los feligreses se sientan protegidos! El cemento y el hierro, los materiales de moda en las nuevas edificaciones de la ciudad, llegarían de Europa para reemplazar las guaduas.
                        
                        El 6 de enero de 1938, a las diez de la mañana, se inauguró el monumento. En medio de la gente estaba un joven de trece años, estudiante del colegio San Luis Gonzaga. Había subido por la ladera, a regañadientes, sin entender por qué no usar la nueva carretera. Y ahí, de la mano de su padre, mientras asistía a la ceremonia, no imaginaba que veinticinco años después, en la Semana Santa de 1963, él mismo organizaría un viacrucis desde el Paseo Bolívar hasta el Cerro y crearía así la tradición caleña de subir a las Tres Cruces a pie el día viernes de Semana Santa.
                        """)
				.build());
		textsForTiemposDeBuziraco.add(Text.builder()
				.startTime(248)
				.content("""
                        Las cruces atraparon a Buziraco en el Cerro. Y sí, de vez en cuando se hacía notar. Las romerías al Cerro, al principio animadas por la fe y la curiosidad, empezaron a degenerarse. “Buziraco anda repartiendo trago y luego organiza bacanales con mujeres de la vida alegre”, así se explicaban las parrandas sexuales al Cerro. Y de pronto se llenó de ladrones, pirómanos y violadores. Fue tanta la delincuencia que un día se prohibieron las romerías. Solo en la Semana Santa de 1963, a instancias del viacrucis del padre Hurtado Galvis, se retomó la visita al Cerro como un acto de fe.
                        
                        En los ochenta todo el mundo se olvidó de Buziraco, incluso la curia que empezó a arrendar espacios para colocar torres con antenas ¡Hay que pagar el predial! fue su excusa. Hoy el monumento casi no se ve, compite con treinta enormes torres con antenas.
                        """)
				.build());

		History tiemposDeBuziraco = History.builder()
				.title("Tiempos de Buziraco")
				.audio(audioForTiemposDeBuziraco)
				.build();

		Audio audioForEnAtomosVolando = Audio.builder()
				.needsUrlGen(false)
				.audioUri("https://drive.google.com/uc?export=view&id=1Ejz-orb0TpG16YpCNBLubno5t2LJl1ww")
				.build();

		List<HistoryImage> historyImagesForEnAtomosVolando = new ArrayList<>();
		historyImagesForEnAtomosVolando.add(HistoryImage.builder()
				.needsUrlGen(false)
				.startTime(0)
				.imageUri("https://drive.google.com/uc?export=view&id=1AN8rzfbozJ3eAINZzv7MtVcCcXsUDGqd")
				.build());

		List<Text> textsForEnAtomosVolando = new ArrayList<>();
		textsForEnAtomosVolando.add(Text.builder()
				.startTime(0)
				.content("""
						El día anterior, Madame Laila, “pitonisa llegada del lejano Oriente”, le había dicho a Carlos Guzmán, reportero de Relator:
						
						―Vengo a prevenirlos, en pocas horas se producirá una tragedia.
						
						Pero nadie le “paró bolas”, es que de ella lo más llamativo no eran sus predicciones, sino el intenso lapislázuli de sus ojos.
						
						Pero esta vez, la madame acertó.
						
						Ese 6 de agosto de 1956 fue un día largo para el coronel Navia Varona, comandante del Batallón Pichincha, era día de revista de armamento y municiones y había que preparar el aniversario de la Batalla de Boyacá. Pasada la media noche, cuando pensaba que su jornada había terminado, escuchó un estruendo ¡Era como si a Cali la estuvieran arrancando del suelo!
						""")
				.build());
		textsForEnAtomosVolando.add(Text.builder()
				.startTime(40)
				.content("""
                        Recordó entonces los camiones que habían llegado al batallón a eso de las cuatro de la tarde. Venían de Buenaventura, camino a Bogotá, seis de ellos cargados con dinamita.
                        
                        El coronel, hombre previsivo, no les permitió parquear ahí. Las celebraciones patrias incluían el disparo de salvas y fuegos artificiales ¿Qué tal un accidente? ¿Qué tal un opositor travieso? ¡Mejor no correr riesgos!
                        
                        Y su preocupación era real. Por la ubicación del batallón (donde hoy está el CAM), la explosión de esas cuarenta y dos toneladas de dinamita habría hecho desaparecer la Ermita, el teatro Jorge Isaacs, el hotel Alférez Real y Villa María, la mansión de Emiliano Otero, la emblemática primera casa con piscina de Cali, ubicada pasando la calle, ahí donde en los setenta se construiría el Palacio Rosa.
                        
                        El suboficial a cargo de la escolta dirigió entonces el convoy hacia la estación del ferrocarril. Le pareció un buen sitio, quedaba lejos del centro y era seguro, ahí se alojaba una compañía del Batallón Codazzi. En ese lugar, a la altura de la calle 25 entre carreras primera y segunda, frente a la torre de la estación, en medio de bares, billares, cafés, pequeños negocios, pensiones y casas de putas, parquearían por última vez.
                        """)
				.build());
		textsForEnAtomosVolando.add(Text.builder()
				.startTime(109)
				.content("""
                        A la una y siete minutos de la madrugada, mientras: Elvira dormía en su cuna y José Mario recordaba a Ifigenia, la chica que salvaría de la prostitución, a quien había dejado en un hotel cercano con la incumplida promesa de regresar esa noche; mientras en los salones se bailaba mambo, chachachá y bolero; mientras Olga y Enrique consumaban su amor en la pensión ABC; un ruido ensordecedor anunciaba la tragedia, cuarenta y dos manzanas prácticamente desaparecerían.
                       
                        Nadie entendía qué pasaba. Los sobrevivientes salían a la calle en pijama o desnudos y minutos después se arrepentían de haberlo hecho. Nada era agradable a la vista, había pedazos de gente hasta en los postes de luz; los que no salían, se refugiaban bajo las camas y las mesas, “están bombardeando Cali” pensaban, recordando la incomodidad de Rojas Pinilla con la creciente oposición y su reciente compra de helicópteros “para mejorar la seguridad”.
                       
                        Y su interpretación no era tan descabellada. El general ya había perdido su encanto: el cierre de El Tiempo y El Espectador, por “difamar” al gobierno, no se veía bien; tampoco el aumento del catastro y de los impuestos a la renta y al patrimonio; menos aún la mesiánica persecución de protestantes, “enemigos de la santa Iglesia”. Además, el recuerdo de la masacre estudiantil de junio del 54 estaba muy fresco.
                        """)
				.build());
		textsForEnAtomosVolando.add(Text.builder()
				.startTime(181)
				.content("""
                        Pero no, esto no tenía que ver con el General. De hecho, al día siguiente él mismo culparía a los opositores de querer “sembrar el caos y desestabilizar el país”. Probablemente, decían los medios oficiales, “son cosas de Alberto Lleras y Laureano Gómez”.
                        
                        Todo era ―y aún es― especulación. Aunque es la mayor tragedia que ha vivido Cali, peor aún que la retoma de la ciudad por David Peña en la Navidad de 1876, nadie sabe por qué ocurrió. Pudo ser un accidente ―una colilla de cigarrillo tirada con descuido o una bala perdida―, pero también un acto criminal, como sugiere el informe de James Dedman Jr., un experto en explosivos enviado desde Wilmington por Atlas Powder, quien precisó que “el agente provocador (…) fue un elemento detonante extraño a los materiales transportados”.
                        
                        Casi nada entre las calles 21 y 25 y las carreras primera y séptima se salvó, hasta las palmas perdieron sus coronas. Como testigo mudo quedó un cráter de 50 o 60 metros de diámetro y ocho, doce o quince de profundidad, rodeado de manzanas de escombros. Ya no habría más galería Belmonte ni estación del ferrocarril ni teatro Roma ni puesto de prensa de las Del Castillo. De las casas que quedaron en pie no quedó mucho: volaron puertas y ventanas, cayeron techos y paredes.
                        """)
				.build());
		textsForEnAtomosVolando.add(Text.builder()
				.startTime(257)
				.content("""
                        La tierra tembló tan fuerte que se movió Caloto y también Buga. En el epicentro era difícil circular, montañas de escombros y pedazos de gente lo impedían. Una nube interminable de polvo naranja dominaba todo. A la explosión le siguió un incendio… y otro… y otro más. Y con ellos la temperatura aumentó. El infierno se había trasladado a Cali.
                        
                        ¡Y a nadie se le ocurrió robar! ¿Para qué si el mundo se estaba acabando?
                        
                        Los bomberos fueron los héroes de la jornada, por momentos extinguían el fuego, por momentos desenterraban gente a punta de manguera y por momentos orientaban a los sobrevivientes que corrían, heridos, medio desnudos o desnudos, ensangrentados, sin saber a dónde ir. La maquina 7, una Ford 53 al mando del sargento Gómez, fue la primera en llegar.
                        
                        De pronto, la discusión se centró en temas de método ¿Cómo contar muertos si solo hay pedazos de gente? ¿Cómo enterrarlos sin mezclarlos?
                        
                        ―Contemos cráneos ―sugirió alguien.
                        
                        ―Hagamos una fosa común —dijo otro.
                        
                        ¡Y así se hizo! Y la cuenta llegó a 3.725.
                        
                        Al cementerio central, ubicado a varias cuadras de la estación, llegaron algunos motores de los camiones. Y el hecho no fue tan notorio porque como si se tratara de un guión de Jairo Pinilla, algunos cadáveres habían abandonado sus tumbas. Cuando se pudo, se juntaron sus partes, cuando no… a la fosa común.
                        """)
				.build());
		History enAtomosVolando = History.builder()
				.title("En átomos volando")
				.audio(audioForEnAtomosVolando)
				.build();

		Audio audioForElTerremotoDe1925 = Audio.builder()
				.needsUrlGen(false)
				.audioUri("https://drive.google.com/uc?export=view&id=1zM7kBSdJI3lhGpG9J40BDPPFNHbIImaS")
				.build();

		List<HistoryImage> historyImagesForElTerremotoDe1925 = new ArrayList<>();

		historyImagesForElTerremotoDe1925.add(HistoryImage.builder()
				.needsUrlGen(false)
				.startTime(0)
				.imageUri("https://drive.google.com/uc?export=view&id=1x5jn-hwueMZ_D1mOz1iOvuNfiI6lVUcL")
				.build());

		List<Text> textsForElTerremotoDe1925 = new ArrayList<>();

		textsForElTerremotoDe1925.add(Text.builder()
				.startTime(0)
				.content("""
                        Era una tarde de domingo bastante atípica para la soleada Cali. A eso de las cuatro y media ya el cielo presagiaba lluvia ¿Pero es junio?, se quejaban los caleños acostumbrados a sus bien marcadas temporadas de sol y agua. Minutos después iniciaba una llovizna interminable. Y como la relación de los caleños con la lluvia nunca ha sido buena, no había mucha gente en la calle. Lástima el clima, decían muchos, añorando poder salir a discutir el tema del día, la manera en que Pedro Nel Ospina, el Presidente de la República, solucionaría la crisis de ministros. Seguro incluye a Laureano Gómez, intuía acertadamente la mayoría.
                        
                        Veintitrés minutos antes de las siete de la noche se sintió el primer movimiento, de lado a lado, suavecito, como en una hamaca. Y antes de que alguien pudiera decir “está temblando”, trepidó. Fueron 16 segundos que parecieron horas de movimientos irregulares, como si alguien hubiera cogido la ciudad por un costado y la estuviera zarandeando. El espanto se apoderó de la población que salió a las calles “como estaba”. Es que a quién le importa el pudor y el qué dirán ante un buen susto. Los cables de luz se reventaron y al mezclarse con la lluvia completaron un dantesco escenario.
                        """)
				.build());
		textsForElTerremotoDe1925.add(Text.builder()
				.startTime(69)
				.content("""
                        ¡San Emigdio, San Emigdio! gritaban desesperados los caleños, pero el santo italiano no escuchó esta vez. La luces se apagaron y con ello el pánico aumentó. Unos se abrazaban, otros se arrodillaban, unos más se tiraban al piso en cruz. Mientras unos rezaban y prometían a gritos componer su vida a cambio de un poco más de vida, otros solo atinaban a una risa nerviosa. El caos se apoderó de la ciudad. Cerca del matadero municipal el desmadre fue mayor, una de sus paredes se derribó y las reses previstas para el sacrificio de la madrugada siguiente salieron huyendo despavoridas en una especie de San Fermín criollo.
                        
                        Los pocos edificios con que contaba la ciudad debieron ser desalojados. La torre del Colegio de Santa Librada –que funcionaba en el cruce de las actuales carrera 4 y calle 13–, quedó a “un pelo” de desprenderse y las autoridades debieron cerrar el paso. El palacio municipal se agrietó; igual suerte corrió el edificio donde funcionaba Relator, el periódico de la ciudad. Una de las atalayas del cuartel (ubicado en el sitio del actual CAM) se desplomó y otra quedó lista para ser derribada. En el edificio Otero, en ese momento en construcción, se tuvo que reemplazar una de las paredes.
                        """)
				.build());
		textsForElTerremotoDe1925.add(Text.builder()
				.startTime(138)
				.content("""
                        Las iglesias vivieron su drama particular. Los arcos, el techo y la torre del reloj de la Catedral sufrieron daños mayores; su reloj se paró justo a las 6:37, como testigo mudo del momento mismo en que la tragedia empezó. La Ermita quedó literalmente en el suelo, solo la Virgen de la Soledad del Río, el Señor de la Caña y la luz que iluminaba a este último sobrevivieron. Como anunciando su despedida, el templo cayó en dos tramos, el segundo a mitad de la noche, con un estruendo que erizó hasta al más valiente. La torre de la iglesia de San Nicolás se rajó y de ella se desprendieron varios ladrillos, uno impactó al sacristán, quien en ese momento se aprestaba a repicar las campanas. La capilla del colegio de Santa Librada se fue cayendo, poco a poco, con el correr de las horas.
                        
                        El convento de San Francisco tuvo su escena particular. Al momento del primer temblor estaban todos los padres reunidos rezando, arriba, en el lugar del coro. Y nadie se inmutó. “Dios sabe lo que hace”. Pero cuando trepidó y de la cornisa se desprendieron algunos ladrillos y uno de ellos rozó la cabeza de Fray Tomás Becerra, recordaron aquello de “A Dios rogando y con el mazo dando” ¡Todos a la escalera! Pero como no había luz, tropezarse era fácil. Uno de los padres rodó por el piso y terminó abajo con la cabeza rajada, una mano dislocada, una costilla rota y mucha vergüenza; el padre Becerra, mostrando frialdad y buen juicio se quedó tranquilo, sonriente, confiando en Dios y en la cornisa del órgano. Al día siguiente cada uno tenía su propia anécdota, uno contaba cómo un ladrillo le había tumbado, literalmente, su libro de oración de las manos; otro cómo se había congelado al ver desprenderse el pararrayos de la torre y avanzar amenazante a gran velocidad.
                        """)
				.build());
		textsForElTerremotoDe1925.add(Text.builder()
				.startTime(240)
				.content("""
                        El terremoto no hizo distinción de clases. Las casas del sindicato popular se vinieron abajo, pero las casas de dos plantas del centro, las de las familias prestantes, también sufrieron grandes daños. El Hotel Francia quedó totalmente destruido, todavía nadie se explica cómo se logró que todos menos uno de los comensales y huéspedes alcanzaran a salir –y más aún, cómo el rezagado salió vivo de los escombros–. Las cantinas de Juanchito se derrumbaron y se quebraron las botellas de licor. Tremenda pérdida para los comerciantes, tremendo vacío para sus clientes.
                        
                        Fueron tres remezones. Suficientes para llenar de pánico a una ciudad que no durmió. Las familias permanecieron en las calles, como pudieron improvisaron cambuches y se organizaron en parques y solares, todos con todos. Los enfermos de los hospitales que no salieron por sus propios medios tuvieron que ser evacuados. La noche era oscura, fría y lluviosa. Y el coro de perros aullando no ayudaba. Parecía el fin del mundo. Las pérdidas se calcularon en 400.000 dólares, una fortuna para la época. A la mañana siguiente hubo una misa campal en la Plaza de Cayzedo para agradecer al Todopoderoso haber conservado la vida. Ahí se incluyeron unos inexplicables presagios de nuevos temblores para la tarde, que solo llenaron de temor a las personas. Pero el “recen, arrepiéntanse, pidan piedad” se quedó corto ante la curiosidad: “mientras llega la tarde, vamos a ver escombros ve”.
                        """)
				.build());

		History elTerremotoDe1925 = History.builder()
				.title("El terremoto de 1925")
				.audio(audioForElTerremotoDe1925)
				.build();

		Audio audioForTristeNavidad = Audio.builder()
				.needsUrlGen(false)
				.audioUri("https://drive.google.com/uc?export=view&id=1T_tZ4PITz-lHiOtT9A0y3PYXyJQpZpNK")
				.build();

		List<HistoryImage> historyImagesForTristeNavidad = new ArrayList<>();
		historyImagesForTristeNavidad.add(HistoryImage.builder()
				.needsUrlGen(false)
				.startTime(0)
				.imageUri("https://drive.google.com/uc?export=view&id=1ug0Pjc9QkCMA5_2e1h3VIJS6AnrnCa1e")
				.build());

		List<Text> textsForTristeNavidad = new ArrayList<>();
		textsForTristeNavidad.add(Text.builder()
				.startTime(0)
				.content("""
                        A las seis y media de la tarde del 18 de diciembre de 1876, un grupo de 36 conservadores modestamente armado llegó al puente sobre el río nuevo ―un brazo que se le había sacado al río Cali―. Ahí se dividió en dos: uno tomó a mano izquierda y recostado sobre la antigua Ermita alcanzó la calle 13, su objetivo era tomarse el cuartel de Santa Librada, ubicado en la actual carrera cuarta; el otro siguió derecho y fue tomando posiciones en la esquina de la Plaza de la Constitución ―la actual Plaza de Cayzedo―, su objetivo era tomarse la Casa Municipal.
                        
                        La toma del cuartel resultó más fácil de lo previsto, no hubo necesidad de disparar un solo tiro, no se mató a nadie. Un buen empellón fue suficiente para desarmar al centinela y entrar. Y no hubo resistencia, ninguno de los trescientos reclutas esperaba un ataque, unos jugaban naipes, otros dormían, todos aprovechaban la ausencia de su comandante, el general David Peña, quien iba camino a Cartago con sus mejores hombres para unirse al batallón que combatiría a los rebeldes de Antioquia.
                        """)
				.build());
		textsForTristeNavidad.add(Text.builder()
				.startTime(56)
				.content("""
                        El reto para el otro grupo era más complicado, en la planta baja de la Casa Municipal había ochenta presos políticos. Tocaba liberarlos con vida y tomar el control. Y el sitio era custodiado por doscientos hombres, que aunque eran reclutas estaban atentos, había que cuidar los presos. Y no estaban dispuestos a ponerla fácil.
                        
                        Empezaron los tiros y empezó la defensa. En los balcones se armaron trincheras: en los que daban a la plaza, con pedazos de pared sacados “a la brava”; en los que daban a la carrera cuarta, con los libros y expedientes de los juzgados. No era momento de delicadezas, había que evitar las balas.
                        
                        ¡Y nadie sabía cuántos rebeldes eran! Pero se calculaba que eran muchos porque los tiros venían de todo lado. Es que los veinte hombres no hacían dos tiros desde un mismo punto, se movían antes de disparar.
                        """)
				.build());
		textsForTristeNavidad.add(Text.builder()
				.startTime(99)
				.content("""
                        ―Si saben cuántos somos, salen y nos matan —había sentenciado Carlos Patiño, el líder conservador.
                        
                        Durante la noche la resistencia bajó, muchos decidieron cuidar su vida y saltando tapias huyeron. A las once de la mañana del 19 una bandera blanca indicó la rendición de las fuerzas oficiales.
                        
                        Pero ¿Por qué sucedió todo esto?
                        
                        Era 1876. Los radicales liberales estaban en el gobierno de los Estados Unidos de Colombia y Aquileo Parra, su presidente, venía construyendo un Estado laico. Una de sus metas era reducir –eliminar de ser posible–, el rol de la Iglesia en la educación. Trajo incluso una misión alemana para definir contenidos y métodos. Y claro, la Iglesia no se quedó quieta, desde el pulpito y fuera de él: recordó a su feligresía que gracias a ella existía la educación en América y la exhortó a defender su derecho a educación con valores cristianos. Así las cosas, en julio de ese año había iniciado una guerra civil que duraría un año: la guerra de las escuelas.
                        """)
				.build());
		textsForTristeNavidad.add(Text.builder()
				.startTime(155)
				.content("""
                        Cali era esencialmente conservadora y católica, por eso la toma de ese día generó regocijo. Era una buena noticia, se estaban librando de quienes los encerraban y les “daban perrero” solo por ser conservadores. Y el balance era muy positivo: solo hubo dos muertos ―ninguno en combate―, uno por imprudente, otro por error; los presos estaban libres; se había conseguido armamento, incluso cuatro cañones; y se tenía el control de Cali, el paso obligado desde el interior hacia Popayán y el Sur del país ¡Parecía una victoria!
                        
                        David Peña iba llegando a Cartago cuando recibió la noticia de la toma. Y no dudo en regresar, había que recuperar el control. Y como ya no contaba con refuerzos en Cali, en el camino fortaleció su tropa con presos y voluntarios; a los primeros les ofreció libertad, a los segundos, un botín.
                        
                        Llegó a Cali en la madrugada del 24 de diciembre, dos mil combatientes le acompañaban. Su apuesta era simple, la evidente desproporción de fuerzas haría que su propuesta de rendición con indulto fuera aceptada de inmediato.
                        """)
				.build());
		textsForTristeNavidad.add(Text.builder()
				.startTime(211)
				.content("""
                        ¡Pero no sucedió así! Su propuesta fue rechazada.
                        
                        A las cinco de la mañana iniciaron los disparos. Un grupo resistía por los lados del nuevo cementerio ―el Central―, otro en la Plaza, con un cañón en cada esquina. Pero antes del mediodía, los radicales ya habían tomado el control.
                        
                        ¡Y ahí fue Troya! Los voluntarios empezaron a “cobrar” su lealtad. Y alguien se animó, violentó una puerta y entró a robar. Y la muchedumbre se contagió; los saqueos se multiplicaron, casas y comercios sufrieron por igual. Y de pronto robar ya no era suficiente, había que dañar... y se dañó a golpes, por el solo hecho de dañar, por el solo placer de golpear. Unos se robaban las finas vajillas francesas, otros solo las tiraban contra el piso para ver cómo se hacían añicos; solo las botellas de licor se respetaban.
                        """)
				.build());
		textsForTristeNavidad.add(Text.builder()
				.startTime(255)
				.content("""
                        Y empezaron los incendios, uno tras otro; y siguieron los asesinatos. “Abajo los Borrero” ―y los Velasco y los Olano y todos los conservadores conocidos―. La horda enloquecida gritaba mientras los buscaba ... y al hallarlos los degollaba. Y ya no importaba el apellido, cualquier conservador era digno de ser asesinado. Por los solares se veía gente corriendo. Amparados en la solidaridad de sus vecinos liberales, saltaban de tapia en tapia buscando refugiarse por los lados del río. Unas pocas familias lograron “camuflarse” de liberales colocando una rama verde en la ventana y dejando los portones abiertos ¡Así salvaron vida y bienes!
                        
                        A San Pedro, donde murieron degollados varios ancianos, entró triunfal David Peña montado en su caballo, sin sombrero, como supuesta “muestra de respeto”.
                        
                        Tres días duró ese infierno. Y solo paró cuando parecía que seguirían con las casas liberales.
                        
                        Paradójicamente, la ley 46 de 1878 premió a Peña por sus “importantes servicios al país”. En Cali, sin embargo, fue señalado en silencio como el responsable de permitir los peores abusos de su historia.
                        """)
				.build());

		History tristeNavidad = History.builder()
				.title("Triste Navidad")
				.audio(audioForTristeNavidad)
				.build();

		return args -> {
			Role tourismManagerRoleCreated = roleRepository.save(tourismManagerRole);
			tourismManagerUserRoles.add(tourismManagerRoleCreated);
			HistouricUser tourismManagerUserInDB = userRepository.save(tourismManagerUser);

			Role adminRoleCreated = roleRepository.save(adminRole);
			adminUserRoles.add(adminRoleCreated);
			userRepository.save(adminUser);
			userRepository.save(adminUser1);

			Role researcherRoleCreated = roleRepository.save(researcher);
			researcherUserRoles.add(researcherRoleCreated);
			HistouricUser researcherInDB = userRepository.save(researcherUser);

			test2Roles.add(tourismManagerRoleCreated);
			userRepository.save(test2User);

			test3Roles.add(researcherRoleCreated);
			userRepository.save(test3User);

			BIC ermitaInDB = bicRepository.save(ermita);
			BIC antiguoMataderoInDB = bicRepository.save(antiguoMatadero);
			BIC iglesiaSanFranciscoInDB = bicRepository.save(iglesiaSanFrancisco);
			BIC plazaCayzedoInDB = bicRepository.save(plazaCayzedo);
			BIC cementerioCentralDB = bicRepository.save(cementerioCentral);
			bicRepository.save(parqueArtesanalLomaDeLaCruz);
			bicRepository.save(elCerroDeLasTresCruces);

			image1ForparqueArtesanalLomaDeLaCruz.setBic(parqueArtesanalLomaDeLaCruz);
			bicImageRepository.save(image1ForparqueArtesanalLomaDeLaCruz);
			image2ForparqueArtesanalLomaDeLaCruz.setBic(parqueArtesanalLomaDeLaCruz);
			bicImageRepository.save(image2ForparqueArtesanalLomaDeLaCruz);
			image1ForElCerroDeLasTresCruces.setBic(elCerroDeLasTresCruces);
			bicImageRepository.save(image1ForElCerroDeLasTresCruces);
			image2ForElCerroDeLasTresCruces.setBic(elCerroDeLasTresCruces);
			bicImageRepository.save(image2ForElCerroDeLasTresCruces);
			image1ForCementerioCentral.setBic(cementerioCentralDB);
			bicImageRepository.save(image1ForCementerioCentral);

			nicknameErmita.setBic(ermitaInDB);
			nicknameRepository.save(nicknameErmita);
			nicknameAntiguoMatadero.setBic(antiguoMataderoInDB);
			nicknameRepository.save(nicknameAntiguoMatadero);
			nicknameIglesiaSanFrancisco.setBic(iglesiaSanFranciscoInDB);
			nicknameRepository.save(nicknameIglesiaSanFrancisco);
			nicknamePlazaCayzedo.setBic(plazaCayzedoInDB);
			nicknameRepository.save(nicknamePlazaCayzedo);

			saveHistory(historyRepository, audioRepository, textRepository, historyImageRepository,
					videoRepository, audioForLaManoNegra, textsForLaManoNegra, historyImagesForLaManoNegra,
					List.of(), laManoNegra, researcherInDB);

			saveHistory(historyRepository, audioRepository, textRepository, historyImageRepository,
					videoRepository, audioForTiemposDeBuziraco, textsForTiemposDeBuziraco, historyImagesForTiemposDeBuziraco,
					List.of(), tiemposDeBuziraco, researcherInDB);

            saveHistory(historyRepository, audioRepository, textRepository, historyImageRepository,
					videoRepository, audioForEnAtomosVolando, textsForEnAtomosVolando, historyImagesForEnAtomosVolando,
					List.of(), enAtomosVolando, researcherInDB);

			saveHistory(historyRepository, audioRepository, textRepository, historyImageRepository,
					videoRepository, audioForElTerremotoDe1925, textsForElTerremotoDe1925, historyImagesForElTerremotoDe1925,
					List.of(), elTerremotoDe1925, researcherInDB);

			saveHistory(historyRepository, audioRepository, textRepository, historyImageRepository,
					videoRepository, audioForTristeNavidad, textsForTristeNavidad, historyImagesForTristeNavidad,
					List.of(), tristeNavidad, researcherInDB);

			BICHistory parqueArtesanalLomaDeLaCruzAndLaManoNegra = associateHistoryAndBIC(bicHistoryRepository, laManoNegra, parqueArtesanalLomaDeLaCruz);
			BICHistory elCerroDeLasTresCrucesAndTiemposDeBuziraco = associateHistoryAndBIC(bicHistoryRepository, tiemposDeBuziraco, elCerroDeLasTresCruces);
			associateHistoryAndBIC(bicHistoryRepository, enAtomosVolando, cementerioCentral);
			associateHistoryAndBIC(bicHistoryRepository, elTerremotoDe1925, ermita);
			associateHistoryAndBIC(bicHistoryRepository, tristeNavidad, plazaCayzedo);

			createRoute(routeRepository, routeBICHistoryRepository, "Historias aterradoras", "Historias de situaciones paranormales", tourismManagerUserInDB,
					List.of(parqueArtesanalLomaDeLaCruzAndLaManoNegra, elCerroDeLasTresCrucesAndTiemposDeBuziraco));

		};
	}

	private static void createRoute(RouteRepository routeRepository, RouteBICHistoryRepository routeBICHistoryRepository,
									 String name, String description, HistouricUser owner,
									 List<BICHistory> bicHistoryList) {
		Route route = Route.builder()
				.name(name)
				.description(description)
				.owner(owner)
				.build();
		routeRepository.save(route);
		int order = 0;
		for (BICHistory bicHistory : bicHistoryList) {
			order++;
			RouteBICHistoryPK routeBICHistoryPK = RouteBICHistoryPK.builder()
					.bicHistoryPK(bicHistory.getId())
					.routeId(route.getId())
					.build();
			RouteBICHistory routeBICHistory = RouteBICHistory.builder()
					.id(routeBICHistoryPK)
					.bicOrder(order)
					.build();
			routeBICHistoryRepository.save(routeBICHistory);
		}
	}

	private static BICHistory associateHistoryAndBIC (BICHistoryRepository bicHistoryRepository, History history, BIC bic) {
		BICHistoryPK bicHistoryPK = BICHistoryPK.builder()
				.bicId(bic.getId())
				.historyId(history.getId())
				.build();
		BICHistory bicHistory = BICHistory.builder()
				.id(bicHistoryPK)
				.build();
		return bicHistoryRepository.save(bicHistory);
	}

	private static void saveHistory(HistoryRepository historyRepository,
									   AudioRepository audioRepository,
									   TextRepository textRepository,
									   HistoryImageRepository historyImageRepository,
									   VideoRepository videoRepository,
									   Audio audioForHistory,
									   List<Text> textsForHistory,
									   List<HistoryImage> historyImagesForHistory,
									   List<Video> videosForHistory,
									   History history,
									   HistouricUser researcherInDB) {
		audioRepository.save(audioForHistory);
		history.setOwner(researcherInDB);
		historyRepository.save(history);

        for (Text text : textsForHistory) {
            text.setHistory(history);
            textRepository.save(text);
        }
		history.setTexts(textsForHistory);

		for (HistoryImage historyImage: historyImagesForHistory) {
			historyImage.setHistory(history);
			historyImageRepository.save(historyImage);
		}

		history.setImages(historyImagesForHistory);

		for (Video video: videosForHistory) {
			video.setHistory(history);
			videoRepository.save(video);
		}

		history.setVideos(videosForHistory);
	}

}
