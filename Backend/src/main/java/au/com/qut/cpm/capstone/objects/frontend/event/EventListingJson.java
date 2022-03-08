package au.com.qut.cpm.capstone.objects.frontend.event;

import au.com.qut.cpm.capstone.objects.utility.socialmedia.SocialMedia;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class EventListingJson {

    public static class EventListingSerializer extends JsonSerializer<EventListing> {

        @Override
        public void serialize(EventListing value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeObjectFieldStart("event");
            gen.writeStringField("name", value.eventName);
            gen.writeNumberField("startTime", value.eventStart.getTime());
            gen.writeNumberField("startEnd", value.eventStart.getTime());
            gen.writeArrayFieldStart("socials");
            for (SocialMedia socialMedia : value.socialMedia) gen.writeObject(socialMedia);
            gen.writeEndArray();
            gen.writeStringField("desc", value.eventDescription);
            gen.writeEndObject();
        }
    }

    public static class EventListingDeserializer extends JsonDeserializer<EventListing> {

        @Override
        public EventListing deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            return null;
        }
    }
}
