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
										RouteThemeRepository routeThemeRepository,
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

		BIC rioAguacatal = BIC.builder()
				.name("Río Aguacatal")
				.latitude(3.4716058487791432)
				.longitude(-76.58505182751173)
				.description("El río Aguacatal es un río colombiano que nace en el Alto aguacatal, a 1.800 metros sobre el nivel del mar, en los límites de los municipios Dagua, La Cumbre, Yumbo y Cali.")
				.existss(true)
				.build();
		BICImage image1ForAguacatal = BICImage.builder()
				.imageUri("https://drive.google.com/uc?export=view&id=1DtqguEdwnpfygh4KFib3fh6wqc_4VDXY")
				.build();
		BICImage image2ForAguacatal = BICImage.builder()
				.imageUri("https://drive.google.com/uc?export=view&id=1hVRp6gwwC9ztp0W2lABJZWuJZAKdUGoJ")
				.build();

		Audio audioForElMonstruoDeLosMangones = Audio.builder()
				.needsUrlGen(false)
				.audioUri("https://drive.google.com/uc?export=view&id=1K_iH-OVAaDxecAA6ZIqfRcApKJM81UQ5")
				.build();

		HistoryImage historyImage1ForElMonstruoDeLosMangones = HistoryImage.builder()
				.needsUrlGen(false)
				.startTime(0)
				.imageUri("https://drive.google.com/uc?export=view&id=1X5SRqA5qNNVToKnf2vuUCy_xLxniMwXP")
				.build();

		List<Text> textsForElMonstruoDeLosMangones = new ArrayList<>();

		textsForElMonstruoDeLosMangones.add(Text.builder()
						.startTime(0)
						.content("En enero de 1964, la frase “éntrese que se lo lleva el Coco” quedó en desuso, el mito sucumbió ante una amenaza real: el Monstruo de los Mangones. Y no es que ese mes hubiera hecho su aparición, sino que a partir de ahí tuvo nombre propio, así lo bautizó la prensa.")
				.build());
		textsForElMonstruoDeLosMangones.add(Text.builder()
				.startTime(26)
				.content("Un par de meses antes, el seis de noviembre, una noticia había escandalizado a los caleños: el día anterior, por los lados del Bosque Municipal, en un mangón, se había encontrado el cadáver de un menor desnudo. Lo poco que quedaba de su cuerpo dejaba en evidencia que había sido torturado y abusado.")
				.build());
		textsForElMonstruoDeLosMangones.add(Text.builder()
				.startTime(41)
				.content("Por la ropa que se encontró cerca del lugar, su madre lo reconoció. Se supo entonces que era un niño de diez años, un hijo ejemplar que a su corta edad compartía con su madre la responsabilidad de generar el dinero para mantener a sus seis hermanos. Uno de esos niños que ante la vista de todos, sin que se le llamara trabajo infantil, sin que se mirara mal, sin que las autoridades se pronunciaran, se ganaba la vida recorriendo las calles de los barrios residenciales, vendiendo periódicos. Llevaba veinte días desaparecido.")
				.build());
		textsForElMonstruoDeLosMangones.add(Text.builder()
				.startTime(69)
				.content("Con el correr de los días, la cosa empeoró: un segundo niño apareció muerto el 4 de diciembre en un mangón de Prados del Norte, había sido violado; y ocho días después, se encontró uno más por los lados del río Aguacatal, estaba además desfigurado. Y las malas noticias del año no habían terminado: dos niños más aparecieron muertos, uno en vísperas de la Navidad, en Versalles, otro la semana siguiente, en Prados del Norte.")
				.build());
		textsForElMonstruoDeLosMangones.add(Text.builder()
				.startTime(93)
				.content("""
                        Y aunque el nombre se volvió común para los caleños, no había consenso sobre su aspecto: para unos se trataba de un degenerado, un asesino en serie, un sádico; para otros, de una pandilla. En lo que sí había acuerdo era en su modus operandi: sorprendía niños menores de doce años, los tiraba a un potrero, los desnudaba, los golpeaba, abusaba de ellos y les chuzaba el pecho con una aguja de acero.
                        
                        Ese último detalle hizo que se especulara con que su intención era obtener su sangre, así surgió su segundo nombre; “el vampiro humano”.
                        """)
				.build());
		textsForElMonstruoDeLosMangones.add(Text.builder()
				.startTime(125)
				.content("""
                        Antes de cerrar el mes, se hallaron otros tres niños en similares circunstancias. El monstruo, persona o pandilla, era una realidad. Los lotes enmalezados, los mangones —como se llamaban en esa época—, fueron considerados los lugares más peligrosos de Cali. Por eso, en los barrios populares, por definición lo más organizados, los más solidarios, los vecinos realizaron jornadas de limpieza. No había que dejarle ningún espacio disponible al monstruo.
                        
                        El pánico era evidente, si en el camino a la tienda o al colegio o a la iglesia o a donde fuera había un lote de por medio, la instrucción era clarísima “pásese al otro andén”. Y si había que escoger quién iba a la tienda, mejor si era una niña, al monstruo solo le gustaban los muchachos.
                        """)
				.build());
		textsForElMonstruoDeLosMangones.add(Text.builder()
				.startTime(165)
				.content("""
                        ¡No había para qué correr riesgos!
                        
                        El primero de abril se completarían trece casos. Cali no aguantaba más, las autoridades debían dar resultados, su inacción era evidente.
                        
                        Ese tres de abril, la Policía caleña tuvo una memorable salida en falso, en un comunicado de prensa anunció: que no había tal monstruo; que se trataba de “asesinos imaginarios”; que de los trece cadáveres hallados, solo cuatro eran reales y no tenían nada de especial, que eran casos “de común ocurrencia”.
                        
                        Y como si eso fuera poco, explicó que los otros nueve cadáveres, los de los niños no identificados, los que se sepultaron sin nombre en el cementerio de Siloé, eran muertos robados del cementerio y de la morgue que, en un acto de terrorismo, enemigos del Gobierno tiraban por ahí, en mangones, para confundir a la población, para generar zozobra. La respuesta de la realidad fue contundente, en la prensa, al lado de la imagen de la cumbre de autoridades que había llegado a esas conclusiones aparecía la fotografía de un niño asesinado el día anterior, tenía una aguja clavada en el pecho.
                        """)
				.build());
		textsForElMonstruoDeLosMangones.add(Text.builder()
				.startTime(225)
				.content("""
                        En la búsqueda de explicaciones, se llegó incluso a darle nombre y apellido al autor intelectual de estos crímenes. Se decía que no había un monstruo sino personas que trabajaban para el monstruo; que se trataba de un poderoso y reconocido empresario paisa, una especie de vampiro moderno que tenía una “enfermedad de la sangre”; que él, para aliviarse debía someterse a recurrentes transfusiones; que la sangre se la llevaban a su casa ubicada en las lomas de Santa Isabel; que ahí tenía médicos y enfermeras que se encargaban del proceso.
                        
                        —Pero si necesita sangre y tiene plata ¿Por qué tiene que matar niños? ¿Por qué no va a una clínica a que lo traten?
                        
                        —Porque la sangre debe estar fresca, se explicaba.
                        
                        —Además, no puede ser cualquier sangre, debe ser de niños porque es más pura.
                        
                        Y pronto se le fueron agregando detalles de corte racial.
                        
                        —Mejor si son niños de piel oscura, decían unos, son más fuertes.
                        
                        —No pueden ser de color, le contaminan la sangre, ripostaba la versión racista.
                        """)
				.build());

		textsForElMonstruoDeLosMangones.add(Text.builder()
				.startTime(279)
				.content("""
                        Y aunque esta explicación forma parte del imaginario caleño, a pesar de que aún hay quienes la repiten y la dan por cierta, lo único real, lo constatable, es que el empresario en cuestión murió en diciembre de 1963, un mes después del primer crimen del Monstruo de los Mangones, cuya estela de asesinatos, torturas y violaciones se extendería por una década.
                        
                        En 1974, el monstruo desapareció de repente, sin dejar rastro, en medio de la más terrible impunidad. Nunca se supo siquiera si se trató de uno o de varios asesinos ni si sus más de tres docenas de víctimas realmente fueron suyas. Las mamás respiraron aliviadas ¡El inocente Coco estaba de vuelta!
                        
                        Como si se tratara de una catarsis colectiva, poco a poco se dejó de hablar de mangones, en adelante serían potreros o lotes, toda una generación había aprendido que mangón eran sinónimo de peligro.
                        """)
				.build());

		History elMonstruoDeLosMangones = History.builder()
				.title("El monstruo de los mangones")
				.audio(audioForElMonstruoDeLosMangones)
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

		Audio audioForTiemposDeBuziraco = Audio.builder()
				.needsUrlGen(false)
				.audioUri("https://drive.google.com/uc?export=view&id=1p9ZrRYyTSHzpqtZQ7yBtTh8QIbHd6aFR")
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

		BIC testBIC = BIC.builder()
				.name("Test BIC")
				.latitude(3.451929471542790)
				.longitude(-76.5319398863672)
				.description("Test BIC")
				.existss(true)
				.build();
		Nickname nicknameTestBIC = Nickname.builder()
				.nickname("Test BIC")
				.build();

		Audio audioForHistory1 = Audio.builder()
				.needsUrlGen(true)
				.audioUri("audios/3bd1dfe8-f2c5-4890-8cc1-4004786a6a24_the-best-jazz-club-in-new-orleans-164472.mp3")
				.build();

		Audio audioForHistory2 = Audio.builder()
				.needsUrlGen(false)
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

		RouteTheme routeThemeOfMiedo = RouteTheme.builder()
				.name("Miedo")
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

			BIC ermitaInDB = bicRepository.save(ermita);
			BIC antiguoMataderoInDB = bicRepository.save(antiguoMatadero);
			BIC edifioOteroInDB = bicRepository.save(edifioOtero);
			BIC iglesiaSanFranciscoInDB = bicRepository.save(iglesiaSanFrancisco);
			BIC plazaCayzedoInDB = bicRepository.save(plazaCayzedo);
			BIC testBICInDB = bicRepository.save(testBIC);
			BIC rioAguacatalInDB = bicRepository.save(rioAguacatal);
			bicRepository.save(parqueArtesanalLomaDeLaCruz);
			bicRepository.save(elCerroDeLasTresCruces);

			image1ForAguacatal.setBic(rioAguacatalInDB);
			bicImageRepository.save(image1ForAguacatal);
			image2ForAguacatal.setBic(rioAguacatalInDB);
			bicImageRepository.save(image2ForAguacatal);
			image1ForparqueArtesanalLomaDeLaCruz.setBic(parqueArtesanalLomaDeLaCruz);
			bicImageRepository.save(image1ForparqueArtesanalLomaDeLaCruz);
			image2ForparqueArtesanalLomaDeLaCruz.setBic(parqueArtesanalLomaDeLaCruz);
			bicImageRepository.save(image2ForparqueArtesanalLomaDeLaCruz);
			image1ForElCerroDeLasTresCruces.setBic(elCerroDeLasTresCruces);
			bicImageRepository.save(image1ForElCerroDeLasTresCruces);
			image2ForElCerroDeLasTresCruces.setBic(elCerroDeLasTresCruces);
			bicImageRepository.save(image2ForElCerroDeLasTresCruces);

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
			nicknameTestBIC.setBic(testBICInDB);
			nicknameRepository.save(nicknameTestBIC);

			History history1InDB = saveHistory(historyRepository, audioRepository, textRepository, historyImageRepository,
					videoRepository, audioForHistory1, List.of(text1ForHistory1, text2ForHistory1), List.of(historyImage1ForHistory1,
							historyImage2ForHistory1), List.of(videoForHistory1), history1, researcherInDB);

			History history2InDB = saveHistory(historyRepository, audioRepository, textRepository, historyImageRepository,
					videoRepository, audioForHistory2, List.of(text1ForHistory2, text2ForHistory2), List.of(historyImage1ForHistory2,
							historyImage2ForHistory2), List.of(videoForHistory2), history2, researcherInDB);

			saveHistory(historyRepository, audioRepository, textRepository, historyImageRepository,
					videoRepository, audioForElMonstruoDeLosMangones, textsForElMonstruoDeLosMangones, List.of(historyImage1ForElMonstruoDeLosMangones),
					List.of(), elMonstruoDeLosMangones, researcherInDB);

			saveHistory(historyRepository, audioRepository, textRepository, historyImageRepository,
					videoRepository, audioForLaManoNegra, textsForLaManoNegra, historyImagesForLaManoNegra,
					List.of(), laManoNegra, researcherInDB);

			saveHistory(historyRepository, audioRepository, textRepository, historyImageRepository,
					videoRepository, audioForTiemposDeBuziraco, textsForTiemposDeBuziraco, historyImagesForTiemposDeBuziraco,
					List.of(), tiemposDeBuziraco, researcherInDB);

			BICHistoryPK bicHistoryPK1 = BICHistoryPK.builder()
					.bicId(ermitaInDB.getId())
					.historyId(history1InDB.getId())
					.build();
			BICHistory bicHistory1 = BICHistory.builder()
					.id(bicHistoryPK1)
					.build();
			bicHistoryRepository.save(bicHistory1);

			BICHistoryPK bicHistoryPK2 = BICHistoryPK.builder()
					.bicId(antiguoMataderoInDB.getId())
					.historyId(history1InDB.getId())
					.build();
			BICHistory bicHistory2 = BICHistory.builder()
					.id(bicHistoryPK2)
					.build();
			bicHistoryRepository.save(bicHistory2);

			BICHistoryPK bicHistoryPK3 = BICHistoryPK.builder()
					.bicId(edifioOteroInDB.getId())
					.historyId(history1InDB.getId())
					.build();
			BICHistoryPK bicHistoryPK4 = BICHistoryPK.builder()
					.bicId(edifioOteroInDB.getId())
					.historyId(history1InDB.getId())
					.build();
			BICHistory bicHistory3 = BICHistory.builder()
					.id(bicHistoryPK3)
					.build();
			BICHistory bicHistory4 = BICHistory.builder()
					.id(bicHistoryPK4)
					.build();
			bicHistoryRepository.save(bicHistory3);
			bicHistoryRepository.save(bicHistory4);

			BICHistoryPK bicHistoryPK5 = BICHistoryPK.builder()
					.bicId(iglesiaSanFranciscoInDB.getId())
					.historyId(history1InDB.getId())
					.build();
			BICHistory bicHistory5 = BICHistory.builder()
					.id(bicHistoryPK5)
					.build();
			BICHistoryPK bicHistoryPK6 = BICHistoryPK.builder()
					.bicId(iglesiaSanFranciscoInDB.getId())
					.historyId(history2InDB.getId())
					.build();
			BICHistory bicHistory6 = BICHistory.builder()
					.id(bicHistoryPK6)
					.build();

			bicHistoryRepository.save(bicHistory5);
			bicHistoryRepository.save(bicHistory6);

			BICHistory rioAguatalAndElMonstruoDeLosMangones = associateHistoryAndBIC(bicHistoryRepository, elMonstruoDeLosMangones, rioAguacatal);
			BICHistory parqueArtesanalLomaDeLaCruzAndLaManoNegra = associateHistoryAndBIC(bicHistoryRepository, laManoNegra, parqueArtesanalLomaDeLaCruz);
			BICHistory elCerroDeLasTresCrucesAndTiemposDeBuziraco = associateHistoryAndBIC(bicHistoryRepository, tiemposDeBuziraco, elCerroDeLasTresCruces);

			routeThemeRepository.save(routeThemeOfMiedo);
			createRoute(routeRepository, routeBICHistoryRepository, "Historias aterradoras", "Historias de situaciones paranormales y de un crimen sin resolver", tourismManagerUserInDB, routeThemeOfMiedo,
					List.of(rioAguatalAndElMonstruoDeLosMangones, parqueArtesanalLomaDeLaCruzAndLaManoNegra, elCerroDeLasTresCrucesAndTiemposDeBuziraco));

		};
	}

	private static void createRoute(RouteRepository routeRepository, RouteBICHistoryRepository routeBICHistoryRepository,
									 String name, String description, HistouricUser owner, RouteTheme theme,
									 List<BICHistory> bicHistoryList) {
		Route route = Route.builder()
				.name(name)
				.description(description)
				.owner(owner)
				.theme(theme)
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

	private static History saveHistory(HistoryRepository historyRepository,
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
		return history;
	}

}
