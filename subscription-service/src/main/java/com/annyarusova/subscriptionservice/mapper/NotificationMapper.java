package com.annyarusova.subscriptionservice.mapper;

import com.annyarusova.subscriptionservice.repository.NotificationInterfaceDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class NotificationMapper {
    private static final String issueUrl = "http://localhost:18003/v1/issues/";
    private static final String unsubscribeUrl = "http://localhost:18003/v1/subscription";

    public static String toEmailBody(NotificationInterfaceDto notif) {

        if (notif.getIssues().isEmpty()) {
            throw new IllegalArgumentException("No issues found");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<p>\nЗдравствуйте");
        if (notif.getNickname() != null) {
            sb.append(", ");
            sb.append(notif.getNickname());
            sb.append("!\n");
        } else {
            sb.append("!\n");
        }
        sb.append("<br>Мы собрали для вас дайджест вопросов, которые были созданы с момента нашего прошлого письма.</p>\n");
        sb.append("<ol>");
        for (int i = 0; i < notif.getIssues().size(); i++) {
            sb.append("<li>");
            sb.append(createLink(notif.getTitles().get(i), issueUrl + notif.getIssues().get(i)));
            sb.append("</li>");
            sb.append("\n");
        }
        sb.append("\n");
        sb.append("</ol>");

        sb.append("<p>\n");
        sb.append("Надеемся, что-нибудь из этого вам понравится!\n\n");
        sb.append("</p>\n");

        sb.append("<p>\n");
        sb.append("Если вы хотите отписать от рассылки, отправьте запрос DELETE: ");
        sb.append(unsubscribeUrl);
        sb.append("<br>\n");
        sb.append("В теле запроса укажите тег, от которого хотите отписаться, или оставьте поле пустым, чтобы отписаться от всего.\n");
        sb.append("</p>\n");

        sb.append("<p>\n");
        sb.append("\n---<br>\n");
        sb.append("С уважением,<br>\n").append("Команда Ани и Стёпы.<br>\n");
        sb.append(createLink("https://github.com/stepagin", "https://github.com/stepagin")).append("<br>\n");
        sb.append(createLink("https://github.com/anya-yarusova", "https://github.com/anya-yarusova")).append("<br>\n");
        sb.append("</p>\n");
        return sb.toString();
    }

    private static String createLink(String title, String link) {
        return "<a href=\"" + link + "\">" + title + "</a>";
    }
}
