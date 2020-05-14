package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
//import org.testng.annotations.Listeners;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import response.GetUrlForUploadFileResponse;
import response.PutObjectResponse;
import response.metaData.MetaDataResponse;
import steps.TestSteps;
import utils.Log;
//import utils.TestListener;

import java.io.*;

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

        String folderName = "Caldo"+Math.random()*40;
        PutObjectResponse folderResponse;
        TestSteps newFolder = new TestSteps();

        Log.info("************************* Step 1: Create folder **********************************");
        folderResponse = newFolder.createFolderMethod(folderName);

        Log.info("************************* Step 2: Delete folder **********************************");
        newFolder.deleteFolderMethod(folderResponse.getHref());
    }

    @Ignore
    @Test(description = "Test 2: Create, delete file in the folder and check it")
    @Description("Create and delete file in the folder")
    public void createAndDeleteFileInFolder() {
        Log.info("********************************************************************");
        Log.info("Test 2: Create, delete file in folder and check it ");
        Log.info("********************************************************************");
        String folderName = "Test17t"+Math.random()*40;
        //System.out.println(folderName);
        String fileName = "autotest.txt";

        Log.info("************************* Step 1: Create folder **********************************");
        PutObjectResponse folderResponse;
        TestSteps newFolder = new TestSteps();

        folderResponse = newFolder.createFolderMethod(folderName);

        Log.info("************************* Step 2: Get Url for create file in folder **********************************");
        GetUrlForUploadFileResponse folderForFileResponse;
        TestSteps newFolderForFile = new TestSteps();

        folderForFileResponse = newFolderForFile.getUrlForFileFolderMethod(folderName+"/"+fileName);

        Log.info("************************* Step 3: Create file **********************************");
        File imageFile = new File("src\\main\\resources\\autotest.txt");
        newFolderForFile.uploadFile(folderForFileResponse.getHref(), folderForFileResponse.getMethod(), imageFile, fileName);

        Log.info("************************* Step 4: Delete file **********************************");
        newFolderForFile.deleteFolderMethod(folderResponse.getHref()+"/"+fileName);

        Log.info("************************* Step 5: Delete folder **********************************");
        newFolderForFile.deleteFolderMethod(folderResponse.getHref());
    }
    @Ignore
    @Test(description = "Test 3: Restore created file from trash to the folder and check it")
    @Description("Restore created file from trash")
    public void restoreFileFromTrashToFolder() {
        Log.info("********************************************************************");
        Log.info("Test 3: Restore created file from trash to the folder and check it");
        Log.info("********************************************************************");
        String folderName = "Note" + Math.random() * 4;
        //System.out.println(folderName);
        String fileName = "autotest.txt";

        Log.info("************************* Step 1: Create folder **********************************");
        PutObjectResponse folderResponse;
        TestSteps newFolder = new TestSteps();

        folderResponse = newFolder.createFolderMethod(folderName);

        Log.info("************************* Step 2: Get Url for create file in folder **********************************");
        GetUrlForUploadFileResponse folderForFileResponse;
        TestSteps newFolderForFile = new TestSteps();

        folderForFileResponse = newFolderForFile.getUrlForFileFolderMethod(folderName + "/" + fileName);

        Log.info("************************* Step 3: Create file **********************************");
        File imageFile = new File("src\\main\\resources\\autotest.txt");
        newFolderForFile.uploadFile(folderForFileResponse.getHref(), folderForFileResponse.getMethod(), imageFile, fileName);

        Log.info("************************* Step 4: Sent file to trash **********************************");
        newFolderForFile.sentObjectToTrashMethod(folderResponse.getHref() + "/" + fileName);

        Log.info("************************* Step 5: Restore file **********************************");
        newFolderForFile.restoreObjectMethod(fileName, folderName);

        Log.info("************************* Step 6: Delete folder **********************************");
        newFolderForFile.deleteNotEmptyFolderMethod(folderResponse.getHref());
    }
    @Ignore
    @Test(description = "Test 4: Clear trash and check it")
    @Description("Restore created file from trash")
    public void clearTrash() throws InterruptedException {
        Log.info("********************************************************************");
        Log.info("Test 4: Clear trash and check it");
        Log.info("********************************************************************");
        String folderName = "test";
        String subfolder = "foo";
        //System.out.println(folderName);
        String fileName = "autotest.txt";

        Log.info("************************* Step 1: Create folder **********************************");
        PutObjectResponse folderResponse;
        TestSteps newFolder = new TestSteps();

        folderResponse = newFolder.createFolderMethod(folderName);

        Log.info("************************* Step 2: Create subfolder folder **********************************");
        TestSteps newSubFolder = new TestSteps();
        newSubFolder.createFolderMethod(folderName+"/"+subfolder);

        Log.info("************************* Step 3: Get Url for create file in folder **********************************");
        GetUrlForUploadFileResponse folderForFileResponse;
        TestSteps newFolderForFile = new TestSteps();

        folderForFileResponse = newFolderForFile.getUrlForFileFolderMethod(folderName+"/"+subfolder + "/" + fileName);

        Log.info("************************* Step 4: Create file **********************************");
        File imageFile = new File("src\\main\\resources\\autotest.txt");
        newFolderForFile.uploadFile(folderForFileResponse.getHref(), folderForFileResponse.getMethod(), imageFile, fileName);

        Log.info("************************* Step 5: Sent folder to trash **********************************");
        newFolder.sentNotEmptyObjectToTrashMethod(folderResponse.getHref());

        Log.info("************************* Step 6: Clear trash **********************************");
        PutObjectResponse trashResponse;
        trashResponse = newFolder.clearTrashMethod();

        Log.info("************************* Step 7: Check that clearing the trash is success **********************************");
        newFolder.checkStatusMethod(trashResponse.getHref(), trashResponse.getMethod());

        Log.info("************************* Step 8: Check that trash is empty **********************************");
        newFolder.checkEmptyTrashMethod();
    }

    @Ignore
    @Test(description = "Test 5: Get metadata about folder and check it")
    @Description("Get metadata about folder")
    public void getFolderMetaData() throws InterruptedException {
        Log.info("********************************************************************");
        Log.info("Test 5: Get metadata about folder and check it");
        Log.info("********************************************************************");
        String folderName = "test";
        String subfolder = "foo";
        String fileName = "autotest.txt";

        Log.info("************************* Step 1: Create folder **********************************");
        PutObjectResponse folderResponse;
        TestSteps newFolder = new TestSteps();

        folderResponse = newFolder.createFolderMethod(folderName);

        Log.info("************************* Step 2: Create subfolder folder **********************************");
        PutObjectResponse subfolderResponse;
        TestSteps newSubFolder = new TestSteps();

        subfolderResponse = newSubFolder.createFolderMethod(folderName+"/"+subfolder);

        Log.info("************************* Step 3: Get Url for create file in folder **********************************");
        GetUrlForUploadFileResponse folderForFileResponse;
        TestSteps newFolderForFile = new TestSteps();

        folderForFileResponse = newFolderForFile.getUrlForFileFolderMethod(folderName + "/" + fileName);

        Log.info("************************* Step 4: Create file **********************************");
        File imageFile = new File("src\\main\\resources\\autotest.txt");
        newFolderForFile.uploadFile(folderForFileResponse.getHref(), folderForFileResponse.getMethod(), imageFile, fileName);

        Log.info("************************* Step 5: Get metadata about folder and check it **********************************");
        newFolder.getObjectMetadata(folderName, subfolder, fileName);

        Log.info("************************* Step 6: Delete folder **********************************");
        newFolder.deleteNotEmptyFolderMethod(folderResponse.getHref());

        Log.info("************************* Step 7: Check that all deleted **********************************");
        newFolder.getDataAboutObjectMethod(folderName);
        newSubFolder.getDataAboutObjectMethod(folderName+"/"+subfolder);
        newFolderForFile.getDataAboutObjectMethod(folderName + "/" + fileName);
    }

    @Test(description = "Test 6: Check the size of folder files")
    @Description("Check the size of folder files")
    public void CountFolderDataSize() throws InterruptedException {
        Log.info("********************************************************************");
        Log.info("Test 6: Check the size of folder files");
        Log.info("********************************************************************");
        String folderName = "test"+Math.random()*40;
        String fileName = "autotest.txt";
        String fileName1 = "autotest1.txt";
        String fileName2 = "autotest2.txt";

        Log.info("************************* Step 1: Create folder **********************************");
        PutObjectResponse folderResponse;
        TestSteps newFolder = new TestSteps();

        folderResponse = newFolder.createFolderMethod(folderName);

         ////////////////////////////////////////////////////////////////////////////////////////////////////
        Log.info("************************* Step 2: Get Url for create file in folder **********************************");
        GetUrlForUploadFileResponse folderForFileResponse;
        TestSteps newFolderForFile = new TestSteps();

        folderForFileResponse = newFolderForFile.getUrlForFileFolderMethod(folderName + "/" + fileName);

        Log.info("************************* Step 3: Create first file **********************************");
        File imageFile = new File("src\\main\\resources\\autotest.txt");
        newFolderForFile.uploadFile(folderForFileResponse.getHref(), folderForFileResponse.getMethod(), imageFile, fileName);
        //////////////////////////////////////////////////////////////////////////////////////////////

        Log.info("************************* Step 4: Get Url for create file in folder **********************************");
        GetUrlForUploadFileResponse folderForFileResponse1;
        TestSteps newFolderForFile1 = new TestSteps();

        folderForFileResponse1 = newFolderForFile1.getUrlForFileFolderMethod(folderName + "/" + fileName1);

        Log.info("************************* Step 5: Create thred file **********************************");
        File imageFile1 = new File("src\\main\\resources\\autotest1.txt");
        newFolderForFile1.uploadFile(folderForFileResponse1.getHref(), folderForFileResponse.getMethod(), imageFile1, fileName1);

        //////////////////////////////////////////////////////////////////////////
        Log.info("************************* Step 4: Get Url for create file in folder **********************************");
        GetUrlForUploadFileResponse folderForFileResponse2;
        TestSteps newFolderForFile2 = new TestSteps();

        folderForFileResponse2 = newFolderForFile2.getUrlForFileFolderMethod(folderName + "/" + fileName2);

        Log.info("************************* Step 5: Create second file **********************************");
        File imageFile2 = new File("src\\main\\resources\\autotest2.txt");
        newFolderForFile2.uploadFile(folderForFileResponse2.getHref(), folderForFileResponse.getMethod(), imageFile2, fileName2);

        Log.info("************************* Step 6: Get metadata about folder and check it **********************************");
        newFolder.getObjectMetadata(folderName, fileName);


//        Log.info("************************* Step 6: Sent file to trash **********************************");
//        newFolderForFile2.sentObjectToTrashMethod(folderResponse.getHref() + "/" + fileName2);

//        Log.info("************************* Step 5: Get metadata about folder and check it **********************************");
//        newFolder.getObjectMetadata(folderName, subfolder, fileName);
//
//        Log.info("************************* Step 6: Delete folder **********************************");
//        newFolder.deleteNotEmptyFolderMethod(folderResponse.getHref());
//
//        Log.info("************************* Step 7: Check that all deleted **********************************");
//        newFolder.getDataAboutObjectMethod(folderName);
//        newSubFolder.getDataAboutObjectMethod(folderName+"/"+subfolder);
//        newFolderForFile.getDataAboutObjectMethod(folderName + "/" + fileName);
    }










}

