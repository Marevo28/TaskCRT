
import io.restassured.response.Response;
import org.junit.Assert;
import pojo.UserModel;
import org.junit.Test;
import reqeust.Reqeust;

public class FullTest {
    @Test
    public void fullCorrectTest() {

        UserModel user = new UserModel("201", "123", "vk_user");

        Response session = Reqeust.loginPost (user);
        Assert.assertEquals(204,session.statusCode());
        String sessionId = session.jsonPath().get("session_id");

        Response check = Reqeust.checkStatus(sessionId);
        Assert.assertTrue(check.jsonPath().get("is_active"));

        Response delete = Reqeust.deleteSession(sessionId);
        Assert.assertEquals(204,delete.statusCode());
    }
}
