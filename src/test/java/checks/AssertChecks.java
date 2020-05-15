package checks;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import response.GetUrlForUploadFileResponse;
import response.NotFoundObject;
import response.OperationStatusResponse;
import response.BaseObjectResponse;
import response.metaData.Items;
import response.metaData.MetaDataResponse;
import response.trash.TrashResponse;
import utils.Log;

public class AssertChecks {
    int expectedStatus204 = 204;
    int expectedStatus201 = 201;
    int expectedStatus202 = 202;
    String expected404 = "DiskNotFoundError";
    String expectedMethodGet = "GET";
    String expectedMethodPut = "PUT";
    String expectedStatus = "success";
    String expectedDir = "dir";
    String expectedFile = "file";
    Integer expectedTotal = 0;
    Boolean result = false;

    /* Check Folder Creation*/
    public void checkFolderCreation(BaseObjectResponse actual, String url, String folderName) {

        String href = url + "?path=disk%3A%2F" + folderName.replaceAll("\\/", "%2F");

        Assert.assertEquals(actual.getHref(), href, "Expected href is not equal actual");
        Assert.assertEquals(actual.getMethod(), expectedMethodGet, "Expected method is not equal actual");
        Assert.assertEquals(actual.getTemplated(), result, "Expected templated is not equal actual");
        Log.info("All keys from response object are correct");
    }

    public void checkDeletedFolder(int result){
        Assert.assertEquals(result, expectedStatus204, "Expected status is not 204");

        Log.info("The get correct status for operation");
    }

    public void checkFolderForUploadFileCreation (GetUrlForUploadFileResponse actual, String url, String folderName){

        Assert.assertEquals(actual.getOperationId().isEmpty(), false, "Expected operationId is not equal actual");
        Assert.assertEquals(actual.getHref().isEmpty(), false, "Expected href is not equal actual");
        Assert.assertEquals(actual.getMethod(), expectedMethodPut, "Expected method is not equal actual");
        Assert.assertEquals(actual.getTemplated(), result, "Expected templated is not equal actual");
        Log.info("All keys from response object are correct");
    }

    public void checkUploadedFile(int result){
        Assert.assertEquals(result, expectedStatus201, "Expected status is not 201");

        Log.info("The file successfully uploaded");
    }

    public void checkObjectRestoring (BaseObjectResponse actual, String url, String fileName, String folderName) {

        String href = url + "?path=disk%3A%2F" + folderName + "%2F" + fileName;

        Assert.assertEquals(actual.getHref(), href, "Expected href is not equal actual");
        Assert.assertEquals(actual.getMethod(), expectedMethodGet, "Expected method is not equal actual");
        Assert.assertEquals(actual.getTemplated(), result, "Expected templated is not equal actual");
        Log.info("All keys from response object are correct");
    }


    public void checkDeletedNotEmptyFolder(int result){
        Assert.assertEquals(result, expectedStatus202, "Expected status is not 202");

        Log.info("The get correct status for operation");
    }

    public void checkStatusDeletedFile(int result){
        Assert.assertEquals(result, expectedStatus204, "Expected status is not 204");

        Log.info("The get correct status for operation");
    }

    public void checkResponseWhenClearTrash (BaseObjectResponse actual){

        Assert.assertEquals(actual.getHref().isEmpty(), false, "Expected href is not equal actual");
        Assert.assertEquals(actual.getMethod(), expectedMethodGet, "Expected method is not equal actual");
        Assert.assertEquals(actual.getTemplated(), result, "Expected templated is not equal actual");
        Log.info("All keys from response object are correct when clear object");
    }

    public void checkStatus (OperationStatusResponse actual, String actualMethod){

        Assert.assertEquals(actual.getStatus(), expectedStatus, "Expected status is not equal actual");
        Assert.assertEquals(actualMethod, expectedMethodGet, "Expected method is not equal actual");

        Log.info("All keys from response object are correct when check status");
    }

    public void chectTotalCountInTresh (TrashResponse actual){

        Assert.assertEquals(actual.getEmbedded().getTotal(), expectedTotal, "Expected total is not equal actual");

        Log.info("All keys from response object are correct when thash is empty");
    }

    public void checkObjectMetadata (MetaDataResponse actual, String folderName, String subFolderName, String fileName){

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actual.getName(), folderName, "Expected folderName is not equal actual");
        softAssert.assertEquals(actual.getEmbedded().getItems().get(0).getName(), subFolderName, "Expected subFolderName is not equal actual");
        softAssert.assertEquals(actual.getEmbedded().getItems().get(1).getName(), fileName,"Expected fileName is not equal actual");

        softAssert.assertEquals(actual.getType(), expectedDir, "Expected metaType is not equal actual");
        softAssert.assertEquals(actual.getEmbedded().getItems().get(0).getType(), expectedDir, "Expected metaType is not equal actual");
        softAssert.assertEquals(actual.getEmbedded().getItems().get(1).getType(), expectedFile,"Expected metaType is not equal actual");
        softAssert.assertAll();
        Log.info("All keys from response object are correct when get metadata of object");
    }

    public void checkDirectObjectMetadata (Items actual, String fileName){

        Assert.assertEquals(actual.getName(), fileName,"Expected fileName is not equal actual");

        Log.info("All keys from response object are correct when get metadata of object");
    }

    public void checkSizeSumInTnTreshMetadata (int actual, int expected){

        Assert.assertEquals(actual , expected,"Expected sum is not equal actual");

        Log.info("The actual trash sum size is equal expected");
    }
}
