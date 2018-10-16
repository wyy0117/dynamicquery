package com.wyy.dynamicquery;

import com.wyy.dynamicquery.exception.UnSupportDataTypeException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author wyy
 * @date 18-6-19
 * @time 下午6:37
 */
public class EntityFilterWorker<T> {

    private List<T>entityList;
    private DynamicFilter<T>filter;

    public EntityFilterWorker(List<T> entityList, DynamicFilter<T> filter) {
        this.entityList = entityList;
        this.filter = filter;
    }

    public List<T> doWork() {
        if (filter == null) {
            return entityList;
        }

        //entityList maybe can't modified so new ArrayList;
        List<T> arrayList = new ArrayList<>(entityList);
        List<T> result = filter(arrayList, filter);
        result.sort(new SortConverter<>(filter.getSorts()).convert());
        return result;
    }

    public List<T> doWork(List<T> entityList, DynamicFilter<T> filter, int start, int end) {

        List<T> result = filter(entityList, filter);
        result.sort(new SortConverter<T>(filter.getSorts()).convert());

        return new PageUtil<T>().page(result, start, end);
    }

    private List<T> filter(List<T> entityList, DynamicFilter<T> filter) {
        if (entityList == null || entityList.size() == 0 || filter == null || filter.getCriterionList() == null || filter.getCriterionList().size() == 0) {
            return entityList;
        }
        if (filter.getOperatorType() == OperatorType.AND) {
            for (Criterion<T> Criterion : filter.getCriterionList()) {
                if (Criterion.haveSubFilter()) {
                    entityList = filter(entityList, new DynamicFilter<>(Criterion.getOperatorType(), Criterion.getCriterionList()));
                } else {
                    Predicate<T> predicate = new CriterionConverter<>(Criterion).convert();
                    entityList = entityList.stream().filter(predicate).collect(Collectors.toList());
                }
            }
            return entityList.stream().distinct().collect(Collectors.toList());
        } else if (filter.getOperatorType() == OperatorType.OR) {
            List<T> result = new ArrayList<>();
            for (Criterion<T> Criterion : filter.getCriterionList()) {
                if (Criterion.haveSubFilter()) {
                    result.addAll(filter(entityList, new DynamicFilter<>(Criterion.getOperatorType(), Criterion.getCriterionList())));
                } else {
                    Predicate<T> predicate = new CriterionConverter<>(Criterion).convert();
                    result.addAll(entityList.stream().filter(predicate).collect(Collectors.toSet()));
                }
            }
            return result.stream().distinct().collect(Collectors.toList());
        } else {
            throw new UnSupportDataTypeException("unSupport operator type : " + filter.getOperatorType());
        }
    }
}
