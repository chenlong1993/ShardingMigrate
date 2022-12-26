package com.sharding.migrate.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TOrder {
    /**
    * order_id
    */
    private Long orderId;

    /**
    * 用户id
    */
    private Long userId;
}