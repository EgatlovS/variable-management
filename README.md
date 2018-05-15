# VariableManagement

## Badges

[![Maven Central](https://img.shields.io/maven-central/v/com.github.egatlovs/variable-management.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.egatlovs/variable-management)
[![Javadoc](http://www.javadoc.io/badge/com.github.egatlovs/variable-management.svg)](http://www.javadoc.io/doc/com.github.egatlovs/variable-management)

[![Build Status](https://travis-ci.org/EgatlovS/variable-management.svg?branch=master)](https://travis-ci.org/EgatlovS/variable-management)
[![codecov.io](https://codecov.io/gh/EgatlovS/variable-management/branch/master/graphs/badge.svg?branch=master)](https://codecov.io/gh/EgatlovS/variable-management?branch=master)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Foreword

Variable Management is an extension of the Camunda Java-API.
Camunda itself provides a process engine to automate and orchestrate business processes.
I personally recognized that executing tasks developing and modeling a process is quite easy. But writing variables and retrieving them from the so called execution can get quite messy. Thats why I code variable-management.

## Content

- [Badges](#badges)
- [Foreword](#foreword)
- [Introduction](#introduction)
    - [Managers](#managers)
    - [Annotations](#annotations)
- [Getting Started](#getting-started)
- [Example Projects](#example-projects)
- [Documentation](#documentation)
- [Purpose](#purpose)
- [Code Snippets](#code-snippets)
- [Roadmap](#roadmap)
- [Epilogue](#epilogue)

## Introduction

This Project provides Objects called Managers which wrap Camunda Services and give you the ability to write and read variables in a different way.
See the Topics below to read more about Managers and their abilities.

### Managers

Each Manager provides the ability to read, write and remove variables globally and locally. Also every Manager allows access to the wrapped Camunda object.

Following Managers are provided:
 - **RuntimeManager**
 	- This Manager wraps a RuntimeService.
 - **TaskManager**
 	- This Manager wraps a TaskService.
 - **ExecutionManager**
	- This Manager wraps the current DelegateExecution.

You can use each Manager to write, read or remove an Object from an Execution.
The Manager will at first run a Validation using the BeanValidation Spec.
That means that you can annotate each field of your Object with the well-known [BeanValidation Annotations](https://docs.oracle.com/javaee/6/tutorial/doc/gircz.html).
After the Validation is successful the Manager will process your Object and it takes care of the Annotations you've used
so that you can manipulate the Result. More on Manipulations of an Execution Entity is provided on the Annotations section.


### Annotations

Each Annotation gives you the ability to manipulate the Object retrieved or written to the Execution.

Following Annotations are provided:
 - **ObjectValue**
 	- provides following fields:
 		- storeFields -> where you can decide if the whole Object is used and serialized with the values below or the Declared Fields them self are used
 		- serializationDataFormat -> which takes the Camunda provided serializationDataFormats  
 		- customSerializationDataFormat -> where you can specify you're own custom Formats
 - **FileValue**
 	- provides following fields:
 		- name -> the filename
 		- encoding -> the files encoding
 		- mimeType -> the files mimeType
 - **Ignore**
 	- tells the Manager to ignore the field
 - **FieldName**
 	- name -> the name to be used (at default the variable name would be used)
 	- prefix -> the prefix to be used (if set this would result in a name like: *prefix_name*)

## Getting Started

1. Create a project using [Camundas Maven Archetype for a Process Application (EJB, WAR)](https://docs.camunda.org/manual/7.4/user-guide/process-applications/maven-archetypes/#overview-of-available-maven-archetypes).
2. Add the [newest dependency](https://search.maven.org/#search%7Cga%7C1%7Ccom.github.egatlovs) of this project.
3. Create a process and a Service Task with a JavaDelegate.
4. Create an Object and declare with the provided Annotations.
5. Inject the ExecutionManager into your Delegate.
6. Start writing, reading and removing variables.

## Example Projects

 - [Variable Management Sample](https://github.com/EgatlovS/variable-management-sample) made by EgatlovS

*You got an OpenSource Project using this Framework? -> Send an [Email](mailto:dev.egatlovs@gmail.com) and I will paste the Link for the community!*

## Documentation

For more Information on this Framework please visit the [Documentation](https://egatlovs.github.io/variable-management/).

## Purpose

Everyone of us knows the TwitterProcess example of Camunda. Inside the whole process you work with a maximum of maybe ten variables. Most of them are some sort of Human-Written Text where you don't necessary need to apply Constraints or some sort of validation.

Heres a little example of that:

```java
public class MyDelegate implements JavaDelegate {

    @Inject
    TwitterService twitterService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String message = (String) execution.getVariable("message");
        twitterService.tweet(message);
    }

}
```

This looks pretty easy right?
-> Yes but actually... thats an example.
Did you notice the String cast? Thats actually the first bit of code where everything starts to look a little bit wrong.

Lets think about a more complex example where you need some form of list and actually you want to throw an exception when size is less than 5.

Pretty easy:

```java
    public void execute(DelegateExecution execution) throws Exception {
        List<String> informations = someService.getInformation();
        if (informations.size() < 5) {
            throw new Exception("failed");
        }
    }
```

Okay now we have that. Lets think about some sort of service where you get this array from. Of course you can never trust anyone.. thats why you probably want to null-check the array.

So you're code looks like that:

```java
    public void execute(DelegateExecution execution) throws Exception {
        List<String> informations = someService.getInformation();
        if (informations == null || informations.size() < 5) {
            throw new Exception("failed");
        }
        execution.setVariable("informations", informations);
    }
```

Note that I added a line to save the array inside the execution. Thats also my next concern. You always access and write variables based of a String representing the variable. Think about a process where you want to access a single variable in various different tasks. Thats the point where most developers think about some sort of Enumeration where you store the names of variables and retrieve them elsewhere to access the variable. Thats to ensure, that if someone renames them, everything else will still be functional.

Having all of this accomplished you can model and implement a whole lot of processes.
At some point you realize your variables aren't that great when you watch them inside the Camunda cockpit. So you probably want some form of Meta-Data. Lets stick with a prefix to a variable name.
Codewise that means you add a field to your Enumeration. After u realize the same prefix is used multiple times you go with multiple Enumerations based on there prefix and each Field actually corresponds to the defined prefix. Just because if you change it, functionality is still provided and code duplication is reduced.

And now think how your code would look like if you gather twenty different variables from some sort of Service and you want each of them inside the execution, all validated differently and you do that for ten Services in ten different tasks.

Yes thats a mess.

If you read through until now and you actually don't think i made a huge mistake, you can see some examples on how I personally tried to make things less messy and try to make everything feel a little more known.
Otherwise if you think I'm totally wrong don't hesitate [contact me](mailto:dev.egatlovs@gmail.com) and let me learn from my mistakes.

## Code Snippets

Instead of:

```java
    public void execute(DelegateExecution execution) throws Exception {
        String name1 = (String) execution.getVariable("name1");
        String name2 = (String) execution.getVariable("name2");
        String name3 = (String) execution.getVariable("name3");
        String name4 = (String) execution.getVariable("name4");
        String name5 = (String) execution.getVariable("name5");
        String name6 = (String) execution.getVariable("name6");
        String name7 = (String) execution.getVariable("name7");
        String name8 = (String) execution.getVariable("name8");
        String name9 = (String) execution.getVariable("name9");
     
        someObject.setName1(name1);
        // Map Everything to some Object...
        
        // do some logic
    }
```

You can just write this:

```java
    @Inject
    private ExecutionManager manager;
    
    public void execute(DelegateExecution execution) throws Exception {
       SomeObject someObject = manager.getVariable(SomeObject.class);
       //do some logic
    }
```

## Roadmap

Here are some plans for the next weeks:
 - Work on Project "Logging with Correlation ids"
 - Work on Managed Fields to Inject the Fields you desire directly in your JavaDelegate

## Epilogue

**Problems using this framework?**
- Create an issue i will try to solve problems as soon as possible.

Thanks for reading everything!

Please make sure to contact me for any question and don't hesitate to contribute.
