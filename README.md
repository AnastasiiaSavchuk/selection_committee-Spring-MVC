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