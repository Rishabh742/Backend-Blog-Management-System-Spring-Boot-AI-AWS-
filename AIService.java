
4. AI-Powered Text Summarization

   Service for AI Summary (AIService.java)

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
