package com.zpc.mybatis.pojo;

import lombok.Data;

@Data
public class Item {
    private Integer id;
    private String itemName;
    private Float itemPrice;
    private String itemDetail;
}
