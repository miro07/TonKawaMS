package auth.services.converterservices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectToJsonConverterImpl implements ObjectToJsonConverter {
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public <T> String convert(T object) throws JsonProcessingException {
            return objectMapper.writeValueAsString(object);
    }
}
