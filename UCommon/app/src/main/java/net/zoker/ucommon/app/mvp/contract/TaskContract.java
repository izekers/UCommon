package net.zoker.ucommon.app.mvp.contract;

import android.support.annotation.NonNull;


import net.zoker.ucommon.app.datalayer.Task;

import java.util.List;

import zoker.net.clean.BasePresenter;
import zoker.net.clean.BaseView;

/**
 * Created by zoker on 2017/12/29.
 */

public interface TaskContract {
    interface View extends BaseView<Presenter>{
        void setLoadingIndicator(boolean active);

        void showTasks(List<Task> tasks);

        void showAddTask();

        void showTaskDetailsUi(String taskId);

        void showTaskMarkedComplete();

        void showTaskMarkedActive();

        void showCompletedTasksCleared();

        void showLoadingTasksError();

        void showNoTasks();

        void showActiveFilterLabel();

        void showCompletedFilterLabel();

        void showAllFilterLabel();

        void showNoActiveTasks();

        void showNoCompletedTasks();

        void showSuccessfullySavedMessage();

        boolean isActive();

        void showFilteringPopUpMenu();
    }

    interface Presenter extends BasePresenter{
        void result(int requestCode, int resultCode);

        void loadTasks(boolean forceUpdate);

        void addNewTask();

        void openTaskDetails(@NonNull Task requestedTask);

        void completeTask(@NonNull Task completedTask);

        void activateTask(@NonNull Task activeTask);

        void clearCompletedTasks();

//        void setFiltering(TasksFilterType requestType);
//
//        TasksFilterType getFiltering();
    }
}
