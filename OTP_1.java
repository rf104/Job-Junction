
package com.example.dtl;




        import javafx.concurrent.Task;
        import javax.mail.*;
        import javax.mail.internet.InternetAddress;
        import javax.mail.internet.MimeMessage;
        import java.util.Properties;
        import java.util.Random;


public class OTP_1
{
 public static String otp;

 public static int sendOTP(String userEmail)
 {
  final String username = "kingaref13@gmail.com";
  //final String password = "mffstvynnqvznynw";//gnow njbn jjgf xcgj
  final String password = "gnownjbnjjgfxcgj";

  String otp = generateOTP();

  Properties properties = new Properties();

  properties.put("mail.smtp.host", "smtp.gmail.com");
  properties.put("mail.smtp.port",587);
  properties.put("mail.smtp.starttls.enable","true");
  properties.put("mail.smtp.auth","true");

  Session session = Session.getInstance(properties, new Authenticator()
  {
   @Override
   protected PasswordAuthentication getPasswordAuthentication()
   {
    return new PasswordAuthentication(username,password);
   }
  });

  try{
   Message message = new MimeMessage(session);
   message.setFrom(new InternetAddress("kingaref13@gmail.com"));
   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
   message.setSubject("Your OTP");
   message.setText("Your OTP is : "+otp);
   Transport.send(message);
  }catch (MessagingException e){
   e.printStackTrace();
  }
  return Integer.parseInt(otp);
 }
 private static String generateOTP() {
  // Generate a random 6-digit OTP
  Random random = new Random();
  int otp = 100000 + random.nextInt(900000);
  System.out.println(otp);
  return Integer.toString(otp);
 }

 public static void main(String[] args) {
  String userEmail = "kingaref13@gmail.com";
  //sendOTP(userEmail);
  //System.out.println(otp);
 }
}

