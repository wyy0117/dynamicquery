package com.wyy.dynamicquery;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wyy
 * @date 18-10-16
 * @time 上午9:39
 */
public class Criterion<T> {

    private String property;
    private CriterionDataType dataType;
    private CompareType compareType;
    private String value;
    private List<String> values;

    public Criterion() {
    }

    public Criterion(String property, CriterionDataType dataType, CompareType compareType, String value) {
        this.property = property;
        this.dataType = dataType;
        this.compareType = compareType;
        this.value = value;
    }

    public Criterion(String property, CriterionDataType dataType, CompareType compareType, List<String> values) {
        this.property = property;
        this.dataType = dataType;
        this.compareType = compareType;
        this.values = values;
    }

    private OperatorType operatorType;
    private List<Criterion<T>> criterionList = new ArrayList<>();

    public Criterion(OperatorType operatorType, List<Criterion<T>> criterionList) {
        this.operatorType = operatorType;
        this.criterionList = criterionList;
    }

    public boolean haveSubFilter() {
        return operatorType != null && criterionList.size() > 0;
    }

    public String getProperty() {
        return property;
    }

    public Criterion<T> setProperty(String property) {
        this.property = property;
        return this;
    }

    public CriterionDataType getDataType() {
        return dataType;
    }

    public Criterion<T> setDataType(CriterionDataType dataType) {
        this.dataType = dataType;
        return this;
    }

    public CompareType getCompareType() {
        return compareType;
    }

    public Criterion<T> setCompareType(CompareType compareType) {
        this.compareType = compareType;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Criterion<T> setValue(String value) {
        this.value = value;
        return this;
    }

    public List<String> getValues() {
        return values;
    }

    public Criterion<T> setValues(List<String> values) {
        this.values = values;
        return this;
    }

    public OperatorType getOperatorType() {
        return operatorType;
    }

    public Criterion<T> setOperatorType(OperatorType operatorType) {
        this.operatorType = operatorType;
        return this;
    }

    public List<Criterion<T>> getCriterionList() {
        return criterionList;
    }

    public Criterion<T> setCriterionList(List<Criterion<T>> criterionList) {
        this.criterionList = criterionList;
        return this;
    }

    @Override
    public String toString() {
        return " Criterion{" +
                "property='" + property + '\'' +
                ", dataType=" + dataType +
                ", compareType=" + compareType +
                ", value='" + value + '\'' +
                ", values=" + values +
                ", operatorType=" + operatorType +
                ", criterionList=" + criterionList +
                '}';
    }
}
