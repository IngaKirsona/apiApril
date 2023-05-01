package apiApril.clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static apiApril.constants.ProjectConstants.API_KEY;
import static apiApril.constants.ProjectConstants.API_TOKEN;
//HERE WE ARE CREATING THE METHODS WHICH WILL BE CALLED AFTERWARD

public class TrelloClient {

    //to not use the same repeating code, use this, will add this in the first method:
    private static RequestSpecification trelloSpec(){
        return RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .queryParam("key",API_KEY)
                .queryParam("token", API_TOKEN);
    }

    public static Response getBoardInfo(String boardId){
        return RestAssured
                //to log everything from the calls
                .given(trelloSpec())
                .when()
                .get("https://api.trello.com/1/boards/"+ boardId)
                //to receive the information
                .then().log().all()
                //what status code we are expecting, if another code will be received, test at this step will be failed
                .statusCode(200)
                //to get back response from server
                .extract().response();
    }
    public static Response updateBoardInfo(String name, String boardId) {
        return RestAssured
                //to log everything from the calls
                .given().log().all()
                //will be communicating with server using JSOn and receiving back JSON
                .contentType(ContentType.JSON)
                //naming needs to be exactly the same as in api documentation, to access the PI_KEY, option+enter
                .queryParam("key",API_KEY)
                .queryParam("token",API_TOKEN)
                //to update the name
                .queryParam("name",name)
                .when()
                .put("https://api.trello.com/1/boards/"+ boardId)
                //to receive the information
                .then().log().all()
                //what status code we are expecting, if another code will be received, test at this step will be failed
                .statusCode(200)
                //to get back response from server
                .extract().response();
    }
    public static Response createList(String name, String boardId) {
        return RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .queryParam("key",API_KEY)
                .queryParam("token",API_TOKEN)
                .queryParam("name", name)
                .queryParam("idBoard", boardId)
                .when()
                .post ("https://api.trello.com/1/lists")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    //we need list id:
    public static Response deleteList(String id){
        return RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .queryParam("key",API_KEY)
                .queryParam("token", API_TOKEN)
                .queryParam("value", true)
                .when()
                .put("https://api.trello.com/1/lists/" + id + "/closed")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

}
