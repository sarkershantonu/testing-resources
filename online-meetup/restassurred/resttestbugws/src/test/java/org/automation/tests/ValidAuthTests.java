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
@Title("Testing valid Authentications")
public class ValidAuthTests extends RestAssuredTestBase {

    @Parameterized.Parameter(value = 0)
    public String user;
    @Parameterized.Parameter(value = 1)
    public String pass;

    @Parameterized.Parameters(name = "{0}")
    public static Collection validParameters() {
        return Arrays.asList(new String[][]{
                {"shantonu", "123456"},});
    }
    @Test
    @Title(value = "Testing Valid Parameters")
    @Description("For checking all valid passwords")
    @Severity(SeverityLevel.CRITICAL)
    public void testValidAuths() {
        request = setDefaultHeaders(request);
        request = request.auth().basic(user, pass);
        response = request.get();
        assertEquals("Status Code", HttpStatus.SC_OK, response.statusCode());


    }
}
