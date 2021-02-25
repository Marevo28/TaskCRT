import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pojo.UserModel;
import reqeust.Reqeust;

@RunWith(DataProviderRunner.class)

public class DeleteSessionTest {

    private static String sessionId;
    @DataProvider
    public static Object[][] sumTestData() {
        return new Object[][]{
                {400,""},
                {400,"0"},
                {400,"-1"},
                {400,"null"},
                {401,"be08fd8d-579b-4e56-949f-b97a00774151"},
                {400,"be08fd8d-579b-4e56-949f-b97a00774151-be08fd8d-579b-4e56-949f-b97a00774151"},
                {400,"be++++8d-579b-4e56-949f-b97a00774151"},
                {400,"be08fd8d 579b 4e56 949f b97a00774151"},
                {400,"beO8fd8d-579b-4e56-949f-b97a00774151"},
                {400,"be08fd8d-579b-4e56-949f-b97a00774!51"},
        };
    }
    @Before
    public void before(){
        UserModel user = new UserModel("201", "123", "vk_user");

        Response session = Reqeust.loginPost (user);
        sessionId = session.jsonPath().get("session_id");
    }

    @Test
    public void deleteCorrect(){
        Response delete = Reqeust.deleteSession(sessionId);
        Assert.assertEquals(204,delete.statusCode());
        //проверить на эквивалетность SessionId
    }

    @Test
    @UseDataProvider("sumTestData")
    public void deleteNotCorrect(int k,String m){

        Response delete = Reqeust.deleteSession(m);
        Assert.assertEquals(k,delete.statusCode());
        //Можно брать активный SessionID и в него подтсавлять уже различные проверки, изменяя 1 или несколько случайных символов
    }



}
