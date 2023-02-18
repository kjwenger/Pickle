[![CircleCI](https://dl.circleci.com/status-badge/img/gh/kjwenger/Pickle/tree/main.svg?style=shield)](https://dl.circleci.com/status-badge/redirect/gh/kjwenger/Pickle/tree/main)

Table of Contents:

<!-- TOC -->
* [Pickle for Java](#pickle-for-java)
  * [Version Control](#version-control)
    * [Install Git](#install-git)
    * [Clone Repository](#clone-repository)
    * [Hooks](#hooks)
  * [Build](#build)
    * [Install Maven (optional)](#install-maven--optional-)
    * [Install Module](#install-module)
  * [Test](#test)
    * [Unit/Component Test](#unitcomponent-test)
    * [Integration Test](#integration-test)
<!-- TOC -->

# Pickle for Java
Maybe, a much-needed extension to
[Cucumber-JVM](https://cucumber.io/docs/installation/java/) and
[Gherkin](https://cucumber.io/docs/gherkin/).

## Version Control
[Git](https://git-scm.com/) is **the** version control system these days.

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
