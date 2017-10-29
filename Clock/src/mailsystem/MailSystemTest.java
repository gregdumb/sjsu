package mailsystem;

/**
  Run a test of the mail system using two telephones to access the system
*/
public class MailSystemTest
{
   /**
     Creates two phones and connects them to the system.
   */
   public static void main(String[] args)
   {
      MailSystem system = new MailSystem(MAILBOX_COUNT);

      Telephone p1 = new Telephone();
      Telephone p2 = new Telephone();
      Connection c1 = new Connection(system, p1);
      Connection c2 = new Connection(system, p2);
      p1.run(c1);
      p2.run(c2);
   }

   private static int MAILBOX_COUNT = 20;
}
