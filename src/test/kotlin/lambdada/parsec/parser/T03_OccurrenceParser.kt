package lambdada.parsec.parser

import lambdada.parsec.io.Readers
import org.junit.Assert.assertEquals
import org.junit.Test

class T03_OccurrenceParser {

    @Test
    fun shouldOptionalParserWithEmptyStringReturnsAccept() {
        val parser = opt(any<Char>()) then eos()

        assertEquals(parser(Readers.fromString("")).fold({ it.value.first == null }, { false }), true)
    }

    @Test
    fun shouldOptionalParserWithNonEmptyStringReturnsAccept() {
        val parser = opt(any<Char>()) then eos()

        assertEquals(parser(Readers.fromString("a")).fold({ it.value.first == 'a' }, { false }), true)
    }

    @Test
    fun shouldOptionalRepeatableParserWithEmptyStringReturnsAccept() {
        val parser = optRep(any<Char>()) then eos()

        assertEquals(parser(Readers.fromString("")).fold({ true }, { false }), true)
    }

    @Test
    fun shouldOptionalRepeatableParserWithNonEmptyStringReturnsAccept() {
        val parser = optRep(any<Char>()) then eos()

        assertEquals(parser(Readers.fromString("ab")).fold({ it.value.first == listOf('a', 'b') }, { false }), true)
    }

    @Test
    fun shouldRepeatableParserWithEmptyStringReturnsReject() {
        val parser = rep(any<Char>()) then eos()

        assertEquals(parser(Readers.fromString("")).fold({ false }, { true }), true)
    }

    @Test
    fun shouldRepeatableParserWithNonEmptyStringReturnsAccept() {
        val parser = rep(any<Char>()) then eos()

        assertEquals(parser(Readers.fromString("ab")).fold({ it.value.first == listOf('a', 'b') }, { false }), true)
    }

    @Test
    fun shouldRepeatableNotParserWithNonEmptyStringReturnsAccept() {
        val parser = rep(not(char('a'))) then eos()

        assertEquals(parser(Readers.fromString("bc")).fold({ it.value.first == listOf('b', 'c') }, { false }), true)
    }

    @Test
    fun shouldRepeatableNotThenCharParserWithNonEmptyStringReturnsAccept() {
        val parser: Parser<Char, List<Char>> = optRep(not(char('a')))

        assertEquals(parser(Readers.fromString("bca")).fold({ it.value == listOf('b', 'c') }, { false }), true)
    }
}