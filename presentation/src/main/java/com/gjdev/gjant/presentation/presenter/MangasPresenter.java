package com.gjdev.gjant.presentation.presenter;

import com.gjdev.gjant.domain.interactor.BaseInteractor;
import com.gjdev.gjant.domain.interactor.DefaultSubscriber;
import com.gjdev.gjant.domain.model.Manga;
import com.gjdev.gjant.presentation.ui.contract.MangasContract;

import java.util.Collection;
import java.util.List;


public class MangasPresenter implements Presenter {

  private final BaseInteractor getMangasInteractor;
  private MangasContract.View view;

  public MangasPresenter(BaseInteractor getMangasInteractor,
                         MangasContract.View view) {
    this.getMangasInteractor = getMangasInteractor;
    this.view = view;
  }

  @Override
  public void resume() {}

  @Override
  public void pause() {}

  @Override
  public void destroy() {
    this.getMangasInteractor.unsubscribe();
    this.view = null;
  }

  public void initialize() {
    this.loadMangas();
  }

  private void loadMangas() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getMangas();
  }

  private void showViewLoading() {
    this.view.showLoading();
  }

  private void hideViewLoading() {
    this.view.hideLoading();
  }

  private void showViewRetry() {
    this.view.showRetry();
  }

  private void hideViewRetry() {
    this.view.hideRetry();
  }

  private void showErrorMessage(/*ErrorBundle errorBundle*/) {
    /*String errorMessage = ErrorMessageFactory.create(this.view.context(),
        errorBundle.getException());*/
    this.view.showError("Error Message");
  }

  private void showUsersCollectionInView(Collection<Manga> usersCollection) {
    this.view.renderMangaList(usersCollection);
  }

  private void getMangas() {
    this.getMangasInteractor.execute(new MangasSubscriber());
  }

  private final class MangasSubscriber extends DefaultSubscriber<List<Manga>> {

    @Override
    public void onCompleted() {
      MangasPresenter.this.hideViewLoading();
    }

    @Override
    public void onError(Throwable e) {
      MangasPresenter.this.hideViewLoading();
      MangasPresenter.this.showErrorMessage(/*new DefaultErrorBundle((Exception) e)*/);
      MangasPresenter.this.showViewRetry();
    }

    @Override
    public void onNext(List<Manga> mangas) {
      MangasPresenter.this.showUsersCollectionInView(mangas);
    }
  }
}
