package com.android.http.paginglistview;

import android.graphics.drawable.Drawable;

import java.text.Collator;
import java.util.Comparator;

public class ModelItem {
    
    private Drawable iconItem  ;
    private String   dataItem01;
    private String   dataItem02;
    private String   dataItem03;

    public ModelItem() {
        super();
    }
    
    public ModelItem(Drawable iconItem, String dataItem01, String dataItem02,
            String dataItem03) {
        super();
        
        this.iconItem = iconItem;
        this.dataItem01 = dataItem01;
        this.dataItem02 = dataItem02;
        this.dataItem03 = dataItem03;
    }
    
    public Drawable getIconItem() {
        return iconItem;
    }
    public void setIconItem(Drawable iconItem) {
        this.iconItem = iconItem;
    }
    public String getDataItem01() {
        return dataItem01;
    }
    public void setDataItem01(String dataItem01) {
        this.dataItem01 = dataItem01;
    }
    public String getDataItem02() {
        return dataItem02;
    }
    public void setDataItem02(String dataItem02) {
        this.dataItem02 = dataItem02;
    }
    public String getDataItem03() {
        return dataItem03;
    }
    public void setDataItem03(String dataItem03) {
        this.dataItem03 = dataItem03;
    }

    /**
     * 알파벳 이름으로 정렬
     */
    public static final Comparator<ModelItem> ALPHA_COMPARATOR = new Comparator<ModelItem>() {
        private final Collator sCollator = Collator.getInstance();
        
        @Override
        public int compare(ModelItem mListDate_1, ModelItem mListDate_2) {
            return sCollator.compare(mListDate_1.dataItem01, mListDate_2.dataItem01);
        }
    };
}
