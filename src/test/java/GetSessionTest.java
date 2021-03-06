
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
                {400,""},
                {400,"0"},
                {400,"%$%#GDDGDGDGDGDG545354%$%#GDDGDGDGDGDG545354%$%#GDDGDGDGDGDG545354%$%#GDDGDGDGDGDG545354"},
                {400,"null"},
                {400,"1000000"},
                {400,"beO8fd8d-579b-4e56-949f-b97a00774151-beO8fd8d-579b-4e56-949f-b97a00774151"},
                {400,"be.8fd8d-579b-4e56-949f-b97a00774151"},
                {400,"be!8fd8d-579b-4e56-949f-b97a00774151"},
                {400,"'be08fd8d-579b-4e56-949f-b97a00774151'"},
                {400,"null-null-null-null-null"},
                {400,"DROP TABLE username"},
                {400,"(<script>alert(«Hello, world!»)</alert>, <script>document.getElementByID(«…»).disabled=true</script>)"},
        };
    }

    @Test
    public void checkCorrect(){
        Response response = Reqeust.checkStatus(sessionId);
        Assert.assertTrue(response.jsonPath().get("is_active"));
    }
    @Test
    @UseDataProvider("sumTestData")
    public void checkNotCorrect(int k, String m){
        Response response = Reqeust.checkStatus(m);
        Assert.assertEquals(k,response.statusCode());
    }


}
