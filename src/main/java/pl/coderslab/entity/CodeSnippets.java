package pl.coderslab.entity;

public class CodeSnippets {
}
/* Hash a password for the first time
    String hashed = org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());

    // gensalt's log_rounds parameter determines the complexity
// the work factor is 2**log_rounds, and the default is 10
    String hashed = org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt(12));

// Check that an unencrypted password matches one that has
// previously been hashed
if (org.mindrot.jbcrypt.BCrypt.checkpw(candidate, hashed))
            System.out.println("It matches");
else
        System.out.println("It does not match");*/