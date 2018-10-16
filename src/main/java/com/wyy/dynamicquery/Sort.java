package com.wyy.dynamicquery;

/**
 * @author wyy
 * @date 18-6-21
 * @time 上午8:56
 */
public class Sort<T> {

    private String property;
    private SortType sortType;
    private CriterionDataType dataType;

    public Sort<T> defaultSort() {
        return new Sort<T>().setProperty("createDate").setSortType(SortType.DESC).setDataType(CriterionDataType.DATE);
    }

    public CriterionDataType getDataType() {
        return dataType;
    }

    public Sort<T> setDataType(CriterionDataType dataType) {
        this.dataType = dataType;
        return this;
    }

    public String getProperty() {
        return property;
    }

    public Sort<T> setProperty(String property) {
        this.property = property;
        return this;
    }

    public SortType getSortType() {
        return sortType;
    }

    public Sort<T> setSortType(SortType sortType) {
        this.sortType = sortType;
        return this;
    }

    @Override
    public String toString() {
        return "Sort{" +
                "property='" + property + '\'' +
                ", sortType=" + sortType +
                ", dataType=" + dataType +
                '}';
    }

}
