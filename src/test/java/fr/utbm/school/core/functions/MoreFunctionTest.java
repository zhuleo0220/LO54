package fr.utbm.school.core.functions;

import org.junit.jupiter.api.Test;

import static fr.utbm.school.core.functions.MoreFunction.conditionnalString;
import static fr.utbm.school.core.functions.MoreFunction.nvl;
import static org.junit.jupiter.api.Assertions.*;

class MoreFunctionTest {

    @Test
    void nvlTest() {
        assertEquals("nonNullValue", nvl("nonNullValue", "WrongValue"));
        assertEquals("nullValue", nvl(null, "nullValue"));
    }

    @Test
    void conditionnalStringTest() {
        assertEquals("stringTrue", conditionnalString("stringTrue", "stringFalse", 10 > 9));
        assertEquals("stringFalse", conditionnalString("stringTrue", "stringFalse", 10 < 9));
    }
}