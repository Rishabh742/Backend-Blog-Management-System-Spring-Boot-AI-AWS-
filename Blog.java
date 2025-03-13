
2. Database Schema & JPA Repository

    Entity Class (Blog.java)

    import jakarta.persistence.*;
    import java.util.Date;

    @Entity

	public class Blog {

        @Id

        @GeneratedValue(strategy = GenerationType.IDENTITY)
    
 	private Long id;

    	private String title;
    
	@Column(columnDefinition = "TEXT")
    
	private String content;
    
	private String author;

    
	@Temporal(TemporalType.TIMESTAMP)
    
	private Date createdAt = new Date();

  
	}
