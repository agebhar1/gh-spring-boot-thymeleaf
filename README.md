# Bugreport

## Spring Boot v2.7.5

```text
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.7.5)

2022-11-14 18:22:21.248  INFO 155770 --- [           main] com.example.demo.DemoApplicationTests    : Starting DemoApplicationTests using Java 17.0.5 on babbage with PID 155770 (started by agebhar1 in /home/agebhar1/src/github.com/agebhar1/gh-spring-boot-thymeleaf/v2.7.5)
2022-11-14 18:22:21.249  INFO 155770 --- [           main] com.example.demo.DemoApplicationTests    : No active profile set, falling back to 1 default profile: "default"
2022-11-14 18:22:21.930  INFO 155770 --- [           main] o.s.b.a.w.s.WelcomePageHandlerMapping    : Adding welcome page template: index
2022-11-14 18:22:22.178  INFO 155770 --- [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2022-11-14 18:22:22.179  INFO 155770 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2022-11-14 18:22:22.179  INFO 155770 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 0 ms
2022-11-14 18:22:22.471  INFO 155770 --- [           main] com.example.demo.DemoApplicationTests    : Started DemoApplicationTests in 1.361 seconds (JVM running for 1.937)
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.036 s - in com.example.demo.DemoApplicationTests
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.957 s
[INFO] Finished at: 2022-11-14T18:22:23+01:00
[INFO] ------------------------------------------------------------------------
```

## Spring Boot v3.0.0-RC2

```text
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::            (v3.0.0-RC2)

2022-11-14T18:22:44.173+01:00  INFO 156844 --- [           main] com.example.demo.DemoApplicationTests    : Starting DemoApplicationTests using Java 17.0.5 with PID 156844 (started by agebhar1 in /home/agebhar1/src/github.com/agebhar1/gh-spring-boot-thymeleaf/v3.0.0-RC2)
2022-11-14T18:22:44.174+01:00  INFO 156844 --- [           main] com.example.demo.DemoApplicationTests    : No active profile set, falling back to 1 default profile: "default"
2022-11-14T18:22:44.710+01:00  INFO 156844 --- [           main] o.s.b.a.w.s.WelcomePageHandlerMapping    : Adding welcome page template: index
2022-11-14T18:22:44.798+01:00  INFO 156844 --- [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2022-11-14T18:22:44.799+01:00  INFO 156844 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2022-11-14T18:22:44.801+01:00  INFO 156844 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 1 ms
2022-11-14T18:22:45.050+01:00  INFO 156844 --- [           main] com.example.demo.DemoApplicationTests    : Started DemoApplicationTests in 1.031 seconds (process running for 1.529)
[ERROR] Tests run: 2, Failures: 0, Errors: 2, Skipped: 0, Time elapsed: 1.536 s <<< FAILURE! - in com.example.demo.DemoApplicationTests
[ERROR] shouldServeIndex_mockMvc  Time elapsed: 0.236 s  <<< ERROR!
jakarta.servlet.ServletException: Request processing failed: java.lang.IllegalArgumentException: Cannot build an application for a request which servlet context does not match with the application that it is being built for.
        at com.example.demo.DemoApplicationTests.shouldServeIndex_mockMvc(DemoApplicationTests.java:26)
Caused by: java.lang.IllegalArgumentException: Cannot build an application for a request which servlet context does not match with the application that it is being built for.
        at com.example.demo.DemoApplicationTests.shouldServeIndex_mockMvc(DemoApplicationTests.java:26)

[ERROR] shouldServeIndex_webClient  Time elapsed: 0.026 s  <<< ERROR!
java.io.IOException: jakarta.servlet.ServletException: Request processing failed: java.lang.IllegalArgumentException: Cannot build an application for a request which servlet context does not match with the application that it is being built for.
        at com.example.demo.DemoApplicationTests.shouldServeIndex_webClient(DemoApplicationTests.java:33)
Caused by: jakarta.servlet.ServletException: Request processing failed: java.lang.IllegalArgumentException: Cannot build an application for a request which servlet context does not match with the application that it is being built for.
        at com.example.demo.DemoApplicationTests.shouldServeIndex_webClient(DemoApplicationTests.java:33)
Caused by: java.lang.IllegalArgumentException: Cannot build an application for a request which servlet context does not match with the application that it is being built for.
        at com.example.demo.DemoApplicationTests.shouldServeIndex_webClient(DemoApplicationTests.java:33)

```

## Analysis

In `org.thymeleaf.spring6.view.ThymeleafView` of `org.thymleaf:thymeleaf-spring6:3.1.0.RC1.jar` while render the request the check `org.thymeleaf.web.servlet.JakartaServletWebApplication#servletContextMatches` fails because the provided current context path is not equal to the requested one. In spring module v5 it's not checked.
