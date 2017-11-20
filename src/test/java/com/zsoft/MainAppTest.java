package com.zsoft;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainAppTest {

    @Test
    public void returnTrue() throws Exception {
        Assert.assertEquals(MainApp.returnTrue(), "true");
    }

}