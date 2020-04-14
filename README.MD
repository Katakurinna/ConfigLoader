# ConfigLoader
### Library to load / save fully configurable configuration ###
----
This project has been created by Alejandro Cerrato Espejo to be presented as PI ( end-of-degree project)  at the end of his studies.
----
## Releases
 - Currently release: [v1.0.0](https://github.com/Katakurinna/ConfigLoader/releases/tag/v1.0.0)
  - Last release: [v1.0.0](https://github.com/Katakurinna/ConfigLoader/releases/tag/v1.0.0)
  
## How to use
ConfigLoader allows you to handle any type of configuration files, it can do it automatically from the ConfigFactory
in which it returns an instance to use that file, or create your own ConfigManager in charge of handling everything.
### Create a new ConfigLoader instance
With the following code, we will obtain an instance of ConfigLoader thanks to the ConfigFactory class, which is automatically in charge of reviewing if the file is among the allowed extensions.
```java
ConfigLoader loader = ConfigFactory.getConfigLoader("files\\someFile.yml");
```

If the extension is not among the known ones, the exception 'IllegalArgumentException' will be thrown, saying that it is not among the known ones.
```java
ConfigLoader loader = ConfigFactory.getConfigLoader("files\\someFile.unknownExtension");
```
![IllegalArgumentException error message](https://i.imgur.com/hJLEQzc.png)

If you want to create your own ConfigManager class, you simply have to create a class that implements the ConfigManager interface, as used in those already created.
In order to add it to the ConfigFactory list, you just have to call the 'registerComparator (ConfigComparator)' method of the ConfigFactory class.
```java
ConfigFactory.registerComparator(new UnknownExtensionManager());
```

## Working actually
 - Save/load files from/to YAML
 - Get/Set to yaml files.
 
 - Create generic config loader / saver
 - Continue the configuration file wherever you want.

## TODO - Save/Load
 - Save/load files from/to JSON
 - Save/load files from/to PROPERTIES
 - Permit encrypt/decrypt configuration (multiple types).
 - Load/Save from binary/character files.

## TODO - General
 - Create method optimized to load/save with low ram usage (always saving/loading)
 - Allows you to create a log file to see the changes made and have a history
