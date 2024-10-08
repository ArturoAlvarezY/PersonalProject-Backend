-- Inserts para los roles
INSERT INTO roles (role_id, name) VALUES 
(default, 'ROLE_ADMIN'),
(default, 'ROLE_USER');

-- Inserts para los usuarios
INSERT INTO users (id, username, password) VALUES 
(default, 'adminLucas@gmail.com', '$2a$12$K0PNWuP6xtBLdt8iFc.Jee6eJuCOHJx/y7gpQdp.I5EXg0Ub.JnEa'),
(default, 'Bumby@gmail.com', '$2a$12$K0PNWuP6xtBLdt8iFc.Jee6eJuCOHJx/y7gpQdp.I5EXg0Ub.JnEa');

-- Asignación de roles a los usuarios
INSERT INTO user_roles (role_id, user_id) VALUES 
(1, 1),
(2, 2);

-- Creación de perfiles para los usuarios
INSERT INTO profiles (id, user_id) VALUES
(default, 1),
(default, 2);

INSERT INTO posts (id, title, content, profile_id, image_url) VALUES
(default, 'Juan - Cuidadores Express', '¡Juan ofrece servicios de Cuidadores Express! Vamos a tu hogar a cuidar de tu adorable mascota. Profesional y con años de experiencia en el cuidado de perros y gatos.', 1, 'https://randomuser.me/api/portraits/men/1.jpg'),
(default, 'Laura - Paseadores', '¿Necesitas un paseo para tu peludito? Laura es una paseadora experta, cuidará de tu mascota mientras disfruta del aire libre. Tiempo flexible y disponibilidad inmediata.', 1, 'https://randomuser.me/api/portraits/women/2.jpg'),
(default, 'Carlos - Educadores', 'Carlos es un educador certificado que te ayudará a mejorar los modales de tu mascota. Con técnicas de refuerzo positivo, hará que tu perro sea más obediente y feliz.', 1, 'https://randomuser.me/api/portraits/men/3.jpg'),
(default, 'María - Pasar la noche', '¿Tienes que salir de la ciudad? Deja a tu peludito con María en un espacio seguro y agradable para que pase la noche con los mejores cuidados. ¡Tranquilidad garantizada!', 1, 'https://randomuser.me/api/portraits/women/4.jpg'),
(default, 'Ana - Cuidadores de diferentes especies', 'Ana tiene experiencia cuidando no solo perros y gatos, sino también aves, reptiles y más. ¡Encuentra el cuidador ideal para tu tipo de mascota con Ana!', 1, 'https://randomuser.me/api/portraits/women/5.jpg'),
(default, 'Sofía - Servicio de veterinarios', 'Sofía es una veterinaria altamente recomendada por sus clientes. Si buscas el lugar ideal para encontrar a los mejores veterinarios para tu mascota, Sofía es la opción perfecta.', 1, 'https://randomuser.me/api/portraits/women/6.jpg'),
(default, 'Pedro - Cuidadores Express', 'Pedro es un cuidador que va a tu hogar para asegurarse de que tu mascota esté feliz y segura mientras estás fuera.', 1, 'https://randomuser.me/api/portraits/men/7.jpg'),
(default, 'Marta - Paseadores', 'Marta disfruta paseando a los perros en parques y áreas naturales. Ideal para quienes desean que sus mascotas disfruten del aire fresco.', 1, 'https://randomuser.me/api/portraits/women/8.jpg'),
(default, 'Jorge - Educadores', 'Jorge se especializa en entrenar cachorros y enseñarles buenos hábitos desde una edad temprana.', 1, 'https://randomuser.me/api/portraits/men/9.jpg'),
(default, 'Sara - Pasar la noche', 'Sara ofrece un servicio de hospedaje cómodo y seguro para tu mascota mientras estás fuera. Servicio nocturno disponible.', 1, 'https://randomuser.me/api/portraits/women/10.jpg'),
(default, 'Lucas - Cuidadores de diferentes especies', 'Lucas tiene experiencia cuidando todo tipo de mascotas, desde aves hasta reptiles, asegurando un entorno adecuado para cada una.', 1, 'https://randomuser.me/api/portraits/men/11.jpg'),
(default, 'Clara - Servicio de veterinarios', 'Clara es una veterinaria con más de 10 años de experiencia en animales pequeños y exóticos. Ideal para cualquier consulta veterinaria.', 1, 'https://randomuser.me/api/portraits/women/12.jpg'),
(default, 'Luis - Cuidadores Express', 'Luis te ofrece un servicio de cuidado a domicilio de mascotas con seguimiento en tiempo real para tu tranquilidad.', 1, 'https://randomuser.me/api/portraits/men/13.jpg'),
(default, 'Daniela - Paseadores', 'Daniela es conocida por pasear grupos de perros de manera profesional, asegurando la diversión y el ejercicio de tu mascota.', 1, 'https://randomuser.me/api/portraits/women/14.jpg'),
(default, 'Fernando - Educadores', 'Fernando utiliza métodos de adiestramiento avanzado para mejorar la conducta de perros grandes y pequeños.', 1, 'https://randomuser.me/api/portraits/men/15.jpg'),
(default, 'Carolina - Pasar la noche', 'Carolina tiene una guardería nocturna acogedora donde tu mascota puede quedarse cómodamente durante tu ausencia.', 1, 'https://randomuser.me/api/portraits/women/16.jpg'),
(default, 'Roberto - Cuidadores de diferentes especies', 'Roberto es un especialista en cuidar animales menos comunes como hurones y conejos.', 1, 'https://randomuser.me/api/portraits/men/17.jpg'),
(default, 'Patricia - Servicio de veterinarios', 'Patricia tiene una clínica veterinaria especializada en animales de compañía, con atención de emergencias las 24 horas.', 1, 'https://randomuser.me/api/portraits/women/18.jpg'),
(default, 'Alex - Cuidadores Express', 'Alex ofrece un servicio de Cuidadores Express con seguimiento por videollamada para que veas cómo está tu mascota en todo momento.', 1, 'https://randomuser.me/api/portraits/men/19.jpg'),
(default, 'Verónica - Paseadores', 'Verónica pasea a tu mascota por parques naturales, asegurando una experiencia divertida y segura.', 1, 'https://randomuser.me/api/portraits/women/20.jpg'),
(default, 'Gabriel - Educadores', 'Gabriel es un adiestrador especializado en resolver problemas de comportamiento y ansiedad en perros.', 1, 'https://randomuser.me/api/portraits/men/21.jpg'),
(default, 'Isabel - Pasar la noche', 'Isabel ofrece servicios para que tu mascota pase la noche en un ambiente hogareño y seguro, con atención personalizada.', 1, 'https://randomuser.me/api/portraits/women/22.jpg'),
(default, 'Esteban - Cuidadores de diferentes especies', 'Esteban tiene conocimientos en el cuidado de animales como tortugas y serpientes, proporcionando las condiciones óptimas para su bienestar.', 1, 'https://randomuser.me/api/portraits/men/23.jpg'),
(default, 'Olga - Servicio de veterinarios', 'Olga es veterinaria especialista en animales exóticos y ofrece consultas y tratamientos únicos para estas especies.', 1, 'https://randomuser.me/api/portraits/women/24.jpg'),
(default, 'Diego - Cuidadores Express', 'Diego es tu cuidador de confianza para cualquier mascota, con referencias impecables y servicio de calidad.', 1, 'https://randomuser.me/api/portraits/men/25.jpg'),
(default, 'Monica - Paseadores', 'Monica te asegura paseos diarios para tu mascota, con control de tiempos y rutas personalizadas.', 1, 'https://randomuser.me/api/portraits/women/26.jpg'),
(default, 'Sebastián - Educadores', 'Sebastián ofrece programas de adiestramiento en obediencia y conducta con resultados garantizados.', 1, 'https://randomuser.me/api/portraits/men/27.jpg'),
(default, 'Lucía - Pasar la noche', 'Lucía tiene una estancia tranquila y hogareña para mascotas donde pueden pasar la noche con el mejor cuidado.', 1, 'https://randomuser.me/api/portraits/women/28.jpg'),
(default, 'Tomás - Cuidadores de diferentes especies', 'Tomás ofrece cuidados especializados para aves y reptiles. Tu mascota estará en las mejores manos.', 1, 'https://randomuser.me/api/portraits/men/29.jpg'),
(default, 'Paula - Servicio de veterinarios', 'Paula tiene una clínica veterinaria moderna con tecnología avanzada para asegurar el mejor cuidado de tu mascota.', 1, 'https://randomuser.me/api/portraits/women/30.jpg'),
(default, 'Nico - Cuidadores Express', 'Nico te ofrece la opción de cuidar a tu mascota a domicilio con informes detallados y constantes sobre su bienestar.', 1, 'https://randomuser.me/api/portraits/men/31.jpg'),
(default, 'Elena - Paseadores', 'Elena pasea a tu mascota con responsabilidad, garantizando un tiempo divertido y seguro para ambos.', 1, 'https://randomuser.me/api/portraits/women/32.jpg'),
(default, 'Álvaro - Educadores', 'Álvaro es experto en modificar comportamientos negativos en perros y mejorar su respuesta en situaciones cotidianas.', 1, 'https://randomuser.me/api/portraits/men/33.jpg'),
(default, 'Raquel - Pasar la noche', 'Raquel ofrece el servicio perfecto para que tu mascota pase la noche en un entorno confortable, vigilado y seguro.', 1, 'https://randomuser.me/api/portraits/women/34.jpg'),
(default, 'Iván - Cuidadores de diferentes especies', 'Iván tiene experiencia en cuidar y alimentar animales poco comunes como camaleones y erizos.', 1, 'https://randomuser.me/api/portraits/men/35.jpg'),
(default, 'Miguel - Servicio de veterinarios', 'Miguel ofrece servicios veterinarios con diagnóstico avanzado y atención personalizada.', 1, 'https://randomuser.me/api/portraits/men/36.jpg');
