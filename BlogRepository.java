
2.1 JPA Repository (BlogRepository.java)

    import org.springframework.data.jpa.repository.JpaRepository;

    public interface BlogRepository extends JpaRepository<Blog, Long> {

    }
