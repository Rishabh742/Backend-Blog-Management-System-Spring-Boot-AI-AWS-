
6. Bonus Features

   JWT Service (JWTUtil.java)

   import io.jsonwebtoken.*;

   import java.util.Date;

   public class JWTUtil {
     private final String SECRET_KEY = "your_secret_key";

     public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
      }

     public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
     }
  }

