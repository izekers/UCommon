package net.zoker.ucommon.mvp;

/**
 * Created by zoker on 2017/12/29.
 */

public interface MvpView {
    /**
     * 显示loading对话框
     *
     * @param msg
     */
    void showLoading(String msg);

    /**
     * 隐藏loading对话框
     */
    void hideLoading();

    /**
     * 显示错误信息
     *
     * @param errorMsg
     */
    void showError(String errorMsg);

}
