package com.example.ghserver01.app.util.Helper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class Common {

    public List<Integer> generateCode() {
        return new Random()
                .ints(6, 0, 9)
                .boxed()
                .collect(Collectors.toList());
    }

    public String parseCode() {
        String code = "";
        List<Integer> list = generateCode();

        for (Integer integer : list) {
            code = code + (integer);
        }
        return code;
    }

    public String getCode () {
        return parseCode();
    }


}
