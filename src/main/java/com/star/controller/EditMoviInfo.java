package com.star.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.star.controller.admin.R;
import com.star.entity.Img;
import com.star.entity.Picture;
import com.star.service.EditMovieInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

/**
 * @program: myblog
 * @author: cx
 * @create: 2022-02-04 16:05
 * @description: 传输图片以及图片管理
 **/
@Controller
public class EditMoviInfo  extends BaseController{

    @Autowired
    EditMovieInfoService editMovieInfo;

    @GetMapping("/t")
    public String about() {
        return "t";
    }

    @RequestMapping("/editMovieInfo")
    @ResponseBody
    public String editMovieInfo(@RequestParam("file") List<MultipartFile> files) throws Exception {

        // 文件上传的路径
        String uploadDir =  "/opt/my/img";

        HashMap result = editMovieInfo.editMovieInfo(files, uploadDir);
        if ((Integer)result.get("state") > 0) {
            return JSON.toJSONString(result);
        } else {
            return JSON.toJSONString(result);

        }
    }


    @GetMapping("/admin/img")
    public String getImgList(Model model, Img img,
                             @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<Img> listFriendLink = editMovieInfo.getImgList(img);
        PageInfo<Img> pageInfo = new PageInfo<Img>(listFriendLink);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/image-manage";
    }

    //    跳转照片编辑页面
    @GetMapping("/admin/img/{id}/input")
    public String editInput(@PathVariable Integer id, Model model) {
        model.addAttribute("picture", editMovieInfo.getImgById(id));
        return "admin/image-edit";
    }


    //    编辑相册
    @PostMapping("/admin/img/{id}")
    public String editPost(@Valid Img picture, RedirectAttributes attributes) {

        int P = editMovieInfo.updateImg(picture);
        if (P == 0 ) {
            attributes.addFlashAttribute("message", "编辑失败");
        } else {
            attributes.addFlashAttribute("message", "编辑成功");
        }
        return "redirect:/admin/img";
    }

    //    删除照片
    @GetMapping("/admin/img/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes attributes){
        editMovieInfo.deleteImg(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/img";
    }

}