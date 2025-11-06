package com.AlP250.first;

import com.google.android.material.textfield.TextInputLayout;

import org.mindrot.jbcrypt.BCrypt;

public class RegisterUtilities {

    public boolean isTILEmpty(TextInputLayout textInputLayout){
        return String.valueOf(textInputLayout.getEditText().getText()).isEmpty();
    }
    public String generateHashedPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getTILText(TextInputLayout textInputLayout){
        return String.valueOf(textInputLayout.getEditText().getText());
    }

    public boolean checkPassword(String candidate, String hashed){
        return BCrypt.checkpw(candidate,hashed);
    }
}
