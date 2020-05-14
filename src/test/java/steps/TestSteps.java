package steps;

import checks.AssertChecks;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import request.SendRequest;
import response.GetUrlForUploadFileResponse;
import response.NotFoundObject;
import response.OperationStatusResponse;
import response.PutObjectResponse;
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
    public PutObjectResponse createFolderMethod(String folderName) {
        Log.info("PUT reguest for create folder");

        PutObjectResponse makeFolderActualResponse;
        makeFolderActualResponse = sendRequest.putRequest(BASE_DISK_URL, TOKEN, PutObjectResponse.class, folderName);

        checks.checkFolderCreation(makeFolderActualResponse, BASE_DISK_URL, folderName);
        return makeFolderActualResponse;
    }

    @Step("Delete empty folder step")
    public void deleteFolderMethod(String url) {
        Log.info("Delete reguest for object deleting");
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

    public void uploadFile(String url, String method, File file, String fileName) {
        int getResponseStatus;
        getResponseStatus = sendRequest.putRequestWithAttachments(url, TOKEN, file, fileName);

        checks.checkUploadedFile(getResponseStatus);
    }

    @Step("Sent object to the trash step")
    public void sentObjectToTrashMethod(String url) {
        Log.info("Delete reguest for sent object to trash");
        int status;
        String href = url.replaceAll("%2F", "\\/").replaceAll("%3A", "\\:");

        status = sendRequest.deleteRequest(href, TOKEN, "false");

        checks.checkDeletedFolder(status);
    }

    @Step("Restore object from trash step")
    public PutObjectResponse restoreObjectMethod(String fileName,  String folderName ) {
        Log.info("PUT reguest for restore file from trash");

        PutObjectResponse makeFolderActualResponse;
        makeFolderActualResponse = sendRequest.putRequest(BASE_TRASH_URL+"/restore", TOKEN, PutObjectResponse.class, fileName);

        checks.checkObjectRestoring(makeFolderActualResponse, BASE_DISK_URL, fileName, folderName);
        return makeFolderActualResponse;
    }

    @Step("Delete not empty folder step")
    public void deleteNotEmptyFolderMethod(String url) {
        Log.info("Delete reguest for not empty object deleting");
        int status;
        String href = url.replaceAll("%2F", "\\/").replaceAll("%3A", "\\:");

        status = sendRequest.deleteRequest(href, TOKEN, "true");

        checks.checkDeletedNotEmptyFolder(status);
    }

    @Step("Sent not empty object to the trash step")
    public void sentNotEmptyObjectToTrashMethod(String url) {
        Log.info("Delete reguest for sent not empty object to trash");
        int status;
        String href = url.replaceAll("%2F", "\\/").replaceAll("%3A", "\\:");

        status = sendRequest.deleteRequest(href, TOKEN, "false");

        checks.checkDeletedNotEmptyFolder(status);
    }

    @Step("Sent not empty object to the trash step")
    public PutObjectResponse  clearTrashMethod() throws InterruptedException {
        Log.info("Delete reguest for sent not empty object to trash");
        PutObjectResponse operationResponse;
        int statusTrash;

        statusTrash = sendRequest.getTrashStatusRequest(BASE_TRASH_URL, TOKEN);
        if (statusTrash != 423){
            operationResponse = sendRequest.deleteTrashRequest(BASE_TRASH_URL, TOKEN, PutObjectResponse.class);
        } else {
            wait(100);
            operationResponse = sendRequest.deleteTrashRequest(BASE_TRASH_URL, TOKEN, PutObjectResponse.class);
        }

        checks.checkResponseWhenClearTrash(operationResponse);

        return operationResponse;
    }

    @SneakyThrows
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

    public void checkEmptyTrashMethod() {
        Log.info("Request that check total count in the trash");
        TrashResponse countTotalItemsInTrash;
        countTotalItemsInTrash = sendRequest.getTrashRequest(BASE_TRASH_URL, TOKEN, TrashResponse.class);

        checks.chectTotalCountInTresh(countTotalItemsInTrash);
    }

    @Step("Restore object from trash step")
    public MetaDataResponse getObjectMetadata( String folderName, String subFolderName, String fileName ) {
        Log.info("PUT reguest for restore file from trash");

        MetaDataResponse metaDataResponse;
        metaDataResponse = sendRequest.getRequest(BASE_DISK_URL, TOKEN, MetaDataResponse.class, folderName);
        //System.out.println(metaDataResponse.getEmbedded().getItems().listIterator((0)));
        checks.checkObjectMetadata(metaDataResponse, folderName, subFolderName, fileName);
        return metaDataResponse;
    }
    @Step("Restore object from trash step")
    public void getDataAboutObjectMethod(String folderName){
        NotFoundObject notFoundObject;
        notFoundObject = sendRequest.getRequest(BASE_DISK_URL, TOKEN, NotFoundObject.class, folderName);

        checks.checkNotFoundAnsver(notFoundObject);
    }

    public MetaDataResponse getObjectMetadata( String folderName, String fileName ) {
        Log.info("PUT reguest for restore file from trash");

        MetaDataResponse metaDataResponse;
        metaDataResponse = sendRequest.getRequest(BASE_TRASH_URL, TOKEN, MetaDataResponse.class, folderName);
        //System.out.println(metaDataResponse.getEmbedded().getItems().listIterator((0)));
        //checks.checkObjectMetadata(metaDataResponse, folderName, fileName);
        return metaDataResponse;
    }
}
