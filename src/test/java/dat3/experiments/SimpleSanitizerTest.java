package dat3.experiments;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

class SimpleSanitizerTest {

    @Test
    void simpleSanitizeTest() {
        String result = SimpleSanitizer.simpleSanitize("Hello <b>World</b>");

        assertEquals("Hello World", result);
    }
}


