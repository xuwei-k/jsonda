package com.github.kmizu.jsonda.dsl

import com.github.kmizu.jsonda.JsondaDSL
import play.api.libs.json._

class PlayJsonDSL extends JsondaDSL {
  type JsonValueType = JsValue
  type JsonInt = JsNumber
  type JsonString = JsString
  type JsonBool = JsBoolean
  type JsonDouble = JsNumber
  type JsonObject = JsObject
  type JsonArray = JsArray

  class PJSON(override val underlying: JsonValueType) extends super[JsondaDSL].PJSON(underlying) {

    def dump(compress: Boolean = false): String = Json.stringify(underlying)
  }

  implicit def null2JsonNull(mnull: Null): JsonValueType = JsNull

  implicit def int2JInt(arg: Int): JsonInt = JsNumber(arg)

  implicit def long2JInt(arg: Long): JsonInt = JsNumber(arg)

  implicit def string2JString(arg: String): JsonString = JsString(arg)

  implicit def boolean2JBool(arg: Boolean): JsonBool = JsBoolean(arg)

  implicit def float2JDouble(arg: Float): JsonDouble = JsNumber(arg)

  implicit def double2JDouble(arg: Double): JsonDouble = JsNumber(arg)

  implicit def bigInt2JString(arg: BigInt): JsonString = JsString(arg.toString())

  implicit def bigDecimal2String(arg: BigDecimal): JsonString = JsString(arg.toString())

  implicit def pimpJsonAST(arg: JsValue): PJSON = new PJSON(arg)

  implicit def toJsonArray[A <% JsonValueType](arg: Traversable[A]): JsonArray = JsArray(arg.map{e => e: JsonValueType}.toBuffer)

  def constructJsonObject() = JsObject(values.value)

  val JsonNull: JsonValueType = JsNull

  def $(elements: JsonValueType*): JsonArray = JsArray(elements.toBuffer)
}

object PlayJsonDSL extends PlayJsonDSL
