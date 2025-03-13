Dr. UPSC Interview Assignment - Backend Blog Management System (Spring Boot, AI, AWS)
This project involves developing a backend service using Java Spring Boot that allows users to create, retrieve, and manage blogs. The system integrates AI-powered text summarization and is deployed on AWS using Docker. The application follows best practices for RESTful API design, database management, and scalability.

📌 Project Breakdown
1. Tech Stack & Tools
  Category Technology Used
    (1) Backend : Framework	Spring Boot (Java)
    (2) Database : PostgreSQL/MySQL
    (3) ORM :	Spring Data JPA
    (4) AI Integration	: OpenAI API (GPT-4)
    (5) Deployment	: Docker, AWS EC2, AWS Lambda, API Gateway
    (6) Authentication (Bonus) : JWT (JSON Web Token)
    (7) Caching (Bonus)	: Redis
    (8) Orchestration (Bonus)	: Kubernetes
   
2. Features & Functionalities
  Feature	Description
  (1) Create a blog	Users can create a new blog with a title, content, and author.
  (2) Fetch all blogs	List all blogs with pagination support.
  (3) Fetch a single blog	Retrieve blog details by ID.
  (4) Update a blog	Modify an existing blog by its ID.
  (5) Delete a blog	Remove a blog from the system.
  (6) AI-powered summarization:	Generates a short summary of the blog using OpenAI API.
  (7) AWS Deployment:	The app is containerized using Docker and deployed on AWS EC2/Lambda.
  (8) JWT Authentication (Bonus):	Secure API endpoints using token-based authentication.
  (9) Redis Caching (Bonus):	Improve performance by caching frequently accessed blogs.

3. Database Design
The system uses Spring Data JPA to interact with the database (PostgreSQL/MySQL).

 Database Schema
Column	Type	Description
 id	Long (Primary Key)	Unique identifier for each blog.
 title	String	Blog title.
 content	TEXT	Full content of the blog.
 author	String	Name of the author.
 createdAt	Date	Timestamp of blog creation.
 
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

    // Getters & Setters
}

4. API Endpoints
 Method	Endpoint	Description
 POST	/blogs	Create a new blog
 GET	/blogs?page=0&size=10	Fetch paginated blogs
 GET	/blogs/{id}	Get a specific blog
 PUT	/blogs/{id}	Update a blog
 DELETE	/blogs/{id}	Delete a blog
 GET	/blogs/{id}/summary	AI-generated summary
 Controller (BlogController.java)

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;

 import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
        return ResponseEntity.ok(blogService.createBlog(blog));
    }

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs(@RequestParam(defaultValue = "0") int page, 
                                                  @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(blogService.getAllBlogs(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.getBlogById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody Blog blog) {
        return ResponseEntity.ok(blogService.updateBlog(id, blog));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.ok("Blog deleted successfully.");
    }
}

5. AI-Powered Text Summarization
Uses OpenAI API (GPT-4) to generate a summary of blog content.
Sends an HTTP request to OpenAI with the content and gets a summarized version.
AI Service (AIService.java)

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AIService {

    private final String OPENAI_API_URL = "https://api.openai.com/v1/completions";
    private final String API_KEY = "your_openai_api_key";

    public String summarizeBlog(String content) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> request = new HashMap<>();
        request.put("model", "text-davinci-003");
        request.put("prompt", "Summarize this: " + content);
        request.put("max_tokens", 50);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(OPENAI_API_URL, entity, Map.class);

        return response.getBody().get("choices").toString();
    }
}

6. AWS Deployment
   Dockerfile

   FROM openjdk:17-jdk-slim
   WORKDIR /app
   COPY target/blog-service.jar blog-service.jar
   ENTRYPOINT ["java", "-jar", "blog-service.jar"]

   Deployment Steps
  Build Docker Image

  mvn clean package
  docker build -t blog-service .
  
  Push to AWS ECR
  
  aws ecr create-repository --repository-name blog-service
  aws ecr get-login-password | docker login --username AWS --password-stdin <AWS_ACCOUNT_ID>.dkr.ecr.<AWS_REGION>.amazonaws.com
  docker tag blog-service:latest <AWS_ACCOUNT_ID>.dkr.ecr.<AWS_REGION>.amazonaws.com/blog-service
  docker push <AWS_ACCOUNT_ID>.dkr.ecr.<AWS_REGION>.amazonaws.com/blog-service

 Run on AWS EC2

  docker run -d -p 8080:8080 <AWS_ACCOUNT_ID>.dkr.ecr.<AWS_REGION>.amazonaws.com/blog-service

8. Bonus Features
  JWT Authentication
 Implements Spring Security to secure API endpoints.

   Redis Caching
 Caches frequently accessed blogs to reduce DB calls.

  @Cacheable(value = "blogs", key = "#id")
  public Blog getBlogById(Long id) {
    return blogRepository.findById(id).orElseThrow();
  }

  Kubernetes Deployment
 Deploys the application on Kubernetes for scalability.

  Conclusion
This project is a fully functional Spring Boot application with AI-powered summarization and AWS deployment. Implementing JWT authentication and Redis caching improves security & performance.


   

