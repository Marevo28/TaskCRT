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
                /*Проверка пары*/
                {400,"201","",""},
                {404,"201","    ","    "},
                {400,"201",null,null},
                {404,"201","password","username"},
                {404,"201","    123","   vk_user"},
                {404,"201","<123>","<vk_user>"},
                /*Проверка password*/
                {400,"201","","vk_user"},
                {401,"201","null","vk_user"},
                {401,"201","0","vk_user"},
                {401,"201","1","vk_user"},
                {401,"201","2,2","vk_user"},
                {401,"201","333 ","vk_user"},
                {401,"201","4444","vk_user"},
                {401,"201","-55555","vk_user"},
                {401,"201","666.666","vk_user"},
                {401,"201"," 7777777","vk_user"},
                {401,"201","8888 8888","vk_user"},
                {401,"201","9999999999","vk_user"},
                {401,"201","qwer1234","vk_user"},
                {401,"201","abc","vk_user"},
                {401,"201","прив","vk_user"},
                {401,"201","1$3","vk_user"},
                {401,"201","/n 123","vk_user"},
                {401,"201","'123'","vk_user"},
                {401,"201","1+3","vk_user"},
                {401,"201","%%%","vk_user"},
                {500,"201","%%%","%%%"},
                /*Проверка username*/
                {404,"201","123","456"},
                {404,"201","123","vk$user"},
                {404,"201","123","vk_uservk_uservk_user"},
                {404,"201","123","вк_юзер"},
                {404,"201","123","VK_USER"},
                {404,"201","123","VK_usER"},
                {404,"201","123","'vk_user'"},
                {404,"201","123","null"},
                {400,"201","123",""},
                {404,"201","123","3.3"},
                {404,"201","123","1+3"},
                {500,"201","123","%%%"},
                /*Проверка domain*/
                {400,"","123","vk_user"},
                {400,"null","123","vk_user"},
                {404,"0","123","vk_user"},
                {404,"1","123","vk_user"},
                {400,"2,2","123","vk_user"},
                {400,"333 ","123","vk_user"},
                {404,"4444","123","vk_user"},
                {404,"-55555","123","vk_user"},
                {400,"666.666","123","vk_user"},
                {400," 7777777","123","vk_user"},
                {400,"8888 8888","123","vk_user"},
                {404,"9999999999","123","vk_user"},
                {400,"qwer1234","123","vk_user"},
                {400,"abc","123","vk_user"},
                {400,"прив","123","vk_user"},
                {400,"1$3","123","vk_user"},
                {400,"/n 123","123","vk_user"},
                {400,"'123'","123","vk_user"},
                {400,"'1+3'","123","vk_user"},
        };
    }

    @Test
    public void loginCorrect(){
        UserModel user = new UserModel("201", "123", "vk_user");

        Response session = Reqeust.loginPost (user);
        Assert.assertEquals(200,session.statusCode());
        String sessionId = session.jsonPath().get("session_id");
        //проверить на эквивалетность SessionId
    }

    @Test
    @UseDataProvider("sumTestData")
    public void loginNotCorrect(int k, String m, String n, String l){
        UserModel user = new UserModel(m, n, l);

        Response response = Reqeust.loginPost (user);
        Assert.assertEquals(k,response.statusCode());
    }


}

