package treeModule;

/**
 * Representa un contacto con nombre, teléfono y email.
 * Implementa Comparable para poder ordenarse alfabéticamente por nombre
 * dentro del árbol AVL de ContactsExercise.
 */
public class Contact implements Comparable<Contact> {

    private String name;
    private String phone;
    private String email;

    /** Constructor completo para uso público; valida que ningún campo esté vacío. */
    public Contact(String name, String phone, String email) {
        if (name == null || name.isBlank())  throw new IllegalArgumentException("name cannot be blank");
        if (phone == null || phone.isBlank()) throw new IllegalArgumentException("phone cannot be blank");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("email cannot be blank");
        this.name  = name.trim();
        this.phone = phone.trim();
        this.email = email.trim();
    }

    /**
     * Constructor de clave (package-private): solo necesita el nombre.
     * Se usa para buscar y comparar en el árbol sin construir un contacto completo.
     */
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

    /** Compara por nombre ignorando mayúsculas; determina el orden en el árbol. */
    @Override
    public int compareTo(Contact other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        return String.format("%-25s | Phone: %-15s | Email: %s", name, phone, email);
    }
}

