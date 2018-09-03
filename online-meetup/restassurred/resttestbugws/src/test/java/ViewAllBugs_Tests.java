
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.automation.config.PropertyLoader;
import org.automation.model.Bug;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

/**
 * Created by SSarker on 9/2/2018.
 */
public class ViewAllBugs_Tests extends RestAssuredTestBase {

    @Before
    public void initForAllTest() throws IOException {
        RestAssured.baseURI = getAppUrl();
        RestAssured.basePath = "/table/bugs";
    }

    @Test
    public void testAppRunning() throws IOException {
        System.out.println(RestAssured.baseURI + RestAssured.basePath);
        given()
                .header(new Header("Accept-Language", "en,bn;q=0.9"))
                .contentType(ContentType.JSON)
                .auth().basic(user, pass)
                .get()
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .header("X-XSS-Protection", "1; mode=block")
                .header("X-Frame-Options", "DENY")
                .time(lessThan(respose_timeout_ms));
    }


    @Test
    public void testWithAllData() throws IOException {
        request = setDefaultHeaders(setCredentials(request));
        response = request.get();
        Bug response_bug = response.as(Bug.class);
        testCommonHeaders(response);
        assertTrue("Time error", response.getTime() < respose_timeout_ms);
        List<Bug> bugs = getAllBugsFromResponse(response);

        for (Bug aBug : bugs) {
            assertEquals("ID", 1, aBug.getId().intValue());
        }


    }

    @Test
    public void testAddOne_validateResponseObject() {
        Bug input_data_bug = Bug.getABug();
        Bug response_data_bug =
                given().
                        auth().basic(user, pass).
                        contentType(ContentType.JSON).
                        body(input_data_bug, ObjectMapperType.JACKSON_2).
                        post().as(Bug.class);

        assertTrue(input_data_bug.equalValueWithoutID(response_data_bug));// validating responseded item is equal to what i put in

        assertEquals("TITLE", input_data_bug.getTitle(), response_data_bug.getTitle());

        System.out.println(response_data_bug.toString());// optional, to view purpose

        //cleanup my data
        given().auth().basic(user, pass).
                delete(response_data_bug.getId().
                        toString())
                .then().
                assertThat().statusCode(HttpStatus.SC_NO_CONTENT);


        given()
                .auth().basic("", "").contentType(ContentType.JSON).body(input_data_bug, ObjectMapperType.JACKSON_2).post();
    }

}
