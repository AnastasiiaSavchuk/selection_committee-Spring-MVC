 Spring_3 Web MVC
-
Required:

1. Migrate functionality related to core business logic of Servlet-based application to newly created Spring MVC project
   adapting the old one to the correct package structure, naming conventions, etc.:
    - get rid of all JSP views (now your service will be consuming and populating only JSON text format)
    - make your service RESTful (don't implement HATEOAS logic for now. It will be accomplished in the scope of next
      lecture)
    - the best approach is to start from the scratch and create an empty Spring Boot project using the 'Spring
      Initializr'. • avoid code copypaste from the old project, unless it relates to the business logic.
2. Create DTO classes and use them at the controller level.
3. Add logging to all layers of the application.

Optional (would be a great plus):

1. Use any of available open source mapping tool in order to remap from business classes to DTO and vice versa (
   BeanUtils, MapStruct, etc.)

Spring_4 Web MVC
-
Required:

1. Extend your service functionality by:
    - adding basic validation to DTO classes:
        - use standard set of hibernate validator annotations.
        - use different validation groups in combination with @Validated annotation.
    - implementing custom common and unified error handling functionality using Spring MVC components.
    - adding a logging interceptor to the new 'interceptor' package that will handle all requests and write a log with
      the user's session id.
    - adding the Spring Boot Actuator to your project and configuring the ‘/info’ endpoint of it.
    - extending your service RESTful endpoints by adding HATEOAS links to the relative resources.
    - extending your service RESTful endpoints by adding versioning alongside Swagger2 documentation.

Optional (would be a great plus):

1. Get hands-on experience working with the RestTemplate:
    - create a separate service that will make REST calls to your main service endpoints (GET, POST, PUT, PATCH, DELETE)
      and parse the response.

2. Create custom validation annotations using ConstraintValidatoror. Samples:
    - check whether there is a user in the database with such username during registration. If so, throw an error.
    - make a class level validation annotation that will check whether 2 passwords (‘password’ and ‘repeatPassword’
      fields) are the same during registration. If not, throw an error.

3. Add multilingual support for exception messages configuring the ‘MessageSource’ bean
    - https://www.baeldung.com/spring-custom-validation-message-source
4. Add Basic Security to your application. Sources:
    - https://spring.io/guides/topicals/spring-security-architecture
    - https://spring.io/guides/gs/securing-web/
    - https://docs.spring.io/spring-security/site/docs/5.4.5/reference/html5/
    - https://www.baeldung.com/spring-security-basic-authentication

Spring_5 Data
-
Required:

1. Get well acquainted with all topics mentioned in the lecture (especially with JPA and Spring Data JPA)

    2. Integrate your application with any relational database. You are free of choice regarding the relational database
       management system to be used.
        - integrate using Spring Data JPA (configure connection details, implement JPA repositories, etc.)
        - add JPA entity mapping including mappings between entities (@OneToOne, @ManyToOne, etc.)
        - make sure to use all/any of listed annotation: @Query, @NamedQuery, and @NamedNativeQuery.
        - make sure to use ‘pageable’ and ‘sortable’ reads from the database.
        - add transactions in your code using declarative or programmatic approach.

Optional (would be a great plus):

1. Make use of JdbcTemplate class somewhere in you code.

    2. Get well familiar with the @Transactional annotation (how it works under the hood, what CGlib & JDK Proxies are,
       physical vs logical transactions etc.). Sources:
        - https://docs.spring.io/spring-data/jpa/docs/current/reference/html/index.html#transactions
        - https://www.marcobehler.com/guides/spring-transaction-management-transactional-in-depth

Spring_6 Test
-
Required:

1. Cover all your business logic with the unit tests. Make sure your services have a 100% level of coverage. Tools to
   use:
    - JUnit 4 or 5
    - Mockito (with the PowerMockito if it is required)
    - Hamcrest Matchers

2. Cover all your APIs using MockMvc. Make sure to verify all validation checks that are implemented in your service.

3. Implement integration tests that run against the fully configured application with a temporary in-memory database
   using TestRestTemplate (or simple RestTemplate).

Optional (would be a great plus):

1. Get hands-on experience working with the network protocol analyzers such as Wireshark or Fiddler 2.

2. Get hands-on experience working with the Java VisualVM.

3. Extend your application with the mutation tests and make sure that all of the created mutations are killed. Popular
   java implementation:
    - https://pitest.org/