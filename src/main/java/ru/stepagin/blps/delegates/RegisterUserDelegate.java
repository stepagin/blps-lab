package ru.stepagin.blps.delegates;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        if (!delegateExecution.getProcessEngineServices().getIdentityService().getCurrentAuthentication().getGroupIds().contains("guest")) {
            throw new BpmnError("Not a guest");
        }
        String login = delegateExecution.getVariable("login_register").toString();
        String name = delegateExecution.getVariable("name_register").toString();
        String password = delegateExecution.getVariable("password_register").toString();

        User user = delegateExecution.getProcessEngineServices().getIdentityService().newUser(login);
        user.setFirstName(name);
        user.setPassword(password);
        user.setEmail(login);
        delegateExecution.getProcessEngineServices().getIdentityService().saveUser(user);
    }
}
