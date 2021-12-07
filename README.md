# CDI parameterized bean with wildcard type parameter injection failure

The purpose of this project is to reproduce the failure of a CDI parameterized bean, that contains a wildcard 
type parameter, injection.

It is clearly written in the [CDI specifications](https://docs.jboss.org/cdi/spec/2.0/cdi-spec.html#legal_bean_types) that:
**A parameterized type that contains a wildcard type parameter is not a legal bean type.**

## Installation

Import the project in your favorite IDE.

Let maven import the dependencies.

You should have a JDK 11 installed on your computer.

If you only have a JDK 8, you must first change the maven.compiler properties in [pom.xml](pom.xml) file from 11 to 8.

## Reproducer

Once maven has import all the required dependencies, you can run the test [ApplicationTest](src/test/java/org/reproducer/ApplicationTest.java)

The test should fail as an exception will be raised.

```
[ERROR] org.reproducer.ApplicationTest.test  Time elapsed: 0.093 s  <<< ERROR!
java.util.NoSuchElementException: No parameterized abstract class found
        at org.reproducer.ApplicationTest.test(ApplicationTest.java:17)
```

## Details

The parameterized bean [ParameterizedBean](src/main/java/org/reproducer/ParameterizedBean.java) extends from [ParameterizedAbstractClass](src/main/java/org/reproducer/ParameterizedAbstractClass.java) with a type [MyClass](src/main/java/org/reproducer/MyClass.java).

The [ParameterizedAbstractClass](src/main/java/org/reproducer/ParameterizedAbstractClass.java) class contains a wildcard type parameter.

The [FactoryBean](src/main/java/org/reproducer/FactoryBean.java) injects instances of [ParameterizedAbstractClass](src/main/java/org/reproducer/ParameterizedAbstractClass.java) wildcard type parameter beans in its constructor.

No beans are discovered and are injected as written in CDI specifications.
