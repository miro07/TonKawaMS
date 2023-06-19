package auth.payload.response;

public class JwtResponse {


    private String token;

    private String type = "Bearer";

    private final Long id;

    private String username;

    private String email;

    private int expiration;

    public JwtResponse(String token, String type, Long id, String userName, String email, int expiration) {
        this.token = token;
        this.type = type;
        this.id = id;
        this.username = userName;
        this.email = email;
        this.expiration = expiration;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

}
