[![Licence](https://img.shields.io/github/license/kjwenger/Pickle?color=success)](https://github.com/kjwenger/Pickle/blob/master/LICENSE)

Table of Contents:

<!-- TOC -->
* [Pickle](#pickle)
  * [Contexts](#contexts)
    * [Contexts for Java](#contexts-for-java)
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
  * [Continuous Integration/Continuous Deployment (CI/CD)](#continuous-integrationcontinuous-deployment--cicd-)
<!-- TOC -->

# Pickle
Maybe, a much-needed extension to
[Cucumber](https://cucumber.io/) and
[Gherkin](https://cucumber.io/docs/gherkin/).

## Contexts
Contexts, often called the *World* object, provide shared state between steps.

A vastly reusable yet unconstrained *World* should be provided by this project.

It should not be bound to one single instantiation or injection mechanism.

### Contexts for Java
The provided *World* object for the *JVM* is described in
[Java#Contexts](./java/README.md#contexts)

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

## Continuous Integration/Continuous Deployment (CI/CD)
An easy-to-use and partially free-to-use CI/CD system is
[CircleCI](https://app.circleci.com/pipelines/github/kjwenger/Pickle).

It connects auto-magically to *GitHub* and provides low-hassle build pipelines.

After registration (best with *GitHub* *OAuth*) and enabling the web hooks,
all that remains is to check-mark a project for build and provide a **YAML**
file that is even outlined by the system.

It then only has to be adapted and committed to the project
as [.circleci/config.yaml](.circleci/config.yml).

```yaml
version: 2.1
jobs:
  build-and-test-java:
    docker:
      - image: cimg/openjdk:11.0
    steps:
      - checkout
      - run:
          name: Build
          command: mvn --file java --batch-mode --define skipTests clean package
      - run:
          name: Test
          command: mvn --file java --batch-mode integration-test
workflows:
  build-and-test:
    jobs:
      - build-and-test-java
```
