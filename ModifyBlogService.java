
6. Bonus Features

   Modify BlogService to cache frequently accessed blogs:

    import org.springframework.cache.annotation.Cacheable;

    @Cacheable(value = "blogs", key = "#id")

     public Blog getBlogById(Long id) {
    
           return blogRepository.findById(id).orElseThrow();
     }



