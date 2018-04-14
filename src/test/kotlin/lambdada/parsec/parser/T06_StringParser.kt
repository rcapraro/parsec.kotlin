package lambdada.parsec.parser

import lambdada.parsec.io.Reader
import org.junit.Assert.assertEquals
import org.junit.Test

class T06_StringParser {

    @Test
    fun shouldStringParserReturnAccept() {
        val parser = string("hello") thenLeft eos

        assertEquals(parser(Reader.new("hello")).fold({ it.value == "hello" }, { false }), true)
    }

    @Test
    fun shouldDelimitedStringParserReturnEmptyString() {
        val parser = delimitedString() thenLeft eos

        assertEquals(parser(Reader.new(""""hel\"lo"""")).fold({ it.value }, { null }), """hel\"lo""")
    }
}