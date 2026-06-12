package treeModule;


public class Contact implements Comparable<Contact> {

    private String name;
    private String phone;
    private String email;

    public Contact(String name, String phone, String email) {
        if (name == null || name.isBlank())  throw new IllegalArgumentException("name cannot be blank");
        if (phone == null || phone.isBlank()) throw new IllegalArgumentException("phone cannot be blank");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("email cannot be blank");
        this.name  = name.trim();
        this.phone = phone.trim();
        this.email = email.trim();
    }

    Contact(String name) {
        this.name  = name == null ? "" : name.trim();
        this.phone = "";
        this.email = "";
    }

    public String getName()  { return name;  }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name cannot be blank");
        this.name = name.trim();
    }

    public void setPhone(String phone) {
        if (phone == null || phone.isBlank()) throw new IllegalArgumentException("phone cannot be blank");
        this.phone = phone.trim();
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) throw new IllegalArgumentException("email cannot be blank");
        this.email = email.trim();
    }

    @Override
    public int compareTo(Contact other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        return String.format("%-25s | Phone: %-15s | Email: %s", name, phone, email);
    }
}

