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

    public Sort() {
    }

    public Sort(String property, SortType sortType, CriterionDataType dataType) {
        this.property = property;
        this.sortType = sortType;
        this.dataType = dataType;
    }

    public Sort<T> defaultSort() {
        //todo change the default sort strategy
        return new Sort<T>().setProperty("age").setSortType(SortType.DESC).setDataType(CriterionDataType.LONG);
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
