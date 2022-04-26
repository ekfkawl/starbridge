package kr.starbridge.web.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
@RequiredArgsConstructor
public class RecaptchaService {
    private final RestTemplateBuilder builder;

    public static final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    @Value("${reCAPTCHA}")
    private String secret;

    public Float verify(String gRecaptchaResponse) throws ParseException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("secret", secret);
        map.add("response", gRecaptchaResponse);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = builder.build().postForEntity(SITE_VERIFY_URL, request, String.class);

        JSONParser jsonParse = new JSONParser();
        JSONObject jsonObject = (JSONObject)jsonParse.parse(response.getBody());

        return Float.parseFloat(jsonObject.get("score").toString());
    }
}
