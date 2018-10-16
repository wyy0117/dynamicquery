package com.wyy.dynamicquery;

import com.wyy.dynamicquery.domain.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FilterSortPageTest {

    // test filter A&&B
    @Test
    public void testFilter1() {

        //prepare data
        String name1 = "name1";
        long age1 = 25;
        double height1 = 180.0;
        Date birthday1 = new Date();
        User user1 = new User(name1, age1, height1, birthday1);

        String name2 = "name2";
        long age2 = 18;
        double height2 = 180.0;
        Date birthday2 = new Date();
        User user2 = new User(name2, age2, height2, birthday2);

        List<User> userList = Arrays.asList(user1, user2);

        //prepare filter
        DynamicFilter<User> dynamicFilter = new DynamicFilter<>(OperatorType.AND, Arrays.asList(
                new Criterion<>("name", CriterionDataType.STRING, CompareType.EQUAL, "name1"),
                new Criterion<>("age", CriterionDataType.LONG, CompareType.GREATEREQUAL, "20")
        ));

        List<User> result = new EntityFilterWorker<>(userList, dynamicFilter).doWork();

        assert result.size() == 1;
        assert result.get(0).equals(user1);
    }

    // test filter A||B
    @Test
    public void testFilter2() {

        //prepare data
        String name1 = "name1";
        long age1 = 25;
        double height1 = 180.0;
        Date birthday1 = new Date();
        User user1 = new User(name1, age1, height1, birthday1);

        String name2 = "name2";
        long age2 = 18;
        double height2 = 180.0;
        Date birthday2 = new Date();
        User user2 = new User(name2, age2, height2, birthday2);

        List<User> userList = Arrays.asList(user1, user2);

        //prepare filter
        DynamicFilter<User> dynamicFilter = new DynamicFilter<>(OperatorType.OR, Arrays.asList(
                new Criterion<>("name", CriterionDataType.STRING, CompareType.EQUAL, "name1"),
                new Criterion<>("age", CriterionDataType.LONG, CompareType.EQUAL, "18")
        ));

        List<User> result = new EntityFilterWorker<>(userList, dynamicFilter).doWork();

        assert result.size() == 2;
    }

    // test filter A&&（B||C）
    @Test
    public void testFilter3() {

        //prepare data
        String name1 = "name1";
        long age1 = 25;
        double height1 = 180.0;
        Date birthday1 = new Date();
        User user1 = new User(name1, age1, height1, birthday1);

        String name2 = "name2";
        long age2 = 18;
        double height2 = 180.0;
        Date birthday2 = new Date();
        User user2 = new User(name2, age2, height2, birthday2);

        List<User> userList = Arrays.asList(user1, user2);

        //prepare filter
        DynamicFilter<User> dynamicFilter = new DynamicFilter<>(OperatorType.AND, Arrays.asList(
                new Criterion<>("name", CriterionDataType.STRING, CompareType.EQUAL, "name1"),
                new Criterion<>(OperatorType.OR, Arrays.asList(
                        new Criterion<>("height", CriterionDataType.DOUBLE, CompareType.GREATEREQUAL, "200.0"),
                        new Criterion<>("age", CriterionDataType.LONG, CompareType.EQUAL, "25")
                ))
        ));

        List<User> result = new EntityFilterWorker<>(userList, dynamicFilter).doWork();

        assert result.size() == 1;
        assert result.get(0).equals(user1);
    }

    // test filter A||（B&&C）
    @Test
    public void testFilter4() {

        //prepare data
        String name1 = "name1";
        long age1 = 25;
        double height1 = 180.0;
        Date birthday1 = new Date();
        User user1 = new User(name1, age1, height1, birthday1);

        String name2 = "name2";
        long age2 = 18;
        double height2 = 180.0;
        Date birthday2 = new Date();
        User user2 = new User(name2, age2, height2, birthday2);

        List<User> userList = Arrays.asList(user1, user2);

        //prepare filter
        DynamicFilter<User> dynamicFilter = new DynamicFilter<>(OperatorType.OR, Arrays.asList(
                new Criterion<>("name", CriterionDataType.STRING, CompareType.EQUAL, "name1"),
                new Criterion<>(OperatorType.AND, Arrays.asList(
                        new Criterion<>("height", CriterionDataType.DOUBLE, CompareType.EQUAL, "180.0"),
                        new Criterion<>("age", CriterionDataType.LONG, CompareType.EQUAL, "18")
                ))
        ));

        List<User> result = new EntityFilterWorker<>(userList, dynamicFilter).doWork();

        assert result.size() == 2;
    }

    @Test
    public void testSort() {
        //prepare data
        String name1 = "name1";
        long age1 = 25;
        double height1 = 180.0;
        Date birthday1 = new Date();
        User user1 = new User(name1, age1, height1, birthday1);

        String name2 = "name2";
        long age2 = 18;
        double height2 = 180.0;
        Date birthday2 = new Date();
        User user2 = new User(name2, age2, height2, birthday2);

        List<User> userList = Arrays.asList(user1, user2);
        Sort<User> sort = new Sort<>("age", SortType.DESC, CriterionDataType.LONG);
        userList.sort(new SortConverter<>(Arrays.asList(sort)).convert());
        assert userList.get(0).equals(user1);

        sort = new Sort<>("age", SortType.ASC, CriterionDataType.LONG);
        userList.sort(new SortConverter<>(Arrays.asList(sort)).convert());
        assert userList.get(0).equals(user2);
    }

    @Test
    public void testPage() {
        //prepare data
        String name1 = "name1";
        long age1 = 25;
        double height1 = 180.0;
        Date birthday1 = new Date();
        User user1 = new User(name1, age1, height1, birthday1);

        String name2 = "name2";
        long age2 = 18;
        double height2 = 180.0;
        Date birthday2 = new Date();
        User user2 = new User(name2, age2, height2, birthday2);

        List<User> userList = Arrays.asList(user1, user1, user1, user1, user2, user2, user2, user2);
        userList = new PageUtil<User>().page(userList, 3, 5);
        assert userList.size() == 2;
        assert userList.get(0).equals(user1);
        assert userList.get(1).equals(user2);
    }

}
