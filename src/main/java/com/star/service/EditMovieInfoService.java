package com.star.service;

import com.star.entity.Img;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

/**
 * @program: myblog
 * @author: cx
 * @create: 2022-02-04 16:08
 * @description: service层处理
 **/
public interface EditMovieInfoService {

    public HashMap editMovieInfo(List<MultipartFile> file, String uploadDir) throws Exception;

    public int updateImg(Img img);


    /**
     * 删除特定目录下的指定文件
     * @param img
     * @return
     */
    public boolean deleteImg(Integer id);

    public List<Img> getImgList(Img img);

    public Img getImgById(Integer id);

}
