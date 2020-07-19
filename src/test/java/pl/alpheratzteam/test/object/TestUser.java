package pl.alpheratzteam.test.object;

import pl.alpheratzteam.fastcollections.ListData;

/**
 * @author Unix
 * @since 18.07.2020
 */

public class TestUser implements ListData
{
    private final String nickName;
    private final String password;

    public TestUser(String nickName, String password) {
        this.nickName = nickName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getKeyData() {
        return nickName;
    }
}