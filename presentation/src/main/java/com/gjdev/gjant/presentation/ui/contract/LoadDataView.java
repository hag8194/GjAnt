package com.gjdev.gjant.presentation.ui.contract;

public interface LoadDataView {

  void showLoading();

  void hideLoading();

  void showRetry();

  void hideRetry();

  void showError(String message);
}
