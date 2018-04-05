package pl.gaamit.librarySecurity.model;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}

