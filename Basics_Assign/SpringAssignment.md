**UZAIF R. ISANI**

1\. What is Dependency Injection in Spring, and how is it different from regular object creation using new?

Dependency Injection (DI) in Spring is a design pattern where objects receive their dependencies from an external source (the Spring container) rather than creating them internally.

With regular object creation using new, your class is responsible for:

1. Creating its dependencies
1. Managing their lifecycle
1. Handling configuration

![Image](https://github.com/user-attachments/assets/33d3404e-dace-4277-99dd-922fa4ade5c2)

With Spring DI, the framework:

1. Creates the dependencies
1. Manages their lifecycle
1. "Injects" them into your class

![Image](https://github.com/user-attachments/assets/4677b925-c9d7-4a7f-8b15-b5b02cba7f86)



2\. You have two beans of the same type. What will happen if you use @Autowired without @Qualifier or @Primary?

When you have **multiple beans of the same type** in the Spring context and use @Autowired **without** @Qualifier or @Primary, Spring gets **confused** about which bean to inject, and it throws an error

![Image](https://github.com/user-attachments/assets/02961bdc-491c-4c92-9a2c-8100426856a8)

![Image](https://github.com/user-attachments/assets/68a31430-25e8-4382-b66c-13f92bab33e0)

![Image](https://github.com/user-attachments/assets/99311274-c348-4870-8dc6-ee147bb916e3)

![Image](https://github.com/user-attachments/assets/b6d80a72-ab2e-4998-8718-b0a36839b80d)

When Spring tries to start the application, it finds **two beans** (Dog and Cat) that implement the Animal interface. But in PetOwner, we used only @Autowired, so Spring doesn't know **which one to inject**.

This Result into an Error:

![Image](https://github.com/user-attachments/assets/cb7b3a5b-8b61-4e99-9f4d-5ea1e811bdfb)

We can fix this using two method 

1. By using Qualifier

![Image](https://github.com/user-attachments/assets/6ccf9e4e-7a8a-451c-a669-eacd6ce030a3)

1. By Using Primary

![Image](https://github.com/user-attachments/assets/9113ed8a-1426-4f0e-9715-59b0d366e9f3)


1. Explain how the Spring IoC container works behind the scenes when your application starts

   The IoC container is responsible for:

- Creating objects (beans)
- Managing their lifecycle
- Injecting dependencies (DI)
- Wiring the beans together

1\. **Application Starts**

- Spring Boot initializes the application context (ApplicationContext).
- @SpringBootApplication triggers component scanning, auto-configuration, and bean registration.

**2. Component Scanning**

- Spring scans packages starting from the package where your main class is located.
- It looks for classes annotated with @Component, @Service, @Repository, @Controller, and @Configuration.
- These classes are marked as **Spring Beans** and registered in the container.

**3. Bean Definition and Registration**

- Spring creates **bean definitions** (metadata) for all found components.
- It registers these definitions into the **BeanFactory** (part of ApplicationContext).

**4. Bean Instantiation**

- Spring uses **reflection** to instantiate beans.
- It follows **singleton** scope by default (only one object per type unless specified).

**5. Dependency Injection**

- Spring resolves dependencies using @Autowired, constructor, setter, or field injection.
- It checks the bean registry and injects the right objects into dependent beans.

**6. Post-Processing**

- If any beans implement interfaces like InitializingBean, DisposableBean, or use @PostConstruct, Spring calls those lifecycle methods.

**7. Bean Ready for Use**

- Once dependencies are injected and lifecycle callbacks are complete, the bean is ready.
- You can use it in your application via auto-wiring or ApplicationContext.getBean().

4\. You have a service class with multiple dependencies. Which injection method would you prefer (constructor, field, setter) and why?

I would prefer **constructor injection** for a service class with multiple dependencies, for these reasons:

1. **Immutability**: Dependencies can be declared as final, preventing accidental reassignment
1. **Testability**: Makes unit testing easier as dependencies are explicitly passed in constructors
1. **Mandatory Dependencies**: Clearly indicates required dependencies (they can't be null)
1. **Circular Dependency Detection**: Spring detects circular dependencies at startup rather than runtime
1. **Framework Independence**: Code works without Spring annotations, making it more portable

While field injection is more concise and setter injection allows optional dependencies, constructor injection provides the best combination of clarity, immutability, and testability.


5\. What is the role of @Component, @Service, @Repository, and @Controller?

They **all serve the same core purpose** — they mark a class as a **Spring Bean** — but they’re **semantically different** and help Spring understand the **role or layer** of that class in the application architecture.

**1️. @Component**

Generic stereotype for **any Spring-managed bean**.

Used when a class doesn't fit into service/repo/controller specifically.

**2. @Service**

Marks a class as a **Service layer** component (business logic).

Gives better clarity to the developer and enables AOP (like transaction management, etc.)

**3.  @Repository**

Indicates that the class is a **Data Access Object (DAO) in Simple Words for Database Operations**.

Enables **exception translation** — Spring automatically converts low-level DB exceptions into **Spring’s DataAccessException**.

**4. @Controller**

Marks the class as a **web controller** (MVC controller).

Used with @RequestMapping or @GetMapping to serve web responses.

6\. Write a small Spring Boot configuration class that defines a custom bean of type RestTemplate using @Bean.

A **helper class** that allows you to **send and receive data** (JSON, XML, plain text, etc.) to and from remote RESTful services **easily**.

![Image](https://github.com/user-attachments/assets/ea6fcc06-04d8-4b11-ba29-53c321d62ef3)

![Image](https://github.com/user-attachments/assets/f72190b2-610a-45b1-a7ee-c51d77a69133)

@Configuration: Tells Spring this class contains bean definitions.

@Bean: Registers the method's return value (RestTemplate) as a Spring bean.

This makes RestTemplate available for @Autowired anywhere in the app.

7\. What would happen if you forget to annotate your custom service class with @Component or any stereotype annotation?

If you forget to annotate your service class with @Component or any stereotype annotation, Spring won't recognize it during component scanning. This means:

1. The class won't be registered as a bean in the Spring container
1. You'll get a NoSuchBeanDefinitionException when trying to autowire it
1. The application might fail during startup if it's a required dependency

Example demonstrating the problem:

![Image](https://github.com/user-attachments/assets/7435f458-fa24-4d76-86fa-68d64c355ab5)

Missing @Service annotation

![Image](https://github.com/user-attachments/assets/61cf2fd1-c43d-499a-9baa-f2d4d864cd52)

And trying to autowire, but it will generate an Error because bean is not created because , without annotation Framework wasn’t able to find and create one.

![Image](https://github.com/user-attachments/assets/eb528e7b-2b74-4aeb-9d48-80853fd5b389)

8\. In application.properties, you define app.env=dev. How can you use this to conditionally load a bean only in dev environment?

Profile : It allows Spring to load beans **only when a specific profile is active**, like dev, test, or prod

![Image](https://github.com/user-attachments/assets/62c34658-fbcd-482c-9355-40858b8b6a9b)

This sets the active profile to dev.

**Why use @Profile?**

To define **environment-specific beans** like:

- Different DB configs for dev and prod
- Mock services in test
- Real implementations in prod

![Image](https://github.com/user-attachments/assets/e00b55d5-d1f8-4c82-a53e-4643cbbb4c50)

![Image](https://github.com/user-attachments/assets/5c8b23dc-ed3c-4bd1-9b7f-90d7845dac3d)

![Image](https://github.com/user-attachments/assets/fbd229d5-9f43-46a7-b3fe-ca3ef186c6ee)

If you change app.env=prod, the DevDataService won't load — and ProdDataService will.

9\. What’s the difference between @ComponentScan and @EnableAutoConfiguration in Spring Boot?

**@ComponentScan**:

- Tells Spring where to look for annotated components (services, repositories, etc.)
- Defines the packages to scan for Spring beans
- Explicitly declares which components should be available in your application

**@EnableAutoConfiguration**:

- Enables Spring Boot's auto-configuration mechanism
- Automatically configures beans based on classpath, properties, and existing beans
- Creates "opinionated" default configurations when certain libraries are detected

Practical use case:

- **@ComponentScan**: You define custom services, repositories, and controllers in your application packages that need to be discovered.
- **@EnableAutoConfiguration**: When you add Spring Data JPA to your classpath, Spring Boot auto-configures a DataSource, EntityManagerFactory, and JpaTransactionManager without explicit configuration.

Together, they allow you to focus on your application's unique logic (@ComponentScan) while getting sensible defaults for infrastructure concerns (@EnableAutoConfiguration).

Example :

![Image](https://github.com/user-attachments/assets/ff3f31db-e0ad-42c0-894f-a3fa09a0cffb)
Spring Boot will:

- Use @ComponentScan to detect your @RestController, @Service, etc. in the same or child packages.
- Use @EnableAutoConfiguration to auto-create a DispatcherServlet, Jackson JSON converter, and more for your REST app.

This 3 Annotations are present in a single @SpringBootApplication. If we write any of the  annotation after @SpringBootApplication, this overwrites.

