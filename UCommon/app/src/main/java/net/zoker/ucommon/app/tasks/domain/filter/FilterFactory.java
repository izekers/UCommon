package net.zoker.ucommon.app.tasks.domain.filter;

import net.zoker.ucommon.app.tasks.domain.TasksFilterType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zoker on 2017/12/29.
 */

/**
 * Factory of {@link TaskFilter}s.
 */
public class FilterFactory {

    private static final Map<TasksFilterType, TaskFilter> mFilters = new HashMap<>();

    public FilterFactory() {
        mFilters.put(TasksFilterType.ALL_TASKS, new FilterAllTaskFilter());
        mFilters.put(TasksFilterType.ACTIVE_TASKS, new ActiveTaskFilter());
        mFilters.put(TasksFilterType.COMPLETED_TASKS, new CompleteTaskFilter());
    }

    public TaskFilter create(TasksFilterType filterType) {
        return mFilters.get(filterType);
    }
}
