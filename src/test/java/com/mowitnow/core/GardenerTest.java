package com.mowitnow.core;

import org.junit.Test;

public class GardenerTest {

    @Test(expected = IllegalArgumentException.class)
    public void main_should_throw_exception_if_no_args_are_provided() throws IllegalArgumentException{
        String[] args = {};
        Gardener.main(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void empty_conf_should_throw_exception_if_more_than_1arg_is_provided() throws IllegalArgumentException{
        String[] args = {"valid_args", "test"};
        Gardener.main(args);
    }
}
