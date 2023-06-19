package auth.services.converterservices;


import auth.entities.User;
import auth.services.converterservices.ObjectToJsonConverterImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ObjectToJsonConverterImplTest {
    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks
    private ObjectToJsonConverterImpl objectToJsonConverterImpl;

    @Test
    public void convert () throws JsonProcessingException {
        User sellerObject = new User("usernametest", "emailtest@test.com", "passwordtest");
        String expectedJson = "{\"username\":\"usernametest\",\"email\":\"emailtest@test.com\",\"password\":\"passwordtest\"}";
        when(objectMapper.writeValueAsString(sellerObject)).thenReturn(expectedJson);
        String jsonResult = objectToJsonConverterImpl.convert(sellerObject);
        assertEquals(jsonResult, expectedJson);
    }

}
