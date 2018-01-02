package net.zoker.ucommon.app.datalayer.source;

import android.support.annotation.NonNull;

/**
 * Created by zoker on 2017/12/29.
 */

public class TasksRepository implements TasksDataSource{
    @Override
    public void refreshTasks() {

    }

    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {

    }

    @Override
    public void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback) {

    }

    @Override
    public void completeTask(@NonNull String taskId) {

    }
}
