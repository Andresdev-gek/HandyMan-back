package co.com.ias.project.HandyMan.technician.infrastructure.codecs.json;

import co.com.ias.project.HandyMan.technician.application.domain.ServiceId;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class ServiceIdCodec {

    public static class ServiceIdEncoder extends JsonSerializer<ServiceId> {

        @Override
        public void serialize(ServiceId serviceId, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(serviceId.getValue());
        }
    }

    public static class ServiceIdDecoder extends JsonDeserializer<ServiceId> {

        @Override
        public ServiceId deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            final String valueAsString = jsonParser.getValueAsString();
            return ServiceId.of(valueAsString);
        }
    }
}
