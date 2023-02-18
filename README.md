[![Licence](https://img.shields.io/github/license/kjwenger/Pickle?color=success)](https://github.com/kjwenger/Pickle/blob/master/LICENSE)

Table of Contents:

<!-- TOC -->
* [Pickle](#pickle)
  * [Contexts](#contexts)
    * [PicoContainer](#picocontainer)
      * [PicoContainer Dependency](#picocontainer-dependency)
      * [PicoContainer Injection](#picocontainer-injection)
  * [Preprocessing](#preprocessing)
  * [Variables](#variables)
    * [Simple](#simple)
    * [Environment](#environment)
    * [Typed](#typed)
  * [Multiline](#multiline)
  * [Interpolations](#interpolations)
  * [Regular Expressions](#regular-expressions)
  * [Templates](#templates)
  * [Embedding](#embedding)
    * [Yaml](#yaml)
    * [JavaScript](#javascript)
  * [Resources](#resources)
    * [Step Resources](#step-resources)
    * [Feature Resources](#feature-resources)
  * [Futures](#futures)
  * [Languages](#languages)
    * [Kotlin](#kotlin)
<!-- TOC -->

# Pickle
Maybe, a much-needed extension to
[Cucumber](https://cucumber.io/) and
[Gherkin](https://cucumber.io/docs/gherkin/).

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

## Preprocessing

## Variables

### Simple

```bash
${myVariable}
```

### Environment

### Typed

```bash
${myType@myObject}
```

## Multiline

```
"""
This is multi-line
"""
```

## Interpolations

## Regular Expressions

## Templates

```handlebars
This is a {{myAttribute}} template.
```

## Embedding

### Yaml

### JavaScript

## Resources

### Step Resources

### Feature Resources

## Futures

## Languages

### Kotlin