# Build application


1. **build application**

- abrir una terminal:

- Ubicarse en la reaiz del proyecto ejecutar

	> mvn clean install -Dmaven.test.skip=true

2. Para dockerizar la aplicacion ejecutar el comando

- mvn spring-boot:build-image -Dmaven.test.skip=true -Dspring-boot.build-image.imageName=registry.gitlab.com/veronikait/microservice-pcftubol-core


- Luego ubicarse en src/main/resouces y ejecutar
	
	> docker-compose up

- La aplicacion esta levantada y se puede probar con postman
