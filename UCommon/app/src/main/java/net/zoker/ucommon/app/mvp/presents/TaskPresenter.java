package net.zoker.ucommon.app.mvp.presents;

import android.support.annotation.NonNull;

import net.zoker.ucommon.app.datalayer.Task;
import net.zoker.ucommon.app.mvp.contract.TaskContract;
import net.zoker.ucommon.app.tasks.domain.TasksFilterType;
import net.zoker.ucommon.app.tasks.domain.usecase.CompleteTask;
import net.zoker.ucommon.app.tasks.domain.usecase.GetTasks;

import zoker.net.clean.UseCase;
import zoker.net.clean.UseCaseHandler;

/**
 * Created by zoker on 2017/12/29.
 */

public class TaskPresenter implements TaskContract.Presenter {
    private TaskContract.View mTasksView;
    private GetTasks mGetTasks;
    private CompleteTask mCompleteTask;

    private UseCaseHandler mUseCaseHandler;

    private boolean mFirstLoad = true;

    private TasksFilterType mCurrentFiltering = TasksFilterType.ALL_TASKS;
    public TaskPresenter(@NonNull UseCaseHandler useCaseHandler,
                         @NonNull TaskContract.View tasksView,
                         @NonNull GetTasks getTasks) {
        mTasksView = tasksView;
        mUseCaseHandler = useCaseHandler;
        mGetTasks = getTasks;
    }

    @Override
    public void start() {
        loadTasks(false);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadTasks(boolean forceUpdate) {
        // Simplification for sample: a network reload will be forced on first load.
        //简单示例:一个网络重新加载将被迫在第一次加载。
        loadTasks(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    /**
     * @param forceUpdate    Pass in true to refresh the data in the {@link TasksDataSource}
     * @param showLoadingUI Pass in true to display a loading icon in the UI
     */
    private void loadTasks(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI){
            mTasksView.setLoadingIndicator(true);
        }

        GetTasks.RequestValues requestValues = new GetTasks.RequestValues(forceUpdate,mCurrentFiltering);

        mUseCaseHandler.execute(mGetTasks, requestValues, new UseCase.UseCaseCallback<GetTasks.ResponseValue>() {
            @Override
            public void onSuccess(GetTasks.ResponseValue response) {

            }

            @Override
            public void onError() {

            }
        });
    }


    @Override
    public void addNewTask() {

    }

    @Override
    public void openTaskDetails(@NonNull Task requestedTask) {

    }

    @Override
    public void completeTask(@NonNull Task completedTask) {

    }

    @Override
    public void activateTask(@NonNull Task activeTask) {

    }

    @Override
    public void clearCompletedTasks() {

    }
}
