package ru.stepagin.blps.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.repository.UserRepository;
import ru.stepagin.blps.service.AnswerService;
import ru.stepagin.blps.service.AuthService;
import ru.stepagin.blps.service.IssueService;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final IssueService issueService;
    private final AnswerService answerService;
    private final AuthService authService;

    public boolean canEditIssue(Long issueId) {
        UserEntity user = authService.getAuthenticatedUser();
        return user.getRoles().contains(Role.MODERATOR) || issueService.isIssueOwner(issueId, user);
    }

    public boolean canEditAnswer(Long answerId) {
        UserEntity user = authService.getAuthenticatedUser();
        return user.getRoles().contains(Role.MODERATOR) || answerService.isAnswerOwner(answerId, user);
    }
}
