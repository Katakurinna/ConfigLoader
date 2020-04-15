# ConfigLoader
### Library to load / save configuration ###
#### Fully configurable ####
----
This project has been created by Alejandro Cerrato Espejo to be presented as PI ( end-of-degree project)  at the end of his studies.
----
## Releases

 - Last release: [v1.5.0](https://github.com/Katakurinna/ConfigLoader/releases/tag/v1.5.0)
 - [Version 1.5.0](https://github.com/Katakurinna/ConfigLoader/releases/tag/v1.5.0)
 - [Version 1.0.0](https://github.com/Katakurinna/ConfigLoader/releases/tag/1.0.0)
  
## How to use

### Maven Dependencies
#### YAML
```
<dependency>
    <groupId>org.yaml</groupId>
    <artifactId>snakeyaml</artifactId>
    <version>1.21</version>
</dependency>
```

#### JSON
```
<dependency>
    <groupId>org.json</groupId>
    <artifactId>json</artifactId>
    <version>20190722</version>
</dependency>
```
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
### Loading a file
Once we have the instance of ConfigLoader, we can load the file and send it to the ConfigFactory by parameter.
```java
loader.load();
```
Once this is done, we will have the file loaded in memory, in order to add nodes to the file.

### Getting a node
When you already have the file loaded, the way to get parts of it is with the get method of the loader get (key)
The way to go to a child node, is using '.', That is, to go from the parent node cookie, to the node node chocolate, we will have to do: 'cookie.ingredients.chocolate'
```java
Chocolate chocolate = (Chocolate) loader.get("cookie.ingredients.chocolate");
```
We can even go deeper, and get the description of this
```java
String chocolateDescription = (String) loader.get("cookie.ingredients.chocolate.description");
```

The get method does not return a copy, so any changes made will have a direct effect on the ConfigLoader
If the node does not exist, it returns null.

### Adding new node
Whether you have nodes created or not, the set method allows you to add new nodes.
Whether you want to add a child or a parent, it will create the necessary nodes to be able to add the desired one.
Its use is similar to the get method, since the way to differentiate nodes is using '.'.
If for example we only have the cookie node, and we want to add a description to the chocolate description node, without having chocolate created, we can do it in the following way:
```java
String chocolateDescription = "Chocolate is a preparation of roasted and ground cacao seeds that is made in the form of a liquid, paste, or in a block, which may also be used as a flavoring ingredient in other foods.";
loader.set("cookie.ingredients.chocolate.description",chocolateDescription);
```
In this way, we will have created all the previous nodes simply adding descriptions to the chocolate.

### Saving to file
When we have finished adding elements, and we want to save it in the file, we can do it in the following way:
```java
loader.save();
```
Once this is done, the file will contain the new nodes.
## Working actually
 - Save/load files from/to YAML
 - Save/load files from/to JSON
 - Get/Set to yaml files.
 
 - Create generic config loader / saver
 - Continue the configuration file wherever you want.

## TODO - Save/Load
 - Save/load files from/to PROPERTIES
 - Permit encrypt/decrypt configuration (multiple types).
 - Load/Save from binary/character files.

## TODO - General
 - Create method optimized to load/save with low ram usage (always saving/loading)
 - Allows you to create a log file to see the changes made and have a history
