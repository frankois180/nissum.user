# User API

### Reference Documentation
Api para crear usuarios 
### swagger
http://localhost:10035/swagger-ui/index.html#/user-controller/create

### Guides
Para correr la aplicacion se debe ejecutar la tarea de gradle bootRun, automaticamente se crearan los modelos de bd en memoria H2 y la aplicacion quedara disponible en el puerto 10035

### Ruta bd H2
http://localhost:10035/h2-console/login.jsp?jsessionid=d1ce22f8e8fd64a2f01048ff06138379

### Conexiones a la BD
Driver Class:	org.h2.Driver

JDBC URL: jdbc:h2:mem:testdb

User Name:sa

Password:

### Curl Ejemplo

curl --location --request POST 'http://localhost:10035/api/v1/users' \
--header 'Content-Type: application/json' \
--header 'Cookie: COOKIE_SUPPORT=true; GUEST_LANGUAGE_ID=en_US' \
--data-raw '{
"name": "francisco",
"email": "frankois180@gmail01.com",
"password": "123",
"phones": [
{
"number": "1234567",
"cityCode": "1",
"countryCode": "57"
}
]
}'