package com.gjdev.gjant.data.api.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MangaEntity {

  @SerializedName("mangaId")
  @Expose
  private String mangaId;
  @SerializedName("name")
  @Expose
  private String name;

  public String getMangaId() {
    return mangaId;
  }

  public void setMangaId(String mangaId) {
    this.mangaId = mangaId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}