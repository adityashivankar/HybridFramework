# HybridFramework

## Selenium page object hybrid automation framework using java & Maven & TestNG.

### Steps to Setup:
1. git clone
2. Import project in any Editor(Eclispe/Intellij)
3. Right click on testRunner file available inside "src\test\java\testRunner"
4. Run as TestNG

## Project Structure
#### com.base:
> com.base: In this package all the common web funcations are defined.

#### com.config:
> com.base: This package is used to defined pre configureation data like URL, Browser Name.

#### com.constant:
> com.base: In this package all the constant path is defined. Like pages properties file.

#### com.base:
> com.base: This package is used to defined all the java related funcation. For now PropertyFileReader file used to read the property file.

### Steps to create feature, page property file and step defination:
1. Create a feature file inside "src\test\resources\Features". Write steps inside the feature file.
2. Create a stepDefication file for the new feature file inside "src\test\java\stepDefinations".
3. Create a property file of a page inside "src\test\java\com\pages" and write all the xpaths of a page.
