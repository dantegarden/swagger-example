package com.example.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@ApiModel("用户实体")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @ApiModelProperty("用户ID")
    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户昵称")
    private String nickname;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("注册时间")
    private Date registerDate = new Date();
}
