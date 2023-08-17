package com.chuang.bootplus.vo.leanmodule;

import com.chuang.bootplus.base.database.EntityBase;
import com.chuang.bootplus.vo.learnpoint.LearnPointVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author ${author}
 * @since 2021-08-14
 */
@Data
public class LearnModuleVO {
    private Long id;



    @ApiModelProperty(value = "等级")
    private Integer moduleLevel;

    private String moduleName;

    private Long groupId;

    private Integer moduleDays;

    @ApiModelProperty(value = "描述")
    private String moduleDescribe;


    private List<LearnPointVO> learnPointVOList;



}
