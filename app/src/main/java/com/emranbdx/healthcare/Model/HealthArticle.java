package com.emranbdx.healthcare.Model;

import java.io.Serializable;

public class HealthArticle implements Serializable {
    String articleName,articleDetails;

    public HealthArticle(String articleName, String articleDetails) {
        this.articleName = articleName;
        this.articleDetails = articleDetails;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleDetails() {
        return articleDetails;
    }

    public void setArticleDetails(String articleDetails) {
        this.articleDetails = articleDetails;
    }
}
