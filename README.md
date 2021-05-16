# Spring + Redis Event Bridge

PoC to bridge/share ApplicationEvents between multiple instances of a Spring application.
This can be useful to implement High-Available applications using Spring.

## How it works

The pub/sub system of Redis is used to bridge (or share) the built-in `ApplicationEvent`s of Spring across multiple instances of a Spring (Boot) application.

The `src/main/java/be/ledfan/springredisevents/eventbridge` contains all necessary glue code, the `src/main/java/be/ledfan/springredisevents/example` contains a minimal working example of the library.
