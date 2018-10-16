package com.wyy.dynamicquery;


import com.wyy.dynamicquery.exception.UnSupportDataTypeException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author wyy
 * @date 18-6-21
 * @time 上午9:44
 */
public class SortConverter<T> {

    private List<Sort<T>> sorts;

    public SortConverter(List<Sort<T>> sorts) {
        this.sorts = sorts;
    }

    public Comparator<T> convert() {

        return (T e1, T e2) -> {
            int result = 0;
            if (sorts == null) {
                sorts = Arrays.asList(new Sort<T>().defaultSort());
            }
            for (Sort<T> sort : sorts) {
                CriterionDataType dataType = sort.getDataType();
                String property = sort.getProperty();
                Object p1 = new FieldReader<>(e1).readField(property);
                Object p2 = new FieldReader<>(e2).readField(property);
                SortType sortType = sort.getSortType();
                switch (dataType) {
                    case STRING:
                        result = p1.toString().compareTo(p2.toString());
                        break;
                    case LONG:
                        result = ((Long) p1).compareTo(((Long) p2));
                        break;
                    case DOUBLE:
                        result = ((Double) p1).compareTo(((Double) p2));
                        break;
                    case DATE:
                        result = ((Date) p1).compareTo(((Date) p2));
                        break;
                    default:
                        throw new UnSupportDataTypeException("unSupport data type : " + dataType);
                }
                if (result != 0) {
                    if (sortType.equals(SortType.DESC)) {
                        result = 0 - result;
                    }
                    break;
                }
            }
            return result;
        };
    }
}
