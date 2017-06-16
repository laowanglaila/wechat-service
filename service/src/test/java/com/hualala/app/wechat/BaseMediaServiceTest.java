package com.hualala.app.wechat;

import com.alibaba.fastjson.JSONObject;
import com.dld.hualala.util.FileUtil;
import com.hualala.app.wechat.service.AccessTokenService;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.service.BaseMediaService;
import com.hualala.core.app.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


/**
 * Created by Administrator on 2017/3/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseMediaServiceTest {

    private Logger logger = Logger.of(BaseMediaServiceTest.class);

    @Autowired
    private BaseMediaService baseMediaService;
    @Autowired
    private AccessTokenService accessTokenService;

    /**
     * 测试图片上传接口
     */
    @Test
    public void test() {
        String source = "http://res.hualala.com/group2/M00/6A/78/wKgVT1kdWLbQm5NNAAPBkK92Ylc478=600x300.png";
        String[] split = source.split("\\.");
        String suffix = "";
        if (split.length > 1) {
            suffix = "." + split[split.length - 1];
        }
        File file = null;
        try {
            file = File.createTempFile(UUID.randomUUID().toString(), suffix);
            System.out.println(file.getPath());
            FileUtil.downloadFile(source, file.getPath(), 1000, 1000);
            JSONObject jsonObject = baseMediaService.uploadImage(file, "doulaofangceshi");
            System.out.println(jsonObject.toJSONString());
        } catch (IOException e) {
            logger.error(">>> update user photoIma is error");
            e.printStackTrace();
        } finally {
            if (file != null) {
                file.delete();
            }
        }
    }

    /**
     * 测试临时素材上传接口
     */
    @Test
    public void test2() {
//        String source = "http://res.hualala.com/group2/M00/6A/78/wKgVT1kdWLbQm5NNAAPBkK92Ylc478=600x300.png";
//        String[] split = source.split("\\.");
//        String suffix = "";
//        if (split.length > 1) {
//            suffix = "." + split[split.length - 1];
//        }
        //1v8VRGUBcS-ummQyh8I5f9hn6ksni1ZTRmHz_IPLKIsCfDshGcRPXCKrU41uEuk8
        File file = null;
        try {
            file = new File("C:\\Users\\Administrator\\Desktop\\111.jpg");
//            file = File.createTempFile(UUID.randomUUID().toString(), suffix);
//            System.out.println(file.getPath());
//            File file1 = FileUtil.downloadFile(source, file.getPath(), 1000, 1000);
            JSONObject jsonObject = baseMediaService.uploadTempMedia("thumb", file, "hualala_com");
            System.out.println(jsonObject.toJSONString());
//        } catch (IOException e) {
//            logger.error(">>> update user photoIma is error");
//            e.printStackTrace();
        } finally {
            if (file != null) {
//                file.delete();
            }
        }
    }

    @Autowired
    private BaseHttpService baseHttpService;
    /**
     * 测试图文消息素材上传接口
     */
    @Test
    public void test3() {
        String mpID = "hualala_com";
//        String mpID = "doulaofangceshi";
        String url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews";
        String json = "{" +
                "   \"articles\": [" +
//                            " {" +
//                            " \"thumb_media_id\":\"y4Uea_YWUgdvC1EgySpHUmX6ZECmlDr0kO1cIKB2cs8TkmzbuXF9gJfmtPeRiaB0\"," +
//                            " \"author\":\"xxx\"," +
//                            " \"title\":\"Happy Day\"," +
//                            " \"content_source_url\":\"www.qq.com\"," +
//                            " \"content\":\"content\"," +
//                            " \"digest\":\"digest\"," +
//                            " \"show_cover_pic\":1" +
//                            " }," +
                            " {" +
                            " \"thumb_media_id\":\"Hc_6LH-Exi_Vztfa9EbuOQhEs-8Mq6_eDvOljKOvRsXqb6Vb1U3dvltaA-UvHl-U\"," +
                            " \"author\":\"xxx\"," +
                            " \"title\":\"Happy Day\"," +
                            " \"content_source_url\":\"www.qq.com\"," +
                            " \"content\":\"<a href='http://www.hualala.com/'>点击有惊喜<a/>\"," +
                            " \"digest\":\"digest\"," +
                            " \"show_cover_pic\":1" +
                            " }" +
                     "   ]" +
                "}";
        JSONObject jsonObject = baseHttpService.commonHttpPost(url, json, mpID);
        System.out.println(jsonObject);

    }
}
