# Lesson 5 JPA with Wildfly

## Setting up database driver and datasource on server

To setup database driver and datasource on Wildfly server you can use script [datasource.cli](./datasource.cli) in the root of the project. Before using of it you have to 
* install and configure MySQL server
* download MySQL connector driver and put path to it into this script
* create on MySQL database with name _javaee_shop_ 

There are two ways how to run this script:
* With Wildfly CLI runner. Command is `# JBOSS_HOME/bin/jboss-cli.sh --connect --file datasource.cli`
* With maven Wildfly plugin. _JBOSS_HOME_ environment variable should be defined here or _<jboss_home>_ parameter should be defined in pom file.
Command is `# mvn wildfly:execute-commands`

As you can see file [datasource.cli](./datasource.cli) contains three commands

1. **module add** command put specified database driver into folder _JBOSS_HOME/modules/com/mysql/driver/main_ and create in this folder module.xml file with new module options
2. **/subsystem=datasources** command add driver section for new driver into file _JBOSS_HOME/standalone/configuration/standalone.xml_
3. **data-source add** command add new datasource section into file _JBOSS_HOME/standalone/configuration/standalone.xml_

[Here](https://docs.jboss.org/author/display/AS71/CLI+Recipes#CLIRecipes-ScriptedConfiguration) you can find more info about Wildfly CLI # Go to link for more info
That's not only way to setup database driver and datasource on Wildfly all described changes in setup files could be made manually.

## Default H2 database in Wildfly
Also worth to mention that by default Wildfly have configured testing datasource with JNDI name _"java:jboss/datasource/ExampleDS"_. This datasource is using relational database H@ with in-memory mode.

## JPA configuration in application
To use database connection from server in application you need to create file [persistence.xml](./src/main/resources/META-INF/persistence.xml). In app sources this file have to be placed in folder resources/META-INF. You can find name of created datasource in this file.

## Liquibase usage
This module use Liquibase to maintain changes of database structure. Code first approach is used here. 
To uptake changes into [liquibase change log](src/main/resources/liquibase-diff-changeLog.xml) you should run 
```
mvn clean install liquibase:diff
mvn clean install
```
Don't run liquibase:diff multiple times. That could cause duplicates in liquibase change log.

Some links about liquibase usage:
* https://github.com/liquibase/liquibase-hibernate/wiki
* https://sadalage.com/blog/2017/05/15/using-liquibase-to-load-data-and-ignore-some-columns
* https://www.wiktordyngosz.pl/25-02-2019-liquibase
* https://www.baeldung.com/liquibase-refactor-schema-of-java-app
