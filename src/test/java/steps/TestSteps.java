package steps;

import checks.AssertChecks;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.testng.Assert;
import request.SendRequest;
import response.GetUrlForUploadFileResponse;
import response.NotFoundObject;
import response.OperationStatusResponse;
import response.BaseObjectResponse;
import response.metaData.Items;
import response.metaData.MetaDataResponse;
import response.trash.TrashResponse;
import utils.Log;

import java.io.File;

public class TestSteps {

    private final static String BASE_DISK_URL = "https://cloud-api.yandex.net/v1/disk/resources";
    private final static String BASE_TRASH_URL = "https://cloud-api.yandex.net/v1/disk/trash/resources";
    private final static String TOKEN = "AgAAAABAWWRvAAZUGbXs552EVERij8KKQVHZiQY";

    private SendRequest sendRequest = new SendRequest();
    private AssertChecks checks = new AssertChecks();

    @Step("Create folder step")
    public BaseObjectResponse createFolderMethod(String folderName) {
        Log.info("PUT request for create folder");

        BaseObjectResponse makeFolderActualResponse;
        makeFolderActualResponse = sendRequest.putRequest(BASE_DISK_URL, TOKEN, BaseObjectResponse.class, folderName);

        checks.checkFolderCreation(makeFolderActualResponse, BASE_DISK_URL, folderName);
        return makeFolderActualResponse;
    }

    @Step("Delete empty folder step")
    public void deleteFolderMethod(String url) {
        Log.info("Delete request for object deleting");
        int status;
        String href = url.replaceAll("%2F", "\\/").replaceAll("%3A", "\\:");
                
        status = sendRequest.deleteRequest(href, TOKEN, "true");

        checks.checkDeletedFolder(status);
    }

    @Step("Create folder and get Url for uploading file step")
    public GetUrlForUploadFileResponse getUrlForFileFolderMethod(String folderForFile) {
        Log.info("Get request method for get Url for uploading file ");

        GetUrlForUploadFileResponse getUrlForUploadFileResponse;
        getUrlForUploadFileResponse = sendRequest.getRequest(BASE_DISK_URL+"/upload", TOKEN, GetUrlForUploadFileResponse.class, folderForFile);

        checks.checkFolderForUploadFileCreation(getUrlForUploadFileResponse, BASE_DISK_URL, folderForFile);

        return getUrlForUploadFileResponse;
    }

    @Step("Uploading file step")
    public void uploadFile(String url, String method, File file, String fileName) {
        int getResponseStatus;
        getResponseStatus = sendRequest.putRequestWithAttachments(url, TOKEN, file, fileName);

        checks.checkUploadedFile(getResponseStatus);
    }

    @Step("Sent object to the trash step")
    public void sentObjectToTrashMethod(String url) {
        Log.info("Delete request for sent object to trash");
        int status;
        String href = url.replaceAll("%2F", "\\/").replaceAll("%3A", "\\:");

        status = sendRequest.deleteRequest(href, TOKEN, "false");

        checks.checkDeletedFolder(status);
    }

    @Step("Restore object from trash step")
    public void restoreObjectMethod(String fileName, String folderName ) {
        Log.info("PUT request for restore file from trash");

        BaseObjectResponse makeFolderActualResponse;
        makeFolderActualResponse = sendRequest.putRequest(BASE_TRASH_URL+"/restore", TOKEN, BaseObjectResponse.class, fileName);

        checks.checkObjectRestoring(makeFolderActualResponse, BASE_DISK_URL, fileName, folderName);
    }

    @Step("Delete not empty folder step")
    public void deleteNotEmptyFolderMethod(String url) {
        Log.info("Delete request for not empty object deleting");
        int status;
        String href = url.replaceAll("%2F", "\\/").replaceAll("%3A", "\\:");

        status = sendRequest.deleteRequest(href, TOKEN, "true");

        checks.checkDeletedNotEmptyFolder(status);
    }

    @Step("Sent not empty object to the trash step")
    public void sentNotEmptyObjectToTrashMethod(String url) {
        Log.info("Delete request for sent not empty object to trash");
        int status;
        String href = url.replaceAll("%2F", "\\/").replaceAll("%3A", "\\:");

        status = sendRequest.deleteRequest(href, TOKEN, "false");

        checks.checkDeletedNotEmptyFolder(status);
    }

    @Step("Sent not empty object to the trash step")
    public BaseObjectResponse clearTrashMethod() throws InterruptedException {
        Log.info("Delete request for sent not empty object to trash");
        BaseObjectResponse operationResponse;
        int statusTrash;

        statusTrash = sendRequest.getTrashStatusRequest(BASE_TRASH_URL, TOKEN);

        if (statusTrash != 423){
            operationResponse = sendRequest.deleteTrashRequest(BASE_TRASH_URL, TOKEN, BaseObjectResponse.class);
        } else {
            wait(100);
            operationResponse = sendRequest.deleteTrashRequest(BASE_TRASH_URL, TOKEN, BaseObjectResponse.class);
        }

        checks.checkResponseWhenClearTrash(operationResponse);

        return operationResponse;
    }

