package utilities;

import java.util.Properties;

import jakarta.mail.Flags;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.search.FlagTerm;

public class EmailOTPReaderTest {

    public static String getOTP() {

        String host = "outlook.office365.com";
        String mailStoreType = "imap";
        String username = "nagasai@axisrooms.com";
        String password = "Sai@2965";

        try {

            Properties properties = new Properties();
            properties.put("mail.store.protocol", "imaps");

            Session emailSession = Session.getDefaultInstance(properties);

            Store store = emailSession.getStore("imaps");
            store.connect(host, username, password);

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            Message[] messages = emailFolder.search(
                    new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            for (int i = messages.length - 1; i >= 0; i--) {

                String content = messages[i].getContent().toString();

                if (content.contains("OTP")) {

                    String otp = content.replaceAll("\\D+", "").substring(0,6);
                    return otp;

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}