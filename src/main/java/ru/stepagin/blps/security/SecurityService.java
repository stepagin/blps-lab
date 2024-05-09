package ru.stepagin.blps.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.repository.UserRepository;
import ru.stepagin.blps.service.AnswerService;
import ru.stepagin.blps.service.IssueService;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final UserRepository userRepository;
    private final IssueService issueService;
    private final AnswerService answerService;

    public UserEntity getUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userRepository.findByLoginIgnoreCase(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(userDetails.getUsername()));
    }

    public boolean isIssueOwner(Long issueId, Authentication authentication) {
        UserEntity user = getUser(authentication);
        return issueService.isIssueOwner(issueId, user);
    }

    public boolean isAnswerOwner(Long answerId, Authentication authentication) {
        UserEntity user = getUser(authentication);
        return answerService.isAnswerOwner(answerId, user);
    }
}
