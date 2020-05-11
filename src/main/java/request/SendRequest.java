package request;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import response.EmptyResponseInDeletedFolder;
import utils.Log;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SendRequest {
    public <T> T getRequest(String url, String token, Class<T> classResponse, String folderName) {
        Log.info("Start getRequest method");
        return
                createdHeader(token)
                        .when()
                        .log().uri()
                        .queryParam("path", folderName)
                        .get(url)
                        .then()
                        .log().status()
                        .log().headers()
                        .log().body()
                        .extract().response().as(classResponse);
    }

    public <T> T putRequest(String url, String token, Class<T> classResponse, String folderName) {
        Log.info("Start putRequest method");
        return
                createdHeader(token)
                        .when()
                        .queryParam("path", folderName)
                        .put(url)
                        .then()
                        .log().status()
                        .log().headers()
                        .log().body()
                        .extract().response().as(classResponse);
    }

    public <T> T putRequestWithAttachments(String url, String token, Class<T> classResponse, String filePath, String fileName) {
        Log.info("Start putRequest method");
        return
                createdHeader(token)
                        .when()
                        .multiPart(fileName, new File("src\\main\\resources\\Screen_23.jpg"))
                        //.contentType("image/jpeg")
                        .put(url)
                        .then()
                        .log().status()
                        .log().headers()
                        .log().body()
                        .extract().response().as(classResponse);
    }

    public Integer deleteRequest(String url, String token, String offOn) {
        Log.info("Start deleteRequest method");
        return
                createdHeader(token)
                        .when()
                        .queryParam("permanently", offOn)
                        .delete(url)
                        .then()
                        .log().status()
                        .extract().statusCode();


    }

    private RequestSpecification createdHeader(String token) {
        return given()
                .relaxedHTTPSValidation()
                .request()
                .header("Authorization", token)
                .log().uri()
                .log().headers();
    }
}
