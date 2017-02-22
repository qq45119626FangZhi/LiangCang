package com.fz.liangcang.magazine.bean;

import java.util.List;

/**
 * @FileName:com.fz.liangcang.magazine.bean.MagazineBean.java
 * @author：方志
 * @date: 2017-01-13 18:56
 * @QQ：459119626
 * @微信：15549433151
 * @function <当前类的功能>
 */

public class MagazineBean {
    private boolean has_more;
    private int num_items;
    private List<String> keys;
    private List<List<MagazineItemBean>> magazineItemBeens;

    public List<List<MagazineItemBean>> getMagazineItemBeens() {
        return magazineItemBeens;
    }

    public void setMagazineItemBeens(List<List<MagazineItemBean>> magazineItemBeens) {
        this.magazineItemBeens = magazineItemBeens;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public int getNum_items() {
        return num_items;
    }

    public void setNum_items(int num_items) {
        this.num_items = num_items;
    }
}
