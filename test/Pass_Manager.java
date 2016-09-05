/**
 * Created by Marin on 8/23/2016.
 */
public class Pass_Manager {
    String email;
    String password;
    String note;

    public Pass_Manager () {

    }

    public Pass_Manager (String email , String password , String note) {
        this.email = email;
        this.password = password;
        this.note = note;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
