# Test Execution Guide

This project contains three test groups:

- Unit tests under `src/test/java/com/bank/unit/`
- Integration tests under `src/test/java/com/bank/integration/`
- E2E tests under `src/test/java/suites/banking/`

## Run a single unit test

```bash
./mvnw -DskipTests=false test -Dtest=TransferValidatorTest
```

On Windows:

```powershell
.\mvnw.cmd -DskipTests=false test -Dtest=TransferValidatorTest
```

## Run all unit tests

```bash
./mvnw test -Dtest=TransferValidatorTest,InterestCalculatorTest,PasswordValidatorTest,FeeCalculatorTest
```

## Run integration tests

```bash
./mvnw test -Dtest=TransactionApiIntegrationTest,AuthIntegrationTest
```

## Run E2E tests

```bash
./mvnw test -Dtest=LoginAndOverviewE2ETest,MoneyTransferE2ETest
```

## ChromeDriver note

The E2E tests use ChromeDriver. If the driver is not available on the PATH, set the system property before running Maven:

```bash
./mvnw test -Dtest=LoginAndOverviewE2ETest -Dwebdriver.chrome.driver=C:/path/to/chromedriver.exe
```

The E2E tests also fall back to WebDriverManager if `webdriver.chrome.driver` is not set.