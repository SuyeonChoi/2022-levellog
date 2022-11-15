package com.woowacourse.levellog.authentication.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woowacourse.levellog.authentication.domain.OAuthClient;
import com.woowacourse.levellog.authentication.dto.request.GithubCodeRequest;
import com.woowacourse.levellog.authentication.dto.response.GithubProfileResponse;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final OAuthClient oAuthClient;
    private final ObjectMapper objectMapper;

    public GithubProfileResponse requestGithubProfile(final GithubCodeRequest request) {
//        final String code = request.getAuthorizationCode();
//        final String githubAccessToken = oAuthClient.getAccessToken(code);
//        return oAuthClient.getProfile(githubAccessToken);
        try {
            Thread.sleep(500);
            return objectMapper.readValue(request.getAuthorizationCode(),
                    GithubProfileResponse.class);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
