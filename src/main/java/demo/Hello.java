package demo;

import com.github.freewind.lostlist.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.lang.reflect.Type;
import java.util.List;

public class Hello {

    private static RuntimeTypeAdapterFactory adapter = RuntimeTypeAdapterFactory.of(Fruit.class, "class")
            .registerSubtype(Apple.class)
            .registerSubtype(Banana.class);

    private static Gson gson = new GsonBuilder().registerTypeAdapterFactory(adapter).create();

    private static List<User> users = Lists.newList(
            new User("Jeff", new Apple("apple")),
            new User("Mike", new Banana("banana"))
    );

    public static void main(String[] args) {
        String json = usersToJson();
        jsonToUsers(json);
    }

    private static void jsonToUsers(String json) {
        System.out.println("--------------- jsonToUsers -----------");


        Type listType = new TypeToken<List<User>>() {
        }.getType();

        List<User> users = gson.fromJson(json, listType);
        System.out.println(users);
    }

    private static String usersToJson() {
        System.out.println("---------- usersToJson -----------");
        String json = gson.toJson(users);
        System.out.println(json);
        return json;
    }

}

interface Fruit {
}

class Apple implements Fruit {
    public String appleName;

    public Apple(String appleName) {
        this.appleName = appleName;
    }
}

class Banana implements Fruit {
    public String bananaName;

    public Banana(String bananaName) {
        this.bananaName = bananaName;
    }
}

class User {
    public String name;
    public Fruit fruit;

    public User(String name, Fruit fruit) {
        this.name = name;
        this.fruit = fruit;
    }

    @Override
    public String toString() {
        return "User{name='" + name + '\'' + ", fruit=" + fruit + '}';
    }
}
