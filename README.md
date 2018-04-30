# VariableManagement

## Foreword

Variable Management is an extension of the camunda java api.
Camunda itself provides a process engine to automate and orchestrate business processes. At work out goal is to provide processes and optimize them as needed.
We did this for 2 years at this point in time.
I personally recognized that executing tasks developing and modelling a process is quite easy. But writing variables and retrieving them from the so called execution can get quite messy. Thats why i code variable-management.

## Content

- [The reason why](#the-reason-why)
- [Introduction](#introduction)
    - [Managers](#managers)
    - [Annotations](#annotations)
- [Examples](#examples)
- [Epilogue](#epilogue)


## The reason why

Everyone of us nows the TwitterProcess example of camunda. Inside the whole process you work with a maximum of maybe 10 variables. Most of them are some sort of Human-Written Text where you dont necessarly need to apply Constraints or some sort of validation.

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

Ok now we have that. Lets think about some sort of service where you get this array from. Ofcourse you can never trust anyone.. thats why you probably want to nullcheck the array.

So youre code looks like that:

```java
    public void execute(DelegateExecution execution) throws Exception {
        List<String> informations = someService.getInformation();
        if (informations == null || informations.size() < 5) {
            throw new Exception("failed");
        }
        execution.setVariable("informations", informations);
    }
```

Note that i added a line to save the array inside the execution. Thats also my next concern. You always access and write variables based of a String representing the variable. Think about a process where you want to access a single variable in various different tasks. Thats the point where most developers think about some sort of enum where you store the names of variables and retrieve them elsewhere to access the variable. Thats to ensure, that if someone renames them, everything else will still be functional.

Having all of this accomplished you can model and implement a whole lot of processes.
At some point you realize your variables arent that great when you watch them inside the camunda cockpit. So you probably want some form of metadata. Lets stick with a prefix to a variable name.
Codewise that means you add a field to your enum. After u realize the same prefix is used multiple times you go with multiple enums based on there prefix and each Field actually corresponds to the defined prefix. Just because if you change it, functionality is still provided and code duplication is reduced.

And now think how about how your code would Look like if you gather 20 different variables from some sort of Service and you want each of them inside the execution, all validated differently and you do that for 10 Services in 10 different tasks.

Yes thats a mess.

It you read through until now and you actually dont think i made a huge mistake, you can see some examples on how i personally tried to make things less messy and make everything feel a little more known.
Otherwise if you think im totally wrong dont hesitate contact me and let me learn from my mistakes.

## Introduction

This Project provides Objects called Managers which wrap camunda Services and give you the ability to write and read variables in a different way.
The first release focuses a lot on JPA like behaviour with Entitys and BeanValidation.

### Managers

Following Managers are provided:
 - RuntimeManager
 - TaskManager
 - ExecutionManager

Each of them provide the ability to read, write and remove variables globally and locally. Also every Manager allows access to the wrapped camunda object.

### Annotations

Following Annotations are provided:
 - Execution
 - ExecutionField
 - Ignore

The Execution Annotation is used to describe how a class should be stored. 
For this two Attributes can be overriden.
StoreFields is one of those and will describe if the object itself or each variable declared in it will be written to the execution. The Attribute storeStrategy is used to describe how variables are written. Either json or object can be used.

The ExecutionField annotation describes how a field should be called. So you can set a name attribute and if you want to you can set a prefix too.

The Ignore annotation is used to Mark variables to be ignored from the manager when written in the storeFields-mode.

## Examples

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

## Epilogue

**Problems using this framework?**
 Create an issue i will try to solve problems as soon as possible.

> Thanks for reading everything!
> Please make sure to contact me for any question and dont hesitate to contribute.

**Have a nice day!**
