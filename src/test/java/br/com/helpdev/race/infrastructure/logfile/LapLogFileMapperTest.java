package br.com.helpdev.race.infrastructure.logfile;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LapLogFileMapperTest {

    @Test
    @DisplayName("Should parse line with success")
    void shouldParseLineWithSuccess() {
        final var input = "07:49:08.277      038 – F.MASSA                           1   1:02.852                        44,275";
        final var entity = LapLogFileMapper.parse(input);
        assertEquals("07:49:08.277", entity.getTime().toString());
        assertEquals("00:01:02.852", entity.getLapTime().toString());
        assertEquals(1, (int) entity.getLapNumber());
        assertEquals(38, entity.getPilot().getNumber());
        assertEquals("F.MASSA", entity.getPilot().getName());
    }

    @Test
    @DisplayName("Should throw NumberFormatException when parse invalid line")
    void shouldThrowsExceptionWhenParseInvalidLine() {
        assertThrows(NumberFormatException.class, () -> {
            String input = "Hora                               Piloto             Nº Volta   Tempo Volta       Velocidade média da volta";
            LapLogFileMapper.parse(input);
        });
    }
}