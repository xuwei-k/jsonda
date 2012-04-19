# Jsonic - a simple DSL for JSON objects construction.

This project provides a Jsonic DSL, which can be used to construct JSON object
easily, in Scala.  The code size of jsonic is small and it is easy to 
understand what it does.

# For Developer

* By running sbt gen-idea, project definition files for IntelliJ IDEA is generated.
* By running sbt eclipse, project definition files for Scala IDE for Eclipse is generated.

# Syntax

Jsonic is very simple DSL for creating JSON objects.  Notations are followings:

* object: 
    %{ $key1 :- $value1; $key2 :- $value2; ... }
* array:
    $($value1, $value2, ...)
* primitive: 
  * number(integer): e.g. 100
  * number(double): e.g. 10.5
  * string: e.g. "Hello, World!"
  * boolean: e.g. true 
  * null: null

# Quick Start

Here are the way to create JSON using Jsonic:

    import org.onion_lang.jsonic.Jsonic._
    
    val person = %{
      'name :- "Kota Mizushima"
      'age :- 28
    }
    
The type of person is net.liftweb.json.JsonAST.JValue.  If you are familiar with lift-json, you can easily manipulate the JSON object.
