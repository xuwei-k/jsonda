package com.github.kmizu.jsonda

import dsl.StdJsonDSL
import dsl.StdJsonDSL._
import org.specs2.mutable.Specification
import util.parsing.json.JSONObject
import util.parsing.json.JSONArray

/**
 * @author Mizushima
 *
 */
class JsondaStdJsonDSLSpecification extends Specification {
  """%{'name :- "Kota Mizushima", 'age :- 18}}}""" should {
    val person = % {
      'name :- "Kota Mizushima"; 'age :- 28
    }
    """have name "Kota Mizushima""" in {
      person.obj("name") must ===("Kota Mizushima")
    }
    """have age 28""" in {
      person.obj("age") must ===(28)
    }
  }

  """%{'str :- "a String"; 'arr :- $(1, 2, 3, 4, 5); 'obj :- %{ 'x :- 1; 'y :- 2}}""" should {
    val data: StdJsonDSL.JsonObject = % {
      'str :- "a String"; 'arr :- $(1, 2, 3, 4, 5); 'obj :- % {
        'x :- 1; 'y :- 2
      }
    }
    """have str "a String"""" in {
      data.obj("str").asInstanceOf[String] must ===("a String")
    }
    """have arr [1, 2, 3, 4, 5]""" in {
      data.obj("arr").asInstanceOf[JSONArray].list must_== List(1, 2, 3, 4, 5)
    }
    """have obj which x == 1 and y == 2""" in {
      val obj = data.obj("obj").asInstanceOf[JSONObject]
      obj.obj("x") must ===(1)
      obj.obj("y") must ===(2)
    }

  }

  """%{ 'some_key :- Option(100); 'none_key :- None }""" should {
    val data = %{
      'some_key :- Option(100)
      'none_key :- None
    }

    """have some_key Option(100)""" in {
      data.obj("some_key") must ===(Option(100))
    }

    """have none_key None""" in {
      data.obj("none_key") must ===(None)
    }
  }

  """%{'long_key :- 100L}""" should {
    val data = %{
      'long_key :- 100L
    }
    """have long_key 100""" in {
      data.obj("long_key") must ===(100)
    }
  }

  """%{'float_key :- 1.5F}""" should {
    val data = %{
      'float_key :- 1.5f
    }
    """have float_key 1.5""" in {
      data.obj("float_key") must ===(1.5F)
    }
  }
}
