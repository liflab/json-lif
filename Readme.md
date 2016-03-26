A JSON library for Java
=======================

This is a simple library for parsing and outputing JSON strings. It is a
wrap around the [json-simple](https://github.com/fangyidong/json-simple)
library.

The JSON objects of this library can be safely serialized
with [Azrael](https://github.com/sylvainhalle/Azrael).

Build instructions
------------------

Download the source code or clone the repository. If you have Ant, you
can compile by simply typing:

    $ ant

at the command prompt. This will create the `json-lif.jar` library,
which you can then include in your projects.

Usage
-----

### To parse a string

    String my_string = "some JSON...":
    JsonParser p = new JsonParser();
    try {
      JsonElement elem = p.parse(my_string);
    } catch (JsonParseException e) {
      // Do something
    }

### To create a JSON structure

    JsonMap my_map = new JsonMap();
    my_map.put("a", new JsonNumber(2));
    JsonList list = new JsonList();
    list.add(new JsonString("c"));
    list.add(new JsonNumber(1));
    my_map.put("b", list);

A `JsonMap` implements the `java.util.Map` interface, and a `JsonList`
implements the `java.util.List` interface.

### To query an object using a path expression

    JsonElement e = JsonPath.get(my_map, "b[0]");

With the element `my_map` created above, this would return the `JsonNumber`
with value 1.

Original Authors
----------------

The authors of json-simple are:

- Yidong Fang
- Chris Nokleberg
- Dave Hughes

The original code was released under the Apache 2.0 License. The `json-lif`
library is released under the GNU LGPL 3.

Changes with respect to json-simple
-----------------------------------

- All parsed JSON elements are descendents of the JsonElement class
- The `JsonPath` class allows elements to be queried in a
  JavaScript-like syntax
