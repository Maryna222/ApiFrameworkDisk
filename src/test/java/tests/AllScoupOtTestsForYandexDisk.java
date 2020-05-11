package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
//import org.testng.annotations.Listeners;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import response.GetUrlForUploadFileResponse;
import response.MakeFolderResponse;
import steps.TestSteps;
import utils.Log;
import request.SendRequest;
//import utils.TestListener;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

//@Listeners({TestListener.class})
@Epic("Epic: Pull of tests for yansex.disk")
@Feature("Feature 1: check that created folder was deleted")
public class AllScoupOtTestsForYandexDisk {
    @Ignore
    @Test(description = "Test 1: Create, delete new folder and check it")
    @Description("Create and delete new folder")
    public void createAndDeleteFolder() {
        Log.info("********************************************************************");
        Log.info("Test 1: Create, delete new folder and check it ");
        Log.info("********************************************************************");

        MakeFolderResponse folderResponse;
        TestSteps newFolder = new TestSteps();

        Log.info("************************* Step 1: Create folder **********************************");
        folderResponse = newFolder.createFolderMethod("CaldoR");

        Log.info("************************* Step 2: Delete folder **********************************");
        newFolder.deleteFolderMethod(folderResponse.getHref());
    }


    @Test(description = "Test 2: Create, delete file in the folder and check it")
    @Description("Create and delete file in the folder")
    public void createAndDeleteFileInFolder() {
        Log.info("********************************************************************");
        Log.info("Test 2: Create, delete file in folder and check it ");
        Log.info("********************************************************************");
        String folderName = "Test5";
        String fileName = "FileImage1";

        Log.info("************************* Step 1: Create folder **********************************");
        MakeFolderResponse folderResponse;
        TestSteps newFolder = new TestSteps();

        folderResponse = newFolder.createFolderMethod(folderName);

        Log.info("************************* Step 2: Get Url for create file in folder **********************************");
        GetUrlForUploadFileResponse folderForFileResponse;
        TestSteps newFolderForFile = new TestSteps();

        folderForFileResponse = newFolderForFile.getUrlForFileFolderMethod(folderName+"/"+fileName);
        folderForFileResponse.getHref();

        Log.info("************************* Step 3: Create file **********************************");
        String imageFile = "src\\main\\resources\\Screen_23.jpg";

        newFolderForFile.uploadFile(folderForFileResponse.getHref(), folderForFileResponse.getMethod(), imageFile, fileName);
//        folderForFileResponse = newFolderForFile.createFileMethod("FolderForFile");
//
//        Log.info("************************* Step 2: Delete folder **********************************");
//        newFolderForFile.deleteFolderMethod(folderForFileResponse.getHref());
    }






    @Ignore
    @Test(description = "Test 0: Check get folder")//, dependsOnMethods = { "checkCreateFolder" })
    @Description("Test 0")
    public void checkGetFolder() {
        Log.info("********************************************************************");
        Log.info("Test 0: Check get folder");
        Log.info("********************************************************************");
        Log.info("Reguest for get all planets");
        SendRequest sendRequest = new SendRequest();
        MakeFolderResponse makeFolderTest;
        MakeFolderResponse makeFolderTest2;
        String URL_ORDER = "https://cloud-api.yandex.net/v1/disk/resources";
        String TOKEN = "AgAAAABAWWRvAAZUGbXs552EVERij8KKQVHZiQY";
        makeFolderTest = sendRequest.getRequest(URL_ORDER, TOKEN,  MakeFolderResponse.class, "TestOne/New");
        Log.info("********************************************************************");
        //makeFolderTest2 = sendRequest.getRequest(URL_ORDER, TOKEN, MakeFolderResponse.class, "test");
    }
    @Ignore
    @Test(description = "Test 1: Check delete folder", dependsOnMethods = { "checkGetFolder" })
    @Description("Test 1")
    public void checkDeleteFolder() {
        Log.info("********************************************************************");
        Log.info("Test 1: Check delete folder");
        Log.info("********************************************************************");
        Log.info("Reguest for delete folder");
        SendRequest sendRequest = new SendRequest();
        MakeFolderResponse makeFolderTest;
        String URL_ORDER = "https://cloud-api.yandex.net/v1/disk/resources?permanently=true&path=disk:/test";
        String TOKEN = "AgAAAABAWWRvAAZUGbXs552EVERij8KKQVHZiQY";
        //sendRequest.deleteRequest(URL_ORDER, TOKEN);
    }
//    @Test(description = "Test 1: Check delete folder", dependsOnMethods = { "checkGetFolder" })
//    @Description("Test 1")
//    public void checkDeleteFolder() {
//    Log.info("********************************************************************");
//    Log.info("Test 1: Check delete folder");
//    Log.info("********************************************************************");
//    Log.info("Reguest for delete folder");
//    SendRequest sendRequest = new SendRequest();
//    EmptyResponse emptyResponse;
//    String URL_ORDER = "https://cloud-api.yandex.net/v1/disk/resources?permanently=true&path=disk:/test";
//    String TOKEN = "AgAAAABAWWRvAAZUGbXs552EVERij8KKQVHZiQY";
//    emptyResponse = sendRequest.deleteRequest(URL_ORDER, TOKEN, EmptyResponse.class);
//}



}

