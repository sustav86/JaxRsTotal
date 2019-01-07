package academy.learnprogramming.service;

import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;
import java.io.Serializable;

@Stateful
public class UserSession implements Serializable {

    public String getCurrentUserName() {
        return "";
    }

    @PrePassivate
    private void passivate() {

    }

    @PostActivate
    private void active() {

    }
}
