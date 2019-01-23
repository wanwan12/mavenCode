package com.ecarx.xkbanner.holder;

public interface HolderCreator<Holder> {

    Holder createHolder();

    int getLayoutId();

}
