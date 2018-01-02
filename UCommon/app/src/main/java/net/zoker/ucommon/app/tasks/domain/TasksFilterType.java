package net.zoker.ucommon.app.tasks.domain;

/**
 * Created by zoker on 2017/12/29.
 */

public enum TasksFilterType {
    /**
     * Do not filter tasks.
     */
    ALL_TASKS,

    /**
     * Filters only the active (not completed yet) tasks.
     */
    ACTIVE_TASKS,

    /**
     * Filters only the completed tasks.
     */
    COMPLETED_TASKS
}
