package br.com.helpdev.race.infrastructure.logfile;

import br.com.helpdev.race.infrastructure.logfile.entities.LapEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class LapLogFileTranslateTest {

    @Test
    void should_translate_line_with_success() {
        String input = "07:49:08.277      038 – F.MASSA                           1   1:02.852                        44,275";
        LapEntity translate = LapLogFileTranslate.translate(input);
        assertEquals("07:49:08.277", translate.getTime().toString());
        assertEquals("00:01:02.852", translate.getLapTime().toString());
        assertEquals(1, (int) translate.getLapNumber());
        assertEquals(38, translate.getPilot().getNumber());
        assertEquals("F.MASSA", translate.getPilot().getName());
    }

    @Test
    void should_translate_line_with_fail() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            String input = "Hora                               Piloto             Nº Volta   Tempo Volta       Velocidade média da volta";
            LapLogFileTranslate.translate(input);
        });
    }
}