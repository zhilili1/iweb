package com.iweb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {
    private boolean isOk;//操作是否成功
    private String mess;//返回消息
    private T t;//返回的数据
}
