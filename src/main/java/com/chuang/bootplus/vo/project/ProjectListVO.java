package com.chuang.bootplus.vo.project;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author hsy
 * @create 2021-08-14
 * @注意
 */
@Data
@Accessors(chain = true)
public class ProjectListVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目id")
    private Long id;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "描述")
    private String projectDescribe;

    @ApiModelProperty(value = "展示图")
    private String image;

    @ApiModelProperty(value = "项目对应git链接")
    private String link;

    @ApiModelProperty(value = "项目负责人id")
    private Long userId;

    @ApiModelProperty(value = "项目创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "上传时间(转换格式后)")
    private LocalDate gmdCreate;
}
