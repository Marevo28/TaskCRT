package reqeust;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.UserModel;
import io.restassured.RestAssured;

public class Reqeust {
    static String baseUri = "https://vkplatform5.speechpro.com";
    static String uri = "/vksession/rest/session";

    public static Response loginPost(UserModel user){

        return  RestAssured.given()
                .baseUri(baseUri)
                .basePath(uri)
                .contentType(ContentType.JSON)
                .body(user)
                .log().all()
                .when().post()
                .then().log().all().contentType(ContentType.JSON).extract().response();
    }

    public static Response checkStatus(String session){
        return RestAssured.given()
                .baseUri(baseUri)
                .basePath(uri)
                .contentType(ContentType.JSON)
                .header("X-Session-ID", session)
                .log().all()
                .when().get()
                .then().log().all().contentType(ContentType.JSON).extract().response();
    }

    public static Response deleteSession(String session){
        return  RestAssured.given()
                .baseUri(baseUri)
                .basePath(uri)
                .contentType(ContentType.JSON)
                .header("X-Session-ID", session)
                .log().all()
                .when().delete()
                .then().extract().response();
    }
}
