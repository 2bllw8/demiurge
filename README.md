# Demiurge

[![Demiurge CI](https://github.com/2bllw8/demiurge/actions/workflows/main.yml/badge.svg)](https://github.com/2bllw8/demiurge/actions/workflows/main.yml)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.2bllw8/demiurge)](https://search.maven.org/artifact/io.github.2bllw8/demiurge)

This library provides implementation of commonly used types that are not available in the standard
Java library:

- `Either`: represents a value of one of two possible types (disjoint union).
- `Nothing`: a type that has no instances and is used to represent a value that never exists.
- `State`: a type used to apply functions with hidden dependencies (_states_) in a pure way.
- `Try`: represents a computation that may either result in an exception, or return a successfully
  computed value.
- `Tuple`: an immutable product type that holds multiple (ordered) values (with sizes ranging from
  2 to 7).

## Releases

The latest release is available
on [Maven Central](https://search.maven.org/artifact/io.github.2bllw8/demiurge/1.0.0-alpha01/jar).

```groovy
implementation 'io.github.2bllw8:demiurge:1.0.0-alpha01'
```

## Usage

`Either` and `Try` example:

```java
import exe.bbllw8.demiurge.attempt.Try;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Arrays.stream(args).map(arg -> Try.from(() -> Files.lines(Paths.get(arg)))
                        .map(lines -> lines.collect(Collectors.joining("\n")))
                        .filter(text -> text.length() > 2)
                        .map(text -> text.substring(2))
                        .flatMap(text -> Try.from(() -> Integer.parseInt(text)))
                        .toEither()
                        .left().map(Throwable::getMessage)
                        .filterOrElse(number -> number % 11 == 0, "Unexpected value")
                        .fold(errorMessage -> "Error: " + errorMessage,
                                number -> "Result: " + number))
                .forEach(System.out::println);
    }
}
```

`State`, `Try` and `Tuple` example:

```java
import exe.bbllw8.demiurge.attempt.Try;
import exe.bbllw8.demiurge.state.State;
import exe.bbllw8.demiurge.tuple.Tuple2;

public class Main {

    public static void main(String[] args) {
        final State<Integer, Integer> getNext = State.of(i -> new Tuple2<>(1 + i, 1 + i));
        final State<Integer, Integer> inc3 = getNext.bind(x ->
                getNext.bind(y ->
                        getNext.bind(z -> State.ret(z))));

        Arrays.stream(args).map(arg -> Try.from(() -> Integer.parseInt(arg))
                        .map(inc3::evalState))
                .filter(Try::isSuccess)
                .forEach(System.out::println);
    }
}
```

## Documentation

Javadoc is available at [2bllw8.github.io/demiurge](https://2bllw8.github.io/demiurge)
