package co.com.ias.project.HandyMan.technician.infrastructure.codecs.json;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonComponent
public class LocalDateTimeCodec {
    public static class LocalDateTimeEncoder extends StdSerializer<LocalDateTime> {

        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        public LocalDateTimeEncoder() {
            this(null);
        }

        public LocalDateTimeEncoder(Class<LocalDateTime> t) {
            super(t);
        }

        @Override
        public void serialize(
                LocalDateTime localDateTime,
                JsonGenerator gen,
                SerializerProvider arg2)
                throws IOException, JsonProcessingException {

            gen.writeString(localDateTime.format(formatter));
        }
    }

    public static class LocalDateTimeDecoder extends StdDeserializer<LocalDateTime> {

        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        public LocalDateTimeDecoder() {
            this(null);
        }

        public LocalDateTimeDecoder(Class<LocalDateTime> t) {
            super(t);
        }

        @Override
        public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

            final String valueAsString = jsonParser.getValueAsString();
            return LocalDateTime.parse(valueAsString, formatter);
        }
    }
}
