package org.automation.tests;

import io.restassured.http.Header;
import io.restassured.mapper.ObjectMapperType;
import org.apache.http.HttpStatus;
import org.automation.core.RestAssuredTestBase;
import org.automation.model.Bug;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

/**
 * Created by SSarker on 9/2/2018.
 */
@Title("Test View All Bugs URL")
@Features("F1 : Show all bugs to client")
@Stories({
        "JIRA-15 : User can see all bugs when they hit this URL",
        "JIRA-17 : User can see a bug in all bug list that s/he just inserted"})
@Description("This is main class for testing view all")
public class ViewAllBugsTests extends RestAssuredTestBase {

    @Test
    @Issue("JIRA-26")
    @Severity(SeverityLevel.CRITICAL)
    public void testAppRunning() throws IOException {
        request = setDefaultHeaders(request);
        request = request.auth().basic(user, pass);
        response = request.get();
        testCommonHeaders(response);
    }

    @Test
    @Severity(SeverityLevel.TRIVIAL)
    public void testBodyIsNotAcceptable() {
        request = setDefaultHeaders(request);
        request = request.auth().basic(user, pass);
        request = request.body("ShantonuTest");
        response = request.get();
        assertEquals("Status Code", HttpStatus.SC_BAD_REQUEST, response.statusCode());
    }

    @Test
    @Severity(SeverityLevel.TRIVIAL)
    public void testUrlParameterNotAccepted() {
        request = setDefaultHeaders(request);
        request = request.auth().basic(user, pass);
        request = request.param("bugTable", "25");
        response = request.get();
        assertEquals("Status Code", HttpStatus.SC_BAD_REQUEST, response.statusCode());
    }

    @Test
    @Severity(SeverityLevel.TRIVIAL)
    public void testInvalidRequestHeaders() {
        request = request.header(new Header("Accept-Language", "jp,en;q=0.9"));
        request = request.header(new Header("Accept-Encoding", "gzip, deflate"));
        request = request.auth().basic(user, pass);
        response = request.get();
        assertEquals("Status Code", HttpStatus.SC_BAD_REQUEST, response.statusCode());

    }


    @Test
    @Severity(SeverityLevel.BLOCKER)
    public void testWithAllData() throws IOException {
        request = setDefaultHeaders(setCredentials(request));
        response = request.get();
        testCommonHeaders(response);
        assertTrue("Time error", response.getTime() < respose_timeout_ms);
        List<Bug> bugs = getAllBugsFromResponse(response);
        for (Bug aBug : bugs) {
            assertEquals("ID", 1, aBug.getId().intValue());
        }

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Issue("JIRA-20")
    public void testAddOne_validateResponseObject() throws IOException {
        Bug input_data_bug = Bug.getABug();
        request = setDefaultHeaders(request);
        request = request.auth().basic(user,pass);
        request = request.body(input_data_bug, ObjectMapperType.JACKSON_2);
        response = request.post();
        assertEquals("Bug Not Created",HttpStatus.SC_CREATED,response.statusCode());

        //see all bugs => we are testing this
        request = setDefaultHeaders(request);
        request = request.auth().basic(user,pass);
        response = request.get();

        List<Bug> response_bugs = getAllBugsFromResponse(response);
        boolean result = false;
        Long foundBugID = 0l;
        for (Bug bug : response_bugs) {
            if (bug.equalValueWithoutID(input_data_bug)) {
                result = true;
                foundBugID = bug.getId();
                break;
            }
        }
        assertTrue("NO Bug Found", result);
        //cleanup
        if (result) {
            request = setDefaultHeaders(request);
            request = request.auth().basic(user,pass);
            response = request.delete("/" + foundBugID);
            assertEquals("NOT Deleted", HttpStatus.SC_NO_CONTENT, response.statusCode());
        }
    }

}
