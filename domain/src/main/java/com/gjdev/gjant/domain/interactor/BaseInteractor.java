package com.gjdev.gjant.domain.interactor;

import com.gjdev.gjant.domain.executor.PostExecutionThread;
import com.gjdev.gjant.domain.executor.ThreadExecutor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public abstract class BaseInteractor {

  private final ThreadExecutor threadExecutor;
  private final PostExecutionThread postExecutionThread;

  private Subscription subscription = Subscriptions.empty();

  protected BaseInteractor(ThreadExecutor threadExecutor,
                           PostExecutionThread postExecutionThread) {
    this.threadExecutor = threadExecutor;
    this.postExecutionThread = postExecutionThread;
  }

  protected abstract Observable buildInteractorObservable();

  public void execute(Subscriber UseCaseSubscriber) {
    this.subscription = this.buildInteractorObservable()
        .subscribeOn(Schedulers.from(threadExecutor.execute()))
        .observeOn(postExecutionThread.getScheduler())
        .subscribe(UseCaseSubscriber);
  }

  public void unsubscribe() {
    if (!subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }
  }
}
