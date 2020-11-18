import se.umu.cs.unittest.TestClass;

public class Test2 implements TestClass {

    public MyString myString;

    public Test2() {
        myString = new MyString();
    }

    public boolean testInitialString() {
        return myString.equals("Hello Test!");
    }

    public boolean testAppend() {
        myString.append(" More text!");
        return myString.equals("Hello Test! More text!");
    }

    public boolean testFailingByException() {
        myString = null;
        myString.getMyString();
        return false;
    }

    public boolean testFailing() {
        myString = new MyString();
        return myString.equals("asdasdasd");
    }
}
