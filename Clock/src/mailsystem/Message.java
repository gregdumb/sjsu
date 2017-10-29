package mailsystem;

/**
   A message left by the caller.
*/
public class Message
{
   /**
      Construct a mailsystem.Message object.
      @param messageText the message text
   */
   public Message(String messageText)
   {
      text = messageText;
   }

   /**
      Get the message text.
      @return message text
   */
   public String getText()
   {
      return text;
   }

   private String text;
}
