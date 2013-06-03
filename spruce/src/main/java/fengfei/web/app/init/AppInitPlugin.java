package fengfei.web.app.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fengfei.controllers.FollowController;
import fengfei.controllers.ShowController;
import fengfei.fir.utils.Path;
import fengfei.ucm.registry.ChainExecuteProxy;
import fengfei.ucm.registry.ChainExecuteType;
import fengfei.ucm.repository.RelaionRepository;
import fengfei.ucm.repository.impl.SqlRelaionRepository;
import fengfei.ucm.service.ReadFollowService;
import fengfei.ucm.service.WriteFollowService;
import fengfei.ucm.service.relation.ReadFollowSqlService;
import fengfei.ucm.service.relation.WriteFollowSqlService;

public class AppInitPlugin {

    static Logger logger = LoggerFactory.getLogger(AppInitPlugin.class);
    private String uploadPhotoPath = "/opt/upload/photo";
    private String downloadPhotoPath = "/photo";

    public void onApplicationStart() {
        initFollowService();
    }

    private void initFollowService() {
        Path.UPLOAD_PATH = uploadPhotoPath;
        Path.DOWNLOAD_PATH = downloadPhotoPath;

        RelaionRepository repository = new SqlRelaionRepository();
        //
        ChainExecuteProxy<WriteFollowService> writeChainExecuteProxy = new ChainExecuteProxy<>(
            WriteFollowService.class);

        writeChainExecuteProxy.register(
            1,
            new WriteFollowSqlService(repository),
            ChainExecuteType.NonReturnValue);
        WriteFollowService write = writeChainExecuteProxy.newInstance();
        //
        ChainExecuteProxy<ReadFollowService> readChainExecuteProxy = new ChainExecuteProxy<>(
            ReadFollowService.class);
        readChainExecuteProxy.register(
            1,
            new ReadFollowSqlService(repository),
            ChainExecuteType.ReturnValue);
        ReadFollowService read = readChainExecuteProxy.newInstance();

        //
        FollowController.read = read;
        FollowController.write = write;
        ShowController.readFollowService = read;

    }

    public String getUploadPhotoPath() {
        return uploadPhotoPath;
    }

    public void setUploadPhotoPath(String uploadPhotoPath) {
        this.uploadPhotoPath = uploadPhotoPath;
    }

    public String getDownloadPhotoPath() {
        return downloadPhotoPath;
    }

    public void setDownloadPhotoPath(String downloadPhotoPath) {
        this.downloadPhotoPath = downloadPhotoPath;
    }
}
