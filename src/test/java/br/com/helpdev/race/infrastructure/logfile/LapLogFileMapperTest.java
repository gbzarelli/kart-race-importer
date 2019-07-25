package br.com.helpdev.race.infrastructure.logfile;

import br.com.helpdev.race.infrastructure.logfile.entities.LapEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LapLogFileMapperTest {

    @Test
    void should_parse_line_with_success() {
        String input = "07:49:08.277      038 – F.MASSA                           1   1:02.852                        44,275";
        LapEntity entity = LapLogFileMapper.parse(input);
        assertEquals("07:49:08.277", entity.getTime().toString());
        assertEquals("00:01:02.852", entity.getLapTime().toString());
        assertEquals(1, (int) entity.getLapNumber());
        assertEquals(38, entity.getPilot().getNumber());
        assertEquals("F.MASSA", entity.getPilot().getName());
    }

    @Test
    void should_parse_line_with_fail() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            String input = "Hora                               Piloto             Nº Volta   Tempo Volta       Velocidade média da volta";
            LapLogFileMapper.parse(input);
        });
    }
}