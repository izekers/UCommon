package net.zoker.ucommon.app.datalayer.source;

import android.support.annotation.NonNull;

import net.zoker.ucommon.app.datalayer.Task;

import java.util.List;

/**
 * Created by zoker on 2017/12/29.
 */

public interface TasksDataSource {

    interface LoadTasksCallback {

        void onTasksLoaded(List<Task> tasks);

        void onDataNotAvailable();
    }

    interface GetTaskCallback {

        void onTaskLoaded(Task task);

        void onDataNotAvailable();
    }

    public void refreshTasks();

    void getTasks(@NonNull LoadTasksCallback callback);

    void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback);

    public void completeTask(@NonNull String taskId);
}
