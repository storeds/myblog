package com.star.service.Impl;

import com.star.dao.TImgMapper;
import com.star.entity.Img;
import com.star.service.EditMovieInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

/**
 * @program: myblog
 * @author: cx
 * @create: 2022-02-04 16:10
 * @description: 图片上传的业务处理
 **/
@Service

public class EditMovieInfoServiceImpl implements EditMovieInfoService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TImgMapper tImgMapper;

    @Transactional
    @Override
    public HashMap editMovieInfo(List<MultipartFile> files, String uploadDir) throws Exception {

            // 图片路径，和网络路径
            String imgUrl = null;
            String webImgUrl = null;
            HashMap map = new HashMap();

            // 存储生成图片网址
            List<String> list = new ArrayList<>();

            // 生成图片地址，插入图片到mysql
            for (MultipartFile file : files) {
                String filename = upload(file, uploadDir, file.getOriginalFilename());
                if (!StringUtils.isEmpty(filename)) {
                    imgUrl = new File(uploadDir).getName() + "/" + filename;
                    webImgUrl = "http://www.storedweb.top/img/" + filename;

                    // 将其存入mysql中
                    Img img = new Img();
                    img.setCreateTime(new Date());
                    img.setImgName(filename);
                    img.setImgUrl(imgUrl);
                    img.setWebImgUrl(webImgUrl);
                    int reval = tImgMapper.insert(img);

                    if (reval > 0) {
                        list.add(webImgUrl);
                    }
                }
            }

           if (!list.isEmpty()) {
               map.put("state", 1);
               map.put("webImgUrls", list);
           } else {
               // 失败后返回的情况
               map.put("state", 0);
               map.put("webImgUrls", null);
           }
            return map;
    }

    public String upload(MultipartFile file, String path, String fileName) throws Exception {
        // 生成新的文件名
        // 图片名称
        String imgName = UUID.randomUUID().toString().replace("-", "");
        logger.info("{}",imgName);
        String realPath = path + "/" + imgName + fileName.substring(fileName.lastIndexOf("."));
        File dest = new File(realPath);
        // 判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
        dest.getParentFile().mkdir();
        }
        // 保存文件
        file.transferTo(dest);
        return dest.getName();
    }

    @Override
    public int updateImg(Img img){
        return tImgMapper.updateByPrimaryKey(img) < 0 ? 0 : 1 ;
    }

    @Override
    public boolean deleteImg(Integer id) {

        Img img = tImgMapper.selectByPrimaryKey(id);

        // 获取文件名称删除文件
        String imgUrl = img.getImgUrl();
        File file = new File(imgUrl);

        // 如果删除成功会返回true
        if (file.delete()){
            // 删除mysql中的记录
            return tImgMapper.deleteByPrimaryKey(id) > 0;
        }else {
            return false;
        }
    }

    @Override
    public List<Img> getImgList(Img img) {
        return tImgMapper.selectByPrimary(img);
    }

    public Img getImgById(Integer id) {
        return tImgMapper.selectByPrimaryKey(id);
    }


}
