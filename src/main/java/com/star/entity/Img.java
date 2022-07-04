package com.star.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @program: myblog
 * @author: cx
 * @create: 2022-02-04 22:34
 * @description: 图片对象
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Img {

    private Integer id;
    private String uuid;
    private String imgUrl;
    private String webImgUrl;
    private Date createTime;
    private Date updateTime;
    private String imgName;

}
