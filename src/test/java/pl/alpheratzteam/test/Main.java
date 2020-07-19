package pl.alpheratzteam.test;

import pl.alpheratzteam.fastcollections.FastList;
import pl.alpheratzteam.test.object.TestUser;
import java.util.Objects;

/**
 * @author Unix
 * @since 18.07.2020
 */

public class Main
{
    public static void main(String... args) {
        final int initialCapacity = 10000000;
        final FastList<TestUser> fastList = new FastList<>(initialCapacity);
        {
            final long startTime = System.currentTimeMillis();
            for (int i = 0; i < initialCapacity - 1; ++i)
                fastList.add(new TestUser("abcd", "ez"));

            fastList.add(new TestUser("ha6", "1234"));
            System.out.println(String.format("Added %d objects with time %d ms", fastList.size(), System.currentTimeMillis() - startTime));
        }

        final String searchData = "ha6";
        final long startTime = System.currentTimeMillis();
        final TestUser testUser = fastList.get(searchData);
        if (Objects.isNull(testUser)) {
            System.out.println(String.format("%s Not found!", searchData));
            return;
        }

        System.out.println(String.format("Found %s, password: %s with time %d ms", searchData, testUser.getPassword(), System.currentTimeMillis() - startTime));
    }
}