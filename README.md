# berry-runner
A tiny Java wrapper around [STUPS Berry](https://github.com/zalando-stups/berry).

The purpose is to run Berry application along with
your application without need to run in in a separate terminal.

If you follow STUPS, normally, Berry runs only in development mode,
so probably you'll need a concept of run environment in the application.

## Usage

Berry must be installed.

A YAML configuration file is needed for Berry
(see [the documentation](http://stups.readthedocs.io/en/latest/components/berry.html)).

To run, execute:

```java
final String berryYamlPath = "berry.yaml";
final String berryDirectory = ".";
final BerryRunner berryRunner = new BerryRunner(
        berryYamlPath, berryDirectory);
berryRunner.start();
```

A daemonic thread will be run,
in this thread external Berry process will be executed.
Berry process' output will be printed using SLF4J logger.

`berryRunner` is a `Thread`, so it can be intterupted, joined, etc.

### Contributing

Pull requests are welcome.

### Contact

[Ivan Yurchenko](mailto:ivan0yurchenko@gmail.com)

### License

The library is Open Source Software released under the Apache 2.0 license.
See the [LICENSE file](LICENSE) for details.
