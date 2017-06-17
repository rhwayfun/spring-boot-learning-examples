package com.rhwayfun.springboot.disconf;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by chubin on 2017/6/11.
 */
@Service
public class ConfigTask {

    @Resource
    private PropertyUpdateCallBackV3 propertyUpdateCallBackV3;

    @PostConstruct
    public void execute() throws Exception{
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("factor:" + propertyUpdateCallBackV3.getCalcFactorV2());
                }
            }
        }).start();
    }

}
