package com.lmx.lmxlucenewriter.data;

import lombok.Data;

/**
 * @author lmx
 * @date 2021/1/13 1:12 下午
 */
@Data
public class Car {
    String brand;
    String version;
    long price;

    public Car(String brand, String version, long price) {
        this.brand = brand;
        this.version = version;
        this.price = price;
    }
}
