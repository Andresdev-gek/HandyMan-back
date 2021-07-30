package co.com.ias.project.HandyMan.technician.infrastructure.codecs.json;

import co.com.ias.project.HandyMan.technician.application.domain.TechnicianId;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectSerializer;

import java.io.IOException;

@JsonComponent
public class TechnicianIdCodec {
    public static class TechnicianIdEncoder extends JsonSerializer<TechnicianId> {

        @Override
        public void serialize(TechnicianId technicianId, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeString(technicianId.getValue());
        }
    }

    public static class TechnicianIdDecoder extends JsonDeserializer<TechnicianId> {

        @Override
        public TechnicianId deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            final String valueAsString = jsonParser.getValueAsString();
            return TechnicianId.of(valueAsString);
        }
    }
}
