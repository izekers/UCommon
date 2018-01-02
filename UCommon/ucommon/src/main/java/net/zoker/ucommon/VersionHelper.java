package net.zoker.ucommon;

import android.os.Build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 简单的版本控制类
 * Created by zoker on 2017/12/12.
 */

public class VersionHelper {
    public static final int VERSION_1 = Build.VERSION_CODES.BASE;
    public static final int VERSION_2 = Build.VERSION_CODES.ECLAIR;
    public static final int VERSION_3 = Build.VERSION_CODES.HONEYCOMB;
    public static final int VERSION_4 = Build.VERSION_CODES.JELLY_BEAN;
    public static final int VERSION_5 = Build.VERSION_CODES.LOLLIPOP;
    public static final int VERSION_6 = Build.VERSION_CODES.M;
    public static final int VERSION_7 = Build.VERSION_CODES.N;
    public static final int VERSION_8 = Build.VERSION_CODES.O;

    private List<ActionMap> actionMapList;
    private int sdk_int = Build.VERSION.SDK_INT;

    private VersionHelper() {
        actionMapList = new ArrayList<>();
    }

    static {
    }

    public static VersionHelper createInstance() {
        return new VersionHelper();
    }


    /**
     * 添加目标版本监听
     *
     * @param code               目标版本
     * @param overVersionAction  高于版本的监听
     * @param belowVersionAction 低于版本的监听
     * @return
     */
    public VersionHelper addVersionControl(int code, OverVersionAction overVersionAction, BelowVersionAction belowVersionAction) {
        ActionMap actionMap = new ActionMap();
        actionMap.code = code;
        actionMap.overVersionAction = overVersionAction;
        actionMap.belowVersionAction = belowVersionAction;
        actionMapList.add(actionMap);
        return this;
    }

    public VersionHelper addControlWithVersion8(OverVersionAction overVersionAction, BelowVersionAction belowVersionAction) {
        return addVersionControl(VERSION_8, overVersionAction, belowVersionAction);
    }

    public VersionHelper addControlWithVersion7(OverVersionAction overVersionAction, BelowVersionAction belowVersionAction) {
        return addVersionControl(VERSION_7, overVersionAction, belowVersionAction);
    }

    public VersionHelper addControlWithVersion6(OverVersionAction overVersionAction, BelowVersionAction belowVersionAction) {
        return addVersionControl(VERSION_6, overVersionAction, belowVersionAction);
    }

    public VersionHelper addControlWithVersion5(OverVersionAction overVersionAction, BelowVersionAction belowVersionAction) {
        return addVersionControl(VERSION_5, overVersionAction, belowVersionAction);
    }

    public VersionHelper addControlWithVersion4(OverVersionAction overVersionAction, BelowVersionAction belowVersionAction) {
        return addVersionControl(VERSION_4, overVersionAction, belowVersionAction);
    }

    public VersionHelper addControlWithVersion3(OverVersionAction overVersionAction, BelowVersionAction belowVersionAction) {
        return addVersionControl(VERSION_3, overVersionAction, belowVersionAction);
    }

    /**
     * 版本控制执行
     */
    public void done() {
        for (ActionMap actionMap : actionMapList) {
            if (sdk_int > actionMap.code) {
                if (actionMap.overVersionAction != null)
                    actionMap.overVersionAction.done(actionMap.code, sdk_int);
            } else {
                if (actionMap.belowVersionAction != null)
                    actionMap.belowVersionAction.done(actionMap.code, sdk_int);
            }
        }
    }

    public interface OverVersionAction {
        /**
         * 目标版本监听
         *
         * @param version        区分版本
         * @param currentVersion 当前版本
         */
        void done(int version, int currentVersion);
    }

    /**
     * 低于目标版本监听
     */
    public interface BelowVersionAction {
        /**
         * 目标版本监听
         *
         * @param version        区分版本
         * @param currentVersion 当前版本
         */
        void done(int version, int currentVersion);
    }


    static class ActionMap {
        int code;
        OverVersionAction overVersionAction;
        BelowVersionAction belowVersionAction;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public OverVersionAction getOverVersionAction() {
            return overVersionAction;
        }

        public void setOverVersionAction(OverVersionAction overVersionAction) {
            this.overVersionAction = overVersionAction;
        }

        public BelowVersionAction getBelowVersionAction() {
            return belowVersionAction;
        }

        public void setBelowVersionAction(BelowVersionAction belowVersionAction) {
            this.belowVersionAction = belowVersionAction;
        }
    }

    /**
     * 提供默认的版本控制，一般也用不到，后面再发寻一下使用场景
     *
     * @return
     */
    public static VersionHelper getDefault() {
        VersionHelper versionHelper = createInstance();
        if (defaultMapList != null)
            versionHelper.actionMapList.addAll(defaultMapList);
        return versionHelper;
    }

    public static DefaultBuider beginDefaultBuid() {
        return new DefaultBuider();
    }

    private static List<ActionMap> defaultMapList;

    public static class DefaultBuider {
        private List<ActionMap> mapList;

        DefaultBuider() {
            mapList = new ArrayList<>();
        }

        public DefaultBuider addDefaultVersionControl(int code, OverVersionAction overVersionAction,BelowVersionAction belowVersionAction) {
            ActionMap actionMap = new ActionMap();
            actionMap.code = code;
            actionMap.overVersionAction = overVersionAction;
            actionMap.belowVersionAction = belowVersionAction;
            VersionHelper.defaultMapList.add(actionMap);
            return this;
        }

        public void commit() {
            if (defaultMapList == null) {
                synchronized (VersionHelper.class) {
                    if (defaultMapList == null)
                        defaultMapList = new ArrayList<>();
                }
            }
            defaultMapList.addAll(mapList);
        }
    }
}
