public class MyString {

    private String string;

    public MyString() {
        string = "Hello Test!";
    }

    public void append(String append) {
        string = string + append;
    }

    public boolean equals(String equalsThis) {
        return string.equals(equalsThis);
    }

    public String getMyString() {
        return string;
    }
}
