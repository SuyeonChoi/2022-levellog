package com.woowacourse.levellog.authentication.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woowacourse.levellog.authentication.dto.request.GithubCodeRequest;
import com.woowacourse.levellog.authentication.dto.response.GithubProfileResponse;
import com.woowacourse.levellog.authentication.dto.response.LoginResponse;
import com.woowacourse.levellog.authentication.support.JwtTokenProvider;
import com.woowacourse.levellog.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OAuthService {

    //    private final OAuthClient oAuthClient;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;
    private final ObjectMapper objectMapper;

    @Transactional
    public LoginResponse login(final GithubCodeRequest request) {
//        final String code = request.getAuthorizationCode();
//        final String githubAccessToken = oAuthClient.getAccessToken(code);
//        final GithubProfileResponse githubProfile = oAuthClient.getProfile(githubAccessToken);

        GithubProfileResponse githubProfile = null;
        try {
            Thread.sleep(500);
            githubProfile = objectMapper.readValue(request.getAuthorizationCode(),
                    GithubProfileResponse.class);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        final Long memberId = getMemberIdByGithubProfile(githubProfile);
        final String token = jwtTokenProvider.createToken(memberId.toString());
        return new LoginResponse(memberId, token, githubProfile.getNickname(), githubProfile.getProfileUrl());
    }

    private Long getMemberIdByGithubProfile(final GithubProfileResponse githubProfile) {
        final int githubId = Integer.parseInt(githubProfile.getGithubId());

        return memberService.saveIfNotExist(githubProfile, githubId);
    }
}
