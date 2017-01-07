package com.gjdev.hugo.gjant.data.api.model;

import java.io.Serializable;

/**
 * Created by Hugo on 04/01/2017.
 */
public class Links implements Serializable {
    /**
     * poster : {"href":"http://127.0.0.1/GjAntWebAPI/backend/web/img/NN-TG7bN2wdVF53gvExAr-RH6mQTQJQm.png"}
     */

    private Poster poster;

    public Poster getPoster() {
        return poster;
    }

    public void setPoster(Poster poster) {
        this.poster = poster;
    }
}
