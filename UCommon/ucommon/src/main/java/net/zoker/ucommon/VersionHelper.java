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
     * @param code   目标版本
     * @param action 高于版本的监听和低于版本的监听
     * @return
     */
    public VersionHelper addVersionControl(int code, VersionAction action) {
        ActionMap actionMap = new ActionMap();
        actionMap.code = code;
        actionMap.action = action;
        actionMapList.add(actionMap);
        return this;
    }

    public VersionHelper addControlWithVersion8(VersionAction action) {
        return addVersionControl(VERSION_8, action);
    }

    public VersionHelper addControlWithVersion7(VersionAction action) {
        return addVersionControl(VERSION_7, action);
    }

    public VersionHelper addControlWithVersion6(VersionAction action) {
        return addVersionControl(VERSION_6, action);
    }

    public VersionHelper addControlWithVersion5(VersionAction action) {
        return addVersionControl(VERSION_5, action);
    }

    public VersionHelper addControlWithVersion4(VersionAction action) {
        return addVersionControl(VERSION_4, action);
    }

    public VersionHelper addControlWithVersion3(VersionAction action) {
        return addVersionControl(VERSION_3, action);
    }

    /**
     * 版本控制执行
     */
    public void done() {
        for (ActionMap actionMap : actionMapList) {
            if (actionMap.action != null) {
                if (sdk_int > actionMap.code) {
                    actionMap.action.overVersion(actionMap.code, sdk_int);
                } else {
                    actionMap.action.belowVersion(actionMap.code, sdk_int);
                }
            }
        }
    }

    public interface VersionAction {
        /**
         * 高于目标版本监听
         *
         * @param version        区分版本
         * @param currentVersion 当前版本
         */
        void overVersion(int version, int currentVersion);

        /**
         * 低于目标版本监听
         *
         * @param version        目标版本
         * @param currentVersion 当前版本
         */
        void belowVersion(int version, int currentVersion);
    }

    static class ActionMap {
        int code;
        VersionAction action;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public VersionAction getAction() {
            return action;
        }

        public void setAction(VersionAction action) {
            this.action = action;
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

        public DefaultBuider addDefaultVersionControl(int code, VersionAction action) {
            ActionMap actionMap = new ActionMap();
            actionMap.code = code;
            actionMap.action = action;
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
