# VariableManagement

## Badges

[![Maven Central](https://img.shields.io/maven-central/v/com.github.egatlovs/variable-management.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.egatlovs/variable-management)
[![Javadoc](http://www.javadoc.io/badge/com.github.egatlovs/variable-management.svg)](http://www.javadoc.io/doc/com.github.egatlovs/variable-management)

[![Build Status](https://travis-ci.org/EgatlovS/variable-management.svg?branch=master)](https://travis-ci.org/EgatlovS/variable-management)
[![codecov.io](https://codecov.io/gh/EgatlovS/variable-management/branch/master/graphs/badge.svg?branch=master)](https://codecov.io/gh/EgatlovS/variable-management?branch=master)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)
[![Conventional Commits](https://img.shields.io/badge/Conventional%20Commits-1.0.0-green.svg)](https://conventionalcommits.org)

## Foreword

Variable Management is an extension of the Camunda Java-API. Camunda itself provides a process engine to automate and orchestrate business processes. I personally recognized that executing tasks as well as developing and modeling a process is quite easy. But writing variables and retrieving them from the execution can get quite messy. Thats why I develop a variable-management framework.
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

This project provides objects called managers which wrap Camunda services and give you the ability to write and read variables in a different, easy way.
See the topics below to read more about managers and their abilities.

### Managers

Each manager provides the ability to read, write and remove variables globally and locally.
Additionally every manager allows access to the wrapped Camunda object.

Following Managers are provided: 
- **RuntimeManager** 
    - This Manager wraps a RuntimeService. 
- **TaskManager** 
    - This Manager wraps a TaskService. 
- **ExecutionManager** 
    - This Manager wraps the current DelegateExecution.
    
You can use each manager to write, read or remove an object from an execution.
First the manager will run a validation using the BeanValidation-Spec.
This means that you can annotate each field of your object with the well-known [BeanValidation Annotations](https://docs.oracle.com/javaee/6/tutorial/doc/gircz.html).
After the Validation is successful the manager will process your object and take care of the annotations you used so that you can manipulate the result.
More on manipulations of an Execution entity is provided in the annotations section.

### Annotations

Each Annotation gives you the ability to manipulate the object retrieved from or written to the Execution.

Following Annotations are provided: 
- **ObjectValue** 
    - provides following fields: 
        - storeFields -> where you can decide if the whole object is used and serialized with the values below or if the declared Fields themself are used
        - serializationDataFormat -> which takes the serializationDataFormats  provided by Camunda 
        - customSerializationDataFormat -> where you can specify your own custom formats 
- **FileValue** 
    - provides following fields: 
        - name -> the filename 
        - encoding -> the files encoding 
        - mimeType -> the files mimeType 
- **Ignore** 
    - tells the Manager to ignore the field 
- **FieldName** 
    - name -> the name to be used (as default the variable name would be used)
    - prefix -> the prefix to be used (if set this would result in a name like: *prefix_name*)
    
## Getting Started

1. Create a project using [Camundas Maven Archetype for a Process Application (EJB, WAR)](https://docs.camunda.org/manual/7.4/user-guide/process-applications/maven-archetypes/#overview-of-available-maven-archetypes).
2. Add the [newest dependency](https://search.maven.org/#search%7Cga%7C1%7Ccom.github.egatlovs) of this project. 
3. Create a process and a Service Task with a JavaDelegate.
4. Create an object and declare it with the provided Annotations.
5. Inject the ExecutionManager into your Delegate.
6. Start writing, reading and removing variables.

## Example Projects

- [Variable Management Sample](https://github.com/EgatlovS/variable-management-sample) made by EgatlovS

*You got an OpenSource Project using this framework? -> Send an [Email](mailto:dev.egatlovs@gmail.com) and I will paste the link for the community!*

## Documentation

For more information on this framework please visit the [documentation](http://github.com/EgatlovS).

## Purpose

Everyone of us knows the TwitterProcess example of Camunda. Inside the whole process you work with a maximum of maybe ten variables.
Most of them are some sort of human-written text where you don't necessarily need to apply constraints or some sort of validation.

Heres a short example: 

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

Looks pretty easy right? 
-> Yes but actually... thats just an example.
Did you notice the String cast? Thats already the first bit of code where it starts to look a little bit wrong.

Lets take a more complex example where you need some form of list and want to throw an exception when its size is less than 5.

Pretty easy: 

```java
    public void execute(DelegateExecution execution) throws Exception {
        List<String> informations = someService.getInformation();
        if (informations.size() < 5) {
            throw new Exception("failed");
        }
    }
```

Okay now that we have that, let's think about some sort of service where you could get this array from.
Of course you can never trust anyone.. thats why you probably want to null-check the array, just to be safe.

Your code would probably look like that: 

```java
    public void execute(DelegateExecution execution) throws Exception {
        List<String> informations = someService.getInformation();
        if (informations == null || informations.size() < 5) {
            throw new Exception("failed");
        }
        execution.setVariable("informations", informations);
    }
```

Note that I added a line to save the array inside the execution. 
That's already my next concern.
You always have to access and write variables based on a String representing the variable.
Think about a process where you want to access a single variable in various different tasks.
Thats the point where most developers think about some sort of Enumeration where you store the names of variables and retrieve them elsewhere to access the variable.
This would ensure easy maintainability, since it prevents the code from breaking when you want to rename a variable.

Knowing this you can already model and implement a whole lot of processes.
At some point you realize your variables aren't that great when you watch them inside the Camunda cockpit.
You cant tell where they coming from and what they belong to. So you probably want some form of Meta-Data.
Let's go for a prefix for our variable name. Codewise that means you have to add a field to your Enumeration.
After you realize the same prefix is used multiple times you go with multiple Enumerations based on there prefix and each field actually corresponds to the defined prefix.
All that just that if you change it, functionality is still provided and code duplication is kept to a minimum.

And now imagine how your code would look like if you retrieve twenty different variables from some sort of service and you want each of them inside the execution,
all validated differently and you have to do that for ten services in ten different tasks.

Yeah that's kind of a mess and that szenario isn't even far fetched!

If you read through until now and don't think I made a huge mistake at some point and 
therefore am horribly wrong, you can look at some examples on how I myself tried to make things less messy and make everything feel a little more known.
Otherwise if you think I'm totally mistaken don't hesitate to [contact me](mailto:dev.egatlovs@gmail.com) and help me learn from my mistakes.

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

Here are some plans for the next few weeks:
- Logging with correlation ids  
- Managed fields to inject the fields you desire directly in your JavaDelegate

## Epilogue
 
**Problems using this framework?** 
- Create an issue, I will try to solve problems as soon as possible. 
 
Thanks for reading up to this point! 

Please make sure to contact me regarding any question and don't hesitate to contribute.