package project.doxuanvinh.usermaster.data.entity.test;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Do Xuan Vinh on 27/12/2017.
 */

public class User extends RealmObject {

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
