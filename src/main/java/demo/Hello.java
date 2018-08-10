package demo;

import com.github.freewind.lostlist.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Hello {

    private static List<User> users = Lists.newList(
            new User("java"),
            new User("gson")
    );

    public static void main(String[] args) {
        String json = usersToJson();
        jsonToUsers(json);
    }

    private static void jsonToUsers(String json) {
        System.out.println("--------------- jsonToUsers -----------");
        Type listType = new TypeToken<List<User>>() {
        }.getType();
        List<User> users = new Gson().fromJson(json, listType);
        System.out.println(users);
    }

    private static String usersToJson() {
        System.out.println("---------- usersToJson -----------");
        String json = new Gson().toJson(users);
        System.out.println(json);
        return json;
    }

}

class User {
    public String name;
    public User(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "User{name='" + name + "'}";
    }
}
