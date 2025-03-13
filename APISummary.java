
4.1 AI Summary API Endpoint

    @GetMapping("/{id}/summary")

    public ResponseEntity<String> getBlogSummary(@PathVariable Long id) {
    
	Blog blog = blogService.getBlogById(id);
    
	return ResponseEntity.ok(aiService.summarizeBlog(blog.getContent()));
    }
