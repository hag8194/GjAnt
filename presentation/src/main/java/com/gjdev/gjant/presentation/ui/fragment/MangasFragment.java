package com.gjdev.gjant.presentation.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gjdev.hugo.gjant.R;
import com.gjdev.gjant.data.TaskExecutor;
import com.gjdev.gjant.data.api.Entity.mapper.MangaEntityMapper;
import com.gjdev.gjant.data.repository.datasource.strategy.MangaServiceFactory;
import com.gjdev.gjant.data.repository.provider.MangaDataRepository;
import com.gjdev.gjant.domain.interactor.GetMangas;
import com.gjdev.gjant.domain.model.Manga;
import com.gjdev.gjant.presentation.UIThread;
import com.gjdev.gjant.presentation.presenter.MangasPresenter;
import com.gjdev.gjant.presentation.ui.adapter.MangasAdapter;
import com.gjdev.gjant.presentation.ui.contract.MangasContract;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MangasFragment extends Fragment implements MangasContract.View {

  MangaServiceFactory mangaServiceFactory;
  MangaEntityMapper mangaEntityMapper;
  MangaDataRepository mangaDataRepository;
  GetMangas getMangasInteractor;
  MangasPresenter mangasPresenter;
  
  MangasAdapter mangasAdapter;

  @BindView(R.id.rv_mangas)
  RecyclerView mangasRView;

  @BindView(R.id.view_progress)
  ProgressBar progressView;

  @BindView(R.id.button_retry)
  ImageButton retryButton;

  private Unbinder unbinder;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_mangas, container, false);
    unbinder = ButterKnife.bind(this, fragmentView);

    setupRecyclerView();
    setupDependencies();
    
    return fragmentView;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    if (savedInstanceState == null) {
      this.loadMangaList();
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    this.mangasPresenter.resume();
  }

  @Override
  public void onPause() {
    super.onPause();
    this.mangasPresenter.pause();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    mangasRView.setAdapter(null);
    unbinder.unbind();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    this.mangasPresenter.destroy();
  }

  @Override
  public void onDetach() {
    super.onDetach();
    this.mangasPresenter = null;
  }

  @Override
  public void showLoading() {
    this.progressView.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideLoading() {
    this.progressView.setVisibility(View.GONE);
  }

  @Override
  public void showRetry() {
    this.retryButton.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideRetry() {
    this.retryButton.setVisibility(View.GONE);
  }

  @Override
  public void renderMangaList(Collection<Manga> mangaModelCollection) {
    if (mangaModelCollection != null) {
      this.mangasAdapter.setItems(new ArrayList(mangaModelCollection));
    }
  }

  @Override
  public void viewManga(Manga manga) {

  }

  @Override
  public void showError(String message) {
    Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
  }

  private void setupRecyclerView() {
    mangasAdapter = new MangasAdapter();
    this.mangasRView.setLayoutManager(new LinearLayoutManager(getActivity()));
    this.mangasRView.setAdapter(mangasAdapter);
    this.mangasRView.setHasFixedSize(true);
  }
  
  private void setupDependencies () {
    mangaServiceFactory = new MangaServiceFactory(getActivity(), null);
    mangaEntityMapper = new MangaEntityMapper();
    mangaDataRepository = new MangaDataRepository(mangaServiceFactory, mangaEntityMapper);
    getMangasInteractor = new GetMangas(mangaDataRepository, new TaskExecutor(), new UIThread());
    mangasPresenter = new MangasPresenter(getMangasInteractor, this);
  }

  private void loadMangaList() {
    this.mangasPresenter.initialize();
  }

  @OnClick(R.id.button_retry)
  void onButtonRetryClick() {
    MangasFragment.this.loadMangaList();
  }

}
