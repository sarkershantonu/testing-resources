package org.automation.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.automation.config.PropertyLoader;
import org.automation.model.Bug;
import org.junit.Assert;
import org.junit.BeforeClass;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by SSarker on 9/2/2018.
 */
public class RestAssuredTestBase {
    protected RequestSpecification request = given().log().all();
    protected  Response response = null;
    protected static String user = "";
    protected static String pass = "";
    protected static Long respose_timeout_ms = 0L;

    @BeforeClass
    @Step(value = "Default Init for all tests")
    @Description(" This is load property, init all default elements")
    @Severity(SeverityLevel.BLOCKER)
    public static void initForEveryTestClass() throws IOException {
        PropertyLoader.loadProperties();
        user = System.getProperty("user.name");
        pass = System.getProperty("user.pass");
        respose_timeout_ms = Long.valueOf(System.getProperty("test.app.response.timeout"));

        RestAssured.baseURI = getAppUrl();
        RestAssured.basePath = "/table/bugs";
    }

    @Step
    @Severity(SeverityLevel.BLOCKER)
    @Description("Getting the URL")
    protected static String getAppUrl() {
        return "http://" + System.getProperty("app.host") + ":" + System.getProperty("app.port");
    }

    @Step
    protected RequestSpecification setCredentials(RequestSpecification requestSpecification) {
        return requestSpecification.auth().basic(user, pass);
    }

    @Step
    protected RequestSpecification setDefaultHeaders(RequestSpecification requestSpecification) {
        return requestSpecification.contentType(ContentType.JSON).header(new Header("Accept-Language", "en,bn;q=0.9"));
    }

    @Step(value = "Parsing all Bugs")
    protected List<Bug> getAllBugsFromResponse(Response response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.readValue(response.getBody().asString(),new TypeReference<List<Bug>>(){});

    }
    @Step(value = "Common Checks for all responses")
    protected void testCommonHeaders(Response response)
    {
        assertEquals("Status", HttpStatus.SC_OK, response.getStatusCode());
        assertEquals("CONTENT TYPE", "application/json;charset=UTF-8", response.contentType());
        assertEquals("XSS", "1; mode=block", response.getHeader("X-XSS-Protection"));
        assertEquals("DENY", "DENY", response.getHeader("X-Frame-Options"));
        assertTrue("TIME",response.getTime()<respose_timeout_ms);
    }
    @Step
    public <T> String getJsonString(T t) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(t);
    }
}
