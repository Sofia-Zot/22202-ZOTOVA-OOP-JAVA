package logic;

import consts.ParsersTypes;
import exceptions.ParserException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParsersFactoryTest {

    private ParsersFactory factory_ = null;

    @BeforeEach
    void setUp() {
        factory_ = new ParsersFactory();
    }

    @AfterEach
    void tearDown() {
        factory_ = null;
    }

    @Test
    void createParser() {

        try {
            CommandsParser parser = factory_.createParser(ParsersTypes.FILE_PARSER);
            assertEquals(ParsersTypes.FILE_PARSER, parser.getParserType());
            assertInstanceOf(CommandsParser.class, parser);
        } catch (ParserException ex) {
            fail(STR."Unexpected exception: \{ex.getMessage()}");
        }
        try {
            CommandsParser parser = factory_.createParser(ParsersTypes.CONSOLE_PARSER);
            assertEquals(ParsersTypes.CONSOLE_PARSER, parser.getParserType());
            assertInstanceOf(CommandsParser.class, parser);
        } catch (ParserException ex) {
            fail(STR."Unexpected exception: \{ex.getMessage()}");
        }
    }
}
