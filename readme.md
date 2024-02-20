# API First

Un enfoque de desarrollo de software que ha ganado terreno rápidamente en la industria.

## Objetivo

Generar clases e interfaces a partir de una especificación OpenApi

## Librerias utilizadas

- [Spring Boot : 3.2.2](https://start.spring.io/)
- [Open Api Generator : 7.3.0](https://github.com/OpenAPITools/openapi-generator)
- [Swagger-annotations : 2.2.20](https://mvnrepository.com/artifact/io.swagger.core.v3/swagger-annotations)
- [Jackson-databind-nullable : 0.2.6](https://github.com/OpenAPITools/jackson-databind-nullable)
- [Javax.servlet-api : 4.0.1](https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api/4.0.1)
- [Javax.annotation-api : 1.3.2](https://mvnrepository.com/artifact/javax.annotation/javax.annotation-api/1.3.2)
- [Validation-api : 2.0.1.Final](https://mvnrepository.com/artifact/javax.validation/validation-api/2.0.1.Final)
- [Java : 17]()

## Implementación

1. Se descarga un proyecto maven de `springboot` desde https://start.spring.io/, el cual tendra como dependencia las librerias anteriores mencionadas.

2. Se debe abrir el proyecto desde la IDE de su preferencia, al ubicarse en la ruta `/src/main/resources/` se debe copiar el archivo OAS `cuenta_bancaria_descripcion.yaml`, el cual contiene la especificación de la API.


3. Luego se modifica el archivo `pom.xml` con el siguiente código:

```java

<properties>
	<java.version>17</java.version>
	<openapi-generator.version>7.3.0</openapi-generator.version>
	<swagger.annotations.version>2.2.20</swagger.annotations.version>
	<jackson-nullable.version>0.2.6</jackson-nullable.version>
</properties>

<dependencies>
    <dependency>
        <groupId>io.swagger.core.v3</groupId>
        <artifactId>swagger-annotations</artifactId>
        <version>${swagger.annotations.version}</version>
    </dependency>
    <dependency>
        <groupId>org.openapitools</groupId>
        <artifactId>jackson-databind-nullable</artifactId>
        <version>${jackson-nullable.version}</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>4.0.1</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>javax.annotation</groupId>
        <artifactId>javax.annotation-api</artifactId>
        <version>1.3.2</version>
    </dependency>
    <dependency>
        <groupId>javax.validation</groupId>
        <artifactId>validation-api</artifactId>
        <version>2.0.1.Final</version>
    </dependency>
</dependencies>

<build>
	<plugins>


        <plugin>
				<groupId>org.openapitools</groupId>
				<artifactId>openapi-generator-maven-plugin</artifactId>
				<version>${openapi-generator.version}</version>
				<executions>
					<execution>
						<goals>
							<goal>generate</goal>
						</goals>
						<configuration>
							<skipValidateSpec>true</skipValidateSpec>
							<inputSpec>./src/main/resources/cuenta_bancaria_descripcion.yaml</inputSpec>
							<generateSupportingFiles>true</generateSupportingFiles>
                            <generatorName>spring</generatorName>
                            <strictSpec>true</strictSpec>
                            <generateApiTests>false</generateApiTests>
                            <generateApiDocumentation>false</generateApiDocumentation>
                            <generateModelTests>false</generateModelTests>
                            <generateModelDocumentation>false</generateModelDocumentation>
							<configOptions>
									<controllerThrowsExceptions>java.io.IOException,com.example.NotFoundException</controllerThrowsExceptions>
									<interfaceOnly>true</interfaceOnly>
									<skipDefaultInterface>true</skipDefaultInterface>
									<useBeanValidation>true</useBeanValidation>
									<useClassLevelBeanValidation>false</useClassLevelBeanValidation>
									<useTags>true</useTags>
									<java17>true</java17>
									<useOptional>false</useOptional>
									<hideGenerationTimestamp>true</hideGenerationTimestamp>
									<dateLibrary>java17</dateLibrary>
									<bigDecimalAsString>true</bigDecimalAsString>
									<useBeanValidation>true</useBeanValidation>
							</configOptions>
						</configuration>
					</execution>
				</executions>
		</plugin>
	</plugins>
</build>

```
        
Sobre la configuración de OpenAPI generator se explica las variables:

- inputSpec: Esta configuración especifica la ubicación del archivo de la especificación OpenAPI.

- generateSupportingFiles: Determina si se deben generar archivos de soporte, como utilidades y clases auxiliares. En este caso, se establece como true.

- generatorName: Indica el nombre del generador que se utilizará. En esta instancia, se emplea el generador "spring", diseñado para producir código compatible con proyectos Spring.

strictSpec: Controla si se aplicará una validación estricta a la especificación OpenAPI. Aquí se configura como true.

generateApiTests y generateModelTests: Estas configuraciones determinan si se deben generar pruebas automatizadas para las API y los modelos, respectivamente. En este caso, se establecen como false, lo que indica que no se generarán pruebas automatizadas.

configOptions: Este apartado define varias opciones de configuración para el generador. Algunas de estas opciones abarcan el manejo de excepciones en los controladores, el uso de validación de bean, así como la especificación del paquete de la API y el paquete del modelo.

4. Para generar el código se debera ejecutar lo siguiente:

```java
mvn clean install
```