    @SneakyThrows
    @Step("Check the status of trash clearing")
    public void checkStatusMethod(String url, String method) {
        Log.info("Check the status of trash clearing");
        OperationStatusResponse getResponseStatus;
        OperationStatusResponse getResponseUpdatedStatus;
        getResponseStatus = sendRequest.getTrashRequest(url, TOKEN, OperationStatusResponse.class);

        if (getResponseStatus.getStatus() == "in-progress"){
            wait(100);
            getResponseUpdatedStatus = sendRequest.getTrashRequest(url, TOKEN, OperationStatusResponse.class);
            checks.checkStatus(getResponseUpdatedStatus, method);
        }
    }
    @Step("Request that check total count in the trash")
    public TrashResponse checkEmptyTrashMethod() {
        Log.info("Request that check total count in the trash");
        TrashResponse countTotalItemsInTrash;
        countTotalItemsInTrash = sendRequest.getTrashRequest(BASE_TRASH_URL, TOKEN, TrashResponse.class);

        checks.chectTotalCountInTresh(countTotalItemsInTrash);
        return countTotalItemsInTrash;
    }

    @Step("Restore object from trash step")
    public MetaDataResponse getObjectMetadata( String folderName, String subFolderName, String fileName ) {
        Log.info("PUT request for restore file from trash");

        MetaDataResponse metaDataResponse;
        metaDataResponse = sendRequest.getRequest(BASE_DISK_URL, TOKEN, MetaDataResponse.class, folderName);

        checks.checkObjectMetadata(metaDataResponse, folderName, subFolderName, fileName);
        return metaDataResponse;
    }

    @Step("Get request for getting the metadata from object step")
    public Items getObjectMetadataWithOutSubfolder(String folderName, String fileName ) {
        Log.info("Get request for getting the metadata from object");

        Items metaDataResponse;
        metaDataResponse = sendRequest.getObjectMetadataRequest(BASE_DISK_URL, TOKEN, Items.class, folderName+ "/" + fileName);

        checks.checkDirectObjectMetadata(metaDataResponse, fileName);

        return metaDataResponse;
    }

    @Step("Restore object from trash step")
    public void getDataAboutObjectMethod(String folderName){
        NotFoundObject notFoundObject;
        notFoundObject = sendRequest.getRequest(BASE_DISK_URL, TOKEN, NotFoundObject.class, folderName);

        checks.checkNotFoundAnsver(notFoundObject);
    }
    @Step("Get data from not empty trash")
    public MetaDataResponse getTrashMetadata() {
        Log.info("Get request data from not empty trash");

        MetaDataResponse metaDataResponse;
        metaDataResponse = sendRequest.getTrashRequest(BASE_TRASH_URL, TOKEN, MetaDataResponse.class);

        return metaDataResponse;
    }

    @Step("Create folder with check empty trash step")
    public BaseObjectResponse createFolderWhenEmptyTrashMethod(String folderName) {
        Log.info("PUT request for create folder");
        BaseObjectResponse makeFolderActualResponse;
        Integer expectedTotalCount = 0;

        TrashResponse countTotalItemsInTrash;
        countTotalItemsInTrash = sendRequest.getTrashRequest(BASE_TRASH_URL, TOKEN, TrashResponse.class);

        if ( countTotalItemsInTrash.equals(expectedTotalCount)  ){
            makeFolderActualResponse = sendRequest.putRequest(BASE_DISK_URL, TOKEN, BaseObjectResponse.class, folderName);
            checks.checkFolderCreation(makeFolderActualResponse, BASE_DISK_URL, folderName);
        } else{
            Log.info("Before create folder the trash was cleared");
            sendRequest.deleteEmptyTrashRequest(BASE_TRASH_URL, TOKEN);
            makeFolderActualResponse = sendRequest.putRequest(BASE_DISK_URL, TOKEN, BaseObjectResponse.class, folderName);
            checks.checkFolderCreation(makeFolderActualResponse, BASE_DISK_URL, folderName);
        }

        return makeFolderActualResponse;
    }

    @Step("Sent file to the trash step")
    public void sentFileToTrashMethod(String url) {
        Log.info("Delete request for sent not empty object to trash");
        int status;
        String href = url.replaceAll("%2F", "\\/").replaceAll("%3A", "\\:");

        status = sendRequest.deleteRequest(href, TOKEN, "false");

        checks.checkStatusDeletedFile(status);
    }

    @Step("Compare files size in the trash step")
    public void compareTrashDataSize(MetaDataResponse currentDataSizeInTrashAfterAddingTwoFiles,
                                     MetaDataResponse initialTrashDataSizeResponse,
                                     Items metaDataFirstFileResponse,
                                     Items metaDataSecondFileResponse ) {
        Log.info("Delete request for sent not empty object to trash");
        int currentTrashSumm = currentDataSizeInTrashAfterAddingTwoFiles.getEmbedded().getItems().get(0).getSize()
                + currentDataSizeInTrashAfterAddingTwoFiles.getEmbedded().getItems().get(1).getSize()
                + currentDataSizeInTrashAfterAddingTwoFiles.getEmbedded().getItems().get(2).getSize();

        int expectedSepareteSummSizeOfFiles = initialTrashDataSizeResponse.getEmbedded().getItems().get(0).getSize() + metaDataFirstFileResponse.getSize() + metaDataSecondFileResponse.getSize();


        checks.checkSizeSumInTnTreshMetadata(currentTrashSumm, expectedSepareteSummSizeOfFiles);





    }


}
