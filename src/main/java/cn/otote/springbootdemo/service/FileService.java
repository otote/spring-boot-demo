package cn.otote.springbootdemo.service;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Create on 2019/3/7 14:41
 *
 * @Author stevenl
 */
@Service
public class FileService {
    public String upload(MultipartFile file) throws Exception {
        //配置文件的路径
        String confPath = new ClassPathResource("fdfs_client.conf").getPath();

        StorageServer storageServer= null;
        //初始化客户端
        ClientGlobal.init(confPath);

        TrackerClient trackerClient = new TrackerClient();
        //获取与追踪器的连接
        TrackerServer trackerServer = trackerClient.getConnection();

        //获取客户端对象
        StorageClient client =new StorageClient(trackerServer, storageServer);


        String[] result = client.upload_file(file.getBytes(), "jpg",null);

        return "http://fastdfs.otote.cn/"+result[0]+"/"+result[1];
    }
}
