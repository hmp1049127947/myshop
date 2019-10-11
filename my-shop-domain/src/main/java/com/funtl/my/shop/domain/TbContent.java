package com.funtl.my.shop.domain;

import com.funtl.my.shop.commons.persistence.BaseEntity;
import lombok.Data;

/**
 * @author 黄明潘
 * @date 2019/10/11-13:32
 */
@Data
public class TbContent extends BaseEntity {

    private Long categoryId;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    private String content;
}
