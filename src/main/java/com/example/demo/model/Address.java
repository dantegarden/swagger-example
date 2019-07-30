package com.example.demo.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@ApiModel("地址实体")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @ApiModelProperty("地址id")
    private Long id;
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("住址")
    private String liveAddress;
}
