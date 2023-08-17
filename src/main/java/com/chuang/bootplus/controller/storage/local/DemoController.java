package com.chuang.bootplus.controller.storage.local;

import com.chuang.bootplus.base.utils.ApiResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lcc
 * @create 2021-05-28
 * @注意 本内容仅限于dev414内部传阅，禁止外泄以及用于其他的商业目的
 */
@Api(tags = {"存储"})
@RestController
@RequestMapping("storage/local")
public class DemoController {
    @Autowired
    LocalService service;

    @ApiOperation(value = "本地上传", consumes = "multipart/form-data")
    @PostMapping("upload")
    public ApiResponse upload(@RequestParam(value = "file") MultipartFile file) {
        return service.uploadFile(file);
    }
}
