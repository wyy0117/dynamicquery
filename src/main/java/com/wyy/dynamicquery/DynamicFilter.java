package com.wyy.dynamicquery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wyy
 * @date 18-6-19
 * @time 下午5:53
 */
public class DynamicFilter<T> {
    private OperatorType operatorType;
    private List<Criterion<T>> criterionList = new ArrayList<>();
    private List<Sort<T>> sorts = Arrays.asList(new Sort<T>().defaultSort());

    public DynamicFilter() {
    }

    public DynamicFilter(OperatorType operatorType, List<Criterion<T>> criterionList) {
        this.operatorType = operatorType;
        this.criterionList = criterionList;
    }

    public OperatorType getOperatorType() {
        return operatorType;
    }

    public DynamicFilter setOperatorType(OperatorType operatorType) {
        this.operatorType = operatorType;
        return this;
    }

    public List<Criterion<T>> getCriterionList() {
        return criterionList;
    }

    public DynamicFilter setCriterionList(List<Criterion<T>> criterionList) {
        this.criterionList = criterionList;
        return this;
    }

    public List<Sort<T>> getSorts() {
        return sorts;
    }

    public DynamicFilter<T> setSorts(List<Sort<T>> sorts) {
        this.sorts = sorts;
        return this;
    }

    @Override
    public String toString() {
        return "DynamicFilter{" +
                "operatorType=" + operatorType +
                ", criterionList=" + criterionList +
                ", sorts=" + sorts +
                '}';
    }
}
