package com.gjdev.gjant.data;

import com.gjdev.gjant.domain.executor.ThreadExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskExecutor implements ThreadExecutor {

  public TaskExecutor() {
  }

  @Override
  public Executor execute() {
    return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
  }
}
