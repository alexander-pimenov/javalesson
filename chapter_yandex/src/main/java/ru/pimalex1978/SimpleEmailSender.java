package ru.pimalex1978;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Java-программа для отправки простой электронной почты с использованием
 * электронной почты Apache Commons.
 * Использует SMTP-серверы Gmail.
 * <p>
 * Возможна такая ошибка:<br>
 * <i></>org.apache.commons.mail.EmailException: Sending the email to the following server failed : smtp.gmail.com:465</i><br>
 * Чтобы решить вышеуказанную проблему, вам нужно включить доступ для менее безопасных приложений по этой
 * <a href="https://www.google.com/settings/security/lesssecureapps">ссылке</a> Google.<br>
 * <p>
 * Это требование относится к учетным записям Gmail.
 * Ниже приведены атрибуты конфигурации для различных провайдеров электронной почты, которые вы можете использовать
 * в программе Java, используя библиотеку электронной почты Apache commons.
 * Все указанные ниже учетные записи используют SMTP-порт 465 и поддерживают SSL.
 * <p>
 * <table border="1">
 *   <tr>
 *     <th>Email Provider</th><th>Host name - Имя хоста сервера исходящей почты</th><th>Comments</th>
 *   </tr>
 *   <tr>
 *     <td>Gmail</td><td>smtp.gmail.com</td><td>Включите менее безопасный доступ, как указано выше.</td>
 *   </tr>
 *   <tr>
 *       <td>Yahoo Mail</td><td>smtp.mail.yahoo.com</td><td>На вкладке «Безопасность учетной записи» вашей
 *       учетной записи Yahoo включите разрешение приложений, использующих менее безопасный вход.</td>
 *   </tr>
 *   <tr>
 *       <td>Sendgrid</td><td>smtp.sendgrid.net</td><td>Создайте ключ API в Sendgrid. Используйте «apikey»
 *       в качестве имени пользователя, фактический ключ API в качестве пароля.</td>
 *   </tr>
 *   <tr>
 *       <td>Mailgun</td><td>smtp.mailgun.org</td><td>Имя пользователя и пароль SMTP по умолчанию доступны на странице домена
 *       по умолчанию в панели Mailgun. Если вы используете бесплатную учетную запись, вы можете отправлять только авторизованным получателям.</td>
 *   </tr>
 *   <tr>
 *  *    <td>Yandex</td><td>smtp.yandex.ru</td><td>Имя пользователя и пароль SMTP по умолчанию доступны на странице домена
 *  *       по умолчанию в панели Mailgun. Если вы используете бесплатную учетную запись, вы можете отправлять только авторизованным получателям.</td>
 *  *   </tr>
 * </table>
 */


public class SimpleEmailSender {

    public static final String HOST = "smtp.gmail.com";
    public static final int PORT = 465;
    public static final boolean SSL_FLAG = true;
    public static final Logger LOG = LoggerFactory.getLogger(SimpleEmailSender.class.getName());

    public static void main(String[] args) {
        SimpleEmailSender sender = new SimpleEmailSender();
        sender.sendSimpleEmail();
    }

    private void sendSimpleEmail() {
        LOG.info("Начало работы программы по отправке email!!!>>>>>");
        String userName = "username@gmail.com";
        String password = "password";

        String fromAddress = "username@gmail.com";
        String toAddress = "quickprogrammer@gmail.com";
        String subject = "Test Mail";
        String message = "Hello from Apache Mail";
        try {
            Email email = new SimpleEmail();
            email.setHostName(HOST);
            email.setSmtpPort(PORT);
            email.setAuthenticator(new DefaultAuthenticator(userName, password));
            email.setSSLOnConnect(SSL_FLAG);
            email.setFrom(fromAddress);
            email.setSubject(subject);
            email.setMsg(message);
            email.addTo(toAddress);
            String send = email.send();
            LOG.info(">>>>>Конец работы программы по отправке email. Результат: {}!!!", send);
        } catch (Exception ex) {
            LOG.error("Unable to send email from {} to {}.", fromAddress, toAddress, ex);
        }
    }
}
