
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import pojo.UserModel;
import reqeust.Reqeust;

@RunWith(DataProviderRunner.class)
public class GetSessionTest {

    private static String sessionId;

    @BeforeClass
    public static void beforeClass(){
        UserModel user = new UserModel("201", "123", "vk_user");

        Response session = Reqeust.loginPost (user);
        sessionId = session.jsonPath().get("session_id");
    }

    @DataProvider
    public static Object[][] sumTestData() {
        return new Object[][]{
                {""},
                {"%$%#GDDGDGDGDGDG545354"},
                {"null"},
                {"1000000"}
        };
    }

    @Test
    public void checkCorrect(){
        Response response = Reqeust.checkStatus(sessionId);
        Assert.assertTrue(response.jsonPath().get("is_active"));
    }
    @Test
    @UseDataProvider("sumTestData")
    public void checkNotCorrect(String m){
        Response response = Reqeust.checkStatus(m);
        Assert.assertEquals(400,response.statusCode());
    }


}
