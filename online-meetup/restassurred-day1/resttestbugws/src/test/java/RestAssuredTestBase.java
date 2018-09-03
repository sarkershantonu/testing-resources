import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.automation.config.PropertyLoader;
import org.automation.model.Bug;
import org.junit.Assert;
import org.junit.BeforeClass;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

/**
 * Created by SSarker on 9/2/2018.
 */
public class RestAssuredTestBase {
    protected RequestSpecification request = given();
    protected  Response response = null;
    protected static String user = "";
    protected static String pass = "";
    protected static Long respose_timeout_ms = 0L;

    @BeforeClass
    public static void initForEveryTestClass() throws IOException {
        PropertyLoader.loadProperties();
        user = System.getProperty("user.name");
        pass = System.getProperty("user.pass");
        respose_timeout_ms = Long.valueOf(System.getProperty("test.app.response.timeout"));
    }
    protected static String getAppUrl() {
        return "http://" + System.getProperty("app.host") + ":" + System.getProperty("app.port");
    }
    protected RequestSpecification setCredentials(RequestSpecification requestSpecification) {
        return requestSpecification.auth().basic(user, pass);
    }

    protected RequestSpecification setDefaultHeaders(RequestSpecification requestSpecification) {
        return requestSpecification.contentType(ContentType.JSON).header(new Header("Accept-Language", "en,bn;q=0.9"));
    }

    protected List<Bug> getAllBugsFromResponse(Response response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.readValue(response.getBody().asString(),new TypeReference<List<Bug>>(){});

    }
    protected void testCommonHeaders(Response response)
    {
        assertEquals("Status", HttpStatus.SC_OK, response.getStatusCode());
        assertEquals("CONTENT TYPE", "application/json;charset=UTF-8", response.contentType());
        assertEquals("XSS", "1; mode=block", response.getHeader("X-XSS-Protection"));
        assertEquals("DENY", "DENY", response.getHeader("X-Frame-Options"));

    }
}
