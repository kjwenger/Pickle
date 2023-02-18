Table of Contents:

<!-- TOC -->
* [Pickle for Java Example](#pickle-for-java-example)
  * [Usage](#usage)
    * [Repository](#repository)
    * [Dependency](#dependency)
    * [Glue](#glue)
      * [Suite](#suite)
    * [Feature](#feature)
  * [Test](#test)
<!-- TOC -->

# Pickle for Java Example
This project is an example of how to use *Pickle for Java*.

## Glossary
Please, for all acronyms and definitions used below, refer to:
[Glossary](../Glossary.md)

## Usage
*Pickle for Java* provides highly reusable *World* and *Step* classes
that can simply be referenced for faster implementation of the methodologies of
[Test-Driven Development (TDD)](
https://en.wikipedia.org/wiki/Test-driven_development) and
[Behavior-Driven Development (BDD)](
https://en.wikipedia.org/wiki/Behavior-driven_development).

It can be added to any *Maven* project for faster test rigging.

### Repository
*Pickle for Java* is provided as a *Maven* repository hosted on *GitHub*.

In order to obtain the actual *Maven* modules as dependencies,
the repository has to be added to the **POM** file.

```xml
<project>
    <!-- ... -->
    <repositories>
        <!-- ... -->
        <repository>
            <id>Pickle-github-maven-repository</id>
            <url>https://raw.githubusercontent.com/kjwenger/Pickle/main/java/mvn-repository/</url>
            <releases>
                <enabled>true</enabled>
                <updatePolicy>daily</updatePolicy>
            </releases>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
        </repository>
    </repositories>
    <!-- ... -->
</project>
```

### Dependency
In order to contain the versioning of it, simply add the following lines
to your module or parent **POM** or your **BOM**.

```xml
<project>
    <!-- ... -->
    <dependencyManagement>
        <dependencies>
            <!-- ... -->
            <dependency>
                <groupId>pickle</groupId>
                <artifactId>pickle-java</artifactId>
                <version>0.0.1</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
    <dependencies>
        <!-- ... -->
        <dependency>
            <groupId>pickle</groupId>
            <artifactId>pickle-java</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

> Note: transient dependencies need to be added explicitly. 

### Glue
Once the repository and the dependency have been added alongside others,
the so-called *glue* has to be applied to tell the runner where to find the
*Cucumber* step definitions that are referenced from the *Gherkin* features.

Glue is the information of which packages and classes to include in the
search for step definitions.

Step definition methods are then identified by the annotations
`Given`, `When`, and `Then` respectively their pattern texts that can either be
in simple form ...
```java
"This is the {int}th step definition with parameter {string}"
```
... or regular expression form ...
```java
"^This is the (\\d+)th step definition with parameter \"([^\"]*)\"$"
```
.

#### Suite
One way of providing the glue is by annotating a test suite class.
Test suite classes are known from *JUnit* and use the same tags.
Some properties are specific to the *Cucumber* framework and define the glue.

```java
package pickle.example;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
// Run with Cucumber.
@IncludeEngines("cucumber")
// Features are to be found within src/test/resources/features.
@SelectClasspathResource("features")
// Here comes the glue: name the packages to search across the classpath.
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "pickle")
public class CucumberSuite { }
```

This suite can then be included in the *Maven* test plug-in.

```xml
<project>
    <!-- ... -->
    <build>
        <pluginManagement>
            <plugins>
                <!-- ... -->
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>3.0.0-M1</version>
                </plugin>
            </plugins>
        </pluginManagement>
        <plugins>
            <!-- ... -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <configuration>
                    <includes>
                        <include>pickle/example/CucumberSuite.java</include>
                    </includes>
                </configuration>
            </plugin>
        </plugins>
    </build>
    <!-- ... -->
</project>
```

### Feature
Feature files may be composed of step definitions provided by *Picke for Java*
and steps provided by the project referencing it.

This example reuses the step definitions for dealing with the *World* object.

```gherkin
Feature: World object
  Scenario: World objects
    Given I have a World object
    When I set the object named 'Key' to 'Value'
    Then I should get the object named 'Key' as 'Value'
```

## Test
Simple tests (unit, component) that are short-running and do not
integrate across components, modules and systems, can be run as following.

```shell
mvn test
```
