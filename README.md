[![CircleCI](https://dl.circleci.com/status-badge/img/gh/kjwenger/Pickle/tree/main.svg?style=shield)](https://dl.circleci.com/status-badge/redirect/gh/kjwenger/Pickle/tree/main)
[![Licence](https://img.shields.io/github/license/kjwenger/Pickle?color=success)](https://github.com/kjwenger/Pickle/blob/master/LICENSE)

Table of Contents:

<!-- TOC -->
* [Pickle](#pickle)
  * [Glossary](#glossary)
  * [Version Control](#version-control)
    * [Install Git](#install-git)
    * [Clone Repository](#clone-repository)
    * [Hooks](#hooks)
  * [Build](#build)
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
  * [Continuous Integration and Continuous Deployment (CI/CD)](#continuous-integration-and-continuous-deployment--cicd-)
<!-- TOC -->

# Pickle
Maybe, a much-needed extension to
[Cucumber](https://cucumber.io/) and
[Gherkin](https://cucumber.io/docs/gherkin/).

## Glossary
Please, for all acronyms and definitions used below, refer to:
[Glossary](./Glossary.md)

## Version Control
[Git](https://git-scm.com/) is **the** version control system these days,
and [GitHub](https://github.com/kjwenger/Pickle) one of the central repository
sites of choice.

### Install Git
To install *Git* on *Ubuntu/Debian* best run its package manager *Aptitude*.

```shell
sudo apt install git
```

### Clone Repository
Clone this project by issuing one of the following commands.

```shell
# Clone through HTTPS ...
git clone https://github.com/kjwenger/Pickle.git
```

```shell
# ... or clone though SSH:
git clone git@github.com:kjwenger/Pickle.git
```

### Hooks
In order to aid the overall workflow and quality management of the project,
the so-called *git hooks* have been added to the project.

They have been enabled and shared in the repository as described in the
blog [Store your git hooks in a repository and setup them for all the developers](
https://pilot34.medium.com/store-your-git-hooks-in-a-repository-2de1d319848c).

They have been shared into the repository for all to use.

```shell
git config -f .gitconfig core.hooksPath git/hooks
```

Developers have to run this command to activate the committed hooks.

```shell
git config --local include.path ../.gitconfig
```

To test some of the available hooks, run these lines:

```shell
git hook run pre-commit
git hook run pre-push
```

## Build
For the *Java* projects, please refer to section [Build](./java/README.md#build)
in the regarding README file.

## Test
For the *Java* projects, please refer to section [Test](./java/README.md#test)
in the regarding README file.

For a quick test drive, simply run;

```shell
mvn test
```

## Deploy
For the *Java* projects, please refer to section [Deploy](./java/README.md#deploy)
in the regarding README file.

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

## Continuous Integration and Continuous Deployment (CI/CD)
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
