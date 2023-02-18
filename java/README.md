Table of Contents:

<!-- TOC -->
* [Pickle for Java](#pickle-for-java)
  * [Glossary](#glossary)
  * [Version Control](#version-control)
  * [Build](#build)
    * [Install Maven (optional)](#install-maven--optional-)
    * [Install Module](#install-module)
  * [Test](#test)
    * [Unit/Component Test](#unitcomponent-test)
    * [Integration Test](#integration-test)
  * [Deploy](#deploy)
    * [Distribution Management](#distribution-management)
  * [Contexts](#contexts)
    * [PicoContainer](#picocontainer)
      * [PicoContainer Dependency](#picocontainer-dependency)
      * [PicoContainer Injection](#picocontainer-injection)
<!-- TOC -->

# Pickle for Java
Maybe, a much-needed extension to
[Cucumber-JVM](https://cucumber.io/docs/installation/java/) and
[Gherkin](https://cucumber.io/docs/gherkin/).

## Glossary
Please, for all acronyms and definitions used below, refer to:
[Glossary](../Glossary.md)

## Version Control
Please refer to section [Version Control](../README.md#version-control)
in the parent README.

## Build
[Apache Maven](https://maven.apache.org/) is used as the build tool of choice. 

### Install Maven (optional)
To install *Maven* on *Ubuntu/Debian* best run its package manager *Aptitude*.

```shell
sudo apt install maven
```

In order to require minimal installation, the *Maven Wrapper* has been added to this project.

It handles installing the required *Maven* version on the fly.

Simply replace all calls to `mvn` in the shell script snippets below
to `./mvnw` on Linux and `mvnw` on Windows, respectively.

### Install Module
To build and install the module into the local *Maven* repository,
run the following command.

```shell
mvn install --define skipTests
```

## Test
The project is developed using the methodologies encouraged by
[Test-Driven Development (TDD)](
https://en.wikipedia.org/wiki/Test-driven_development) and
[Behavior-Driven Development (BDD)](
https://en.wikipedia.org/wiki/Behavior-driven_development).

### Unit/Component Test
Simple tests (unit, component) that are short-running and do not
integrate across components, modules and systems, can be run as following.

```shell
mvn test
```

They should run fast and be run frequently.

Therefore, they are run as *pre-commit git hook*.

### Integration Test
Tests (integration, system) that are long-running and do
integrate across components, modules and systems, can be run as following.

```shell
mvn integration-test
```

They should be run at critical steps such as pushing to shared repositories
and on merging short-lived branches to long-lived ones.

Therefore, they are run as *pre-commit git hook*.

## Deploy
The *Maven* project artifacts are deployed to *GitHub* for easy access
according to this gist
[Create a dedicated project to host your Maven repository on GitHub](
https://gist.github.com/nealxyc/96267110f5156ac2ab12).

### Distribution Management
The following section in this **POM** set up how the *Maven* repository is
deployed to locally to later be committed and pushed to the *GitHub* repository.

```xml
<project>
    <!-- ... -->
    <distributionManagement>
        <repository>
            <id>project.maven.repository</id>
            <name>Maven Repository on GitHub</name>
            <url>${project.baseUri}mvn-repository</url>
        </repository>
    </distributionManagement>
    <!-- ... -->
    <build>
        <pluginManagement>
            <plugins>
              <!-- ... -->
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-deploy-plugin</artifactId>
                    <version>3.0.0-M1</version>
                </plugin>
            </plugins>
        </pluginManagement>
        <plugins>
          <!-- ... -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-deploy-plugin</artifactId>
                <configuration>
                    <altDeploymentRepository>project.maven.repository::default::${project.baseUri}mvn-repository</altDeploymentRepository>
                </configuration>
            </plugin>
        </plugins>
    </build>
  <!-- ... -->
</project>
```

The next steps deploy to the local repository [mvn-repository](./mvn-repository)
and the commit and push it to the *GitHub* repository.

```shell
mvn deploy
git add mvn-repository
git commit mvn-repository -m "Deployed latest Maven Repository"
git push
```

## Contexts
Contexts, often called the *World* object, provide shared state between steps.

A vastly reusable yet unconstrained *World* should be provided by this project.

It should not be bound to one single instantiation or injection mechanism.

### PicoContainer
This is the default context injection mechanism of *Cucumber-JVM*.

The provided *World* object can be injected with two simple steps.

#### PicoContainer Dependency
The dependency to this context mechanism has to be configured in the **POM**.

The next lines depict how to manage the dependency versioning by **BOM** import.

Adding the actual dependency enables test steps to simply perform
automatic constructor argument injection of the *World* object.

```xml
<project>
    <!-- ... -->
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>io.cucumber</groupId>
                <artifactId>cucumber-bom</artifactId>
                <version>7.11.1</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
    <!-- ... -->
    <dependencies>
        <!-- ... -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-picocontainer</artifactId>
        </dependency>
        <!-- ... -->
    </dependencies>
    <!-- ... -->
</project>
```

#### PicoContainer Injection
Once the dependency has been added to the project,
the context is injected at construction-time.

```java
package pickle.world;

import pickle.World;
// ...
public class Steps {
    private final World world;
    // ...

    public Steps(final World world) {
        this.world = world;
    }

    // ...
}
```