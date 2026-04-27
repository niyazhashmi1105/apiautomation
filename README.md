# API Automation Framework

A comprehensive REST API automation testing framework built with Java, REST-assured, and TestNG. This framework provides a robust structure for testing RESTful APIs with built-in logging, listeners, and CI/CD integration.

## Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Test Reports](#test-reports)
- [CI/CD Integration](#cicd-integration)
- [Writing Tests](#writing-tests)
- [Contributing](#contributing)
- [License](#license)

## Features

- ✅ RESTful API testing with REST-assured
- ✅ TestNG framework for test organization and execution
- ✅ Page Object Model (POM) pattern for better maintainability
- ✅ Custom test listeners for enhanced reporting
- ✅ Request/Response filters for logging
- ✅ Data-driven testing support with Jackson
- ✅ Log4j2 integration for comprehensive logging
- ✅ Maven-based build and dependency management
- ✅ GitHub Actions CI/CD pipeline
- ✅ Modular and scalable architecture

## Technology Stack

| Technology | Version | Purpose |
|-----------|---------|---------|
| Java | 17 | Programming Language |
| Maven | 3.x | Build Tool & Dependency Management |
| REST-assured | 5.3.0 | API Testing Library |
| TestNG | 7.10.2 | Testing Framework |
| Jackson | 2.18.2 | JSON Serialization/Deserialization |
| Log4j2 | 2.20.0 | Logging Framework |
| Lombok | 1.18.38 | Boilerplate Code Reduction |

## Project Structure

```
apiautomation/
├── .github/
│   └── workflows/
│       └── maven.yml              # GitHub Actions CI/CD workflow
├── src/
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── api/
│       │           ├── base/      # Base test classes and configurations
│       │           ├── filters/   # Request/Response filters for logging
│       │           ├── listeners/ # TestNG listeners (e.g., TestListener)
│       │           ├── models/    # POJO classes for request/response
│       │           └── tests/     # Test classes
│       │               ├── AuthServiceTest.java
│       │               ├── CreateBookingTest.java
│       │               └── GetAllBookingIdsTest.java
│       └── resources/             # Test data and configuration files
├── logs/                          # Test execution logs
├── pom.xml                        # Maven configuration
├── testng.xml                     # TestNG suite configuration
└── README.md                      # Project documentation
```

## Prerequisites

Before running this project, ensure you have the following installed:

- **Java Development Kit (JDK)**: Version 17 or higher
  ```bash
  java -version
  ```

- **Apache Maven**: Version 3.6 or higher
  ```bash
  mvn -version
  ```

- **Git**: For cloning the repository
  ```bash
  git --version
  ```

- **IDE** (Optional but recommended): IntelliJ IDEA, Eclipse, or VS Code

## Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/niyazhashmi1105/apiautomation.git
   cd apiautomation
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Verify installation**
   ```bash
   mvn clean compile
   ```

## Configuration

### Maven Configuration (pom.xml)

The `pom.xml` file includes all necessary dependencies and build configurations:

- **Surefire Plugin**: Configured to run TestNG suites
- **Dynamic Suite Selection**: Use `-Dsuite=testng` to specify which XML file to run
- **Java Version**: Configured for Java 17

### TestNG Configuration (testng.xml)

The `testng.xml` file defines the test suite structure:

```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="API Test Suite">
    <listeners>
        <listener class-name="com.api.listeners.TestListener"/>
    </listeners>
    <test name="API Tests">
        <classes>
            <class name="com.api.tests.AuthServiceTest"/>
            <class name="com.api.tests.CreateBookingTest"/>
            <class name="com.api.tests.GetAllBookingIdsTest"/>
        </classes>
    </test>
</suite>
```

### Log4j2 Configuration

Configure logging by creating a `log4j2.xml` file in `src/test/resources/`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
        </Console>
        <File name="File" fileName="logs/automation.log">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
        </File>
    </Appenders>
    <Loggers>
        <Root level="info">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="File"/>
        </Root>
    </Loggers>
</Configuration>
```

## Running Tests

### Run All Tests

Execute all tests defined in the TestNG suite:

```bash
mvn clean test -Dsuite=testng
```

### Run Specific Test Class

Execute a specific test class:

```bash
mvn clean test -Dtest=CreateBookingTest
```

### Run Tests from IDE

1. Open the project in your IDE
2. Navigate to `testng.xml`
3. Right-click and select "Run"

### Run with Custom Parameters

```bash
mvn clean test -Dsuite=testng -DbaseUrl=https://api.example.com
```

## Test Reports

### TestNG Reports

After test execution, TestNG generates HTML reports in the `test-output` folder:

- **Location**: `target/surefire-reports/`
- **Main Report**: `index.html`
- **Detailed Report**: `emailable-report.html`

### Logs

Test execution logs are stored in:

- **Console Output**: Real-time logs during execution
- **File Logs**: `logs/automation.log`

### Viewing Reports

```bash
# Open HTML report in browser (Mac/Linux)
open target/surefire-reports/index.html

# Open HTML report in browser (Windows)
start target/surefire-reports/index.html
```

## CI/CD Integration

This project includes GitHub Actions workflow for automated testing on every push and pull request.

### Workflow Configuration (.github/workflows/maven.yml)

```yaml
name: Java CI with Maven

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v2
    - name: Set up JDK 17
      uses: actions/setup-java@v2
      with:
        java-version: '17'
        distribution: 'adopt'
    - name: Build with Maven
      run: mvn clean install
    - name: Run tests
      run: mvn test -Dsuite=testng
```

### Triggering CI/CD

The workflow automatically triggers on:
- Push to `main` branch
- Pull requests to `main` branch

## Writing Tests

### Basic Test Structure

```java
package com.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ExampleTest {
    
    @Test
    public void testGetEndpoint() {
        given()
            .baseUri("https://restful-booker.herokuapp.com")
            .header("Content-Type", "application/json")
        .when()
            .get("/booking")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }
}
```

### Using Models

```java
// Create a POJO class in models package
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
}

// Use in test
@Test
public void testCreateBooking() {
    BookingRequest booking = new BookingRequest(
        "John", "Doe", 100, true, new BookingDates("2024-01-01", "2024-01-05")
    );
    
    given()
        .contentType(ContentType.JSON)
        .body(booking)
    .when()
        .post("/booking")
    .then()
        .statusCode(200);
}
```

### Adding Custom Filters

```java
// In filters package
public class LoggingFilter implements Filter {
    @Override
    public Response filter(FilterableRequestSpecification requestSpec, 
                          FilterableResponseSpecification responseSpec, 
                          FilterContext ctx) {
        Response response = ctx.next(requestSpec, responseSpec);
        // Add custom logging logic
        return response;
    }
}

// Use in test
given()
    .filter(new LoggingFilter())
    .when()
    .get("/endpoint");
```

## Project Components

### Base Package
Contains base test classes with common setup and teardown methods, base configurations, and utilities.

### Filters Package
Contains request and response filters for:
- Request/Response logging
- Authentication token injection
- Custom headers manipulation
- Request/Response transformation

### Listeners Package
Contains TestNG listeners for:
- Test execution logging
- Screenshot capture on failure
- Custom reporting
- Test result notifications

### Models Package
Contains POJO (Plain Old Java Objects) classes representing:
- API request payloads
- API response structures
- Data transfer objects (DTOs)

### Tests Package
Contains actual test classes organized by:
- Feature/Module (e.g., `AuthServiceTest`)
- API endpoint (e.g., `CreateBookingTest`)
- Functionality (e.g., `GetAllBookingIdsTest`)

## Best Practices

1. **Test Independence**: Each test should be independent and not rely on others
2. **Clear Naming**: Use descriptive names for test methods (e.g., `testCreateBookingWithValidData`)
3. **Assertions**: Include meaningful assertions to validate responses
4. **Data Management**: Use external data files or test data builders for test data
5. **Error Handling**: Implement proper error handling and logging
6. **Documentation**: Add JavaDoc comments for complex methods
7. **Code Reusability**: Use base classes and utility methods to avoid code duplication
8. **Version Control**: Commit regularly with meaningful commit messages

## Troubleshooting

### Common Issues

**Issue**: Tests fail with "Connection Refused"
```bash
# Solution: Check if API server is running and accessible
curl -I https://your-api-url.com
```

**Issue**: Maven dependencies not resolving
```bash
# Solution: Clean and rebuild
mvn clean install -U
```

**Issue**: Tests pass locally but fail in CI/CD
```bash
# Solution: Check environment variables and configurations
# Ensure base URLs and credentials are properly configured
```

## Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Coding Standards

- Follow Java naming conventions
- Write unit tests for new features
- Update documentation as needed
- Ensure all tests pass before submitting PR
- Use meaningful commit messages

## Future Enhancements

- [ ] Add API contract testing
- [ ] Implement parallel test execution
- [ ] Add performance testing capabilities
- [ ] Integrate with reporting tools (Allure, ExtentReports)
- [ ] Add database validation
- [ ] Implement API mocking for offline testing
- [ ] Add support for GraphQL APIs
- [ ] Enhance error reporting and debugging

## Support

For questions or issues:

- Create an issue in the [GitHub repository](https://github.com/niyazhashmi1105/apiautomation/issues)
- Contact the maintainer: [MD Niyaz Hashmi](https://github.com/niyazhashmi1105)

## License

This project is available for use under the terms specified by the repository owner.

## Acknowledgments

- REST-assured community for excellent documentation
- TestNG team for the powerful testing framework
- All contributors who help improve this framework

---

**Note**: This framework is designed for educational and professional use. Please ensure you have proper authorization before testing any APIs.

**Happy Testing! 🚀**
