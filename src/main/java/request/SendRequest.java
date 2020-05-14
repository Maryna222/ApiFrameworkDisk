package request;

import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import utils.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import static io.restassured.RestAssured.given;

public class SendRequest {
    public synchronized <T> T getRequest(String url, String token, Class<T> classResponse, String folderName) {
        Log.info("Start getRequest method");
        return
                createdHeader(token)
                        .when()
                        .queryParam("path", folderName)
                        .get(url)
                        .then()
                        .log().status()
                        .log().headers()
                        .log().body()
                        .extract().response().as(classResponse);
    }

    public synchronized <T> T getTrashRequest(String url, String token, Class<T> classResponse) {
        Log.info("Start getTrashRequest method");
        return
                createdHeader(token)
                        .when()
                        .get(url)
                        .then()
                        .log().status()
                        .log().headers()
                        .log().body()
                        .extract().response().as(classResponse);
    }

    public synchronized int getTrashStatusRequest(String url, String token) {
        Log.info("Start getTrashRequest method");
        return
                createdHeader(token)
                        .when()
                        .get(url)
                        .then()
                        .log().status()
                        .log().headers()
                        .log().body()
                        .extract().response().statusCode();
    }

//    public synchronized <T> T getTrashContentRequest(String url, String token, Class<T> classResponse) {
//        Log.info("Start get Trash Content Request method");
//        return
//                createdHeader(token)
//                        .when()
//                        .get(url)
//                        .then()
//                        .log().status()
//                        .log().headers()
//                        .log().body()
//                        .extract().response().as(classResponse);
//    }

    public synchronized <T> T putRequest(String url, String token, Class<T> classResponse, String folderName) {
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


    public synchronized int putRequestWithAttachments(String url, String token, File filePath, String fileName) {
        Log.info("Start putRequest method");
        return
                createdHeader(token)
                        .multiPart(fileName, filePath,"text/plain")
                        .contentType("multipart/text/plain")
                        .when()
                        .put(url)
                        .then()
                        .log().status()
                        .log().headers()
                        .log().body()
                        .extract().response().statusCode();
    }

    public synchronized <T> T deleteTrashRequest(String url, String token, Class<T> classResponse) {
        Log.info("Start delete trash method");
        return
                createdHeader(token)
                        .when()
                        .delete(url)
                        .then()
                        .log().status()
                        .log().headers()
                        .log().body()
                        .extract().response().as(classResponse);
    }

    public synchronized Integer deleteRequest(String url, String token, String offOn) {
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
