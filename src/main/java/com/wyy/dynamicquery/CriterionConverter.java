package com.wyy.dynamicquery;

import com.wyy.dynamicquery.exception.DateParseException;
import com.wyy.dynamicquery.exception.UnSupportDataTypeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Predicate;

/**
 * @author wyy
 * @date 18-6-19
 * @time 下午1:40
 */
public class CriterionConverter<T> {
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private Criterion<T> criterion;


    public CriterionConverter(Criterion<T> criterion) {
        this.criterion = criterion;
    }

    public Predicate<T> convert() {
        check();

        return (T baseModel) -> {
            boolean result = false;

            Object property = new FieldReader<>(baseModel).readField(criterion.getProperty());

            if (property == null) {
                throw new RuntimeException("no such field : " + criterion.getProperty());
            }
            switch (criterion.getCompareType()) {
                case EQUAL:
                    switch (criterion.getDataType()) {
                        case STRING:
                            result = property.toString().equals(criterion.getValue());
                            break;
                        case LONG:
                            result = ((Long) property) == Long.parseLong(criterion.getValue());
                            break;
                        case DOUBLE:
                            result = ((Double) property) == Double.parseDouble(criterion.getValue());
                            break;
                        case DATE:
                            try {
                                result = ((Date) property).compareTo(simpleDateFormat.parse(criterion.getValue())) == 0;
                            } catch (ParseException e) {
                                throw new DateParseException(e.getCause());
                            }
                            break;
                        default:
                            throw new UnSupportDataTypeException("unSupport data type : " + criterion.getDataType());
                    }
                    break;
                case LIKE:
                    result = ((String) property).contains(criterion.getValue());
                    break;
                case LESSEQUAL:
                    switch (criterion.getDataType()) {
                        case LONG:
                            result = ((Long) property) <= Long.parseLong(criterion.getValue());
                            break;
                        case DOUBLE:
                            result = ((Double) property) <= Double.parseDouble(criterion.getValue());
                            break;
                        case DATE:
                            try {
                                result = ((Date) property).compareTo(simpleDateFormat.parse(criterion.getValue())) <= 0;
                            } catch (ParseException e) {
                                throw new DateParseException(e.getCause());
                            }
                            break;
                        default:
                            throw new UnSupportDataTypeException("unSupport data type : " + criterion.getDataType());
                    }
                    break;
                case GREATEREQUAL:
                    switch (criterion.getDataType()) {

                        case LONG:
                            result = ((Long) property) >= Long.parseLong(criterion.getValue());
                            break;
                        case DOUBLE:
                            result = ((Double) property) >= Double.parseDouble(criterion.getValue());
                            break;
                        case DATE:
                            try {
                                result = ((Date) property).compareTo(simpleDateFormat.parse(criterion.getValue())) >= 0;
                            } catch (ParseException e) {
                                throw new DateParseException(e.getCause());
                            }
                            break;
                        default:
                            throw new UnSupportDataTypeException("unSupport data type : " + criterion.getDataType());

                    }
                    break;
                case IN:
                    switch (criterion.getDataType()) {
                        case STRING:
                            result = criterion.getValues().stream().anyMatch(value -> value.equals(property.toString()));
                            break;
                        case LONG:
                            result = criterion.getValues().stream().anyMatch(value -> Long.parseLong(value) == ((Long) property));
                            break;
                        case DOUBLE:
                            result = criterion.getValues().stream().anyMatch(value -> Double.parseDouble(value) == ((Double) property));
                            break;
                        default:
                            throw new UnSupportDataTypeException("unSupport data type : " + criterion.getDataType());

                    }
                    break;
                case BETWEEN:
                    switch (criterion.getDataType()) {
                        case DATE:
                            try {
                                result = ((Date) property).compareTo(simpleDateFormat.parse(criterion.getValues().get(0))) >= 0 && ((Date) property).compareTo(simpleDateFormat.parse(criterion.getValues().get(1))) <= 0;
                            } catch (ParseException e) {
                                throw new DateParseException(e.getCause());
                            }
                            break;
                        case LONG:
                            result = (Long) property >= Long.parseLong(criterion.getValues().get(0)) && (Long) property <= Long.parseLong(criterion.getValues().get(1));
                            break;
                    }
                    break;
                default:
                    throw new UnSupportDataTypeException("unSupport data type : " + criterion.getDataType());
            }


            return result;
        };

    }

    private void check() {
        if (criterion.getCompareType() == CompareType.IN) {
            if (criterion.getValues() == null || criterion.getValues().size() == 0) {
                throw new NullPointerException("values is null");
            }
        }
    }


}
