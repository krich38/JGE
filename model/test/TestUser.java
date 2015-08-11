import org.jge.model.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class TestUser {
    @Test
    public void testUser() {
        User userOne = new User("Foo", "foopass");
        User userTwo = new User("Foo2", "foopass");
        User userThree = new User("Foo", "foopass");

        Assert.assertTrue(userOne.equals(userThree));
        Assert.assertFalse(userOne.equals(userTwo));
    }
}
