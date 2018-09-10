package org.automation.tests;

import org.apache.http.HttpStatus;
import org.automation.core.RestAssuredTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

/**
 * Created by SSarker on 9/9/2018.
 */
@RunWith(Parameterized.class)
@Title("Testing Invalid Authentications")
public class InvalidAuthTests extends RestAssuredTestBase {

    @Parameterized.Parameter(value = 0)
    public String user;
    @Parameterized.Parameter(value = 1)
    public String pass;

    @Parameterized.Parameters(name = "{0}")
    public static Collection invalidParameters() {
        return Arrays.asList(new String[][]{
                {"shantonu", "654321"},
                {"muhin", "123456"},
                {"","123456"},
                {null,"123456"},
                {"shantonu",""},
                {"shantonu",null}});
    }



    @Test
    @Title(value = "Testing Invalid Parameters")
    @Description("For checking all individual wrong passwords")
    @Severity(SeverityLevel.TRIVIAL)
    public void testInvalidAuths() {
        request = setDefaultHeaders(request);
        request = request.auth().basic(user, pass);
        response = request.get();
        assertEquals("Status Code", HttpStatus.SC_UNAUTHORIZED, response.statusCode());


    }




}
