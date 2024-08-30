package hw1;

public class Person {
    private String name;
    private String phone_number;

    public Person(String phone_number) {
        String number = process_number(phone_number);

        if (number.length() != 10) {
            throw new IllegalArgumentException("Phone number should be 10 digits long. Ex. xxx-xxx-xxxx");
        }

        this.phone_number = number;
    }

    public Person(String name, String phone_number) {
        String number = process_number(phone_number);

        if (number.length() != 10) {
            throw new IllegalArgumentException("Phone number should be 10 digits long. Ex. xxx-xxx-xxxx");
        }

        this.name = name;
        this.phone_number = number;
    }

    public String process_number(String phone_number){
        StringBuilder number = new StringBuilder();

        for (char c : phone_number.strip().toCharArray()) {
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


    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        if (getName().isEmpty()) {
            return "unknown name (" + getPhone_number() + ")";
        }

        return getName() + " (" + getPhone_number() + ")";
    }
}
