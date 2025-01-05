# Ldap-Util


## Why I created this project?

I had a lot of issues during integrating client LDAP/AD infrastructure with existing spring-boot applications. 

While everything was working with [Apache Directory Studio](https://directory.apache.org/studio/) or with [ldap-utils](https://ubuntu.com/server/docs/install-and-configure-ldap), Sprig Boot application had problems with authenticating to given services.

#### Solution?

I created a library **io.github.inyourhead:ldap-util**, which provides ability to test LDAP/AD connection in easy way. 

It uses existing spring-security features (spring-security-ldap) to ensure that your application will work as expected.
___

## Requirements to build and run from source

You need:

- Java 17+
- Maven 3.8.4+
- (optional) docker/docker-compose for running **ldap** tests
- (optional) standalone instance of Windows Server 2016+ to run **ad** tests

### Installation

Run in terminal:

```shell

./mvnw clean install -DskipTests

```

### Running tests

To run tests you need to configure [**docker**/**docker compose**](https://docs.docker.com/compose/install/)

#### Running ldap tests

To run ldap tests type in terminal: 

```shell

./mvnw clean install -Pldap

```

#### Running ad tests

To run ad tests type in terminal:

```shell

./mvnw clean install -Pad -Dad.http.port=<ad-port> -Dad.hostname=<ad-hostname> -Dad.scheme=<ldap/ldaps>

```

See **Configure AD before testing** for more details

### Run app from source

After installation step you should have jar file named `ldap-util-app.jar` in `/ldap-util/app/target/` directory

Run it like shown below:

```shell
java -jar -Dspring.profiles.active=ldap,ad ldap-util-app.jar
```

Application should be available on http://localhost:8080/

## How to use *util* library in my application?

Be aware, that `<version>` is related to used spring boot version.

Add maven dependency to your application:

```xml

        <dependency>
            <groupId>io.github.inyourhead</groupId>
            <artifactId>util</artifactId>
            <version>3.4.1</version>
        </dependency>

```
(You can find it [here](https://central.sonatype.com/artifact/io.github.inyourhead/ldap-util))

And in your spring boot application add:

```java

@Import(LdapUtilAutoConfiguration.class)
public class LdapUtilApplication {

    public static void main(String[] args) {
        SpringApplication.run(LdapUtilApplication.class, args);
    }

}
```

to allow autowiring 

```java 

@Service
public class MyUtilService {

    @Autowired
    AuthService<AdConfig> adAuthService;

    @Autowired
    AuthService<LdapConfig> ldapAuthService;
    
    public void makeOperation() {
        
        //TODO implement me!
        boolean isValidAdUserAndConfig = adAuthService.autenticate(Credentials, AdConfig);
        boolean isValidLdapUserAndConfig = ldapAuthService.autenticate(Credentials, LdapConfig);
        
    }

}

```

## I want to run application only!

Sure. Grab this  [docker-compose.yml](./ldap-util-app/docker-compose.yml) file and run it!

You may also use docker command by running:

```shell
docker run -p 8080:8080 --name ldap-util-app -d inyourhead/ldap-util-app:3.4.1 
```

## Roadmap

- add more tests for AD
- export configuration from AD to allow testing on you own AD server
- allow to search in LDAP/AD
- return spring boot configuration associated with given authentication
- change package names according to groupId