package com.payments.payments.integration.helpers;

import com.payments.payments.domain.user.enums.UserType;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

public class UsersHelper {

    RestTemplate restTemplate = new RestTemplate();

    public JSONObject createPFUserBody() throws JSONException {
        JSONObject body = new JSONObject();
        body.put("firstName", "Novo");
        body.put("lastName", "Usuario");
        body.put("email", UUID.randomUUID().toString().concat("@mock.com"));
        body.put("password", "senha123");
        body.put("document", this.generateCPF());
        body.put("balance", 100);
        body.put("userType", UserType.PF);
        return body;
    }

    public String generateCPF() {
        String url = "https://www.4devs.com.br/ferramentas_online.php";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> payload = new LinkedMultiValueMap<String, String>();
        payload.add("acao", "gerar_cpf");
        payload.add("pontuacao", "N");
        payload.add("cpf_estado", "");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(payload, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return response.getBody();
    }

}
