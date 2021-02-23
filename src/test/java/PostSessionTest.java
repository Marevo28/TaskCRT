import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import pojo.UserModel;
import reqeust.Reqeust;

@RunWith(DataProviderRunner.class)
public class PostSessionTest {

    @DataProvider
    public static Object[][] sumTestData() {
        return new Object[][]{
                /*Проверка password*/
                {"201","","vk_user"},
                {"201","null","vk_user"},
                {"201","0","vk_user"},
                {"201","1","vk_user"},
                {"201","2,2","vk_user"},
                {"201","333 ","vk_user"},
                {"201","4444","vk_user"},
                {"201","-55555","vk_user"},
                {"201","666.666","vk_user"},
                {"201"," 7777777","vk_user"},
                {"201","8888 8888","vk_user"},
                {"201","9999999999","vk_user"},
                {"201","qwer1234","vk_user"},
                {"201","abc","vk_user"},
                {"201","прив","vk_user"},
                {"201","1$3","vk_user"},
                {"201","/n 123","vk_user"},
                {"201","'123'","vk_user"},
                {"201","'1+3'","vk_user"},
                /*Проверка username*/
                {"201","123","456"},
                {"201","123","vk$user"},
                {"201","123","vk_uservk_uservk_user"},
                {"201","123","вк_юзер"},
                {"201","123","VK_USER"},
                {"201","123","'vk_user'"},
                {"201","123","null"},
                {"201","123",""},
                {"201","123","3.3"},
                {"201","123","1+3"},
                {"201","123","vk_user"},
                /*Проверка domain*/
                {"","123","vk_user"},
                {"null","123","vk_user"},
                {"0","123","vk_user"},
                {"1","123","vk_user"},
                {"2,2","123","vk_user"},
                {"333 ","123","vk_user"},
                {"4444","123","vk_user"},
                {"-55555","123","vk_user"},
                {"666.666","123","vk_user"},
                {" 7777777","123","vk_user"},
                {"8888 8888","123","vk_user"},
                {"9999999999","123","vk_user"},
                {"qwer1234","123","vk_user"},
                {"abc","123","vk_user"},
                {"прив","123","vk_user"},
                {"1$3","123","vk_user"},
                {"/n 123","123","vk_user"},
                {"'123'","123","vk_user"},
                {"'1+3'","123","vk_user"},
        };
    }

    @Test
    public void loginCorrect(){
        UserModel user = new UserModel("201", "123", "vk_user");

        Response session = Reqeust.loginPost (user);
        Assert.assertEquals(204,session.statusCode());
        String sessionId = session.jsonPath().get("session_id");
        //проверить на эквивалетность SessionId
    }

    @Test
    @UseDataProvider("sumTestData")
    public void loginNotCorrect(String m, String n, String l){
        UserModel user = new UserModel(m, n, l);

        Response response = Reqeust.loginPost (user);
        Assert.assertEquals(401,response.statusCode());
        //Надо раскинуть подборки на 404-ую и 401-ую ошибку
        //404 при ненаходе в базе
    }


}

