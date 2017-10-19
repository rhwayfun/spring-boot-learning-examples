package com.rhwayfun.springboot.quickstart.memory;

import java.util.Date;

/**
 * Created by chubin on 2017/8/20.
 */
public class MemoryCounterTest {

    private static final MemoryCounter mc = new MemoryCounter();

    public static void main(String[] args) {
        RiderBasicInfoDTO obj = new RiderBasicInfoDTO();
        obj.setCityId(1);
        obj.setBlackList("1");
        obj.setClient("IOS");
        obj.setCode("test");
        obj.setFeature("test");
        obj.setId(1);
        obj.setImei("aaaaaaaaa");
        obj.setJobNumber("abc");
        obj.setLevel((byte) 5);
        obj.setMobile("18073152816");
        obj.setName("test");
        obj.setRegionId(1);
        obj.setOrderCelling(5);
        obj.setStatus((byte) 5);
        obj.setTakeMode((byte) 0);
        obj.setType((byte) 1);
        obj.setVerified((byte) 5);
        obj.setVerifiedTm(new Date());
        obj.setInviteId("test");
        long estimate = mc.estimate(obj);
        System.out.println(estimate);
    }
}
