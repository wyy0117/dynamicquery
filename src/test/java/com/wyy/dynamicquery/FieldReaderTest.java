package com.wyy.dynamicquery;

import com.wyy.dynamicquery.domain.User;
import org.junit.Test;

import java.util.Date;

/**
 * @author wyy
 * @date 18-10-16
 * @time 下午4:50
 */
public class FieldReaderTest {

    @Test
    public void testReadField() {

        String name = "abc";
        long age = 18;
        double height = 180.0;
        Date birthday = new Date();

        User user = new User(name, age, height, birthday);

        FieldReader<User> fieldReader = new FieldReader<>(user);
        assert name.equals((String) fieldReader.readField("name"));
        assert age == (long) fieldReader.readField("age");
        assert height == (double) fieldReader.readField("height");
        assert birthday.equals((Date) fieldReader.readField("birthday"));
    }


}
