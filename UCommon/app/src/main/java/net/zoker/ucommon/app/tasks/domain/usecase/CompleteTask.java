package net.zoker.ucommon.app.tasks.domain.usecase;

import android.support.annotation.NonNull;


import net.zoker.ucommon.app.datalayer.source.TasksDataSource;

import zoker.net.clean.UseCase;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by zoker on 2017/12/29.
 */

public class CompleteTask extends UseCase<CompleteTask.RequestValues, CompleteTask.ResponseValue> {
    private TasksDataSource mDataSource;

    public CompleteTask(TasksDataSource dataSource) {
        this.mDataSource = dataSource;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        String completeTask = requestValues.getCompletedTask();
        mDataSource.completeTask(completeTask);
        getUseCaseCallback().onSuccess(new ResponseValue());
    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final String mCompletedTask;

        public RequestValues(@NonNull String completedTask) {
            mCompletedTask = checkNotNull(completedTask, "completedTask cannot be null!");
        }

        public String getCompletedTask() {
            return mCompletedTask;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValues {
    }
}
