# HybridFramework

## Selenium page object hybrid automation framework using java & Maven & TestNG.

### Steps to Setup:
1. git clone
2. Import project in any Editor(Eclipse/IntelliJ)
3. Right-click on testRunner file available inside "src\test\java\testRunner"
4. Run as TestNG

## Project Structure
#### com.base:
> com.base: In this package, all the common web functions are defined.

#### com.config:
> com.base: This package is used to defined pre-configuration data like URL, Browser Name.

#### com.constant:
> com.base: In this package, all the constant path is defined. Like pages properties file.

#### com.base:
> com.base: This package is used to define all the java related functions. For now, the PropertyFileReader file is used to read the property file.

### Steps to create the feature, page property file, and step definition:
1. Create a feature file inside "src\test\resources\Features". Write steps inside the feature file.
2. Create a stepDefication file for the new feature file inside "src\test\java\stepDefinations".
3. Create a property file of a page inside "src\test\java\com\pages" and write all the xpaths of a page.
