package com.labelwall.latte.delegates;

/**
 * Created by Administrator on 2017-11-27.
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate{

    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate(){
        return (T) getParentFragment();
    }


}
