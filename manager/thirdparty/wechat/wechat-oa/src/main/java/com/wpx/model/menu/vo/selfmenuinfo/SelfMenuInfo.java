package com.wpx.model.menu.vo.selfmenuinfo;

import java.util.List;

/**
 * @author 不会飞的小鹏
 */
public class SelfMenuInfo {

    private List<SelfButton> button;

    public List<SelfButton> getButton() {
        return button;
    }

    public void setButton(List<SelfButton> button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "SelfMenuInfo{" +
                "button=" + button +
                '}';
    }

}
