
# Demo Automation Framework for DApp Testing with MetaMask

This project is an automated testing framework designed to test Decentralized Applications (DApps) with MetaMask integration. It provides a robust solution for automating the testing of Web3 applications using Selenium WebDriver and TestNG.

## 🚀 Features

- Automated testing of DApps with MetaMask integration
- Selenium WebDriver for web automation
- TestNG for test execution and management
- Allure reporting for detailed test reports
- Excel data handling with Apache POI
- CSV data handling with OpenCSV
- ExtentReports for test reporting
- Log4j for logging

## 🛠️ Prerequisites

- Java JDK 8 or higher
- Maven
- Chrome browser
- MetaMask extension (included in the project)

## 📦 Dependencies

The project uses the following major dependencies:
- Selenium WebDriver 4.32.0
- TestNG 7.11.0
- Apache POI 3.9
- OpenCSV 5.9
- ExtentReports 2.41.2
- Allure TestNG 2.29.1
- Log4j 1.2.17

## 🏗️ Project Structure

```
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── dappwithmm/
│   │               └── qa/
│   │                   ├── Analyzer/         # Test failure analyzer
│   │                   ├── base/            # Base classes and configurations
│   │                   ├── config/          # Configuration files
│   │                   ├── pages/           # Page Object Model classes
│   │                   └── utils/           # Utility classes
│   └── test/
│       └── java/
│           └── com/
│               └── dappwithmm/
│                   └── qa/
│                       └── testcases/       # Test case implementations
├── TestRunner/
│   └── testng.xml                          # TestNG configuration
├── test-output/                            # Test execution output
├── allure-results/                         # Allure report results
├── target/                                 # Maven build output
├── pom.xml                                 # Maven dependencies and plugins
└── README.md
```

## 🚀 Getting Started

1. Clone the repository
2. Install MetaMask extension in Chrome browser
3. Configure MetaMask with your test network and accounts
4. Update test data in the appropriate data files
5. Run the tests using Maven

## 🧪 Running Tests

To run the tests, use the following Maven command:

```bash
mvn clean test
```

The test execution is configured in `TestRunner/testng.xml`.

## 📊 Reports

The framework generates multiple types of reports:
- Allure Reports: For detailed test execution reports
- ExtentReports: For HTML-based test reports
- TestNG Reports: Available in the test-output directory

## 🔧 Configuration

- Test data can be configured in Excel or CSV files
- Browser settings can be modified in the configuration files
- MetaMask settings should be configured before running tests

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👥 Authors

- **Raja Hassan** - *Initial work* - [RajaHassanX36](https://github.com/RajaHassanX36)

## 🙏 Acknowledgments

- Selenium WebDriver
- TestNG
- MetaMask
- All other open-source contributors
