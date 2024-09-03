package hw1;

@SuppressWarnings("ALL")
public class Person {
    private String name;
    private static Integer nextId = 1;
    public Integer id;
    private String phoneNumber;

// --Commented out by Inspection START (9/3/2024 5:57 PM):
//    public Person(String phoneNumber) {
//        String number = processNumber(phoneNumber);
//
//        if (number.length() != 10) {
//            throw new IllegalArgumentException("Phone number should be 10 digits long. Ex. xxx-xxx-xxxx");
//        }
//
//        this.phoneNumber = number;
//        this.id = nextId++;
//    }
// --Commented out by Inspection STOP (9/3/2024 5:57 PM)

    public Person(String name, String phoneNumber) {
        String number = processNumber(phoneNumber);

        if (number.length() != 10) {
            throw new IllegalArgumentException("Phone number should be 10 digits long. Ex. xxx-xxx-xxxx");
        }

        this.name = name;
        this.phoneNumber = number;
        this.id = nextId++;
    }

    public String processNumber(String phoneNumber) {
        StringBuilder number = new StringBuilder();

        for (char c : phoneNumber.strip().toCharArray()) {
            if (Character.isDigit(c)) {
                number.append(c);
            }
        }

        return number.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = processNumber(phoneNumber);
    }

    @Override
    public String toString() {
        if (getName().isEmpty()) {
            return "unknown name (" + getPhoneNumber() + ")";
        }

        return getName() + " (" + getPhoneNumber() + ")";
    }
}
