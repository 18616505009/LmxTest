package com.lmx.lmxlucenewriter.data;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author lmx
 * @date 2021/1/13 12:57 下午
 */
@Data
@Service
public class TestData {

    public ArrayList<Car> getCars() {
        return carList;
    }

    ArrayList<Car> carList = new ArrayList() {{
        add(new Car("benz", "c1", 300000));
        add(new Car("benz", "c2", 400000));
        add(new Car("benz", "c3", 500000));
        add(new Car("benz", "c4", 600000));
        add(new Car("bmw", "x1", 250000));
        add(new Car("bmw", "x2", 350000));
        add(new Car("bmw", "x3", 450000));
        add(new Car("bmw", "x4", 550000));
        add(new Car("bmw", "x5", 650000));
        add(new Car("audi", "a1", 150000));
        add(new Car("audi", "a2", 250000));
        add(new Car("audi", "a3", 350000));
        add(new Car("audi", "a4", 450000));
        add(new Car("audi", "a5", 550000));
    }};

}
