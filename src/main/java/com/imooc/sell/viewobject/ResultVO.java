package com.imooc.sell.viewobject;

import lombok.Data;

@Data
public class ResultVO<T> {
    private Integer code;
    private String message;
    private T Data;
}
