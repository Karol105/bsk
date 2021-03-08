package com.example.bsk;

import org.junit.Assert;
import org.junit.Test;

public class RailFenceTest {
    private RailFence railFence;

    @Test
    public void railtest1() {
        railFence = new RailFence(3);
        Assert.assertEquals("CTARPORPYYGH",
                railFence.getEncryptedData("CRYPTOGRAPHY"));
    }

    @Test
    public void railtest2() {
        railFence = new RailFence(2);
        Assert.assertEquals("KRLAO",
                railFence.getEncryptedData("KAROL"));
    }

    @Test
    public void railtest3() {
        railFence = new RailFence(5);
        Assert.assertEquals("EXERCISMISAWESOME",
                railFence.getDecryptedData("EIEXMSMESAORIWSCE"));
    }

    @Test
    public void railtest4() {
        railFence = new RailFence(3);
        Assert.assertEquals("WEAREDISCOVEREDFLEEATONCE",
                railFence.getDecryptedData("WECRLTEERDSOEEFEAOCAIVDEN"));
    }
}
